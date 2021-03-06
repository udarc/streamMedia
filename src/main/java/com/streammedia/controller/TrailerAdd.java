package com.streammedia.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import com.streammedia.utility.AWSS3UploadUtil;
import com.streammedia.utility.JavaHelperMethods;
import com.streammedia.utility.PropertiesLoader;
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
import java.util.Properties;


/**
 * https://www.javaguides.net/2019/03/jsp-servlet-hibernate-crud-example.html
 * The type Add trailer.
 * Responsible for getting form data for new trailer
 *
 * @author Jeanne
 * @version 1.0
 * @since 2020 -02-25 TODO upload to S3  https://www.youtube.com/watch?v=iQrOmbCiFBs
 */
@Log4j2
@WebServlet(
        name = "trailerAdd",
        urlPatterns = {"/trailer-new"}
)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10 * 10 * 10,      // 1GB
        maxRequestSize = 1024 * 1024 * 5 * 5 * 10   // 100MB
)
public class TrailerAdd extends HttpServlet implements PropertiesLoader {
    private GenericDao trailerDao;
    private GenericDao userDao;

    /**
     * Init.
     * Responsible to create an instance of dao.
     */
    public void init() {
        trailerDao = new GenericDao(Trailer.class);
        userDao = new GenericDao(User.class);

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

    /**
     * Do post.
     *
     * @param req  the req
     * @param resp the resp
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.isUserInRole("admin")) {
            try {
                Trailer trailer = new Trailer();
                Properties properties = loadProperties("/aws.properties");
                User user = (User) userDao.getByPropertyEqual("username", req.getRemoteUser()).get(0);

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

                trailer.setSummary(req.getParameter("summary"));
                log.debug("After Summary: " + trailer);
                log.error("After Summary: " + trailer);
                log.debug("User In trailer Add." + user);
                trailer.setUser(user);
                Part part = req.getPart("cover");
                if (part.getSubmittedFileName().isEmpty()) {
                    trailer.setCover("media/trailer1.jpg");
                } else {
                    String fileName = part.getSubmittedFileName().toLowerCase();
                    if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith("jpeg")) {
                        String accessKeyId = properties.getProperty("aws.access.key.id");
                        String secretAccessKey = properties.getProperty("aws.secret.access.key");
                        String region = properties.getProperty("aws.region");
                        String bucketName = properties.getProperty("aws.bucket.name");
                        String subdirectory = "media/" + Trailer.class.getSimpleName().toLowerCase();
                        String fileObjKeyName = subdirectory + "/covers/" + trailer.getTitle().toLowerCase().trim()
                                .replace(" ", "_") + "_" + trailer.getAuthor().trim()
                                .replace(" ", "_") + fileName.substring(
                                fileName.lastIndexOf("."));
                        String fileToUpload = JavaHelperMethods.saveFileName(System.getProperty("java.io.tmpdir"), part);

                        AWSS3UploadUtil awsS3 = new AWSS3UploadUtil();
                        String fileUrl = awsS3.uploadToAWSS3(part, accessKeyId, secretAccessKey, region, bucketName, fileObjKeyName, fileToUpload);
                        trailer.setCover(fileUrl);
                        Files.deleteIfExists(Paths.get(fileToUpload));

                    } else {
                        String errorMessage = "Cover not saved!Unsupported file extension! " +
                                "<br/>Please only upload JPG, JPEG or PNG files";
                        req.getSession().setAttribute("unsupportedExtension", errorMessage);
                    }
                }
                Part videoPart = req.getPart("video");
                if (videoPart.getSubmittedFileName().isEmpty()) {
                    trailer.setVideo("media/trailer.mp4");
                } else {
                    String fileName = videoPart.getSubmittedFileName().toLowerCase();
                    if (fileName.endsWith(".mp4")) {
                        String accessKeyId = properties.getProperty("aws.access.key.id");
                        String secretAccessKey = properties.getProperty("aws.secret.access.key");
                        String region = properties.getProperty("aws.region");
                        String bucketName = properties.getProperty("aws.bucket.name");
                        String subdirectory = "media/" + Trailer.class.getSimpleName().toLowerCase();
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
                        String errorMessage = "Video not saved!Unsupported file extension! " +
                                "<br/>Please only upload mp4 files";
                        req.getSession().setAttribute("unsupportedVideoExtension", errorMessage);
                    }
                }

                trailerDao.insert(trailer);
                String successMessage = "Successfully added " + Trailer.class.getSimpleName();
                req.getSession().setAttribute("trailerAddSuccessMessage", successMessage);
                resp.sendRedirect("trailers");
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
            req.getSession().setAttribute("trailerErrorMessage", "Failed to add " + Trailer.class.getSimpleName());

            req.getRequestDispatcher("/trailers/trailerAddEdit.jsp").forward(req, resp);
        }
    }
}
