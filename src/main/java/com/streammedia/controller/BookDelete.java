package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


/**
 * The type Book delete.
 * Get form data of selected object and calls dao method to remove it.
 * @author Jeanne
 * @version 1.0
 * @since 05-05-2020
 */
@WebServlet(
        urlPatterns = {"/book-delete"}
)
public class BookDelete extends HttpServlet {
    private GenericDao bookDao;

    @Override
    public void init() throws ServletException {
        bookDao = new GenericDao(Book.class);
//
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = (Book) bookDao.getById(Integer.parseInt(req.getParameter("uid")));
        if (req.isUserInRole("admin")) {
            Set<BkCategory> categoryList = book.getCategories();
            categoryList.clear();
            bookDao.delete(book);
            resp.sendRedirect("books");
        } else {
            resp.sendRedirect("book-details?uid=" + book.getBookId());

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

