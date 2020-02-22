package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


/**
 * https://www.javaguides.net/2019/03/jsp-servlet-hibernate-crud-example.html
 * The type Add trailer.
 * @author Jeanne
 */
@Log4j2
@WebServlet(
        name = "addTailer",
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
        trailer.setDuration(req.getParameter("duration"));
        trailer.setPublicationDate(LocalDate.parse(req.getParameter("pub_date")));
        trailer.setCover(req.getParameter("cover"));
        trailer.setLink(req.getParameter("link"));
        trailer.setVideo(req.getParameter("video"));
        trailer.setSummary(req.getParameter("summary"));
        trailer.setCreatedAt(LocalDate.now());
        trailer.setUpdatedAt(LocalDate.now());
        GenericDao userDao =  new GenericDao(User.class);
//        User user = (User) userDao.getByPropertyEqual("username", req.getRemoteUser()).get(0);
        try {
            User user = (User) userDao.getById(1);
            if (!user.equals(null)) {
                trailer.setUser(user);
                log.debug("Adding Trailer: ", trailer);
                genericDao.insert(trailer);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/trailer/trailerList.jsp");
                dispatcher.forward(req, resp);
            } else {
                req.getRequestDispatcher("/trailer/trailerAdd.jsp").forward(req, resp);
            }
        }catch (NullPointerException npe){
            log.error("User Does not Exists", npe);
        }





    }

}
