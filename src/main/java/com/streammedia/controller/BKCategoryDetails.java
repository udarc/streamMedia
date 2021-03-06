package com.streammedia.controller;

import com.streammedia.entity.BkCategory;
import com.streammedia.entity.Crew;
import com.streammedia.entity.User;
import com.streammedia.perisistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Bk category details.
 * Responsible for getting a single Trailer from and setting it as an attribute to be accessible in JSP
 *
 * @author Jeanne
 * @version 1.0
 * @since 05 -05-2020
 */
@WebServlet(
        urlPatterns = {"/bkcategory-details"}
)
public class BKCategoryDetails extends HttpServlet {

    private GenericDao bkCategory;

    /**
     * Init.
     * Responsible to create an instance of dao.
     * @throws ServletException the servlet exception
     */
    @Override
    public void init() throws ServletException {
        bkCategory =  new GenericDao(BkCategory.class);
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
        int id = Integer.parseInt(req.getParameter("uid"));
        BkCategory bkCat = (BkCategory)bkCategory.getById(id);
        req.setAttribute("bkCategory",bkCat);
        req.getRequestDispatcher("/book/bkCategoryDetails.jsp").forward(req,resp);
    }
}
