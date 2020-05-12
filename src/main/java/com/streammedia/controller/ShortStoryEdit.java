package com.streammedia.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import com.streammedia.utility.AWSS3UploadUtil;
import com.streammedia.utility.JavaHelperMethods;
import com.streammedia.utility.PropertiesLoader;
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
 * The type Short story edit.
 * Responsible for getting form data to update User's recor
 *
 * @author Jeanne
 * @version 1.0
 * @since 2020 -02-22
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
public class ShortStoryEdit extends HttpServlet implements PropertiesLoader {
    private GenericDao storyDao;
    private GenericDao userDao;
    private String projectTargetPathCover;
    private String projectAppPathCover;

    /**
     * Init.
     * Responsible to create an instance of dao.
     */
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
        projectAppPathCover = JavaHelperMethods.createUserImagePath(file.getAbsolutePath(), "covers").replace("//", "/");
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
        if (req.isUserInRole("admin")) {
            try {
                Properties properties = loadProperties("/aws.properties");
                int storyId = Integer.parseInt(req.getParameter("uid"));
                log.debug(storyId);
                ShortStory story = (ShortStory) storyDao.getById(storyId);
                log.debug(story.getShortStoryId());
                story.setTitle(req.getParameter("title"));
                story.setAuthor(req.getParameter("author"));
                story.setPublicationDate(LocalDateTime.parse(req.getParameter("pub_date")));
                story.setDescription(req.getParameter("description").trim());
                Part coverPart = req.getPart("cover");
                log.debug(coverPart.getSubmittedFileName());
                String pathCover = story.getCover();
                log.debug(" Cover Path: " + pathCover);
                if (!coverPart.getSubmittedFileName().isEmpty()) {
                    String storyCoverFileName= coverPart.getSubmittedFileName().toLowerCase();
                    if (storyCoverFileName.endsWith(".jpg") ||storyCoverFileName.endsWith(".png") ||storyCoverFileName.endsWith("jpeg")) {
                        String accessKeyId = properties.getProperty("aws.access.key.id");
                        String secretAccessKey = properties.getProperty("aws.secret.access.key");
                        String region = properties.getProperty("aws.region");
                        String bucketName = properties.getProperty("aws.bucket.name");
                        String subdirectory = "media/" + ShortStory.class.getSimpleName().toLowerCase();
                        String fileObjKeyName = subdirectory + "/" + story.getTitle().toLowerCase().trim()
                                .replace(" ","_") + "_" + story.getAuthor().trim()
                                .replace(" ", "_") +storyCoverFileName.substring(
                               storyCoverFileName.lastIndexOf("."));
                        String fileToUpload = JavaHelperMethods.saveFileName(System.getProperty("java.io.tmpdir"), coverPart);

                        AWSS3UploadUtil awsS3 = new AWSS3UploadUtil();
                        String fileUrl = awsS3.uploadToAWSS3(coverPart, accessKeyId, secretAccessKey, region, bucketName, fileObjKeyName, fileToUpload);
                        story.setCover(fileUrl);
                        Files.deleteIfExists(Paths.get(fileToUpload));
                    } else {
                        String errorMessage = " Unsupported file extension! <br/>Please only upload JPG, JPEG or PNG files";
                       req.getSession().setAttribute("unsupportedExtension",errorMessage);
                        req.getRequestDispatcher("story-details?uid=" + story.getShortStoryId()).forward(req,resp);
                    }
                }
                log.debug("Updating Story: " + story.getTitle());
                storyDao.saveOrUpdate(story);
                String successMessage = "Successfully Updated Short Story!";
                req.getSession().setAttribute("storyEditSuccessMessage", successMessage);
                resp.sendRedirect("story-details?uid=" + story.getShortStoryId());
            } catch (NumberFormatException numberFormatException) {
                log.error(numberFormatException);
            } catch (AmazonServiceException e) {
                // The call was transmitted successfully, but Amazon S3 couldn't process
                // it, so it returned an error response.
                log.error(e);
            } catch (SdkClientException e) {
                // Amazon S3 couldn't be contacted for a response, or the client
                // couldn't parse the response from Amazon S3.
                log.error(e);
            } catch (NullPointerException npe) {
                log.error("Object Does not Exists", npe);
            }catch (Exception exception) {
                log.error(exception);
            }
        } else {
            req.getSession().setAttribute("storyErrorMessage", "Failed to updated Short Story!");
            req.getRequestDispatcher("/stories/storyAddEdit.jsp").forward(req,resp);
        }
    }
}

