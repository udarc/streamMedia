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

/**
 * The type Short story details.
 * Responsible for handling all
 * requests from the user to display Short Story Details.
* @author Jeanne
* @version 1.0
* @since 2020-04-24
*/
@Log4j2
@WebServlet(
        name = "storyDetails",
        urlPatterns = {"/story-details"})
public class ShortStoryDetails extends HttpServlet {
    /**
     * The Generic dao.
     */

    GenericDao storyDao;

    public void init() {
        storyDao = new GenericDao(ShortStory.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShortStory story = (ShortStory) storyDao.getById(Integer.parseInt(request.getParameter("uid")));
        log.debug("Getting a Single Story servlet" + story);
        request.setAttribute("story", story);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/stories/storyDetails.jsp");
        dispatcher.forward(request, response);
    }
}