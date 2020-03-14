package com.streammedia.controller;

import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.*;

/**
 * The type Trailer edit.
 * @author Jeanne
 * @version 1.0
 * @since 2020-02-22
 */
@Log4j2
@WebServlet(
        name = "trailerEdit",
        urlPatterns = {"/trailer-edit"}
)
public class TrailerEdit extends HttpServlet {
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
        request.setAttribute("trailer",genericDao.getById(Integer.valueOf(request.getParameter("uid"))));
        String url ="/trailer/trailerEdit.jsp";
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
        int trailerId = Integer.valueOf(req.getParameter("uid"));
//        int trailerId = Integer.valueOf(String.valueOf(req.getParameterValues("uid")));
        Trailer trailer = (Trailer)genericDao.getById(trailerId);
        if (!trailer.equals(null)) {
            trailer.setTitle(req.getParameter("title"));
            trailer.setAuthor(req.getParameter("author"));
            trailer.setDuration(LocalTime.parse(req.getParameter("duration")));
            trailer.setCover(req.getParameter("cover"));
            if(req.getParameter("pub_date") != null) {
                trailer.setPublicationDate(LocalDateTime.parse(req.getParameter("pub_date")));
            }else {
                trailer.setPublicationDate(LocalDateTime.now());
            }
            trailer.setLink(req.getParameter("link"));
            trailer.setVideo(req.getParameter("video"));
            trailer.setSummary(req.getParameter("summary").trim());
//            trailer.setUpdatedAt(LocalDate.now());
            log.debug("Updating Trailer: " + trailer.getTitle());
            genericDao.saveOrUpdate(trailer);
            String destination = "trailer-detail?uid=" + trailerId;
            resp.sendRedirect(destination);
        }
    }
}

