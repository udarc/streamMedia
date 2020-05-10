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
 * The type Crew delete.
 * Get form data of selected object and calls dao method to remove it.
 *
 * @author Jeanne
 * @version 1.0
 * @since 05 -05-2020
 */
@WebServlet(
        urlPatterns = {"/crew-delete"}
)
public class CrewDelete extends HttpServlet {
    private GenericDao crewDao;
    private GenericDao userDao;

    /**
     * Init.
     * Responsible to create an instance of dao.
     * @throws ServletException the servlet exception
     */
    @Override
    public void init() throws ServletException {
        crewDao = new GenericDao(Crew.class);
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
        User user = (User)userDao.getByPropertyEqual("username",req.getRemoteUser());
        int id = Integer.parseInt(req.getParameter("uid"));
        Crew crew = (Crew) crewDao.getById(id);
        if(crew.getUser().getUserId() == user.getUserId()){
            crewDao.delete(crew);
            resp.sendRedirect("crews");
        }
        //TODO add else message
    }
}
