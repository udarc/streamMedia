package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

    /**
     * FAQController.java
     * This servlet acts as a page controller for the application, handling all
     * requests from the user to add an object
     * https://www.javaguides.net/2019/03/jsp-servlet-hibernate-crud-example.html
     * @author Jeanne
     * @version 1.0
     * @since 2020-02-21
     */
    @Log4j2
    @WebServlet(urlPatterns = {"/faq-delete"})
    public class FAQDelete extends HttpServlet {

        private GenericDao faqDao;
        private GenericDao userDao;

        public void init() {
            faqDao = new GenericDao(FAQ.class);
            userDao = new GenericDao(User.class);
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("uid"));
            log.debug("Delete FAQ");
            FAQ faq = (FAQ) faqDao.getById(id);
            if (request.isUserInRole("admin")
                    || request.getRemoteUser().equals(faq.getUser().getUsername())){
                faqDao.delete(faq);
            } else{
                //TODO Add a message
                return;
            }

            response.sendRedirect("faqs");

        }
    }