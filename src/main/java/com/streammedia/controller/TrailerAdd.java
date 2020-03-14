package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;


/**
 * https://www.javaguides.net/2019/03/jsp-servlet-hibernate-crud-example.html
 * The type Add trailer.
 * Responsible for getting form data for new trailer
 * @author Jeanne
 * @version 1.0
 * @since 2020-02-25
 */
@Log4j2
@WebServlet(
        name = "trailerAdd",
        urlPatterns = {"/add-trailer"}
)
public class TrailerAdd extends HttpServlet {
    private GenericDao trailerDao;
    private  GenericDao userDao;


    public void init() {

        trailerDao = new GenericDao(Trailer.class);
        userDao =  new GenericDao(User.class);


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
        if (request.isUserInRole("admin")) {
            String url = "/trailer/trailerAddEdit.jsp";
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Trailer trailer = new Trailer();
        trailer.setTitle(req.getParameter("title").trim());
        trailer.setAuthor(req.getParameter("author").trim());
        trailer.setDuration(LocalTime.parse(req.getParameter("duration")));
        String pubDate = req.getParameter("pub_date").trim();
        if( pubDate == null || pubDate.length() <= 0 ) {
            trailer.setPublicationDate(LocalDateTime.now());

        }
        else {
            trailer.setPublicationDate(LocalDateTime.parse(pubDate));
        }
        trailer.setCover(req.getParameter("cover"));
        trailer.setLink(req.getParameter("link"));
        trailer.setVideo(req.getParameter("video"));
        trailer.setSummary(req.getParameter("summary"));
//
        try {
            User user = (User) userDao.getByPropertyEqual("username", req.getRemoteUser()).get(0);
            log.debug("User In trailer Add." + user);
            if (!user.equals(null) && req.isUserInRole("admin")) {
                trailer.setUser(user);
                log.debug("Trailer Add: " + trailer);
                trailerDao.insert(trailer);
                resp.sendRedirect("trailers");
            } else {
                req.getRequestDispatcher("/trailer/trailerAddEdit.jsp").forward(req, resp);
            }
        }catch (NullPointerException npe){
            log.error("User Does not Exists", npe);
        }
    }
}
