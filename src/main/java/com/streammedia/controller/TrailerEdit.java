package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import com.streammedia.utility.JavaHelperMethods;
import com.sun.xml.bind.v2.util.StackRecorder;
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
 * The type Trailer edit.
 * @author Jeanne
 * @version 1.0
 * @since 2020-02-22
 */
@Log4j2
@WebServlet(
        name = "trailerEdit",
        urlPatterns = {"/trailer-edit"}
)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10 * 10 * 10,      // 1GB
        maxRequestSize = 1024 * 1024 * 5 * 5 * 5   // 100MB
)
public class TrailerEdit extends HttpServlet {
    private GenericDao trailerDao;
    private GenericDao userDao;
    private String projectTargetPathCover;
    private String projectTargetPathVideo;
    private String projectAppPathCover;
    private String projectAppPathVideo;
    public void init() {
        trailerDao = new GenericDao<>(Trailer.class);
        userDao = new GenericDao<>(User.class);
        createDirectory();
    }

    private void createDirectory() {
        Trailer trailer = new Trailer();
        //Extract the name of the class
        String className = JavaHelperMethods.retrieveClassName(trailer);
        // constructs path of the directory to save uploaded file
        projectTargetPathCover = (getServletContext().getRealPath(File.separator) + File.separator + "media"
                + File.separator + className + File.separator + "covers").replace("//", "/");
        projectTargetPathVideo =  (getServletContext().getRealPath(File.separator) + File.separator + "media"
                + File.separator + className + File.separator + "videos").replace("//", "/");
        // constructs path of the directory to save uploaded file
        File file = new File(projectTargetPathCover.substring(0, 39) + "src/main/webapp/media/" + className);
        projectAppPathCover = (file.getAbsolutePath() +  File.separator + "covers").replace("//", "/");
        projectAppPathVideo = (file.getAbsolutePath() + File.separator + "videos").replace("//", "/");
        log.debug(projectAppPathVideo);
        log.debug(projectTargetPathCover);
    }

    /**
     *  Handles HTTP GET requests.
     *
     *@param  request               Description of the Parameter
     *@param  response              Description of the Parameter
     *@exception ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("trailer",trailerDao.getById(Integer.valueOf(request.getParameter("uid"))));
        String url ="/trailers/trailerAddEdit.jsp";
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request,response);

    }
    /**
     *  Handles HTTP POST requests.
     *@param  req              Description of the Parameter
     *@param  resp             Description of the Parameter
     *@exception ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int trailerId = Integer.parseInt(req.getParameter("uid"));
        log.debug(trailerId);
        Trailer trailer = (Trailer) trailerDao.getById(trailerId);
        log.debug(trailer.getTrailerId());
        if (req.isUserInRole("admin")) {
            trailer.setTitle(req.getParameter("title"));
            trailer.setAuthor(req.getParameter("author"));
            trailer.setDuration(LocalTime.parse(req.getParameter("duration")));
            trailer.setPublicationDate(LocalDateTime.parse(req.getParameter("pub_date")));
            trailer.setLink(req.getParameter("link"));
            Part coverPart = req.getPart("cover");
            log.debug(coverPart.getSubmittedFileName());
            String fileNameCover = "";
            String coverExt = "";
            String fileNameVideo = "";
            String videoExt = "";
            String pathCover = trailer.getCover();
            log.debug(" Cover Path: " + pathCover);
            if (coverPart.getSubmittedFileName().isEmpty()) {
//                int i = pathCover.lastIndexOf('.');
//                int j = pathCover.lastIndexOf('/');
//                fileNameCover = pathCover.substring(j + 1, i);
//                coverExt = pathCover.substring(i);
//                if (pathCover.length() < 20) {
                    trailer.setCover(trailer.getCover());
//                } else {
//                    String coverPath = projectTargetPathCover + File.separator + fileNameCover + coverExt;
//                    Path source = Paths.get(coverPath);
//                    Path original = Paths.get("");
//                    String newName = "trailerCover_" + trailer.getTrailerId() + coverPath.substring(
//                            coverPath.lastIndexOf("."));
//                    if((fileNameCover + coverExt).equals(newName)){
//                        Path coverDestination = Paths.get(projectAppPathCover + File.separator + newName);
//                        original =  source;
//                        Files.copy(source,coverDestination,StandardCopyOption.REPLACE_EXISTING);
//                    } else {
//                        original = Files.move(source, source.resolveSibling(newName));
//                    }
//
//                    Path destination = Paths.get(projectTargetPathCover + File.separator + newName);
//                    Files.copy(original, destination, StandardCopyOption.REPLACE_EXISTING);
//                    trailer.setCover("media/trailer/covers/" + newName);
//                }
            } else {
                    String coverPath = JavaHelperMethods.saveFileName(projectAppPathCover, coverPart);
                    Path source = Paths.get(coverPath);
                    String newName = "trailerCover_" + trailer.getTrailerId() + coverPath.substring(
                            coverPath.lastIndexOf("."));
                    //https://www.geeksforgeeks.org/files-deleteifexists-method-in-java-with-examples/
                    Files.deleteIfExists(Paths.get(projectAppPathCover + File.separator + newName));
                    Files.move(source, source.resolveSibling(newName));
                    Path original = Paths.get(projectAppPathCover + File.separator + newName);
                    Path destination = Paths.get(projectTargetPathCover + File.separator + newName);
                    Files.copy(original, destination, StandardCopyOption.REPLACE_EXISTING);
                    trailer.setCover("media/trailer/covers/" + newName);
            }

            Part videoPart = req.getPart("video");
            String pathVideo = trailer.getVideo();
            log.debug(videoPart.getSubmittedFileName());
            if (videoPart.getSubmittedFileName().isEmpty()) {
//                int i = pathVideo.lastIndexOf('.');
//                int j = pathVideo.lastIndexOf('/');
//                fileNameVideo = pathVideo.substring(j + 1, i);
//                videoExt = pathVideo.substring(i);
//                if (pathVideo.length() < 20) {
                    trailer.setVideo(trailer.getVideo());
//                } else {
//                    String pathV = projectAppPathVideo + File.separator + fileNameVideo + videoExt;
//                    Path source = Paths.get(pathV);
//                    String newName = "trailerVideo_" + trailer.getTrailerId() + pathV.substring(
//                            pathV.lastIndexOf("."));
//                    Files.deleteIfExists(Paths.get(projectAppPathVideo + File.separator + newName));
//                    Path original = Files.move(source, source.resolveSibling(newName));
//                    Path destination = Paths.get(projectTargetPathVideo + File.separator + newName);
//                    Files.copy(original, destination, StandardCopyOption.REPLACE_EXISTING);
//                    trailer.setVideo("media/trailer/videos/" + newName);
//                }
            } else {
                String videoPath = JavaHelperMethods.saveFileName(projectAppPathVideo, videoPart);
                Path source = Paths.get(videoPath);
                String newName = "trailerVideo_" + trailer.getTrailerId() + videoPath.substring(
                        videoPath.lastIndexOf("."));
                Files.deleteIfExists(Paths.get(projectAppPathVideo + File.separator + newName));
                Path original = Files.move(source, source.resolveSibling(newName));
                Path destination = Paths.get(projectTargetPathVideo + File.separator + newName);
                Files.copy(original, destination, StandardCopyOption.REPLACE_EXISTING);
                trailer.setVideo("media/trailer/videos/" + newName);
            }

                trailer.setSummary(req.getParameter("summary").trim());
                log.debug("Updating Trailer: " + trailer.getTitle());
                trailerDao.saveOrUpdate(trailer);
                resp.sendRedirect("trailer-detail?uid=" + trailer.getTrailerId());

        }
    }
}

