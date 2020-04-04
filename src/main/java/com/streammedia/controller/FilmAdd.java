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
import java.time.*;
import java.util.*;

/**
 * The type Film add.
 * https://dzone.com/tutorials/java/hibernate/hibernate-example/hibernate-mapping-many-to-many-using-annotations-1.html
 * http://websystique.com/hibernate/hibernate-many-to-many-bidirectional-annotation-example/
 */
@WebServlet(
        urlPatterns = {"/film-new"}
)
@Log4j2
public class FilmAdd extends HttpServlet {
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
        List<Crew> crewList = crewDao.getAll();
        req.setAttribute("crews",crewList );
        List<Genre> genreList = genreDao.getAll();
        req.setAttribute("genres",genreList );
        RequestDispatcher dispatcher = req.getRequestDispatcher("/film/filmAddEdit.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) userDao.getByPropertyEqual("username", req.getRemoteUser()).get(0);
        Film newFilm = new Film();
        Set<Crew> crewList = new HashSet<>();
        Set<Genre> genreList = new HashSet<>();
        Set<Film> films = new HashSet<Film>();
        String[] genreIds = req.getParameterValues("genre");
        String[] crewIds = req.getParameterValues("crew");

        try {

            retrieveGenres(genreList, genreIds);
            retrieveCrews(crewList, crewIds);
            newFilm.setUser(user);
            log.debug("Genres Size: " + genreList.size());
            log.debug("Crew Size " + crewList.size());
            newFilm.setTitle(req.getParameter("title"));
            newFilm.setDirector(req.getParameter("director"));
            log.debug(newFilm.getDirector());
            // TODO create duration format
            newFilm.setDuration(LocalTime.parse(req.getParameter("duration")));
            log.debug(newFilm.getDuration());
            newFilm.setCover(req.getParameter("cover"));
            String pubDate = req.getParameter("pub_date").trim();
            if( pubDate == null || pubDate.length() <= 0 ) {
                log.debug(newFilm.getPublicationDate());
                newFilm.setPublicationDate(LocalDateTime.now());
            }
            else {
                newFilm.setPublicationDate(LocalDateTime.parse(pubDate));
            }
            // TODO Check if duration is embedded in metadata
            newFilm.setVideo(req.getParameter("video"));
            newFilm.setEpisode(req.getParameter("episode"));
            newFilm.setLink(req.getParameter("link"));
            newFilm.setSummary(req.getParameter("summary"));
            log.debug("Title of th  Film: " + newFilm.getTitle());
            for (Genre genre: genreList) {
                newFilm.getGenres().add(genre);
            }
            for (Crew crew: crewList) {
                newFilm.getCrews().add(crew);
            }
            films.add(newFilm);
            log.debug("Servlet Film " + newFilm.getDuration());
            if(!newFilm.equals(null)){
                filmDao.insert(newFilm);
                resp.sendRedirect("films");
            } else {
                req.getRequestDispatcher("/film/filmAddEdit.jsp").forward(req, resp);
            }
        } catch (NullPointerException npe) {
            log.error("User Does not Exists" + npe);
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
