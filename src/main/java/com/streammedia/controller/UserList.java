package com.streammedia.controller;
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import com.streammedia.utility.JavaHelperMethods;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * The type Users.
 * Responsible for getting all the users and make them available to the JSP.
 *
 * @author Jeanne
 * @version 1.0
 */
@Log4j2
@WebServlet(
        name = "users",
        urlPatterns = {"/users"})
public class UserList extends HttpServlet {

    /**
     * The Generic dao.
     */
    GenericDao genericDao;

    /**
     * Responsible for instantiating Daos
     */
    public void init() {

        genericDao =  new GenericDao(User.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            List<User> listUser;
//        if (request.getParameter("search".trim()).equals("Search".trim())){
//            String  searchTerm = request.getParameter("searchTerm" );
//            String  searchType = request.getParameter("searchType" );
//            listUser = genericDao.getByPropertyLike(searchType, searchTerm);
//            log.debug("Getting All Users servlet" + listUser);
//            request.setAttribute("users",  listUser);
//            } else{
            listUser = genericDao.getAll();
            request.setAttribute("users",  listUser);
//        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/account/listUser.jsp");
        dispatcher.forward(request, response);
    }
}
