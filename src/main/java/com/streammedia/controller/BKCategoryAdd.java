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

/**
 * The type Bk(Stands for book) category add.
 * Gets form data and set them to be saved to database.
 *  @author Jeanne
 *  @version 1.0
 * @since 05-05-2020
 */
//TODO http://zetcode.com/java/hibernatevalidator/
@WebServlet(
        urlPatterns = {"/bkcategory-new"}
)
@Log4j2
public class BKCategoryAdd extends HttpServlet {

    private GenericDao bkCategoryDao;
    private  GenericDao userDao;

    @Override
    public void init() throws ServletException {
        bkCategoryDao =  new GenericDao(BkCategory.class);
        userDao  = new GenericDao<>(User.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.isUserInRole("admin")){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/book/bkCategoryAddEdit.jsp");
            dispatcher.forward(req,resp);
        }
        else{
            resp.sendRedirect("bk-categories");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BkCategory newBkCategory =  new BkCategory();
        newBkCategory.setTitle(req.getParameter("title").trim());
        newBkCategory.setDescription(req.getParameter("description").trim());

        if(!newBkCategory.equals(null) && req.isUserInRole("admin")){
            log.debug("BK Category: " + newBkCategory.getTitle());
            bkCategoryDao.insert(newBkCategory);
            String successMessage = "Successfully added book Category!";
            req.getSession().setAttribute("addBookCategorySuccess",successMessage);
            resp.sendRedirect("bk-categories");
        } else {
            req.getSession().setAttribute("bookCategorySuccessError",newBkCategory.getTitle() + " was not created!");
            req.getRequestDispatcher("/book/bkCategoryAddEdit.jsp").forward(req,resp);
        }
    }
}
