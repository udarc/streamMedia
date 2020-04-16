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
import java.time.*;


/**
 * https://www.javaguides.net/2019/03/jsp-servlet-hibernate-crud-example.html
 * The type Add trailer.
 * Responsible for getting form data for new trailer
 *
 * @author Jeanne
 * @version 1.0
 * @since 2020-02-25
 * TODO upload to S3  https://www.youtube.com/watch?v=iQrOmbCiFBs
 */
@Log4j2
@WebServlet(
        name = "trailerAdd",
        urlPatterns = {"/add-trailer"}
)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 1000,      // 1GB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class TrailerAdd extends HttpServlet {
    private GenericDao trailerDao;
    private GenericDao userDao;
    private final static String UPLOAD_DIR = "media";
    private String appPath;
    private String webPath;
    private Trailer trailer;
    private String className;

    public void init() {
        trailer = new Trailer();
        //Extract the name of the class
        className = JavaHelperMethods.retrieveClassName(trailer);

        trailerDao = new GenericDao(Trailer.class);
        userDao = new GenericDao(User.class);
        //Create Path to directories
        // constructs path of the directory to save uploaded file
        appPath = getServletContext().getRealPath(File.separator) + File.separator + UPLOAD_DIR;
        // constructs path of the directory to save uploaded file
        File file = new File(appPath.substring(0, 39) + "src/main/webapp");
        webPath = file.getAbsolutePath() + File.separator + UPLOAD_DIR;

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
            String url = "/trailer/trailerAddEdit.jsp";
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //Create fullPath
        trailer.setTitle(req.getParameter("title").trim());
        trailer.setAuthor(req.getParameter("author").trim());
        trailer.setDuration(LocalTime.parse(req.getParameter("duration")));
        log.debug("After Duration: " + trailer);
        String pubDate = req.getParameter("pub_date").trim();
        if (pubDate == null || pubDate.length() <= 0) {
            trailer.setPublicationDate(LocalDateTime.now());
        } else {
            trailer.setPublicationDate(LocalDateTime.parse(pubDate));
        }
        trailer.setLink(req.getParameter("link"));

        String saveAtTarget = JavaHelperMethods.createUserImagePath(appPath, className).replace("//", "/");
        String saveAtWebApp = JavaHelperMethods.deleteAndCreateFilePath(webPath, className).replace("//", "/");
        log.debug("Paths: " + saveAtWebApp);
        Part part = req.getPart("cover");
        Part videoPart = req.getPart("video");
        if (!part.equals(null) || !videoPart.equals(null)) {
//
            String targetPathC = JavaHelperMethods.saveFileName(saveAtTarget, part);
//            String projectPathC = JavaHelperMethods.saveFileName (saveAtWebApp,part);
//            trailer.setCover(projectPathC.substring(55,projectPathC.length()));
//            log.debug("After Cover: " + trailer.getCover());
//            //Video TODO Implement upload a video
//            Part videoPart =  req.getPart("video");
//          //https://cloudinary.com/documentation/video_transformation_reference
            String targetPathV = JavaHelperMethods.saveFileName(saveAtTarget, videoPart);
//            String projectPathV = JavaHelperMethods.saveFileName (saveAtWebApp,videoPart);
//            trailer.setVideo(projectPathV.substring(55,projectPathV.length()));
//           log.debug("After Video: " + trailer.getVideo());
        }

        trailer.setSummary(req.getParameter("summary"));
        log.debug("After Summary: " + trailer);
        log.error("After Summary: " + trailer);
        try {
            User user = (User) userDao.getByPropertyEqual("username", req.getRemoteUser()).get(0);
            log.debug("User In trailer Add." + user);
            if (!user.equals(null) && req.isUserInRole("admin")) {
                trailer.setUser(user);
//                trailerDao.insert(trailer);
                resp.sendRedirect("trailers");
            } else {
                req.getRequestDispatcher("/trailer/trailerAddEdit.jsp").forward(req, resp);
            }
        } catch (NullPointerException npe) {
            log.error("User Does not Exists", npe);
        }
    }
}
