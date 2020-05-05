package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * FAQController.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user to update an object
 * https://www.javaguides.net/2019/03/jsp-servlet-hibernate-crud-example.html
 * @author Jeanne
 * @version 1.0
 * @since 2020-02-21
 */
@Log4j2
@WebServlet(urlPatterns = {"/faq-edit"})

public class FAQEdit extends HttpServlet {
    private GenericDao faqDao;
//    private GenericDao userDao;

    public void init() {
        faqDao = new GenericDao(FAQ.class);
//        userDao = new GenericDao(User.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("faq",faqDao.getById(Integer.valueOf(request.getParameter("uid"))));
        String url ="/faq/faqAddEdit.jsp";
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request,response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("uid"));
        log.debug("Updating FAQ", id);
        FAQ faq = (FAQ) faqDao.getById(id);
        faq.setTitle(request.getParameter("title").trim());
        faq.setCategory(request.getParameter("category").trim());
        faq.setDescription(request.getParameter("description").trim());
//        faq.setUpdatedAt(LocalDateTime.now());
        if(request.getRemoteUser().equals(faq.getUser().getUsername())
                || request.isUserInRole("admin")){
            faqDao.saveOrUpdate(faq);
//            response.sendRedirect("faqs");
        }
        //TODO set failure message
        response.sendRedirect("faqs");
    }


}

