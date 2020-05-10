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
 * The type Crew add.
 * Gets form data and calls dao to add data to database
 *
 * @author Jeanne
 * @version 1.0
 * @since 05-10-2020
 */
@WebServlet(
        urlPatterns = {"/crew-new"}
)
@Log4j2
public class CrewAdd extends HttpServlet {

    private GenericDao crewDao;
    private GenericDao userDao;

    /**
     * Init.
     * Responsible to create an instance of dao.
     * @throws ServletException the servlet exception
     */
    @Override
    public void init() throws ServletException {
        crewDao =  new GenericDao(Crew.class);
        userDao = new GenericDao(User.class);
    }

    /**
     * Do get.
     *
     * @param req  the req
     * @param resp the resp
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/film/crewAddEdit.jsp");
        dispatcher.forward(req,resp);
    }

    /**
     * Do post.
     *
     * @param req  the req
     * @param resp the resp
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Crew newCrew =  new Crew();
        User user = (User) userDao.getByPropertyEqual("username", req.getRemoteUser()).get(0);
        newCrew.setUser(user);
        log.debug("Username: " + user.getUsername());
        newCrew.setFirstName(req.getParameter("firstname").trim());
        newCrew.setLastName(req.getParameter("lastname").trim());
        newCrew.setEmail(req.getParameter("email").trim());
        newCrew.setProfession(req.getParameter("profession").trim());
        newCrew.setBiography(req.getParameter("biography").trim());
        log.debug("Crew adding: " + newCrew.getUser());
        if(!newCrew.equals(null) && req.getRemoteUser().equals(newCrew.getUser().getUsername())){
            crewDao.insert(newCrew);
            String successMessage = "Successfully added " + Crew.class.getSimpleName() ;
            req.getSession().setAttribute("crewAddSuccessMessage",successMessage);
            resp.sendRedirect("crews");
        } else {
            req.getSession().setAttribute("crewErrorMessage","Failed to add " + Crew.class.getSimpleName());
            log.debug("Error " + newCrew.getBiography());
            req.getRequestDispatcher("/film/crewAddEdit.jsp").forward(req,resp);
        }

    }
}
