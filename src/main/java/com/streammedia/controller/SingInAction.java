package com.streammedia.controller;
import lombok.extern.log4j.Log4j2;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * The type Sing in action.
 * A simple servlet whose purpose is to redirect
 * after a log in attempt
 * https://stackoverflow.com/questions/1531197/how-do-i-redirect-to-the-current-page-in-servlet-filter
 *
 * @author Jeanne
 * @version 1.0
 * @since 2020 -03-02
 */
@WebServlet(
        urlPatterns = {"/login"}
)
@Log4j2
public class SingInAction extends HttpServlet {

    /**
     * Do get.
     *
     * @param req  the req
     * @param resp the resp
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(resp.getStatus() == 200) {
            String successMessage = "You are logged in successfully!" ;
            req.getSession().setAttribute("loginSuccess",successMessage);
            resp.sendRedirect("user-profile");
        }
    }
}
