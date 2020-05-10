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
import java.util.Properties;


/**
 * The type Add story.
 * Responsible for getting form data for new story
 * and upload cover to S3
 * @author Jeanne
 * @version 1.0
 * @since 2020-04-23
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
public class ShortStoryAdd extends HttpServlet implements PropertiesLoader {
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
//        Properties properties = null;
        try {
            User user = (User) userDao.getByPropertyEqual("username", req.getRemoteUser()).get(0);
            log.debug("user in Story" + user.getUsername());
            Properties properties = loadProperties("/aws.properties");

            ShortStory story = new ShortStory();
            story.setTitle(req.getParameter("title").trim());
            story.setAuthor(req.getParameter("author").trim());
            String pubDate = req.getParameter("pub_date").trim();
            if (pubDate == null || pubDate.length() <= 0) {
                story.setPublicationDate(LocalDateTime.now());
            } else {
                story.setPublicationDate(LocalDateTime.parse(pubDate));
            }
            Part part = req.getPart("cover");
            if (part.getSubmittedFileName().isEmpty()) {
                story.setCover("media/story.jpg");
            } else {
                String fileName = part.getSubmittedFileName().toLowerCase();
                if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith("jpeg")) {
                    String accessKeyId = properties.getProperty("aws.access.key.id");
                    String secretAccessKey = properties.getProperty("aws.secret.access.key");
                    String region = properties.getProperty("aws.region");
                    String bucketName = properties.getProperty("aws.bucket.name");
                    String subdirectory = "media/" + ShortStory.class.getSimpleName().toLowerCase();
                    String fileObjKeyName = subdirectory + "/" + story.getTitle().toLowerCase().trim()
                            .replace(" ","_") + "_" + story.getAuthor().trim()
                            .replace(" ", "_") + fileName.substring(
                            fileName.lastIndexOf("."));
                    String fileToUpload = JavaHelperMethods.saveFileName(System.getProperty("java.io.tmpdir"), part);

                    AWSS3UploadUtil awsS3 = new AWSS3UploadUtil();
                    String fileUrl = awsS3.uploadToAWSS3(part, accessKeyId, secretAccessKey, region, bucketName, fileObjKeyName, fileToUpload);
                    story.setCover(fileUrl);
                    Files.deleteIfExists(Paths.get(fileToUpload));

                } else {
                    resp.getOutputStream().println("<p>Please only upload JPG or PNG files.</p>");
                }
            }
            log.debug("Simple Name : " + ShortStory.class.getSimpleName());
            log.debug(" Name " + ShortStory.class.getName());
            story.setDescription(req.getParameter("description"));


            log.debug("User In story Add." + user.getUsername());
            if (!user.equals(null) && req.isUserInRole("admin")) {
                story.setUser(user);
                int storyId = storyDao.insert(story);
                String successMessage = "Successfully added Short Story!";
                req.getSession().setAttribute("storyAddSuccessMessage", successMessage);
                resp.sendRedirect("short-stories");
            } else {
                req.getSession().setAttribute("storyErrorMessage", "Failed to add SHort Story!");
                req.getRequestDispatcher("/story/storyAddEdit.jsp").forward(req, resp);
            }
        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            log.error(e);
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            log.error(e);
        } catch (NullPointerException npe) {
            log.error("User Does not Exists", npe);
        } catch (Exception e) {
            log.error(e);
        }
    }
}