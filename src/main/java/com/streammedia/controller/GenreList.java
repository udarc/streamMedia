package com.streammedia.controller;
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.sql.SQLException;
import java.time.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FAQController.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user to delete an object.
 * https://www.javaguides.net/2019/03/jsp-servlet-hibernate-crud-example.html
 * @author Jeanne
 * @version 1.0
 * @since 2020-02-21
 */
@Log4j2
@WebServlet(
        urlPatterns = {"/genres"})
public class GenreList extends HttpServlet {

    //        private static final long serialVersionUID = 1L;
    private GenericDao genreDao;
    private GenericDao userDao;

    public void init() {
        genreDao = new GenericDao(Genre.class);
        userDao = new GenericDao(User.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        List<Genre> genreList = genreDao.getAll();
//        request.setAttribute("genres", genreList);
//        log.info(genreList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/film/genreList.jsp");
        dispatcher.forward(request, response);
    }
}