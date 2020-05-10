package com.streammedia.perisistence;

import com.streammedia.entity.*;
import com.streammedia.entity.Trailer;
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
 * The type Trailer dao test.
 * @author Jeanne
 * @version 1.0
 * @since 05-10-2020
 */
@Log4j2
class TrailerDaoTest {

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
        genericDao =  new GenericDao(Trailer.class);
        userDao = new GenericDao(User.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies gets all trailers successfully.
     */
    @Test
    void getAllTrailersSuccess() {
        List<Trailer> trailers = genericDao.getAll();
        assertEquals(5, trailers.size());
    }

    /**
     * Verifies a trailer is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        Trailer retrievedTrailer = (Trailer)genericDao.getById(3);
        assertNotNull(retrievedTrailer);
        assertEquals("Chantal", retrievedTrailer.getAuthor());
        assertEquals("Peace", retrievedTrailer.getTitle());
    }

    /**
     * Verify successful insert of a trailer
     */
//    @Disabled
    @Test
    void insertSuccess() {
        User user = (User)userDao.getById(2);
        Trailer newTrailer = new Trailer("Test","Author", LocalTime.parse("00:01:20"), LocalDateTime.now(),"Sme Data for Test",LocalDateTime.now(),LocalDateTime.now(),user);
        log.debug(newTrailer);
        int id = genericDao.insert(newTrailer);
        assertNotEquals(0,id);
        Trailer insertedTrailer = (Trailer)genericDao.getById(id);
        assertEquals(newTrailer,insertedTrailer);

    }

    /**
     * Verify successful update of trailer
     */
    @Test
    void updateSuccess() {
        String newSummary = "This is a new summary for Updating this content";
        Trailer trailerToUpdate = (Trailer)genericDao.getById(4);
        trailerToUpdate.setSummary(newSummary);
        genericDao.saveOrUpdate(trailerToUpdate);
        Trailer retrievedTrailer = (Trailer)genericDao.getById(4);
        assertTrue(trailerToUpdate.equals(retrievedTrailer));
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Trailer> trailers = genericDao.getByPropertyEqual("title", "Peace");
        assertEquals(1, trailers.size());
        assertEquals(3, trailers.get(0).getTrailerId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Trailer> trailers = genericDao.getByPropertyLike("title", "n");
        assertEquals(2, trailers.size());
    }

    /**
     * Verify successful delete of trailer
     */
//    @Disabled
    @Test
    void deleteSuccess() {
        genericDao.delete(genericDao.getById(5));
        assertNull(genericDao.getById(5));
    }

}
