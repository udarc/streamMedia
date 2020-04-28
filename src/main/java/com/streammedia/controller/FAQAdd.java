package com.streammedia.controller;
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import com.streammedia.utility.AWSS3UploadUtil;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;

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
    @WebServlet(urlPatterns = {"/faq-new"})
    public class FAQAdd extends HttpServlet {

        private GenericDao faqDao;
        private GenericDao userDao;


        public void init(){
            faqDao =  new GenericDao(FAQ.class);
            userDao =  new GenericDao(User.class);
        }
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/faq/faqAddEdit.jsp");
            dispatcher.forward(request, response);

        }
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            FAQ newFAQ = new FAQ();
            String username = request.getRemoteUser();
            String title = request.getParameter("title").trim();
            String category = request.getParameter("category").trim();
            String description = request.getParameter("description").trim();
            try {
                User user = (User) userDao.getByPropertyEqual("username",username).get(0);
                if (request.isUserInRole("admin") && !user.equals(null) && !title.equals(null) && !description.equals(null)) {
                    newFAQ.setTitle(title);
                    newFAQ.setCategory(category);
                    newFAQ.setDescription(description);
                    newFAQ.setCreatedAt(LocalDateTime.now());
                    newFAQ.setUpdatedAt(LocalDateTime.now());
                    newFAQ.setUser(user);
                    log.debug("Adding FAQ: ", newFAQ.getTitle());
//                    AWSS3UploadUtil awss3UploadUtil = new AWSS3UploadUtil();
                    faqDao.insert(newFAQ);
//                    log.error(awss3UploadUtil.getS3Bucket("cloud-for-developers"));
//                    awss3UploadUtil.createS3Bucket("ju-stream-media");
                    response.sendRedirect("faqs");
                } else {
                    request.getRequestDispatcher("/faq/faqAddEdit.jsp").forward(request, response);
                }
            } catch (NullPointerException npe) {
                log.error("User Does not Exists" + npe);
            } catch (ServletException sevex) {
                log.error("Servlet Error" + sevex);
            }
        }

    }
