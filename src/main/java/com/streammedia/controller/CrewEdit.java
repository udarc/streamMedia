package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/crew-edit"}
)
public class CrewEdit extends HttpServlet {
    private GenericDao crewDao;
//    private  GenericDao userDao;

    @Override
    public void init() throws ServletException {
        crewDao =  new GenericDao(Crew.class);
//        userDao =  new GenericDao(User.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int crewId = Integer.parseInt(req.getParameter("uid"));
        Crew crew = (Crew)crewDao.getById(crewId);
        req.setAttribute("crew", crew);
        req.getRequestDispatcher("/film/crewAddEdit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("uid"));
        Crew updateCrew = (Crew)crewDao.getById(id);
        updateCrew.setFirstName(req.getParameter("firstname").trim());
        updateCrew.setLastName(req.getParameter("lastname").trim());
        updateCrew.setEmail(req.getParameter("email").trim());
        updateCrew.setProfession(req.getParameter("profession").trim());
        updateCrew.setBiography(req.getParameter("biography").trim());
        if(updateCrew.getUser().getUsername().equals(req.getRemoteUser()) && req.isUserInRole("admin")) {
            crewDao.saveOrUpdate(updateCrew);
            resp.sendRedirect("crews");
        } else {
            req.getRequestDispatcher("/film/crewAddEdit.jsp").forward(req,resp);
        }
    }
}
