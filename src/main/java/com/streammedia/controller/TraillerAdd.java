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
public class TraillerAdd extends HttpServlet {
    private GenericDao genericDao;


    public void init() {

        genericDao = new GenericDao(Trailer.class);


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
        String url ="/trailer/trailerAdd.jsp";
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request,response);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Trailer trailer = new Trailer();
        trailer.setTitle(req.getParameter("title"));
        trailer.setAuthor(req.getParameter("author"));
        // create a LocalTime Objects
//        LocalTime time
//                = LocalTime.parse("23:59:59");
//
//        // create formatter Object for ISO_LOCAL_TIME
//        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;

        trailer.setDuration(LocalTime.parse(req.getParameter("duration")));
        trailer.setPublicationDate(LocalDateTime.parse(req.getParameter("pub_date")));
        trailer.setCover(req.getParameter("cover"));
        trailer.setLink(req.getParameter("link"));
        trailer.setVideo(req.getParameter("video"));
        trailer.setSummary(req.getParameter("summary"));
//        trailer.setCreatedAt(LocalDate.now());
//        trailer.setUpdatedAt(LocalDate.now());
        GenericDao userDao =  new GenericDao(User.class);
//        User user = (User) userDao.getByPropertyEqual("username", req.getRemoteUser()).get(0);
        try {
            User user = (User) userDao.getById(1);
            if (!user.equals(null)) {
                trailer.setUser(user);
                log.debug("Adding Trailer: ", trailer);
                genericDao.insert(trailer);
//                RequestDispatcher dispatcher = req.getRequestDispatcher("/trailer/trailerList.jsp");
//                dispatcher.forward(req, resp);
                resp.sendRedirect("trailers");
            } else {
                req.getRequestDispatcher("/trailer/trailerAdd.jsp").forward(req, resp);
            }
        }catch (NullPointerException npe){
            log.error("User Does not Exists", npe);
        }
    }
}
