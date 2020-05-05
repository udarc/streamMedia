package com.streammedia.controller;
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * The type Trailer details.
 * Responsible for getting a single Trailer from and setting it as an attribute to be accessible in JSP
 * @author Jeanne
 * @version 1.0
 * @since 2020-02-21
 */
@Log4j2
@WebServlet(
        name = "trailerDetails",
        urlPatterns = {"/trailer-detail"})
public class TrailerDetails extends HttpServlet {
    /**
     * The Generic dao.
     */

    GenericDao genericDao;

    public void init() {
        genericDao =  new GenericDao(Trailer.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Trailer trailer= (Trailer)genericDao.getById(Integer.parseInt(request.getParameter("uid")));
        log.debug("Getting a Single Trailer servlet" + trailer);
        request.setAttribute("trailer", trailer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/trailers/trailerDetails.jsp");
        dispatcher.forward(request, response);
    }
}


