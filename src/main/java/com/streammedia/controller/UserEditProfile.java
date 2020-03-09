package com.streammedia.controller;
//TODO https://www.javatpoint.com/crud-in-servlet
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

/**
 * The type Edit user profile.
 * Responsible for getting form data to update User's record
 * @author Jeanne
 * @version 1.0
 */
@Log4j2
@WebServlet(
        name = "userEdit",
        urlPatterns = {"/profile-edit"}
)
public class UserEditProfile extends HttpServlet {
    private GenericDao  genericDao;

    public void init() {
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
        request.setAttribute("user", genericDao.getByPropertyEqual("username",username).get(0));
//        int userId = Integer.valueOf(request.getParameter("id"));
//        request.setAttribute("user", genericDao.getById(userId));
//        session.setAttribute("user", genericDao.getById(userId));
//            request.setAttribute("user",genericDao.getById(1));
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

//        Enumeration<String> testUsersEdit = req.getParameterNames();

//        int userId = Integer.valueOf(req.getParameter("id"));
        String username = req.getParameter("user");
        User user = (User)genericDao.getByPropertyEqual("username",username).get(0);

//        int userId = 1;
//        int userId = user.getUserId();
        log.error("Value of Get Parameter"+ username);
//        user = (User) genericDao.getById(Integer.valueOf(req.getParameter("id")));

        user.setEmail(req.getParameter("email"));
        user.setUsername(req.getParameter("username"));
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String birthdate = req.getParameter("birthday");
        log.debug("Test for getting birth date" +  birthdate);
        user.setBirthdate(LocalDate.parse(birthdate,formatter));
        user.setGender(req.getParameter("gender"));
        user.setPicture(req.getParameter("profilePicture"));
        user.setBiography(req.getParameter("biography"));
        user.setUpdateAt(LocalDate.now());
        log.debug("Updating User: " + user);
        genericDao.saveOrUpdate(user);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/account/listUser.jsp");
//        dispatcher.forward(req, resp);
        resp.sendRedirect("users");
    }
}
