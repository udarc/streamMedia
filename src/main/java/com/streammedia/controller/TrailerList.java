package com.streammedia.controller;
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


/**
 * The type Trailer list.
 * Gets all the trailers and makes them available in JSP
 * @author Jeanne
 * @version 1.0
 * @since 2020-02-19
 */
@Log4j2
@WebServlet(
        name = "trailerList",
        urlPatterns = {"/trailers"})
public class TrailerList extends HttpServlet {
    /**
     * The Generic dao.
     */
    GenericDao genericDao;

    public void init() {

        genericDao =  new GenericDao(Trailer.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Trailer> listTrailer= genericDao.getAll();
        log.debug("Getting All Trailers servlet" + listTrailer);
        request.setAttribute("trailers", listTrailer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/trailer/trailerList.jsp");
        dispatcher.forward(request, response);
    }
}
