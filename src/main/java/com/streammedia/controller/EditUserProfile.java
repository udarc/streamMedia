package com.streammedia.controller;

import com.streammedia.entity.User;
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

@Log4j2
@WebServlet(
        name = "userEdit",
        urlPatterns = {"/profile-edit"}
)
public class EditUserProfile extends HttpServlet {
        private UserDao userDao;

        public void init() {
            userDao = new UserDao();
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
            user.setUsername(req.getParameter("username"));
            user.setEmail(req.getParameter("email"));
            user.setFirstName(req.getParameter("firstName"));
            user.setLastName(req.getParameter("lastName"));
            user.setPassword(req.getParameter("password"));
            String confirmPassword = req.getParameter("confirmPassword");
            user.setBirthdate(LocalDate.parse(req.getParameter("birthday")));
            user.setGender(req.getParameter("gender"));
            user.setPicture(req.getParameter("profilePicture"));
            user.setBiography(req.getParameter("biography"));
            user.setUpdateAt(LocalDate.now());
            if(confirmPassword.equals(user.getPassword())){
                log.debug("Updating User: " + user);
//        Role role = new Role();
//        role.setUser(user);
//        role.setRole("user");
//        user.addRole(role);
                userDao.insert(user);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/account/userSuccess.jsp");
                dispatcher.forward(req, resp);
            } else {
                req.getRequestDispatcher("/account/editUserProfile.jsp");
            }
        }
}
