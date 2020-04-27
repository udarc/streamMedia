package com.streammedia.controller;
//TODO https://www.javatpoint.com/crud-in-servlet

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
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

/**
 * The type Edit user profile.
 * Responsible for getting form data to update User's record
 * upload an image https://www.youtube.com/watch?v=WtZm1bB6T-8
 *
 * @author Jeanne
 * @version 1.0
 */
@Log4j2
@WebServlet(
        name = "userEdit",
        urlPatterns = {"/profile-edit"}
)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1MB
        maxFileSize = 1024 * 1024 * 2,      // 3MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class UserEditProfile extends HttpServlet {
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

        String username = req.getParameter("user");
        log.debug(" User EDIt Username Value : " + username);
        User user = (User) genericDao.getByPropertyEqual("username", username).get(0);
        if ((req.isUserInRole("admin") || req.isUserInRole("user"))
                && username.equals(user.getUsername())) {
                       user.setEmail(req.getParameter("email"));
            user.setUsername(username);
            user.setFirstName(req.getParameter("firstName"));
            user.setLastName(req.getParameter("lastName"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String birthdate = req.getParameter("birthday");
            if (birthdate == null || birthdate == "") {
                birthdate = LocalDate.now().toString();
                user.setBirthdate(LocalDate.parse(birthdate));

            } else {

                user.setBirthdate(LocalDate.parse(birthdate, formatter));
            }
            user.setGender(req.getParameter("gender"));
            Part part = req.getPart("profilePicture");
            log.debug("Test Part: " + part.getSubmittedFileName());
            if (part.getSubmittedFileName().isEmpty() && user.getPicture().isEmpty()) {
                user.setPicture("images/profile.png");
            } else if(part.getSubmittedFileName().isEmpty() && !user.getPicture().isEmpty()){
                user.setPicture(user.getPicture());
            } else {
                String saveImagePath = JavaHelperMethods.createUserImagePath(appPath, username).replace("//", "/");
                String saveImage = JavaHelperMethods.deleteAndCreateFilePath(rootPath, username).replace("//", "/");
                String targetPath = JavaHelperMethods.saveFileName(saveImagePath, part);
                String projectPath = JavaHelperMethods.saveFileName(saveImage, part);
                user.setPicture(projectPath.substring(55, projectPath.length()));
            }

            user.setBiography(req.getParameter("biography"));
            log.error(user);
            log.debug("Updating User: " + user);
            genericDao.saveOrUpdate(user);
            if (req.isUserInRole("admin")) {
                resp.sendRedirect("users");
            } else {
                resp.sendRedirect("user-profile");
            }
        }
    }

    private String extractFileName(String saveImagePath, Part part) throws IOException {
        String fileName = JavaHelperMethods.extractFileName(part);
        fileName = new File(fileName).getName();
        part.write(saveImagePath + File.separator + fileName);
        return fileName;
    }
}
