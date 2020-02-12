package com.streammedia.controller;
import com.streammedia.entity.User;
import com.streammedia.perisistence.UserDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@Log4j2
@WebServlet(
        name = "signup",
        urlPatterns = {"/register"}
)
public class RegisterUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }
    /**
     *  Handles HTTP GET requests.
     *
     *@param  request               Description of the Parameter
     *@param  response              Description of the Parameter
     *@exception  ServletException  if there is a Servlet failure
     *@exception  IOException       if there is an IO failure
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url ="/account/signup.jsp";
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request,response);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setEmail(req.getParameter("email"));
        user.setFirstName(req.getParameter("firstname"));
        user.setLastName(req.getParameter("lastname"));
        user.setPassword(req.getParameter("password"));
        String confirmPassword = req.getParameter("confirmPassword");
        user.setCreatedAt(LocalDate.now());
        user.setUpdateAt(LocalDate.now());

        if(confirmPassword.equals(user.getPassword())){

            log.debug("Adding User: " + user);
//        Role role = new Role();
//        role.setUser(user);
//        role.setRole("user");
//        user.addRole(role);
            userDao.insert(user);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/account/userSuccess.jsp");
            dispatcher.forward(req, resp);
        } else {
            req.getRequestDispatcher("/account/signup.jsp").forward(req,resp);
        }


    }

}
