package com.streammedia.controller;
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import com.streammedia.utility.JavaHelperMethods;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.internal.util.SaxHelper;


import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.*;


/**
 * The type Add story.
 * Responsible for getting form data for new story
 *
 * @author Jeanne
 * @version 1.0
 * @since 2020-04-23
 * TODO upload to S3  https://www.youtube.com/watch?v=iQrOmbCiFBs
 */
@Log4j2
@WebServlet(
        name = "shortStoryAdd",
        urlPatterns = {"/story-new"}
)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10 * 10 * 10,      // 1GB
        maxRequestSize = 1024 * 1024 * 5 * 5 * 5   // 100MB
)
public class ShortStoryAdd extends HttpServlet {
    private GenericDao storyDao;
    private GenericDao userDao;
    private final static String UPLOAD_DIR = "media";
    private String appTargetPathCover;
    private String appPathCover;
    public void init() {
        //Extract the name of the class
        String className = ShortStory.class.getSimpleName().toLowerCase();
        storyDao = new GenericDao(ShortStory.class);
        userDao = new GenericDao(User.class);
        //Create Path to directories
        // constructs path of the directory to save uploaded file
        appTargetPathCover = getServletContext().getRealPath(File.separator) + File.separator + UPLOAD_DIR
                + File.separator + className + File.separator + "covers";
        // constructs path of the directory to save uploaded file
        File file = new File(appTargetPathCover.substring(0, 39) + "src/main/webapp");
        appPathCover = file.getAbsolutePath() + File.separator + UPLOAD_DIR + File.separator + className
                + File.separator + "covers";

    }

    /**
     * Handles HTTP GET requests.
     *
     * @param request  Description of the Parameter
     * @param response Description of the Parameter
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.isUserInRole("admin")) {
            String url = "/stories/storyAddEdit.jsp";
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ShortStory story =  new ShortStory();
        story.setTitle(req.getParameter("title").trim());
        story.setAuthor(req.getParameter("author").trim());
        String pubDate = req.getParameter("pub_date").trim();
        if (pubDate == null || pubDate.length() <= 0) {
            story.setPublicationDate(LocalDateTime.now());
        } else {
            story.setPublicationDate(LocalDateTime.parse(pubDate));
        }
        Part part = req.getPart("cover");
        if(part.getSubmittedFileName().isEmpty()) {
            story.setCover("media/story.jpg");
        }
        log.debug("Simple Name : " + ShortStory.class.getSimpleName());
        log.debug(" Name " + ShortStory.class.getName());
        story.setDescription(req.getParameter("description"));
        try {
            User user = (User) userDao.getByPropertyEqual("username", req.getRemoteUser()).get(0);
            log.debug("User In story Add." + user.getUsername());
            if (!user.equals(null) && req.isUserInRole("admin")) {
                story.setUser(user);
                int storyId = storyDao.insert(story);
                String successMessage = "Successfully added Short Story!";
                req.getSession().setAttribute("storyAddSuccessMessage",successMessage);
                resp.sendRedirect("short-stories");
            } else {
                req.getSession().setAttribute("storyErrorMessage","Failed to add SHort Story!");
                req.getRequestDispatcher("/story/storyAddEdit.jsp").forward(req, resp);
            }
        } catch (NullPointerException npe) {
            log.error("User Does not Exists", npe);
        }
    }
}