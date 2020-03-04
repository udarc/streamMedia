package com.streammedia.perisistence;

import com.streammedia.entity.*;
import com.streammedia.test.utility.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenreDaoTest {
    GenericDao genericDao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {

        genericDao = new GenericDao(Genre.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }
    /**
     * Verifies gets all Genres successfully.
     */
    @Test
    void getAllGenresSuccess(){
        List<Genre> genres = genericDao.getAll();
        assertEquals(3,genres.size());
    }
    /**
     * Verifies a genre is returned correctly based on id search
     */
    @Test
    void getGenreByIdSuccess(){
        Genre genre =  (Genre)genericDao.getById(2);
        assertTrue("Horror".equals(genre.getTitle()));
        assertNotNull(genre);

    }
    @Test
    void  insertGenreSuccess(){
        Genre newGenre = new Genre();
        newGenre.setTitle("Documentary");
        newGenre.setDescription("A documentary film is a non-fictional, motion picture intended to \"document reality, primarily for the purposes of instruction, education, or maintaining a historical record.\"");
        int id = genericDao.insert(newGenre);
        assertTrue(id > 0);
        assertTrue(newGenre.getTitle().equals("Documentary"));
    }

    /**
     * Verify successful update of genre
     */
    @Test
    void updateGenreSuccess() {
        String newTitle = "Horror";
        Genre genreToUpdate = (Genre)genericDao.getById(2);
        assertNotNull(genreToUpdate);
        System.out.println(genreToUpdate);
        genreToUpdate.setTitle(newTitle);
        genericDao.saveOrUpdate(genreToUpdate);
        Genre retrievedGenre = (Genre)genericDao.getById(2);
        System.out.println(retrievedGenre);
       assertNotNull(retrievedGenre);
        assertTrue(genreToUpdate.equals(retrievedGenre));
    }

}
