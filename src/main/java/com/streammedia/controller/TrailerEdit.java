package com.streammedia.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import com.streammedia.utility.AWSS3UploadUtil;
import com.streammedia.utility.JavaHelperMethods;
import com.streammedia.utility.PropertiesLoader;
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
import java.util.Properties;

/**
 * The type Trailer edit.
 * Responsible for getting form data to update Trailer's record.
 *
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
        maxRequestSize = 1024 * 1024 * 5 * 5 * 10   // 100MB
)
public class TrailerEdit extends HttpServlet implements PropertiesLoader {
    private GenericDao trailerDao;
    private GenericDao userDao;


    public void init() {
        trailerDao = new GenericDao<>(Trailer.class);
        userDao = new GenericDao<>(User.class);
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
        request.setAttribute("trailer", trailerDao.getById(Integer.valueOf(request.getParameter("uid"))));
        String url = "/trailers/trailerAddEdit.jsp";
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.isUserInRole("admin")) {
        try {
            Properties properties = loadProperties("/aws.properties");

                int trailerId = Integer.parseInt(req.getParameter("uid"));
                log.debug(trailerId);
                Trailer trailer = (Trailer) trailerDao.getById(trailerId);
                log.debug(trailer.getTrailerId());
                trailer.setTitle(req.getParameter("title"));
                trailer.setAuthor(req.getParameter("author"));
                trailer.setDuration(LocalTime.parse(req.getParameter("duration")));
                trailer.setPublicationDate(LocalDateTime.parse(req.getParameter("pub_date")));
                trailer.setLink(req.getParameter("link"));
                Part part = req.getPart("cover");
                if (!part.getSubmittedFileName().isEmpty()) {
                    String trailerCoverFileName= part.getSubmittedFileName().toLowerCase();
                    if (trailerCoverFileName.endsWith(".jpg") ||trailerCoverFileName.endsWith(".png")
                            ||trailerCoverFileName.endsWith("jpeg")) {
                        String accessKeyId = properties.getProperty("aws.access.key.id");
                        String secretAccessKey = properties.getProperty("aws.secret.access.key");
                        String region = properties.getProperty("aws.region");
                        String bucketName = properties.getProperty("aws.bucket.name");
                        String subdirectory = "media/" + Trailer.class.getSimpleName().toLowerCase();
                        String fileObjKeyName = subdirectory + "/" + trailer.getTitle().toLowerCase().trim()
                                .replace(" ","_") + "_" + trailer.getAuthor().trim()
                                .replace(" ", "_") +trailerCoverFileName.substring(
                                trailerCoverFileName.lastIndexOf("."));
                        String fileToUpload = JavaHelperMethods.saveFileName(System.getProperty("java.io.tmpdir"), part);

                        AWSS3UploadUtil awsS3 = new AWSS3UploadUtil();
                        String fileUrl = awsS3.uploadToAWSS3(part, accessKeyId, secretAccessKey, region, bucketName, fileObjKeyName, fileToUpload);
                        trailer.setCover(fileUrl);
                        Files.deleteIfExists(Paths.get(fileToUpload));
                    } else {
                        trailer.setCover(trailer.getCover());
                        String errorMessage = " Unsupported file extension! <br/>Please only upload JPG, JPEG or PNG files";
                        req.getSession().setAttribute("unsupportedExtension",errorMessage);
                        req.getRequestDispatcher("/stories/trailerAddEdit.jsp").forward(req,resp);
                    }
                }
            Part videoPart = req.getPart("video");
            if (!videoPart.getSubmittedFileName().isEmpty()) {
                String fileName = videoPart.getSubmittedFileName().toLowerCase();
                if (fileName.endsWith(".mp4")) {
                    String accessKeyId = properties.getProperty("aws.access.key.id");
                    String secretAccessKey = properties.getProperty("aws.secret.access.key");
                    String region = properties.getProperty("aws.region");
                    String bucketName = properties.getProperty("aws.bucket.name");
                    String subdirectory = "media/" + Film.class.getSimpleName().toLowerCase();
                    String fileObjKeyName = subdirectory + "/videos/" + trailer.getTitle().toLowerCase().trim()
                            .replace(" ", "_") + "_" + trailer.getAuthor().trim()
                            .replace(" ", "_") + fileName.substring(
                            fileName.lastIndexOf("."));
                    String fileToUpload = JavaHelperMethods.saveFileName(System.getProperty("java.io.tmpdir"), videoPart);

                    AWSS3UploadUtil awsS3 = new AWSS3UploadUtil();
                    String fileUrl = awsS3.uploadToAWSS3(videoPart, accessKeyId, secretAccessKey, region, bucketName, fileObjKeyName, fileToUpload);
                    trailer.setVideo(fileUrl);
                    Files.deleteIfExists(Paths.get(fileToUpload));

                } else {
                    trailer.setVideo(trailer.getVideo());
                    String errorMessage = "Video not saved!Unsupported file extension! " +
                            "<br/>Please only upload mp4 files";
                    req.getSession().setAttribute("unsupportedVideoExtension",errorMessage);
                }
            }
            trailer.setSummary(req.getParameter("summary").trim());
            trailerDao.saveOrUpdate(trailer);
            String successMessage = "Successfully Updated  Trailer!";
            req.getSession().setAttribute("trailerEditSuccessMessage", successMessage);
            resp.sendRedirect("trailer-details?uid=" + trailer.getTrailerId());
        } catch (NumberFormatException numberFormatException) {
            log.error(numberFormatException);
        } catch (
                AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            log.error(e);
        } catch (
                SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            log.error(e);
        } catch (NullPointerException npe) {
            log.error("Object Does not Exists", npe);
        } catch (Exception exception) {
            log.error(exception);
        }
        } else {
            req.getSession().setAttribute("trailerErrorMessage", "Failed to update " + Trailer.class.getSimpleName());
            req.getRequestDispatcher("/trailers/trailerAddEdit.jsp").forward(req, resp);
        }
    }
}

