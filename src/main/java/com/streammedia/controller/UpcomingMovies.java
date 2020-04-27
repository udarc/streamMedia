package com.streammedia.controller;
import com.streammedia.RestApi.*;
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
    private APIMovieDao popularMovies;

    @Override
    public void init() throws ServletException {
        popularMovies = new APIMovieDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ResultsItem> items = new ArrayList<>();
        for (PopularMovies item : popularMovies.getUpcomingMovies()) {
            for (ResultsItem movie : item.getResults()) {
                items.add(movie);
            }
        }
        req.setAttribute("upcomingMovies", items);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/film/upcomingMovies.jsp");
        dispatcher.forward(req, resp);
    }
}
