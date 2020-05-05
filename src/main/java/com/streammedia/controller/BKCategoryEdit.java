package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Bk category edit.
 * Responsible for getting form data to update User's recor
 * @author Jeanne
 * @version 1.0
 * @since 05-05-2020
 */
@WebServlet(
        urlPatterns = {"/bkcategory-edit"}
)
@Log4j2
public class BKCategoryEdit extends HttpServlet {

    private GenericDao bkCategoryDao;
    private  GenericDao userDao;

    @Override
    public void init() throws ServletException {
        bkCategoryDao =  new GenericDao(BkCategory.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.isUserInRole("admin")){
            int catId = Integer.parseInt(req.getParameter("uid"));
            BkCategory bkCat = (BkCategory) bkCategoryDao.getById(catId);
            req.setAttribute("bkCategory", bkCat);
            req.getRequestDispatcher("/book/bkCategoryAddEdit.jsp").forward(req,resp);
        }
        else{
            resp.sendRedirect("bk-categories");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("uid"));
        BkCategory category =  (BkCategory) bkCategoryDao.getById(id);
        category.setTitle(req.getParameter("title").trim());
        category.setDescription(req.getParameter("description").trim());

        if(!category.equals(null) && req.isUserInRole("admin")){
            log.debug("BK Category: " + category.getTitle());
            bkCategoryDao.saveOrUpdate(category);
            String successMessage = "Successfully added book Category!";
            req.getSession().setAttribute("editBookCategorySuccess",successMessage);
            resp.sendRedirect("bkcategory-details?uid=" + category.getBkCategoryId());
        } else {
            req.getSession().setAttribute("bookCategorySuccessError",category.getTitle() + " was not updated!");
            req.getRequestDispatcher("/book/bkCategoryAddEdit.jsp").forward(req,resp);
        }

    }
}
