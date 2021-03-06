package com.streammedia.perisistence;

import com.streammedia.entity.BkCategory;
import com.streammedia.entity.Crew;
import com.streammedia.entity.Book;
import com.streammedia.test.utility.Database;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Bk category dao test.
 * @author Jeanne
 * @version 1.0
 * @since 05-10-2020
 */
@Log4j2
public class BkCategoryDaoTest {
    /**
     * The Bk category dao.
     */
    GenericDao bkCategoryDao;
    /**
     * The Book dao.
     */
    GenericDao bookDao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {

        bkCategoryDao = new GenericDao(BkCategory.class);
        bookDao = new GenericDao(Book.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verifies gets all BkCategories successfully.
     */
    @Test
    void getAllBkCategoriesSuccess(){
        List<BkCategory> bkCategories = bkCategoryDao.getAll();
        assertEquals(6,bkCategories.size());
    }

    /**
     * Verifies a bkCategory is returned correctly based on id search
     */
    @Test
    void getBkCategoryByIdSuccess(){
        BkCategory bkCategory =  (BkCategory)bkCategoryDao.getById(5);
        assertTrue("Horror".equals(bkCategory.getTitle()));
        assertNotNull(bkCategory);

    }

    /**
     * Insert bk categoriesuccess.
     */
    @Test
    void  insertBkCategoriesuccess(){
        BkCategory newBkCategory = new BkCategory();
        newBkCategory.setTitle("Science Fiction (Sci-Fi)");
        newBkCategory.setDescription("Though they're often thought of in the same vein as fantasy, what distinguishes science fiction stories is that they lean heavily on themes of technology and future science. You'll find apocalyptic and dystopian novels in the sci-fi genre as well");
        int id = bkCategoryDao.insert(newBkCategory);
        assertTrue(id > 0);
        assertTrue(newBkCategory.getTitle().equals("Science Fiction (Sci-Fi)"));
    }

    /**
     * Verify successful update of bkCategory
     */
    @Test
    void testupdateBkCategoriesSuccess() {
        String newTitle = "Horrors";
        BkCategory bkCategoryToUpdate = (BkCategory)bkCategoryDao.getById(5);
        bkCategoryToUpdate.setTitle(newTitle);
        bkCategoryDao.saveOrUpdate(bkCategoryToUpdate);
        BkCategory retrievedBkCategory = (BkCategory)bkCategoryDao.getById(5);
        log.debug("Retrieve" + retrievedBkCategory);
        log.debug("To Update" + bkCategoryToUpdate);
       assertNotNull(retrievedBkCategory);
        assertTrue(bkCategoryToUpdate.equals(retrievedBkCategory));

    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<BkCategory> bkCategories = bkCategoryDao.getByPropertyEqual("title", "Fantasy");
        assertEquals(1, bkCategories.size());
        assertEquals(3, bkCategories.get(0).getBkCategoryId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<BkCategory> bkCategories = bkCategoryDao.getByPropertyLike("title", "c");
        assertEquals(4, bkCategories.size());
    }

    /**
     * Verify successful delete of bkCategory
     */
//    @Disabled
    @Test
    void deleteSuccess() {
        BkCategory bkCategory = (BkCategory) bkCategoryDao.getById(3);
        Book book = (Book) bookDao.getById(1);
        bkCategory.removeBook(book);
        bkCategoryDao.delete(bkCategory);
        assertNull(bkCategoryDao.getById(3));
    }
}
