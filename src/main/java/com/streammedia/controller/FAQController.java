package com.streammedia.controller;
import com.streammedia.entity.FAQ;
import com.streammedia.entity.User;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.sql.SQLException;
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
 * requests from the user.
 * @author Jeanne
 */
@Log4j2
@WebServlet("/")
public class FAQController extends HttpServlet {

        private static final long serialVersionUID = 1L;
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

            try {
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
                    case "/faqs":
                        listFAQ(request, response);
                        break;
//                    default:
//                        listFAQ(request, response);
//                        break;
                }
            } catch (SQLException ex) {
                throw new ServletException(ex);
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("/faq/faqAdd.jsp");
            dispatcher.forward(request, response);
        }

        private void displayEditForm(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, ServletException, IOException {
//            int id = Integer.parseInt(request.getParameter("id"));
//            FAQ existingFAQ = (FAQ)faqDao.getById(id);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/faq/faqAdd.jsp");
//            request.setAttribute("faq", existingFAQ);
//            dispatcher.forward(request, response);
            log.debug("Display Edit Form");

        }

        private void createFAQ(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {
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
                    newFAQ.setUser(user);
                    log.debug("Adding FAQ: ", newFAQ.getTitle());
                    faqDao.insert(newFAQ);
                    response.sendRedirect("faqs");
                } else {
                    request.getRequestDispatcher("/faq/faqAdd.jsp").forward(request, response);
                }
            }catch (NullPointerException npe){
                log.error("User Does not Exists" + npe);
            }catch (ServletException sevex){
                log.error("Servlet Error" + sevex);
            }

        }

        private void editFAQ(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {
//            int id = Integer.parseInt(request.getParameter("id"));
//
//
//            FAQ faq = new FAQ();
//            faqDao.saveOrUpdate(faq);
//            response.sendRedirect("list");
            log.debug("Updating FAQ");

        }

        private void deleteFAQ(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {
//            int id = Integer.parseInt(request.getParameter("id"));
//                log.debug("Delete FAQ");
//            FAQ book = new FAQ();
//            faqDao.delete(faqDao.getById(id));
//            response.sendRedirect("list-faq");
            log.debug("Deleting FAQ");
        }
}
