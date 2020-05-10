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
import java.util.List;


/**
 * BookList.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user to perform CRUD operations.
 * https://www.javaguides.net/2019/03/jsp-servlet-hibernate-crud-example.html
 *
 * @author Jeanne
 * @version 1.0
 * @since 2020 -03-03
 */
@Log4j2
@WebServlet("/short-stories")
public class ShortStoryList extends HttpServlet {
    /**
     * The Story dao.
     */
    GenericDao storyDao;
    /**
     * The User dao.
     */
    GenericDao userDao;

    /**
     * Init.
     * Responsible to create an instance of dao.
     */
    public void init() {
        userDao =  new GenericDao(User.class);
        storyDao =  new GenericDao(ShortStory.class);
    }

    /**
     * Do post.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Do get.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String action = request.getServletPath();
        List<ShortStory> stories =  storyDao.getAll();
        request.setAttribute("stories",stories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/stories/storyList.jsp");
        dispatcher.forward(request, response);

    }
}

