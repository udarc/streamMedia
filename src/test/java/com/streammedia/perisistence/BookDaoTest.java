package com.streammedia.perisistence;

import com.streammedia.entity.*;
import com.streammedia.test.utility.Database;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@Log4j2
public class BookDaoTest {
    GenericDao genericDao;
    GenericDao userDao;
    GenericDao catDao;
  

    @BeforeEach
    void setUp(){
        genericDao =  new GenericDao(Book.class);
        userDao = new GenericDao(User.class);
        catDao =  new GenericDao(BkCategory.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verify that all Crews are retrieved from db
     */
    @Test
    public void getAllBooksSuccess(){
        List<Book> books = genericDao.getAll();
        assertEquals(4,books.size());
    }
    @Test
    void getByIdSuccess() {
        Book retrievedBook = (Book)genericDao.getById(4);
        assertNotNull(retrievedBook);
        assertEquals("Nadia Calm", retrievedBook.getAuthor());
    }
    @Test
    void  insertBookWithExistingCategoriesSuccess(){
        User user = (User) userDao.getById(2);
        BkCategory newCategory = (BkCategory) catDao.getById(4);
        BkCategory categoryTwo = (BkCategory)catDao.getById(6);
        Set<BkCategory> categories = new HashSet<>();
        categories.add(newCategory);
        categories.add(categoryTwo);
        //Add Book
        Book bookOne = new Book();
        bookOne.setTitle("Happy Day");
        bookOne.setPublicationDate(LocalDateTime.now());
        bookOne.setISBN("435226572848");
        bookOne.setAuthor("John Doe");
        bookOne.setPageNumber(678);
        bookOne.setPublisher("Mary Anne");
        bookOne.setCreatedAt(LocalDateTime.now());
        bookOne.setSummary("This is a test of adding a new Book With Category");
        bookOne.setUser(user);
        Set<Book> books = new HashSet<Book>();
        for (BkCategory category: categories) {
            bookOne.addCategory(category);
            log.debug("Loop Category " + category.getTitle());
            log.debug("Loop Book One " + bookOne.getAuthor());

        }
        int id = genericDao.insert(bookOne);
        assertTrue(id > 0);
        Book  insertbook = (Book)genericDao.getById(id);
        assertTrue(insertbook.getTitle().equals(bookOne.getTitle()));
    }

//
//    @Test
//    void  updateBookWIthExistingCrewAndCategoriesuccess(){
//
//        String newTitle = "Home Sweet";
//        Book bookToUpdate = (Book) genericDao.getById(2);
//        bookToUpdate.setTitle(newTitle);
//        Category newCategory = (Category)categoryDao.getById(3);
//        Set<Category> categories =  bookToUpdate.getCategories();
//        System.out.println(categories.size());
////        categories.clear();
//        categories.add(newCategory);
//        genericDao.saveOrUpdate(bookToUpdate);
//        Book retrieveHBook = (Book) genericDao.getById(2);
//        System.out.println(bookToUpdate.getTitle());
//        assertTrue(bookToUpdate.equals(retrieveHBook));
//
//    }
}
