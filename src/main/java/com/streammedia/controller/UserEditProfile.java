package com.streammedia.controller;
//TODO https://www.javatpoint.com/crud-in-servlet
import com.streammedia.entity.User;
import com.streammedia.perisistence.GenericDao;
import com.streammedia.perisistence.UserDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Edit user profile.
 */
@Log4j2
@WebServlet(
        name = "userEdit",
        urlPatterns = {"/profile-edit"}
)
public class UserEditProfile extends HttpServlet {
        private GenericDao genericDao;

        public void init() {
            genericDao = new GenericDao(User.class);
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
            request.setAttribute("user",genericDao.getById(Integer.parseInt(request.getParameter("id"))));
//            request.setAttribute("user",genericDao.getById(1));
            String url ="/account/editUserProfile.jsp";
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
            User user = new User();
            int userId = Integer.valueOf(req.getParameter("id"));
            user = (User)genericDao.getById(userId);
            user.setEmail(req.getParameter("email"));
            user.setUsername(req.getParameter("username"));
            user.setFirstName(req.getParameter("firstName"));
            user.setLastName(req.getParameter("lastName"));
//
            user.setBirthdate(LocalDate.parse(req.getParameter("birthday")));
            user.setGender(req.getParameter("gender"));
            user.setPicture(req.getParameter("profilePicture"));
            user.setBiography(req.getParameter("biography"));
            user.setUpdateAt(LocalDate.now());
            log.debug("Updating User: " + user);
            genericDao.saveOrUpdate(user);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/account/userSuccess.jsp");
                dispatcher.forward(req, resp);
        }
}
