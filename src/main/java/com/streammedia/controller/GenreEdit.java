package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * The type Genre edit.
 * @author Jeanne
 * @version 1.0
 * @since 2020-02-22
 */
@Log4j2
@WebServlet(
        name = "genreEdit",
        urlPatterns = {"/genre-edit"}
)
public class GenreEdit extends HttpServlet {
    private GenericDao genreDao;

    public void init() {
        genreDao = new GenericDao(Genre.class);
    }
    /**
     *  Handles HTTP GET requests.
     *
     *@param  request               Description of the Parameter
     *@param  response              Description of the Parameter
     *@exception ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("genre",genreDao.getById(Integer.valueOf(request.getParameter("uid"))));
        String url ="/film/genreAddEdit.jsp";
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request,response);

    }
    /**
     *  Handles HTTP POST requests.
     *@param  req              Description of the Parameter
     *@param  resp             Description of the Parameter
     *@exception ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int genreId = Integer.valueOf(req.getParameter("uid"));
        Genre genre = (Genre)genreDao.getById(genreId);
        if (!genre.equals(null)) {
            genre.setTitle(req.getParameter("title"));
            genre.setDescription(req.getParameter("description").trim());
            log.debug("Updating Genre: " + genre.getTitle());
            genreDao.saveOrUpdate(genre);
//            String destination = "genre-detail?uid=" + genreId;
            resp.sendRedirect("genres");
        }
    }
}


