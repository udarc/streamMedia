package com.streammedia.controller;
import com.streammedia.perisistence.UserDao;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import java.io.*;
public class SearchForm {



@WebServlet(
        name = "searchUser",
        urlPatterns = {"/searchUser"}
)
public class EmployeeSearch extends HttpServlet {
    /**
     *  Handles HTTP POST requests.
     *https://coderanch.com/t/634866/java/Process-Post-requests-Servlet
     * http://wiki4.caucho.com/Java_EE_Servlet_tutorial_:_Adding_create,_update_and_delete_to_the_bookstore_listing#Servlet_doGet_to_load_a_Book_form
     * https://www.javatpoint.com/crud-in-servlet
     *@param  request               Description of the Parameter
     *@param  response              Description of the Parameter
     *@exception ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException{
        String url = "/SearchUser.jsp";
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request,response);
    }
}

}
