package com.streammedia.perisistence;

import com.streammedia.entity.*;
import com.streammedia.test.utility.Database;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Faq dao test.
 * @author Jeanne
 * @version 1.0
 * @since 05-10-2020
 */
@Log4j2
class FAQDaoTest {

    /**
     * The Generic dao.
     */
    GenericDao genericDao;
    /**
     * The User dao.
     */
    GenericDao userDao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        genericDao =  new GenericDao(FAQ.class);
        userDao = new GenericDao(User.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies gets all faqs successfully.
     */
    @Test
    void getAllFaqsSuccess() {
        List<FAQ> faqs = genericDao.getAll();
        assertEquals(3, faqs.size());
    }

    /**
     * Verifies a FAQ is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        FAQ retrievedFaq= (FAQ)genericDao.getById(3);
        assertNotNull(retrievedFaq);
        assertEquals("Taking better photos", retrievedFaq.getTitle());
    }

    /**
     * Verify successful insert of a faq
     */
//    @Disabled
    @Test
    void insertSuccess() {
        User user = (User)userDao.getById(2);
        FAQ newFaq = new FAQ("Test","Trailer","Sme Data for Test", LocalDateTime.now(),user);
        log.debug(newFaq);
        int id = genericDao.insert(newFaq);
        assertNotEquals(0,id);
        FAQ insertedFaq = (FAQ) genericDao.getById(id);
        assertEquals(newFaq,insertedFaq);
        assertTrue(newFaq.equals(insertedFaq));

    }

    /**
     * Verify successful update of faq
     */
    @Test
    void updateSuccess() {
        String newSummary = "This is a new description for Updating this content";
        FAQ faqToUpdate = (FAQ) genericDao.getById(2);
        faqToUpdate.setDescription(newSummary);
        genericDao.saveOrUpdate(faqToUpdate);
        FAQ retrievedFaq = (FAQ) genericDao.getById(2);
        assertTrue(faqToUpdate.equals(retrievedFaq));
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<FAQ> faqs = genericDao.getByPropertyEqual("category", "Movie");
        assertEquals(2, faqs.size());
        assertEquals(1, faqs.get(0).getFaqId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<FAQ> faqs = genericDao.getByPropertyLike("title", "h");
        assertEquals(3, faqs.size());
    }

    /**
     * Verify successful delete of faq
     */
//    @Disabled
    @Test
    void deleteSuccess() {
        genericDao.delete(genericDao.getById(3));
        assertNull(genericDao.getById(3));
    }

}
