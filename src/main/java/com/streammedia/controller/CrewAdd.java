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

@WebServlet(
        urlPatterns = {"/crew-new"}
)
@Log4j2
public class CrewAdd extends HttpServlet {

    private GenericDao crewDao;
    private GenericDao userDao;

    @Override
    public void init() throws ServletException {
        crewDao =  new GenericDao(Crew.class);
        userDao = new GenericDao(User.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/film/crewAddEdit.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) userDao.getById(2);
        Crew newCrew =  new Crew();
        newCrew.setUser(user);
        newCrew.setFirstName(req.getParameter("firstname").trim());
        newCrew.setLastName(req.getParameter("lastname").trim());
        newCrew.setEmail(req.getParameter("email").trim());
        newCrew.setProfession(req.getParameter("profession").trim());
        newCrew.setBiography(req.getParameter("biography").trim());
        log.debug("Crew" + newCrew.getUser());
        if(!newCrew.equals(null)){
            crewDao.insert(newCrew);
            resp.sendRedirect("crews");
        } else {
            log.debug("Error " + newCrew.getBiography());
            req.getRequestDispatcher("/film/crewAddEdit.jsp").forward(req,resp);
        }

    }
}
