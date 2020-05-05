package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * The type Film delete.
 * Get form data of selected object and calls dao method to remove it.
 * @author Jeanne
 * @version 1.0
 * @since 05-05-2020
 */
@WebServlet(
    name = "filmDelete",
        urlPatterns = {"/film-delete"}
            )
public class FilmDelete extends HttpServlet {
    private GenericDao filmDao;

    @Override
    public void init() throws ServletException {
        filmDao = new GenericDao(Film.class);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Film film = (Film) filmDao.getById(Integer.parseInt(req.getParameter("uid")));
        if (req.isUserInRole("admin")) {
            Set<Genre> genreList = film.getGenres();
            Set<Crew> crewList = film.getCrews();
            crewList.clear();
            genreList.clear();
            filmDao.delete(film);
            resp.sendRedirect("films");
        } else {
            resp.sendRedirect("film-details?uid=" + film.getFilmId());

        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
