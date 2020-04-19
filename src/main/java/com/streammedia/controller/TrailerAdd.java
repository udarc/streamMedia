package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import com.streammedia.utility.JavaHelperMethods;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;


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
        maxFileSize = 1024 * 1024 * 10 * 10 * 10,      // 1GB
        maxRequestSize = 1024 * 1024 * 5 * 5 * 5   // 100MB
)
public class TrailerAdd extends HttpServlet {
    private GenericDao trailerDao;
    private GenericDao userDao;
    private final static String UPLOAD_DIR = "media";
    private String appPath;
    private String webPath;
    private Trailer trailer;
    private  String coverPath;
    private  String videoPath;

    public void init() {
        trailer = new Trailer();
        //Extract the name of the class
        String className = JavaHelperMethods.retrieveClassName(trailer);

        coverPath =  "covers";
        videoPath =  "videos";
        trailerDao = new GenericDao(Trailer.class);
        userDao = new GenericDao(User.class);
        //Create Path to directories
        // constructs path of the directory to save uploaded file
        appPath = getServletContext().getRealPath(File.separator) + File.separator + UPLOAD_DIR + File.separator + className;
        // constructs path of the directory to save uploaded file
        File file = new File(appPath.substring(0, 39) + "src/main/webapp");
        webPath = file.getAbsolutePath() + File.separator + UPLOAD_DIR + File.separator + className ;

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
            String url = "/trailers/trailerAddEdit.jsp";
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
        String fileNameCover = "";
        String fileNameVideo = "";
        String coverExt = "";
        String videoExt = "";
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
        log.debug("After Link " + pubDate);
         Part part = req.getPart("cover");
        if (part.getSubmittedFileName().isEmpty()){
            trailer.setCover("media/trailer1.jpg");
        } else {
            String saveWebPathCover = JavaHelperMethods.createUserImagePath(webPath, coverPath).replace("//", "/");
            String saveAppTargetCover = JavaHelperMethods.createUserImagePath(appPath, coverPath).replace("//", "/");
            String webPathC = JavaHelperMethods.saveFileName(saveWebPathCover, part);
            int i = webPathC.lastIndexOf('.');
            int j = webPathC.lastIndexOf('/');
            fileNameCover =  webPathC.substring(j+1, i);
            coverExt = webPathC.substring(i);
            log.debug("Extension of a File : " + webPathC.substring(i+1) );
            String targertPathC = JavaHelperMethods.saveFileName(saveAppTargetCover, part);
            trailer.setCover(targertPathC.substring(58, targertPathC.length()));
        }

        //Video

        Part partVideo = req.getPart("video");
        if (partVideo.getSubmittedFileName().isEmpty()){
            trailer.setVideo("media/trailerv.mp4");
        } else {

            //log.debug("Parts Data: " + partVideo.getSize());
            //TODO String saveAtWebApp = JavaHelperMethods.deleteAndCreateFilePath(webPath, className).replace("//", "/");
            String saveWebAppVideo = JavaHelperMethods.createUserImagePath(webPath, videoPath).replace("//", "/");;
            String saveAppTargetVideo = JavaHelperMethods.createUserImagePath(appPath, videoPath).replace("//", "/");;
            //TODO Throws FileNotFound Error
            //String targetPathV = JavaHelperMethods.saveFileName(saveAppTargetVideo, partVideo);
            //TODO name Files by Id.
            //TODO Rename file before save
            String webPathV = JavaHelperMethods.saveFileName(saveWebAppVideo, partVideo);
            int i = webPathV.lastIndexOf('.');
            int j = webPathV.lastIndexOf('/');
            fileNameVideo =  webPathV.substring(j+1, i);
            videoExt = webPathV.substring(i);
            Path destination = Paths.get(saveAppTargetVideo + File.separator + fileNameVideo + videoExt);
            Path original =Paths.get(webPathV);
            Files.copy(original, destination, StandardCopyOption.REPLACE_EXISTING);
            trailer.setVideo(webPathV.substring(55, webPathV.length()));
        }
        trailer.setSummary(req.getParameter("summary"));
        log.debug("After Summary: " + trailer);
        log.error("After Summary: " + trailer);
        try {
            User user = (User) userDao.getByPropertyEqual("username", req.getRemoteUser()).get(0);
            log.debug("User In trailer Add." + user);
            if (!user.equals(null) && req.isUserInRole("admin")) {
                trailer.setUser(user);
//                int trailerId = 19;
                int trailerId = trailerDao.insert(trailer);
//                log.debug("trailer.trailerId()" + trailerId);
                resp.sendRedirect("trailers");
            } else {
                req.getRequestDispatcher("/trailers/trailerAddEdit.jsp").forward(req, resp);
            }
        } catch (NullPointerException npe) {
            log.error("User Does not Exists", npe);
        }
    }
}
