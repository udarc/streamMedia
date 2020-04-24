package com.streammedia.controller;


import com.streammedia.perisistence.GenericDao;
import com.streammedia.entity.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * The type User delete.
 * Responsible for getting and deleting User
 * https://www.javatpoint.com/crud-in-servlet
 * @author Jeanne
 * @version 1.0
 * @since 2020-02-22
 */
@WebServlet(
        urlPatterns = {"/deleteUser"}
)

public class UserDelete extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao genericDao = new GenericDao(User.class);
        //genericDao.delete(genericDao.getById(Integer.parseInt(req.getParameter("id"))));
        genericDao.delete(genericDao.getByPropertyEqual("username",req.getParameter("user")).get(0));
        resp.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

