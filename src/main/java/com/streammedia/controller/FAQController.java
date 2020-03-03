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
 * requests from the user to perform CRUD operations.
 * https://www.javaguides.net/2019/03/jsp-servlet-hibernate-crud-example.html
 * @author Jeanne
 * @version 1.0
 * @since 2020-02-21
 */
@Log4j2
@WebServlet("/")
public class FAQController extends HttpServlet {

//        private static final long serialVersionUID = 1L;
        private GenericDao faqDao;
        private GenericDao userDao;

        public void init(){
            faqDao =  new GenericDao(FAQ.class);
            userDao =  new GenericDao(User.class);
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            String action = request.getServletPath();

                if (request.isUserInRole("admin")){
                    switch (action) {
                        case "/new":
                            displayNewForm(request, response);
                            break;
                        case "/add-faq":
                            createFAQ(request, response);
                            break;
                        case "/delete-faq":
                            deleteFAQ(request, response);
                            break;
                        case "/edit":
                            displayEditForm(request, response);
                            break;
                        case "/edit-faq":
                            editFAQ(request, response);
                            break;
                        default:
                            listFAQ(request,response);
                    }
                } else {
                        switch (action) {
                            case "/faqs":
                                listFAQ(request, response);
                                break;
                            case "/faq-details":
                                displayFAQDetails(request, response);
                                break;
                        }
                }
        }

    private void displayFAQDetails(HttpServletRequest request, HttpServletResponse response) {
        FAQ faq= (FAQ)faqDao.getById(Integer.parseInt(request.getParameter("uid")));
        log.debug("Getting a Single FAQ " + faq);
        request.setAttribute("faq", faq);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/faq/faqDetails.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            log.error("Servlet Error: " + e);
        } catch (IOException e) {
            log.error("IO Error: " + e);
        }
    }

    private void listFAQ(HttpServletRequest request, HttpServletResponse response)
                throws  IOException, ServletException {
            List<FAQ> faqs = faqDao.getAll();
            request.setAttribute("listFAQ", faqs);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/faq/faqList.jsp");
            dispatcher.forward(request, response);
        }

        private void displayNewForm(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adminOnly/faqAdd.jsp");
            dispatcher.forward(request, response);
        }

        private void displayEditForm(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            int id = Integer.parseInt(request.getParameter("uid"));
            FAQ existingFAQ = (FAQ)faqDao.getById(id);
            if (request.getRemoteUser().equals(existingFAQ.getUser()) && !request.getRemoteUser().equals(null)) {
                log.debug("Display Edit Form");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/adminOnly/faqAdd.jsp");
                request.setAttribute("faq", existingFAQ);
                dispatcher.forward(request, response);
            }

        }

        private void createFAQ(HttpServletRequest request, HttpServletResponse response)
                throws IOException {
            FAQ newFAQ = new  FAQ();
            String title = request.getParameter("title").trim();
            String category = request.getParameter("category").trim();
            String description = request.getParameter("description").trim();
            try {
                User user = (User) userDao.getById(2);
                if (!user.equals(null) && !title.equals(null) && !description.equals(null)) {
                    newFAQ.setTitle(title);
                    newFAQ.setCategory(category);
                    newFAQ.setDescription(description);
                    newFAQ.setCreatedAt(LocalDateTime.now());
                    newFAQ.setUpdatedAt(LocalDateTime.now());
                    newFAQ.setUser(user);
                    log.debug("Adding FAQ: ", newFAQ.getTitle());
                    faqDao.insert(newFAQ);
                    response.sendRedirect("faqs");
                } else {
                    request.getRequestDispatcher("/adminOnly/faqAdd.jsp").forward(request, response);
                }
            }catch (NullPointerException npe){
                log.error("User Does not Exists" + npe);
            }catch (ServletException sevex){
                log.error("Servlet Error" + sevex);
            }

        }

        private void editFAQ(HttpServletRequest request, HttpServletResponse response)
                throws IOException {
            int id = Integer.parseInt(request.getParameter("uid"));
            log.debug("Updating FAQ", id);
            FAQ faq = (FAQ)faqDao.getById(id);
            faq.setTitle(request.getParameter("title").trim());
            faq.setCategory(request.getParameter("category").trim());
            faq.setDescription(request.getParameter("description").trim());
            faq.setUpdatedAt(LocalDateTime.now());
            faqDao.saveOrUpdate(faq);
            response.sendRedirect("faqs");

        }

        private void deleteFAQ(HttpServletRequest request, HttpServletResponse response)
                throws  IOException {
            int id = Integer.parseInt(request.getParameter("uid"));
            log.debug("Delete FAQ");
            faqDao.delete(faqDao.getById(id));
            response.sendRedirect("faqs");
        }
}
