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
 * The type Sing in action.
 * A simple servlet whose purpose is to redirect
 * after a log in attempt
 *https://stackoverflow.com/questions/1531197/how-do-i-redirect-to-the-current-page-in-servlet-filter
 * @author Jeanne
 * @version 1.0
 * @since 2020-03-02
 */
@WebServlet(
        urlPatterns = {"/login"}
)
@Log4j2
public class SingInAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("The logged in user: " + req.getRemoteUser() + " has a role of admin: " + req.isUserInRole("admin"));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/account/signin.jsp");
        dispatcher.forward(req, resp);

    }
}
