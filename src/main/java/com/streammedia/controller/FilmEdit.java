package com.streammedia.controller;

import com.streammedia.entity.Crew;
import com.streammedia.entity.Film;
import com.streammedia.entity.Genre;
import com.streammedia.entity.User;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@WebServlet(
        urlPatterns = {"/film-edit"}
)
@Log4j2
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
        if (req.isUserInRole("admin")) {
            int id = Integer.parseInt(req.getParameter("uid"));
            Film film = (Film)filmDao.getById(id);
            req.setAttribute("film",film);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/film/filmAddEdit.jsp");
            dispatcher.forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)userDao.getByPropertyEqual("username",req.getRemoteUser()).get(0);
        Film filmToEdit = (Film) filmDao.getById(Integer.parseInt(req.getParameter("uuid")));
        filmToEdit.setTitle(req.getParameter("title"));
        filmToEdit.setDirector(req.getParameter("director"));
        filmToEdit.setDuration(LocalTime.parse(req.getParameter("duration")));
        filmToEdit.setCover(req.getParameter("cover"));
        filmToEdit.setPublicationDate(LocalDateTime.parse(req.getParameter("pub_date").trim()));
        filmToEdit.setVideo(req.getParameter("video"));
        filmToEdit.setEpisode(req.getParameter("episode"));
        filmToEdit.setLink(req.getParameter("link"));
        filmToEdit.setSummary(req.getParameter("summary"));
//        Genre newGenre = (Genre)genreDao.getById(3);
//        Set<Genre> genres =  filmToEdit.getGenres();
//        System.out.println(genres.size());
////        genres.clear();
//        genres.add(newGenre);
        if (req.isUserInRole("admin")){
            filmDao.saveOrUpdate(filmToEdit);
            resp.sendRedirect("films");
        }
        req.getRequestDispatcher("/film/filmAddEdit.jsp").forward(req,resp);

    }

}
