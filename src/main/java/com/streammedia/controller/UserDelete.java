package com.streammedia.controller;


import com.streammedia.perisistence.GenericDao;
import com.streammedia.entity.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Delete the user
 * https://www.javatpoint.com/crud-in-servlet
 * @author Jeanne
 */
@WebServlet(
        urlPatterns = {"/deleteUser"}
)

public class UserDelete extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao genericDao = new GenericDao(User.class);
        genericDao.delete(genericDao.getById(Integer.parseInt(req.getParameter("id"))));
        resp.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

