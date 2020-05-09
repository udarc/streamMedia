package com.streammedia.controller;
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Dashboard servlet for admin homepage.
 * responsible to redirect admin to a secure page.
 */
@WebServlet(
        urlPatterns = {"/dashboard"}
)@Log4j2
public class Dashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if(req.isUserInRole("admin")){
            Map<String, Integer> streamMediaData = new HashMap<>();
            List<User> userList = new GenericDao(User.class).getAll();
            List<BkCategory> categoryList = new GenericDao(BkCategory.class).getAll();
            List<Book> bookList = new GenericDao(Book.class).getAll();
            List<Crew> crewList = new GenericDao(Crew.class).getAll();
            List<Genre> genreList  = new GenericDao(Genre.class).getAll();
            List<Film> filmList  = new GenericDao(Film.class).getAll();
            List<FAQ> faqList  = new GenericDao(FAQ.class).getAll();
            List<ShortStory> storyList  = new GenericDao(ShortStory.class).getAll();
            List<Trailer> trailerList  = new GenericDao(Trailer.class).getAll();
            streamMediaData.put("Users", userList.size());
            streamMediaData.put("Book Categories", categoryList.size());
            streamMediaData.put("Books",bookList.size());
            streamMediaData.put("Crew Members",crewList.size());
            streamMediaData.put("Genres",genreList.size());
            streamMediaData.put("Films",filmList.size());
            streamMediaData.put("FAQ",faqList.size());
            streamMediaData.put("Short Story",storyList.size());
            streamMediaData.put("Trailers",trailerList.size());
            req.setAttribute("streamMediaCounter", streamMediaData);
//            https://www.geeksforgeeks.org/arraylist-sublist-method-in-java-with-examples/
            req.setAttribute("users",userList.subList(userList.size()-2, userList.size()));
            req.setAttribute("books",bookList.subList(bookList.size()-2, bookList.size()));
            req.setAttribute("films",filmList.subList(filmList.size()-2, filmList.size()));
            req.setAttribute("trailers",trailerList.subList(trailerList.size()-2, trailerList.size()));
            req.setAttribute("stories",storyList.subList(storyList.size()-2, storyList.size()));
            getServletContext().getRequestDispatcher("/dashboard/dashboard.jsp")
                    .forward(req,resp);

        } else{
            resp.sendRedirect(req.getContextPath());
        }
    }
}
