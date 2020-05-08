package com.streammedia.controller;
//TODO https://www.javatpoint.com/crud-in-servlet

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import com.streammedia.utility.JavaHelperMethods;
import com.streammedia.utility.PropertiesLoader;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.Properties;

/**
 * The type Edit user profile.
 * Responsible for getting form data to update User's record
 * upload an image https://www.youtube.com/watch?v=WtZm1bB6T-8
 *
 * @author Jeanne
 * @version 1.0
 * @since 05-05-2020
 */
@Log4j2
@WebServlet(
        name = "userEdit",
        urlPatterns = {"/profile-edit"}
)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1MB
        maxFileSize = 1024 * 1024 * 2,      // 3MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class UserEditProfile extends HttpServlet implements PropertiesLoader {
    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "images";
    private GenericDao genericDao;
    private String appPath;
    private String rootPath;

    public void init() {
        // gets absolute path of the web application
        appPath = getServletContext().getRealPath("") + File.separator + SAVE_DIR;
        // constructs path of the directory to save uploaded file
        File file = new File("/home/student/IdeaProjects/streamMedia/src/main/webapp");
        rootPath = file.getAbsolutePath() + File.separator + SAVE_DIR;
        genericDao = new GenericDao(User.class);
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

        HttpSession session = request.getSession();

        String username = request.getParameter("user");
        request.setAttribute("user", genericDao.getByPropertyEqual("username", username).get(0));
        String url = "/account/editUserProfile.jsp";
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("Root Project Path: " + rootPath);
        Properties properties = null;
        try {
            properties = loadProperties("/aws.properties");
        } catch (Exception e) {
            log.error(e);
        }
        String username = req.getParameter("user");
        log.debug(" User EDIt Username Value : " + username);
        User user = (User) genericDao.getByPropertyEqual("username", username).get(0);
        if ((req.isUserInRole("admin") || req.isUserInRole("media creator")|| req.isUserInRole("user"))
                && username.equals(user.getUsername())) {
            user.setEmail(req.getParameter("email"));
            user.setUsername(username);
            user.setFirstName(req.getParameter("firstName"));
            user.setLastName(req.getParameter("lastName"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String birthdate = req.getParameter("birthday");
            if (birthdate != null || birthdate != "") {
                user.setBirthdate(LocalDate.parse(birthdate, formatter));
            } else {
                birthdate = LocalDate.now().minusYears(18).toString();
                user.setBirthdate(LocalDate.parse(birthdate));
            }
            user.setGender(req.getParameter("gender"));
            Part part = req.getPart("profilePicture");

            String fileName = part.getSubmittedFileName().toLowerCase();
            log.info(fileName);
            if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith("jpeg")) {
                String accessKeyId = properties.getProperty("aws.access.key.id");
                String secretAccessKey = properties.getProperty("aws.secret.access.key");
                String region = properties.getProperty("aws.region");
                String bucketName = properties.getProperty("aws.bucket.name");
                String subdirectory = "images/" + username;
                String fileObjKeyName = subdirectory + "/userProfile" + fileName.substring(
                        fileName.lastIndexOf("."));
                log.info(System.getProperty("java.io.tmpdir"));
                String fileToUpload = JavaHelperMethods.saveFileName(System.getProperty("java.io.tmpdir"), part);

                try {
                    String fileUrl = uploadToS3(part, accessKeyId, secretAccessKey, region, bucketName, fileObjKeyName, fileToUpload);
                    user.setPicture(fileUrl);
                    Files.deleteIfExists(Paths.get(fileToUpload));
                } catch (AmazonServiceException e) {
                    // The call was transmitted successfully, but Amazon S3 couldn't process
                    // it, so it returned an error response.
                   log.error(e);
                } catch (SdkClientException e) {
                    // Amazon S3 couldn't be contacted for a response, or the client
                    // couldn't parse the response from Amazon S3.
                    log.error(e);
                }
            } else {
                resp.getOutputStream().println("<p>Please only upload JPG or PNG files.</p>");
            }
            user.setBiography(req.getParameter("biography"));
            log.error(user);
            log.debug("Updating User: " + user);
            genericDao.saveOrUpdate(user);
            String successMessage = "Successfully updated " + username + "!";
            req.getSession().setAttribute("userEditSuccess", successMessage);
            if (req.isUserInRole("admin")) {
                resp.sendRedirect("users");
            } else {
                resp.sendRedirect("user-profile");
            }
        }
    }

    private String uploadToS3(Part part, String accessKeyId, String secretAccessKey, String region, String bucketName, String fileObjKeyName, String fileToUpload) {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();

//                    https://stackoverflow.com/questions/21487066/get-urllink-of-a-public-s3-object-programmatically
//                   https://stackoverflow.com/questions/54610830/how-come-doesobjectexist-and-listobjects-do-not-agree-on-s3s-java-api
        if (s3Client.doesObjectExist(bucketName, fileObjKeyName)) {
//                        https://stackoverflow.com/questions/7763239/delete-files-directories-and-buckets-in-amazon-s3-java
            s3Client.deleteObject(new DeleteObjectRequest(bucketName, fileObjKeyName));

        }
        PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileToUpload));
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(part.getContentType());
        request.setMetadata(metadata);
//                    s3Client.putObject(request);/

        //Make it public so we can use it as a public URL on the internet
        request.setCannedAcl(CannedAccessControlList.PublicRead);

        //Upload the file. This can take a while for big files!
        PutObjectResult result = s3Client.putObject(request);
        log.debug("S3 Result: " + result.getContentMd5());
        //Create a URL using the bucket, subdirectory, and file name
        return "https://" + bucketName + ".s3." + Regions.US_EAST_2.getName() + ".amazonaws.com/" + fileObjKeyName;
    }

    private String extractFileName(String saveImagePath, Part part) throws IOException {
        String fileName = JavaHelperMethods.extractFileName(part);
        fileName = new File(fileName).getName();
        part.write(saveImagePath + File.separator + fileName);
        return fileName;
    }
}
