package com.streammedia.perisistence;

import com.streammedia.entity.*;
import com.streammedia.test.utility.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilmDaoTest {
    GenericDao genericDao;
    GenericDao userDao;
    GenericDao crewDao;
    GenericDao genreDao;

    @BeforeEach
    void setUp(){
        genericDao =  new GenericDao(Film.class);
        userDao = new GenericDao(User.class);
        crewDao =  new GenericDao(Crew.class);
        genreDao = new GenericDao(Genre.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verify that all Crews are retrieved from db
     */
    @Test
    void getAllFilmsSuccess(){
        List<Film> films = genericDao.getAll();
        assertEquals(3,films.size());
    }
}
