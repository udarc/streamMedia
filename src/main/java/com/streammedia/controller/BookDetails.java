package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/book-details"}
)
public class BookDetails extends HttpServlet {
    private GenericDao bookDao;
    @Override
    public void init() throws ServletException {
        bookDao = new GenericDao(Book.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("uid"));
        Book book = (Book)bookDao.getById(id);
        req.setAttribute("book",book);
        req.getRequestDispatcher("/book/bookDetails.jsp").forward(req,resp);
    }
}
