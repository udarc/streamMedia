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

@WebServlet(
        urlPatterns = {"/upcoming-movies"}
)
public class UpcomingMovies extends HttpServlet {
    private APIMovieDao upcomingMovies;

    @Override
    public void init() throws ServletException {
        upcomingMovies = new APIMovieDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ResultsItem> items = new ArrayList<>();
        for (APIMoviesDB item : upcomingMovies.getPlayingMovies()) {
            for (ResultsItem movie : item.getResults()) {
                items.add(movie);
            }
        }
        req.setAttribute("movies", items);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/film/upcomingMovies.jsp");
        dispatcher.forward(req, resp);
    }
}
