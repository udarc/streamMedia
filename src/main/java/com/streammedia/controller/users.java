package com.streammedia.controller;

import com.streammedia.perisistence.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Users.
 */
@WebServlet(urlPatterns = {"/users"})
public class users extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        if(request.getParameter("submit").equals("search")){
            request.setAttribute("users",userDao.getUserByLastName(request.getParameter("username")));
        } else {
            request.setAttribute("users", userDao.getAllUsers());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/account/users.jsp");
        dispatcher.forward(request, response);
    }

}
