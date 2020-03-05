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
        Film newFilm = new Film();
//        Set<Crew> crewList = new HashSet<>();
//        Set<Genre> genreList = new HashSet<>();

        String[] genreIds = req.getParameterValues("genre");
        String[] crewIds = req.getParameterValues("crew");


        try {
            User user = (User) userDao.getById(2);
            
            newFilm.setTitle(req.getParameter("title"));
            newFilm.setDuration(LocalDateTime.parse(req.getParameter("duration")));
            newFilm.setDirector(req.getParameter("description"));
            newFilm.setPublicationDate(LocalDate.parse(req.getParameter("pub_date")));
            newFilm.setEpisode(req.getParameter("episode"));
            newFilm.setLink(req.getParameter("link"));
            newFilm.setVideo(req.getParameter("video"));
            newFilm.setCover(req.getParameter("cover"));
            newFilm.setUser(user);

//            for (String id: genreIds ) {
//                newFilm.addGenre((Genre)genreDao.getById(Integer.parseInt(id)));
//                log.debug("genres Ids " + id);
//            }
//            for (String id: crewIds ) {
//                newFilm.addCrew((Crew)crewDao.getById(Integer.parseInt(id)));
//                log.debug("genres Ids " + id);
//            }
                log.debug("Adding Film: ", newFilm.getTitle());
                if(!newFilm.equals(null)){
                filmDao.insert(newFilm);
                resp.sendRedirect("films");
            } else {
                req.getRequestDispatcher("/film/filmAddEdit.jsp").forward(req, resp);
            }
        } catch (NullPointerException npe) {
            log.error("User Does not Exists" + npe);
        } catch (ServletException sevex) {
            log.error("Servlet Error" + sevex);
        }
    }
}
