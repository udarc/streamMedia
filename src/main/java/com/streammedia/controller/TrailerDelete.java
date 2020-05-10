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
 * The type Trailer delete.
 * Responsible for getting and  deleting Trailer Object.
 *
 * @author Jeanne
 * @version 1.0
 * @since 2020 -02-21
 */
@WebServlet(
            urlPatterns = {"/trailer-delete"}
    )
    public class TrailerDelete extends HttpServlet {
    /**
     * Do post.
     *
     * @param req  the req
     * @param resp the resp
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            GenericDao genericDao = new GenericDao(Trailer.class);
            genericDao.delete(genericDao.getById(Integer.parseInt(req.getParameter("uid"))));
            resp.sendRedirect("trailers");
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
            doPost(req, resp);
        }
    }

