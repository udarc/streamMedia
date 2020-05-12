package com.streammedia.controller;
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

import org.apache.catalina.realm.MessageDigestCredentialHandler;
import org.apache.catalina.CredentialHandler;


/**
 * The type Register user.
 * Simple servlet to get form data nad call dao method to save changes.
 *
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

    /**
     * Init.
     * Responsible to create an instance of dao.
     */
    public void init() {
        genericDao = new GenericDao(User.class);
    }

    /**
     * Handles HTTP GET requests.
     *
     * @param request  Description of the Parameter
     * @param response Description of the Parameter
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url ="/account/signup.jsp";
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request,response);

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        MessageDigestCredentialHandler credentialHandler = new MessageDigestCredentialHandler();
        try {
            credentialHandler.setAlgorithm("sha-256");
        } catch (NoSuchAlgorithmException e) {
            log.error(e);
        }
        credentialHandler.setEncoding("UTF-8");
        String password = req.getParameter("password");
        user.setUsername(req.getParameter("username"));
        user.setEmail(req.getParameter("email"));
        user.setFirstName(req.getParameter("firstname"));
        user.setLastName(req.getParameter("lastname"));

        String confirmPassword = req.getParameter("confirmPassword");

        if(confirmPassword.equals(password)){
            String hashedPassword = credentialHandler.mutate(password);
            user.setPassword(hashedPassword);
            log.debug("Adding User: " + user);
        Role role = new Role();
        role.setUser(user);
        if(req.isUserInRole("admin")) {
            String userRole = req.getParameter("userRole");
            role.setName(userRole);
        } else {
            role.setName("user");
        }
        user.addRole(role);
            genericDao.insert(user);
            String successMessage = "Successfully registered with " + user.getUsername()
                    + "username!Please Login!";
            req.getSession().setAttribute("registerSuccess",successMessage);
            resp.sendRedirect("user-profile");
        } else {
            req.getSession().setAttribute("errorMessage", "Failed to create an account.Try again!");
            req.getRequestDispatcher("/account/signup.jsp").forward(req,resp);
        }
    }
}
