package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Category of book delete.
 * Remove Book Category
 * @author Jeanne
 * @version 1.0
 * @since 05-05-2020
 */
@WebServlet(
        urlPatterns = {"/bkcategory-delete"}
)
public class BKCategoryDelete extends HttpServlet {
    private GenericDao bkCategory;

    @Override
    public void init() throws ServletException {
        bkCategory = new GenericDao(BkCategory.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("uid"));
        BkCategory bkCat = (BkCategory) bkCategory.getById(id);
        if(req.isUserInRole("admin")){
            bkCategory.delete(bkCat);
            resp.sendRedirect("categories");
        }
        //TODO add else message
    }
}
