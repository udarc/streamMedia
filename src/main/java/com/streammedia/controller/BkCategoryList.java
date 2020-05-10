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
 * Responsible for getting all the users and make them available to the JSP.
 *
 * @author Jeanne
 * @version 1.0
 * @since 05 -05-2020
 */
@WebServlet(
        urlPatterns = {"/categories"}
)
public class BkCategoryList extends HttpServlet {

    private GenericDao bkCategoryDao;

    /**
     * Init.
     * Responsible to create an instance of dao.
     * @throws ServletException the servlet exception
     */
    @Override
    public void init() throws ServletException {
        bkCategoryDao =  new GenericDao(BkCategory.class);
    }

    /**
     * Do get.
     *
     * @param req  the req
     * @param resp the resp
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BkCategory> bkCategories =  bkCategoryDao.getAll();
        req.setAttribute("categories", bkCategories);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/book/bkCategoryList.jsp");
        dispatcher.forward(req,resp);
    }
}
