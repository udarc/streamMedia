package com.streammedia.controller;
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The type Trailer list.
 * Gets all the trailers and makes them available in JSP
 * Sort List Options: https://www.codebyamir.com/blog/sort-list-of-objects-by-field-java
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

//        List<List<Trailer>> trailerList = genericDao.getAllWithPagination();
        List<Trailer> trailerList = genericDao.getAll();
        trailerList.sort(Comparator.comparing(Trailer::getTrailerId).reversed());
//        List<Trailer> sortedTrailers = trailerList.stream()
//                .sorted(Comparator.comparing(Trailer::getTrailerId).reversed())
//                .collect(Collectors.toList());
//        Collections.sort(trailerList, Collections.reverseOrder());
//        List<Trailer> listTrailer= genericDao.getAllWithPagination();
        log.debug("Getting All Trailers servlet" + trailerList);
        request.setAttribute("trailers", trailerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/trailers/trailerList.jsp");
        dispatcher.forward(request, response);
    }
}
