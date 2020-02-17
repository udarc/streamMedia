package com.streammedia.controller;
import com.streammedia.entity.Role;
import com.streammedia.entity.User;
import com.streammedia.perisistence.RoleDao;
import com.streammedia.perisistence.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The type Users.
 */
@WebServlet(
        name = "users",
        urlPatterns = {"/users"})
public class ListUser extends HttpServlet {
    private UserDao userDao;
    private RoleDao roleDao;

    public void init() {

        userDao = new UserDao();
        roleDao =  new RoleDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> listUser = userDao.getAllUsers();
        List<Role> roleList = roleDao.getAllRoles();
        request.setAttribute("users", listUser);
        request.setAttribute("useRoles",roleList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/account/listUser.jsp");
        dispatcher.forward(request, response);
    }


}
