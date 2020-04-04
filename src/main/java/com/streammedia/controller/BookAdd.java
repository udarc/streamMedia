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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type Book add.
 * https://dzone.com/tutorials/java/hibernate/hibernate-example/hibernate-mapping-many-to-many-using-annotations-1.html
 * http://websystique.com/hibernate/hibernate-many-to-many-bidirectional-annotation-example/
 */
@WebServlet(
        urlPatterns = {"/book-new"}
)
@Log4j2
public class BookAdd extends HttpServlet {
    private GenericDao bookDao;
    private GenericDao userDao;
    private GenericDao catDao;
    @Override
    public void init() throws ServletException {
        bookDao = new GenericDao(Book.class);
        userDao = new GenericDao(User.class);
        catDao = new GenericDao(BkCategory.class);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BkCategory> categoryList = catDao.getAll();
        req.setAttribute("categories",categoryList );
        RequestDispatcher dispatcher = req.getRequestDispatcher("/book/bookAddEdit.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) userDao.getByPropertyEqual("username", req.getRemoteUser()).get(0);
        Book newBook = new Book();
        Set<BkCategory> categoryList = new HashSet<>();
        Set<Book> books = new HashSet<Book>();
        String[] categoryIds = req.getParameterValues("category");
       

        try {

            retrieveCategories(categoryList, categoryIds);
            newBook.setUser(user);
            newBook.setTitle(req.getParameter("title"));
            newBook.setAuthor(req.getParameter("author"));
            newBook.setISBN(req.getParameter("isbn"));
            newBook.setCover(req.getParameter("cover"));
            newBook.setPublisher(req.getParameter("publisher"));
            newBook.setEdition(req.getParameter("edition"));
            newBook.setPageNumber(Integer.parseInt(req.getParameter("page_number")));
            String pubDate = req.getParameter("pub_date").trim();
            if( pubDate == null || pubDate.length() <= 0 ) {
                log.debug(newBook.getPublicationDate());
                newBook.setPublicationDate(LocalDateTime.now());
            }
            else {
                newBook.setPublicationDate(LocalDateTime.parse(pubDate));
            }
            
            newBook.setSummary(req.getParameter("summary"));
            for (BkCategory category : categoryList) {
                newBook.getCategories().add(category);
            }
            books.add(newBook);
            if(!newBook.equals(null)){
                bookDao.insert(newBook);
                resp.sendRedirect("books");
            } else {
                req.getRequestDispatcher("/book/bookAddEdit.jsp").forward(req, resp);
            }
        } catch (NullPointerException npe) {
            log.error("User Does not Exists" + npe);
        }
    }

    private void retrieveCategories(Set<BkCategory> categorySet, String[] catIds) {
        for (String id: catIds ) {
            categorySet.add((BkCategory) catDao.getById(Integer.parseInt(id)));
        }
    }
}
