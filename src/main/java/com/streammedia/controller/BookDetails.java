package com.streammedia.controller;

import com.streammedia.entity.Crew;
import com.streammedia.entity.Film;
import com.streammedia.entity.Genre;
import com.streammedia.entity.User;
import com.streammedia.perisistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/book-details"}
)
public class BookDetails extends HttpServlet {
    private GenericDao bookDao;
    private GenericDao userDao;
    private GenericDao catDao;
    @Override
    public void init() throws ServletException {
        bookDao = new GenericDao(Film.class);
        userDao = new GenericDao(User.class);
        catDao = new GenericDao(Crew.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
