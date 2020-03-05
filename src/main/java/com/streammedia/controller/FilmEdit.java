package com.streammedia.controller;

import com.streammedia.entity.Crew;
import com.streammedia.entity.Film;
import com.streammedia.entity.Genre;
import com.streammedia.entity.User;
import com.streammedia.perisistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/film-edit"}
)
public class FilmEdit extends HttpServlet {
    private GenericDao filmDao;
    private  GenericDao userDao;
    private GenericDao crewDao;
    private  GenericDao genreDao;

    @Override
    public void init() throws ServletException {
        filmDao = new GenericDao(Film.class);
        userDao = new GenericDao(User.class);
        crewDao = new GenericDao(Crew.class);
        genreDao = new GenericDao(Genre.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("uid"));
        Film film = (Film)filmDao.getById(id);
        req.setAttribute("film",film);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/film/filmAddEdit.jsp");
        dispatcher.forward(req,resp);
    }

}
