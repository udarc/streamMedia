package com.streammedia.perisistence;

import com.streammedia.entity.*;
import com.streammedia.test.utility.Database;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Genre dao test.
 * @author Jeanne
 * @version 1.0
 * @since 05-10-2020
 */
@Log4j2
public class GenreDaoTest {
    /**
     * The Generic dao.
     */
    GenericDao genericDao;
    /**
     * The Film dao.
     */
    GenericDao filmDao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {

        genericDao = new GenericDao(Genre.class);
        filmDao = new GenericDao(Film.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verifies gets all Genres successfully.
     */
    @Test
    void getAllGenresSuccess(){
        List<Genre> genres = genericDao.getAll();
        assertEquals(4,genres.size());
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

    /**
     * Insert genre success.
     */
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
        genreToUpdate.setTitle(newTitle);
        genericDao.saveOrUpdate(genreToUpdate);
        Genre retrievedGenre = (Genre)genericDao.getById(2);
        log.debug("Retrieve" + retrievedGenre);
        log.debug("To Update" + genreToUpdate);
       assertNotNull(retrievedGenre);
        assertTrue(genreToUpdate.equals(retrievedGenre));

    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Genre> genres = genericDao.getByPropertyEqual("title", "Comedy");
        assertEquals(1, genres.size());
        assertEquals(3, genres.get(0).getGenreId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Genre> genres = genericDao.getByPropertyLike("title", "c");
        assertEquals(2, genres.size());
    }

    /**
     * Verify successful delete of genre
     */
    @Test
    void deleteSuccess() {
        Genre  genre = (Genre)genericDao.getById(2);
        Film film = (Film) filmDao.getById(1);
        genre.removeFilm(film);
        genericDao.delete(genre);
        assertNull(genericDao.getById(2));
    }
}
