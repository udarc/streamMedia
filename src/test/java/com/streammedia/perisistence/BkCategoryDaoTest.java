package com.streammedia.perisistence;

import com.streammedia.entity.BkCategory;
import com.streammedia.test.utility.Database;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
public class BkCategoryDaoTest {
    GenericDao bkCategoryDao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {

        bkCategoryDao = new GenericDao(BkCategory.class);

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
    void updateBkCategoriesuccess() {
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
        bkCategoryDao.delete(bkCategoryDao.getById(2));
        assertNull(bkCategoryDao.getById(2));
    }
}
