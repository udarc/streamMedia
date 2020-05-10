package com.streammedia.perisistence;

import com.streammedia.entity.*;
import com.streammedia.test.utility.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * The type Crew dao test.
 * @author Jeanne
 * @version 1.0
 * @since 05-10-2020
 */
public class CrewDaoTest {
    /**
     * The Generic dao.
     */
    GenericDao genericDao;
    /**
     * The User dao.
     */
    GenericDao userDao;
    /**
     * The Film dao.
     */
    GenericDao filmDao;

    /**
     * Set up.
     */
    @BeforeEach
    void setUp(){
        genericDao =  new GenericDao(Crew.class);
        userDao = new GenericDao(User.class);
        filmDao = new GenericDao(Film.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verify that all Crews are retrieved from db
     */
    @Test
    void getAllCrewsSuccess(){
        List<Crew> crews = genericDao.getAll();
        assertEquals(5,crews.size());
    }

    /**
     * Verify that all Crews are retrieved from db
     */
    @Test
    void insertCrewsSuccess(){
        Crew crew = new Crew();
        User user = (User) userDao.getById(2);
        crew.setFirstName("John");
        crew.setLastName("Doe");
        crew.setEmail("jdoe@example.com");
        crew.setProfession("Unknown");
        crew.setBiography("This is a summary");
        crew.setUser(user);
        int id =genericDao.insert(crew);
        assertNotEquals(0,id);
        Crew insertedCrew= (Crew) genericDao.getById(id);
        assertTrue(crew.equals(insertedCrew));
    }

    /**
     * Update crew success.
     */
    @Test
    void updateCrewSuccess(){
        User user = (User) userDao.getById(2);
        Crew crewToUpdate = (Crew) genericDao.getById(2);
        crewToUpdate.setProfession("Nurse");
        crewToUpdate.setBiography("This is an update for Bio");
        genericDao.saveOrUpdate(crewToUpdate);
        Crew retrieveCrew = (Crew) genericDao.getById(2);
        assertTrue(crewToUpdate.equals(retrieveCrew));
        
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Crew> crews = genericDao.getByPropertyEqual("lastName", "Diaz");
        assertEquals(1, crews.size());
        assertEquals(4, crews.get(0).getCrewId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Crew> crews = genericDao.getByPropertyLike("firstName", "a");
        assertEquals(4, crews.size());
    }

    /**
     * Verify successful delete of crews
     */
//    @Disabled
    @Test
    void deleteSuccess() {
        Crew  crew = (Crew)genericDao.getById(5);
        Film film = (Film) filmDao.getById(1);
        crew.removeFilm(film);
        genericDao.delete(crew);
        assertNull(genericDao.getById(5));
    }
}
