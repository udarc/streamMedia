package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(
        urlPatterns = {"/book-delete"}
)
public class BookDelete extends HttpServlet {
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
