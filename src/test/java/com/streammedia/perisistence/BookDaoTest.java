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


/**
 * The type Book dao test.
 * @author Jeanne
 * @version 1.0
 * @since 05-10-2020
 */
@Log4j2
public class BookDaoTest {
    /**
     * The Book dao.
     */
    GenericDao bookDao;
    /**
     * The User dao.
     */
    GenericDao userDao;
    /**
     * The Category dao.
     */
    GenericDao categoryDao;


    /**
     * Set up.
     */
    @BeforeEach
    void setUp(){
        bookDao =  new GenericDao(Book.class);
        userDao = new GenericDao(User.class);
        categoryDao =  new GenericDao(BkCategory.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verify that all books are retrieved from db
     */
    @Test
    public void testGetAllBooksSuccess(){
        List<Book> books = bookDao.getAll();
        assertEquals(4,books.size());
    }

    /**
     * Test get by id success.
     */
    @Test
    public void testGetByIdSuccess() {
        Book retrievedBook = (Book)bookDao.getById(4);
        assertNotNull(retrievedBook);
        assertEquals("Nadia Calm", retrievedBook.getAuthor());
    }

    /**
     * Test insert book with existing categories success.
     */
    @Test
    public void  testInsertBookWithExistingCategoriesSuccess(){
        User user = (User) userDao.getById(2);
        BkCategory newCategory = (BkCategory) categoryDao.getById(4);
        BkCategory categoryTwo = (BkCategory)categoryDao.getById(6);
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
        int id = bookDao.insert(bookOne);
        assertTrue(id > 0);
        Book  insertbook = (Book)bookDao.getById(id);
        assertTrue(insertbook.getTitle().equals(bookOne.getTitle()));
    }


    /**
     * Test update book with category success.
     */
    @Test
    public void  testUpdateBookWithCategorySuccess(){

        String newTitle = "Home Sweet";
        Book bookToUpdate = (Book) bookDao.getById(2);
        bookToUpdate.setTitle(newTitle);
        BkCategory newCategory = (BkCategory)categoryDao.getById(1);
        Set<BkCategory> categories =  bookToUpdate.getCategories();
        log.debug(categories.size());
        categories.clear();
        categories.add(newCategory);
        bookDao.saveOrUpdate(bookToUpdate);
        Book retrieveHBook = (Book) bookDao.getById(2);
        log.debug(bookToUpdate.getTitle());
        assertTrue(bookToUpdate.getCategories().equals(retrieveHBook.getCategories()));

    }

    /**
     * Verify successful delete of books and detach them from category
     */
    @Test
    public void testDeleteSuccess() {
        BkCategory  bkCategory = (BkCategory)categoryDao.getById(5);
        Book book = (Book) bookDao.getById(2);
        book.removeCategory(bkCategory);
        bookDao.delete(book);
        assertNull(bookDao.getById(5));
    }
}
