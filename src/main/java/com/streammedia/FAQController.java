package com.streammedia;
import com.streammedia.entity.FAQ;
import com.streammedia.perisistence.GenericDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.GeneratedValue;
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
@WebServlet(
        urlPatterns = {"/faqs"}
)
public class FAQController extends HttpServlet {

        private static final long serialVersionUID = 1L;
        private GenericDao faqDao;

        public void init(){
            faqDao =  new GenericDao(FAQ.class);
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
                        deleteBook(request, response);
                        break;
                    case "/edit":
                        displayEditForm(request, response);
                        break;
                    case "/edit-faq":
                        changeFAQ(request, response);
                        break;
                    default:
                        listFAQ(request, response);
                        break;
                }
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }

        private void listFAQ(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException, ServletException {
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
            int id = Integer.parseInt(request.getParameter("id"));
            FAQ existingFAQ = (FAQ)faqDao.getById(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/faq/faqAdd.jsp");
            request.setAttribute("faq", existingFAQ);
            dispatcher.forward(request, response);

        }

        private void createFAQ(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            float price = Float.parseFloat(request.getParameter("price"));

            FAQ newFAQ = new  FAQ();
            faqDao.insert(newFAQ);
            response.sendRedirect("list-faq");
        }

        private void changeFAQ(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));


            FAQ faq = new FAQ();
            faqDao.saveOrUpdate(faq);
            response.sendRedirect("list");
        }

        private void deleteBook(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));

            FAQ book = new FAQ();
            faqDao.delete(faqDao.getById(id));
            response.sendRedirect("list-faq");
        }
}
