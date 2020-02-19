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
 * A simple servlet to welcome the user.
 * @author pwaite
 */

@WebServlet(
        urlPatterns = {"/searchUser"}
)

public class SearchUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao genericDao = new GenericDao(User.class);
        String searchTerm = req.getParameter("searchTerm").trim();
        if((searchTerm != null && !searchTerm.isEmpty())){
            try {
                req.setAttribute("searches",genericDao.getByPropertyLike("lastName",searchTerm));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("account/users.jsp");
        dispatcher.forward(req, resp);
    }
}