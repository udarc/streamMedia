package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Film details.
 */
@WebServlet(
        urlPatterns = {"/film-details"}
)
@Log4j2
public class FilmDetails extends HttpServlet {
    private GenericDao filmDao;

    /**
     * Init.
     * Responsible to create an instance of dao.
     * @throws ServletException the servlet exception
     */
    @Override
    public void init() throws ServletException {
        filmDao = new GenericDao(Film.class);
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
        try {
            int id = Integer.parseInt(req.getParameter("uid"));
            Film film = (Film)filmDao.getById(id);
            req.setAttribute("film",film);
            req.getRequestDispatcher("/film/filmDetails.jsp").forward(req,resp);
        } catch (NumberFormatException nfe){
            log.error(nfe);
        } catch (NullPointerException npe){
            log.error(npe);
        } catch (Exception exception){
            log.error(exception);
        }

    }
}
