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

@WebServlet(
        urlPatterns = {"/bkcategory-details"}
)
public class BKCategoryDetails extends HttpServlet {

    private GenericDao bkCategory;

    @Override
    public void init() throws ServletException {
        bkCategory =  new GenericDao(BkCategory.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("uid"));
        BkCategory bkCat = (BkCategory)bkCategory.getById(id);
        req.setAttribute("bkCategory",bkCat);
        req.getRequestDispatcher("/book/bkCategoryDetails.jsp").forward(req,resp);
    }
}
