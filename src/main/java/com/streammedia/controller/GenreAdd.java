package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.validation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * FAQController.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user to add an object
 * https://www.javaguides.net/2019/03/jsp-servlet-hibernate-crud-example.html
 * https://howtodoinjava.com/hibernate/hibernate-validator-java-bean-validation/
 * https://www.geeksforgeeks.org/attributes-in-servlets-java/
 *
 * @author Jeanne
 * @version 1.0
 * @since 2020 -02-21
 */
@Log4j2
@WebServlet(urlPatterns = {"/genre-new"})
public class GenreAdd extends HttpServlet {

    private GenericDao genreDao;
    private GenericDao userDao;

    public void init(){
        genreDao =  new GenericDao(Genre.class);
        userDao =  new GenericDao(User.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/film/genreAddEdit.jsp");
        dispatcher.forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Genre newGenre = new Genre();
        String title = request.getParameter("title").trim();
        String description = request.getParameter("description").trim();
        try {
            User user = (User) userDao.getByPropertyLike("username",
                    request.getRemoteUser()).get(0);
            if (!user.equals(null) && !title.equals(null)
                    && !description.equals(null) && request.isUserInRole("admin")) {
                newGenre.setTitle(title);
                newGenre.setDescription(description);
                Set<ConstraintViolation<Genre>> constraintViolations = getConstraintViolations(newGenre);
                if (!constraintViolations.isEmpty()) {
                    Map<String, String> errors = new HashMap<>();
                    for (ConstraintViolation<Genre> constraintViolation : constraintViolations) {
                            errors.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());

                    }
                    log.debug("Adding Genre: ", newGenre.getTitle());
                    request.setAttribute("errors",errors);
                   request.getRequestDispatcher("/film/genreAddEdit.jsp").forward(request, response);
                } else {
                    genreDao.insert(newGenre);
                    response.sendRedirect("genres");
                }
            }else {
                request.getRequestDispatcher("/film/genreAddEdit.jsp").forward(request, response);
            }
        } catch (NullPointerException npe) {
            log.error("User Does not Exists" + npe);
        } catch (ServletException sevex) {
            log.error("Servlet Error" + sevex);
        }
    }

    private Set<ConstraintViolation<Genre>> getConstraintViolations(Genre newGenre) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator.validate(newGenre);
    }

}
