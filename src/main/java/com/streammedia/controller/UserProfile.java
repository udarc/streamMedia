package com.streammedia.controller;

import com.streammedia.perisistence.GenericDao;
import com.streammedia.entity.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * The type User profile.
 * @author Jeanne
 * @version 1.0
 * @since 2020-03-03
 */
@WebServlet(
        name = "profile",
        urlPatterns = {"/user-profile"}
)
public class UserProfile extends HttpServlet {
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

        String url ="/account/profile.jsp";
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request,response);

    }

}
