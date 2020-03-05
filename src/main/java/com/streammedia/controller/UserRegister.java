package com.streammedia.controller;
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

/**
 * The type Register user.
 * Simple servlet to get form data
 * @author Jeanne
 * @version 1.0
 */
@Log4j2
@WebServlet(
        name = "signUp",
        urlPatterns = {"/register"}
)
public class UserRegister extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GenericDao genericDao;

    public void init() {
        genericDao = new GenericDao(User.class);
    }
    /**
     *  Handles HTTP GET requests.
     *
     *@param  request               Description of the Parameter
     *@param  response              Description of the Parameter
     *@exception  ServletException  if there is a Servlet failure
     *@exception  IOException       if there is an IO failure
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url ="/account/signup.jsp";
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request,response);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setEmail(req.getParameter("email"));
        user.setFirstName(req.getParameter("firstname"));
        user.setLastName(req.getParameter("lastname"));
        user.setPassword(req.getParameter("password"));
        String confirmPassword = req.getParameter("confirmPassword");

        if(confirmPassword.equals(user.getPassword())){

            log.debug("Adding User: " + user);
        Role role = new Role();
        role.setUser(user);
        role.setName("user");
        user.addRole(role);
            genericDao.insert(user);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/account/userSuccess.jsp");
            dispatcher.forward(req, resp);
        } else {
            req.getRequestDispatcher("/account/signup.jsp").forward(req,resp);
        }
    }
}
