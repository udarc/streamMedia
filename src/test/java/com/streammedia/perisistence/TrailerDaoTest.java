package com.streammedia.perisistence;

import com.streammedia.entity.*;
import com.streammedia.entity.Trailer;
import com.streammedia.test.utility.Database;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Trailer dao test.
 */
@Log4j2
class TrailerDaoTest {

    /**
     * The Generic dao.
     */
    GenericDao genericDao;
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

}
