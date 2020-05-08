package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type Book edit.
 * Responsible for getting form data to update User's recor
 * @author Jeanne
 * @version 1.0
 * @since 05-05-2020
 */
@WebServlet(
        urlPatterns = {"/book-edit"}
)
@Log4j2
public class BookEdit extends HttpServlet {
    private GenericDao bookDao;
    private GenericDao userDao;
    private GenericDao catDao;

    @Override
    public void init() {
        bookDao = new GenericDao(Book.class);
        userDao = new GenericDao(User.class);
        catDao = new GenericDao(BkCategory.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("uid"));
        Book book = (Book) bookDao.getById(id);
        List<BkCategory> categoryList = catDao.getAll();
        Set<BkCategory> currentCategories = book.getCategories();
        req.setAttribute("categories", categoryList);
        req.setAttribute("book", book);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/book/bookAddEdit.jsp");
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)userDao.getByPropertyEqual("username",req.getRemoteUser()).get(0);
        Book bookToEdit = (Book) bookDao.getById(Integer.parseInt(req.getParameter("uid")));
        bookToEdit.setTitle(req.getParameter("title"));
        bookToEdit.setPublisher(req.getParameter("publisher"));
        bookToEdit.setCover(req.getParameter("cover"));
        bookToEdit.setISBN(req.getParameter("isbn"));
        bookToEdit.setPublicationDate(LocalDateTime.parse(req.getParameter("pub_date").trim()));
        bookToEdit.setAuthor(req.getParameter("author"));
        bookToEdit.setEdition(req.getParameter("edition"));
        bookToEdit.setPageNumber(Integer.parseInt(req.getParameter("page_number")));
        bookToEdit.setSummary(req.getParameter("summary"));
        Set<BkCategory> categoryList = bookToEdit.getCategories();
        String[] categoryIds = req.getParameterValues("category");
        if(categoryIds != null ){
            categoryList.clear();
            retrieveCategories(categoryList, categoryIds);
        } else{
            for (BkCategory cat: bookToEdit.getCategories()) {
                bookToEdit.addCategory(cat);
            }
        }
        if (req.isUserInRole("admin")){
            log.debug(bookToEdit);
            bookDao.saveOrUpdate(bookToEdit);
            String successMessage = "Successfully added a book!";
            req.getSession().setAttribute("editBookSuccess",successMessage);
            resp.sendRedirect("book-details?uid=" + bookToEdit.getBookId());
        } else {
            req.getRequestDispatcher("/book/bookAddEdit.jsp").forward(req, resp);
            req.getSession().setAttribute("bookErrorMessage","Failed to add a book!");
        }

    }
    private void retrieveCategories(Set<BkCategory> categoryList, String[] categoryIds) {
        for (String id: categoryIds ) {
            log.debug(id);
            categoryList.add((BkCategory)catDao.getById(Integer.parseInt(id)));

        }
    }
}


