package com.streammedia.controller;
import com.streammedia.restapi.*;
import com.streammedia.perisistence.APIMovieDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * The type Top rated movies.
 */
@WebServlet(
        urlPatterns = {"/top-rated-movies"}
)
public class TopRatedMovies extends HttpServlet {
    private APIMovieDao popularMovies;

    /**
     * Init.
     * Responsible to create an instance of dao.
     * @throws ServletException the servlet exception
     */
    @Override
    public void init() throws ServletException {
        popularMovies = new APIMovieDao();
    }

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
        List<ResultsItem> items = new ArrayList<>();
        for (APIMoviesDB item : popularMovies.getTopRatedMovies()) {
            for (ResultsItem movie : item.getResults()) {
                items.add(movie);
            }
        }
        req.setAttribute("movies", items);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/film/topRatedMovies.jsp");
        dispatcher.forward(req, resp);
    }
}
