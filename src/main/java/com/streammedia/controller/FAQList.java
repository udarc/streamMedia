package com.streammedia.controller;
import com.streammedia.entity.FAQ;
import com.streammedia.entity.Trailer;
import com.streammedia.entity.User;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FAQController.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user to delete an object.
 * https://www.javaguides.net/2019/03/jsp-servlet-hibernate-crud-example.html
 * @author Jeanne
 * @version 1.0
 * @since 2020-02-21
 */
@Log4j2
@WebServlet(
        urlPatterns = {"/faqs"})
public class FAQList extends HttpServlet {

    //        private static final long serialVersionUID = 1L;
    private GenericDao faqDao;
    private GenericDao userDao;

    public void init() {
        faqDao = new GenericDao(FAQ.class);
        userDao = new GenericDao(User.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<FAQ> faqs = faqDao.getAll();
        request.setAttribute("listFAQ", faqs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/faq/faqList.jsp");
        dispatcher.forward(request, response);
    }
}