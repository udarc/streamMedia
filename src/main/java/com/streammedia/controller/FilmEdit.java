package com.streammedia.controller;

import com.streammedia.entity.*;
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
import java.util.List;
import java.util.Set;

/**
 * The type Film edit.
 * Responsible for getting form data to update Film's record.
 * @author Jeanne
 * @version 1.0
 * @since 05-05-2020
 */
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
            List<Genre> genreList = genreDao.getAll();
            List<Genre> crewList = crewDao.getAll();
            req.setAttribute("genres", genreList);
            req.setAttribute("crews", crewList);
            req.setAttribute("film",film);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/film/filmAddEdit.jsp");
            dispatcher.forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)userDao.getByPropertyEqual("username",req.getRemoteUser()).get(0);
        Film filmToEdit = (Film) filmDao.getById(Integer.parseInt(req.getParameter("uid")));
        filmToEdit.setTitle(req.getParameter("title"));
        filmToEdit.setDirector(req.getParameter("director"));
        filmToEdit.setDuration(LocalTime.parse(req.getParameter("duration")));
        filmToEdit.setCover(req.getParameter("cover"));
        filmToEdit.setPublicationDate(LocalDateTime.parse(req.getParameter("pub_date").trim()));
        filmToEdit.setVideo(req.getParameter("video"));
        filmToEdit.setEpisode(req.getParameter("episode"));
        filmToEdit.setLink(req.getParameter("link"));
        filmToEdit.setSummary(req.getParameter("summary"));
        Set<Genre> genreList = filmToEdit.getGenres();
        Set<Crew> crewList = filmToEdit.getCrews();
        String[] genreIds = req.getParameterValues("genre");
        String[] crewIds = req.getParameterValues("crew");
        if(genreIds != null && crewIds != null ){
            genreList.clear();
            crewList.clear();
            retrieveCrews(crewList, crewIds);
            retrieveGenres(genreList,genreIds);
        } else if(genreIds == null && crewIds != null){
            crewList.clear();
            retrieveCrews(crewList, crewIds);
            for (Genre genre: genreList) {
                filmToEdit.addGenre(genre);
            }
        } else if(genreIds != null && crewIds == null){
            genreList.clear();
            retrieveGenres(genreList, genreIds);
            for (Crew crew: crewList) {
                filmToEdit.addCrew(crew);
            }
        } else {
            for (Crew crew: crewList) {
                filmToEdit.addCrew(crew);
            }
            for (Genre genre: genreList) {
                filmToEdit.addGenre(genre);
            }
        }
//        filmToEdit.setGenres(filmToEdit.getGenres());
//        filmToEdit.setCrews(filmToEdit.getCrews());
        if (req.isUserInRole("admin")){
            log.error(filmToEdit);
            filmDao.saveOrUpdate(filmToEdit);
            String successMessage = "Successfully updated " + Film.class.getSimpleName() ;
            req.getSession().setAttribute("filmEditSuccessMessage",successMessage);
            resp.sendRedirect("film-details?uid=" + filmToEdit.getFilmId());
        } else{
        log.error(filmToEdit);
        req.getSession().setAttribute("filmErrorMessage","Failed to update " + Film.class.getSimpleName());
        req.getRequestDispatcher("/film/filmAddEdit.jsp").forward(req,resp);
        }

    }
    private void retrieveGenres(Set<Genre> genreList, String[] genreIds) {
        for (String id: genreIds ) {
            genreList.add((Genre)genreDao.getById(Integer.parseInt(id)));
        }
    }

    private void retrieveCrews(Set<Crew> crewList, String[] crewIds) {
        for (String id: crewIds ) {
            crewList.add((Crew)crewDao.getById(Integer.parseInt(id)));
        }
    }
}
