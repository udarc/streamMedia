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
import java.util.List;

/**
 * The type Users.
 */

@Log4j2
@WebServlet(
        name = "trailerList",
        urlPatterns = {"/trailers"})
public class TrailerList extends HttpServlet {
//    private UserDao userDao;
    GenericDao genericDao;

    public void init() {

//        userDao = new UserDao();
        genericDao =  new GenericDao(Trailer.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Trailer> listTrailer= genericDao.getAll();
        log.debug("Getting All Users servlet" + listTrailer);
        request.setAttribute("trailers", listTrailer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/trailer/trailerList.jsp");
        dispatcher.forward(request, response);
    }


}
