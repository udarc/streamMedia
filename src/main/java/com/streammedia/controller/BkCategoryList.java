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
import java.util.List;

/**
 * The type category of Book list.
 */
@WebServlet(
        urlPatterns = {"/bk-categories"}
)
public class BkCategoryList extends HttpServlet {

    private GenericDao bkCategoryDao;

    @Override
    public void init() throws ServletException {
        bkCategoryDao =  new GenericDao(BkCategory.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BkCategory> bkCategories =  bkCategoryDao.getAll();
        req.setAttribute("categories", bkCategories);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/book/bkCategoryList.jsp");
        dispatcher.forward(req,resp);
    }
}
