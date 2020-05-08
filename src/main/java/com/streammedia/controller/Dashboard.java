package com.streammedia.controller;


import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Dashboard servlet for admin homepage.
 * responsible to redirect admin to a secure page.
 */
@WebServlet(
        urlPatterns = {"/dashboard"}
)@Log4j2
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if(req.isUserInRole("admin")){
           getServletContext().getRequestDispatcher("/dashboard/dashboard.jsp")
                    .forward(req,resp);
//        } else{
//            resp.sendRedirect(req.getContextPath());
//        }
    }
}
