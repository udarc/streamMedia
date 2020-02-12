package com.streammedia.controller;

import com.streammedia.entity.User;
import com.streammedia.perisistence.UserDao;
import lombok.extern.log4j.Log4j2;

import javax.persistence.Column;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
@WebServlet(
        name = "users",
        urlPatterns = {"/account"}
)
public class RegisterLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            doGet(req,resp);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
//
        try {
            switch (action) {
                case "account/new":
                    registerForm(request, response);
                    break;
                case "/create":
                    createUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException sqle){
            log.info("Server Error" + sqle);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<User> listUser = userDao.getAllUsers();
        request.setAttribute("users", listUser);
//        if(request.getParameter("submit").equals("search")){
//            request.setAttribute("users",userDao.getUserByLastName(request.getParameter("username")));
//        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/account/users.jsp");
        dispatcher.forward(request, response);
    }


    private void registerForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/account/signup.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDao.getUserById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/account/editUserProfile.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void createUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if(password.equals(confirmPassword)) {
            User newUser =  new User(username,email, password,LocalDate.now(),LocalDate.now());
            userDao.insert(newUser);
        } else {
            System.out.println("Password do not match");
        }

        response.sendRedirect("list");
    }


}
