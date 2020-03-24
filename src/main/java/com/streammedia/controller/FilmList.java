package com.streammedia.controller;

import com.streammedia.perisistence.GenericDao;
import com.streammedia.perisistence.GenreRESTAPIDao;
import lombok.extern.log4j.Log4j2;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


    /**
     * BookList.java
     * This servlet acts as a page controller for the application, handling all
     * requests from the user to perform CRUD operations.
     * https://www.javaguides.net/2019/03/jsp-servlet-hibernate-crud-example.html
     * @author Jeanne
     * @version 1.0
     * @since 2020-03-03
     */
    @Log4j2
    @WebServlet("/films")
    public class FilmList extends HttpServlet {

            private GenericDao filmDao;
            private GenericDao userDao;
            private GenericDao crewDao;
            private GenericDao genericDao;
        public void init() {

        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            String action = request.getServletPath();

            GenreRESTAPIDao genresDao =  new GenreRESTAPIDao();
            request.setAttribute("restGenres", genresDao.getGenres().getGenres());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/film/filmList.jsp");
            dispatcher.forward(request, response);
            }
}
