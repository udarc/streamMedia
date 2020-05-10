package com.streammedia.perisistence;
import com.streammedia.restapi.APIMoviesDB;
import com.streammedia.utility.PropertiesLoader;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Api movie dao test.
 *  @author Jeanne
 * @version 1.0
 *  @since 05-10-2020
 */
@Log4j2
public class APIMovieDaoTest  implements PropertiesLoader {
    /**
     * Get playing movies success.
     */
    @Test
    public void getPlayingMoviesSuccess(){
        APIMovieDao playingMovies =  new APIMovieDao();
        for ( APIMoviesDB item: playingMovies.getPlayingMovies()) {
//            assertTrue(item.getTotalPages() == 58);
            assertTrue(item.getResults().size() == 20);
            assertNotNull(item.getResults());
        }
    }

    /**
     * Get popular movies success.
     */
    @Test
    public void getPopularMoviesSuccess(){
        APIMovieDao popularMovies =  new APIMovieDao();
        for ( APIMoviesDB item: popularMovies.getPopularMovies()) {
//            assertTrue(item.getTotalPages() == 58);
            assertTrue(item.getResults().size() == 20);
            assertNotNull(item.getResults());
        }
    }

    /**
     * Get top rated movies success.
     */
    @Test
    public void getTopRatedMoviesSuccess(){
        APIMovieDao topRatedMovies =  new APIMovieDao();
        for ( APIMoviesDB item: topRatedMovies.getTopRatedMovies()) {
//            assertTrue(item.getTotalPages() == 58);
            assertTrue(item.getResults().size() == 20);
            assertNotNull(item.getResults());
        }
    }

    /**
     * Gets upcoming movies success.
     */
    @Test
    public void getUpcomingMoviesSuccess() {
        APIMovieDao upComingMovies = new APIMovieDao();
        for (APIMoviesDB item : upComingMovies.getUpcomingMovies()) {
//            assertTrue(item.getTotalPages() == 58);
            assertTrue(item.getResults().size() == 20);
            assertNotNull(item.getResults());
        }
    }
}