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

//    /**
//     * Verify successful insert of a trailer
//     */
////    @Disabled
//    @Test
//    void insertSuccess() {
//        User user = (User)userDao.getById(2);
//        Trailer newTrailer = new Trailer("Test","Author","120",LocalDate.now(),"Sme Data for Test",LocalDate.now(),LocalDate.now(),user);
//        log.debug(newTrailer);
//        int id = genericDao.insert(newTrailer);
//        assertNotEquals(0,id);
//        Trailer insertedTrailer = (Trailer)genericDao.getById(id);
//        assertEquals(newTrailer,insertedTrailer);
//        assertTrue(newTrailer.equals(insertedTrailer));
//
//    }

//    /**
//     * Insert with role success.
//     */
//    @Test
//    void insertWithRoleSuccess() {
//
//        Trailer newTrailer = new Trailer("ujeanne", "ujeanne@streammedia.com", "ujeanne", LocalDate.now(),LocalDate.now());
//        String roleName = "Admin";
//        Role role = new Role();
//        role.setName("Admin");
//        role.setTrailer(newTrailer);
//        role.setCreatedAt(LocalDate.now());
//        newTrailer.addRole(role);
//        int id = genericDao.insert(newTrailer);
//        assertNotEquals(0,id);
//        Trailer insertedTrailer = (Trailer)genericDao.getById(id);
////        assertEquals("ujeanne", insertedTrailer.getTrailername());
////        assertEquals("ujeanne@streammedia.com",insertedTrailer.getEmail());
//        assertTrue(newTrailer.equals(insertedTrailer));
//        assertEquals(1,insertedTrailer.getRoles().size());
//    }
//
//    /**
//     * Verify successful update of trailer
//     */
//    @Test
//    void updateSuccess() {
//        String newLastName = "Davis";
//        Trailer trailerToUpdate = (Trailer)genericDao.getById(3);
//        trailerToUpdate.setLastName(newLastName);
//        genericDao.saveOrUpdate(trailerToUpdate);
//        Trailer retrievedTrailer = (Trailer)genericDao.getById(3);
////        assertEquals(newLastName, retrievedTrailer.getLastName());
////        assertEquals(trailerToUpdate,retrievedTrailer);
//        assertTrue(trailerToUpdate.equals(retrievedTrailer));
//    }
//
//    /**
//     * Verify successful get by property (equal match)
//     */
//    @Test
//    void getByPropertyEqualSuccess() {
//        List<Trailer> trailers = genericDao.getByPropertyEqual("lastName", "Curry");
//        assertEquals(1, trailers.size());
//        assertEquals(3, trailers.get(0).getTrailerId());
//    }
//
//    /**
//     * Verify successful get by property (like match)
//     */
//    @Test
//    void getByPropertyLikeSuccess() {
//        List<Trailer> trailers = genericDao.getByPropertyLike("lastName", "c");
//        assertEquals(3, trailers.size());
//    }
//
//    /**
//     * Verify successful delete of trailer
//     */
////    @Disabled
//    @Test
//    void deleteSuccess() {
//        genericDao.delete(genericDao.getById(5));
//        assertNull(genericDao.getById(5));
//    }
//
//    /**
//     * Delete trailer with r ole success.
//     */
//    @Test
//    void deleteTrailerWithROleSuccess() {
//        GenericDao roleDao = new GenericDao(Role.class);
//        Role role = new Role();
//        Trailer trailer  =  (Trailer)genericDao.getById(1);
//        genericDao.delete(trailer);
//        role = (Role)roleDao.getById(1);
//        assertNull(genericDao.getById(1));
//        assertNull(role);
//    }
}
