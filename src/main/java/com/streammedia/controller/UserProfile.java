package com.streammedia.controller;

import com.streammedia.perisistence.GenericDao;
import com.streammedia.entity.*;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * The type User profile.
 *
 * @author Jeanne
 * @version 1.0
 * @since 2020 -03-03
 */
@WebServlet(
        name = "userProfile",
        urlPatterns = {"/user-profile"}
)
@Log4j2
public class UserProfile extends HttpServlet {
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

        User user = (User) genericDao.getByPropertyEqual("username", request.getRemoteUser()).get(0);
        log.debug("Remote User: " +  request.getRemoteUser());
        request.getSession().setAttribute("profileImage", user.getPicture());
        request.setAttribute("user",user);
        String url ="/account/profile.jsp";
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request,response);

    }

}
