package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * The type Crew details.
 * Responsible for getting a single Trailer from and setting it as an attribute to be accessible in JSP
 * @author Jeanne
 * @version 1.0
 * @since 05-05-2020
 */
@WebServlet(
        urlPatterns = {"/crew-details"}
)
public class CrewDetails extends HttpServlet {

    private GenericDao crewDao;
    private GenericDao userDao;

    @Override
    public void init() throws ServletException {
        crewDao =  new GenericDao(Crew.class);
        userDao = new GenericDao(User.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("uid"));
        Crew crew = (Crew)crewDao.getById(id);
        req.setAttribute("crew",crew);
        req.getRequestDispatcher("/film/crewDetails.jsp").forward(req,resp);
    }
}
