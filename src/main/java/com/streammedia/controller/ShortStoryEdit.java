package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import com.streammedia.utility.JavaHelperMethods;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.*;


/**
 * The type Short story edit.
 * @author Jeanne
 * @version 1.0
 *  @since 2020-02-22
 */

@Log4j2
@WebServlet(
        name = "storyEdit",
        urlPatterns = {"/story-edit"}
)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10 * 10 * 10,      // 1GB
        maxRequestSize = 1024 * 1024 * 5 * 5 * 5   // 100MB
)
public class ShortStoryEdit extends HttpServlet {
    private GenericDao storyDao;
    private GenericDao userDao;
    private String projectTargetPathCover;
    private String projectAppPathCover;

    public void init() {
        storyDao = new GenericDao<>(ShortStory.class);
        userDao = new GenericDao<>(User.class);
        createDirectory();
    }

    private void createDirectory() {
        //Extract the name of the class
        String className = ShortStory.class.getSimpleName().toLowerCase();
        // constructs path of the directory to save uploaded file
        projectTargetPathCover = JavaHelperMethods.createUserImagePath((getServletContext().getRealPath(File.separator) + File.separator + "media"
                + File.separator + className), "covers").replace("//", "/");
        // constructs path of the directory to save uploaded file
        File file = new File(projectTargetPathCover.substring(0, 39) + "src/main/webapp/media/" + className);
        projectAppPathCover = JavaHelperMethods.createUserImagePath(file.getAbsolutePath(),"covers").replace("//", "/");
        log.debug(projectTargetPathCover);
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
            request.setAttribute("story", storyDao.getById(Integer.valueOf(request.getParameter("uid"))));
            String url = "/stories/storyAddEdit.jsp";
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("login");
        }
            }

    /**
     * Handles HTTP POST requests.
     *
     * @param req  Description of the Parameter
     * @param resp Description of the Parameter
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int storyId = Integer.parseInt(req.getParameter("uid"));
        log.debug(storyId);
        ShortStory story = (ShortStory) storyDao.getById(storyId);
        log.debug(story.getShortStoryId());
        if (req.isUserInRole("admin")) {
            story.setTitle(req.getParameter("title"));
            story.setAuthor(req.getParameter("author"));
            story.setPublicationDate(LocalDateTime.parse(req.getParameter("pub_date")));
            Part coverPart = req.getPart("cover");
            log.debug(coverPart.getSubmittedFileName());
            String pathCover = story.getCover();
            log.debug(" Cover Path: " + pathCover);
            if (coverPart.getSubmittedFileName().isEmpty()) {
                story.setCover(story.getCover());
            } else {
                String coverPath = JavaHelperMethods.saveFileName(projectAppPathCover, coverPart);
                Path source = Paths.get(coverPath);
                String newName = "storyCover_" + story.getShortStoryId() + coverPath.substring(
                        coverPath.lastIndexOf("."));
                //https://www.geeksforgeeks.org/files-deleteifexists-method-in-java-with-examples/
                Files.deleteIfExists(Paths.get(projectAppPathCover + File.separator + newName));
                Files.move(source, source.resolveSibling(newName));
                Path original = Paths.get(projectAppPathCover + File.separator + newName);
                Path destination = Paths.get(projectTargetPathCover + File.separator + newName);
                Files.copy(original, destination, StandardCopyOption.REPLACE_EXISTING);
                story.setCover("media/shortstory/covers/" + newName);
            }

            story.setDescription(req.getParameter("description").trim());
            log.debug("Updating Story: " + story.getTitle());
            storyDao.saveOrUpdate(story);
            resp.sendRedirect("story-details?uid=" + story.getShortStoryId());

        }
    }
}

