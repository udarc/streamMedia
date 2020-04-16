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

/**
 * The type Trailer delete.
 *The servlet to delete Trailer and redirect to a specified url
 * @author Jeanne
 * @version 1.0
 * @since 2020-02-21
 */
@WebServlet(
            urlPatterns = {"/remove-trailer"}
    )
    public class TrailerDelete extends HttpServlet {
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            GenericDao genericDao = new GenericDao(Trailer.class);
            genericDao.delete(genericDao.getById(Integer.parseInt(req.getParameter("uid"))));
            resp.sendRedirect("trailers");
        }
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doPost(req, resp);
        }
    }

