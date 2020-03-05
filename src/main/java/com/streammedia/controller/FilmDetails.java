package com.streammedia.controller;

import com.streammedia.entity.Crew;
import com.streammedia.entity.Film;
import com.streammedia.entity.Genre;
import com.streammedia.entity.User;
import com.streammedia.perisistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class FilmDetails extends HttpServlet {
    private GenericDao filmDao;
    private GenericDao userDao;
    private GenericDao crewDao;
    private GenericDao genreDao;
    @Override
    public void init() throws ServletException {
        filmDao = new GenericDao(Film.class);
        userDao = new GenericDao(User.class);
        crewDao = new GenericDao(Crew.class);
        genreDao = new GenericDao(Genre.class);
    }

}
