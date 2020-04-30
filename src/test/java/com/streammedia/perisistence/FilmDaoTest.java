package com.streammedia.perisistence;

import com.streammedia.entity.*;
import com.streammedia.test.utility.Database;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@Log4j2
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
    public void getAllFilmsSuccess(){
        List<Film> films = genericDao.getAll();
        assertEquals(3,films.size());
    }
    @Test
    void getByIdSuccess() {
        Film retrievedFilm = (Film)genericDao.getById(3);
        assertNotNull(retrievedFilm);
        assertEquals("Calvin", retrievedFilm.getTitle());
    }

    @Test
    void  insertInMiddleTableWithNewGenreSuccess(){
        User user = (User) userDao.getById(2);
        Genre newGenre = new Genre();
        newGenre.setTitle("Scifi");
        newGenre.setDescription("A documentary film is a non-fictional, motion picture intended to \"document reality, primarily for the purposes of instruction, education, or maintaining a historical record.\"");
        Set<Genre> genres = new HashSet<>();
        Genre genreTwo = new Genre();
        genreTwo.setTitle("Fiction");
        genreTwo.setDescription("No real movie, to reflect reality.");
        genres.add(newGenre);
        genres.add(genreTwo);
        //Add Film
        Film filmOne = new Film();
        filmOne.setTitle("Iron Man");
        filmOne.setPublicationDate(LocalDateTime.now());
        filmOne.setDuration(LocalTime.now());
        filmOne.setDirector("John Doe");
        filmOne.setSummary("This is a test");
        filmOne.setUser(user);

        Set<Film> films = new HashSet<Film>();
        films.add(filmOne);
        filmOne.setGenres(genres);
        genericDao.insert(filmOne);
    }
    @Test
    void  insertMiddleTableWIthExistingGenreSuccess(){
        User user = (User) userDao.getById(2);
        Genre newGenre = (Genre)genreDao.getById(36);
        Genre genreTwo = (Genre) genreDao.getById(37);
        Set<Genre> genres = new HashSet<>();
        genres.add(newGenre);
        genres.add(genreTwo);
        //Add Film
        Film filmOne = new Film();
        filmOne.setTitle("Happy Day");
        filmOne.setPublicationDate(LocalDateTime.now());
        filmOne.setDuration(LocalTime.now());
        filmOne.setDirector("John Doe");
        filmOne.setSummary("This is a test");
        filmOne.setUser(user);
        Set<Film> films = new HashSet<Film>();
        for (Genre genre: genres) {
            filmOne.getGenres().add(genre);
            log.debug("Loop Genre " + genre);
            log.debug("Loop Film One " + filmOne);
            films.add(filmOne);
        }
        log.debug("'Film One " + filmOne);
//        log.debug("'Films " + films);
        int id = genericDao.insert(filmOne);
        assertTrue(id > 0);
        Film  insertfilm = (Film)genericDao.getById(id);
        assertTrue(insertfilm.getTitle().equals(filmOne.getTitle()));
    }
    @Test
    void  insertMiddleTableWIthExistingCrewAndGenreSuccess(){
        User user = (User) userDao.getById(2);
        Crew newCrew = (Crew)crewDao.getById(3);
        Crew crewTwo = (Crew)crewDao.getById(4);
        Genre newGenre = (Genre)genreDao.getById(3);
        Genre genreTwo = (Genre) genreDao.getById(4);
        Set<Crew> crews = new HashSet<>();
        crews.add(newCrew);
        crews.add(crewTwo);
        Set<Genre> genres = new HashSet<>();
        genres.add(newGenre);
        genres.add(genreTwo);
        //Add Film
        Film filmOne = new Film();
        filmOne.setTitle("Hope for Hope");
        filmOne.setPublicationDate(LocalDateTime.now());
        filmOne.setDuration(LocalTime.now());
        filmOne.setDirector("John Doe");
        filmOne.setSummary("This is a test");
        filmOne.setUser(user);
        Set<Film> films = new HashSet<Film>();
        for (Crew crew: crews) {
            filmOne.getCrews().add(crew);
            log.debug("Loop Genre " + crew);
            log.debug("Loop Film One " + filmOne);
        }
        for (Genre genre: genres) {
            filmOne.getGenres().add(genre);
            log.debug("Loop Genre " + genre);
            log.debug("Loop Film One " + filmOne);
        }
        films.add(filmOne);

        log.debug("'Film One " + filmOne);
//        log.debug("'Films " + films);
        int id = genericDao.insert(filmOne);
        assertTrue(id>0);
    }
    @Test
    void  insertMiddleTableWIthExistingCrewSuccess(){
        User user = (User) userDao.getById(2);
        Crew newCrew = (Crew)crewDao.getById(1);
        Crew crewTwo = (Crew)crewDao.getById(2);;
        Set<Crew> crews = new HashSet<>();
        crews.add(newCrew);
        crews.add(crewTwo);
        //Add Film
        Film filmOne = new Film();
        filmOne.setTitle("Home Alone");
        filmOne.setPublicationDate(LocalDateTime.now());
        filmOne.setDuration(LocalTime.now());
        filmOne.setDirector("John Doe");
        filmOne.setSummary("This is a test");
        filmOne.setUser(user);
        Set<Film> films = new HashSet<Film>();
        for (Crew crew: crews) {
            filmOne.getCrews().add(crew);
            log.debug("Loop Genre " + crew);
            log.debug("Loop Film One " + filmOne);
        }
        films.add(filmOne);
        log.debug("'Film One " + filmOne);
//        log.debug("'Films " + films);
        int id = genericDao.insert(filmOne);
        assertTrue(id>0);
    }

    @Test
    void  updateFilmWIthExistingCrewAndGenreSuccess(){

        String newTitle = "Home Sweets";
        Film filmToUpdate = (Film) genericDao.getById(2);
        filmToUpdate.setTitle(newTitle);
        Genre newGenre = (Genre)genreDao.getById(3);
        Set<Genre> genres =  filmToUpdate.getGenres();
        System.out.println(genres.size());
//        genres.clear();
        genres.add(newGenre);
        genericDao.saveOrUpdate(filmToUpdate);
        Film retrieveHFilm = (Film) genericDao.getById(2);
        System.out.println(filmToUpdate.getTitle());
        assertTrue(filmToUpdate.equals(retrieveHFilm));

    }
}
