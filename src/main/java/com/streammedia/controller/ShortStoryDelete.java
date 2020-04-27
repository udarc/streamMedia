package com.streammedia.controller;

import com.streammedia.entity.Genre;
import com.streammedia.entity.ShortStory;
import com.streammedia.entity.Trailer;
import com.streammedia.entity.User;
import com.streammedia.perisistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * The type User delete.
 * Responsible for getting and deleting Short Story Object
 * @author Jeanne
 * @version 1.0
 * @since 2020-04-24
 */
@WebServlet(
        urlPatterns = {"/story-delete"}
)
public class ShortStoryDelete extends HttpServlet {
    private GenericDao storyDao ;
    @Override
    public void init () {
        storyDao = new GenericDao(
                ShortStory.class);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.isUserInRole("admin")){
            storyDao.delete(storyDao.getById(Integer.parseInt(req.getParameter("uid"))));
            resp.sendRedirect("short-stories");
        }
        else {
            req.setAttribute("errorMessage","Short Story was not delete. Tray again.");
        }

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}