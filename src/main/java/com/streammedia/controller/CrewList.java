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
import java.util.List;

/**
 * The type Crew list.
 */
@WebServlet(
        urlPatterns = {"/crews"}
)
public class CrewList extends HttpServlet {

    private GenericDao crewDao;

    @Override
    public void init() throws ServletException {
        crewDao =  new GenericDao(Crew.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Crew> crewList =  crewDao.getAll();
        req.setAttribute("crews", crewList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/film/crewList.jsp");
        dispatcher.forward(req,resp);
    }
}
