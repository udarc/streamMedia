package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/book-edit"}
)
public class BookEdit extends HttpServlet {
    private GenericDao bookDao;
    private GenericDao userDao;
    private GenericDao catDao;
    @Override
    public void init()  {
        bookDao = new GenericDao(Book.class);
        userDao = new GenericDao(User.class);
        catDao = new GenericDao(BkCategory.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("uid"));
        Book book = (Book) bookDao.getById(id);
        req.setAttribute("book",book);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/book/bookAddEdit.jsp");
        dispatcher.forward(req,resp);
    }

}
