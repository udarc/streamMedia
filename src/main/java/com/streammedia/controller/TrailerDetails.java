package com.streammedia.controller;
import com.streammedia.entity.Trailer;
import com.streammedia.entity.User;
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
 * The type Trailer details.
 * @author Jeanne
 */
@Log4j2
@WebServlet(
        name = "trailerDetails",
        urlPatterns = {"/trailer-detail"})
public class TrailerDetails extends HttpServlet {
    /**
     * The Generic dao.
     */
//    private UserDao userDao;
    GenericDao genericDao;

    public void init() {

//        userDao = new UserDao();
        genericDao =  new GenericDao(Trailer.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Trailer trailer= (Trailer)genericDao.getById(Integer.parseInt(request.getParameter("uid")));
        log.debug("Getting a Single Trailer servlet" + trailer);
        request.setAttribute("trailer", trailer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/trailer/trailerDetails.jsp");
        dispatcher.forward(request, response);
    }
}


