package com.streammedia.controller;

import com.streammedia.entity.Genre;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Genre delete.
 * Get form data of selected object and calls dao method to remove it.
 * @author Jeanne
 * @version 1.0
 * @since 05-05-2020
 */
@WebServlet(
        urlPatterns = {"/genre-delete"}
)
@Log4j2
public class GenreDelete extends HttpServlet {
    private GenericDao genreDao;

    @Override
    public void init() throws ServletException {
        genreDao = new GenericDao(Genre.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Genre genre = (Genre) genreDao.getById(Integer.parseInt(req.getParameter("uid")));
        genreDao.delete(genre);

        resp.sendRedirect("genres");
    }
}
