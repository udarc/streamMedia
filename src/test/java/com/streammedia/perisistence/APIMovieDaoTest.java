package com.streammedia.perisistence;
import com.streammedia.RestApi.PlayingMovies;
import com.streammedia.RestApi.PopularMovies;
import com.streammedia.utility.PropertiesLoader;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

@Log4j2
public class APIMovieDaoTest  implements PropertiesLoader {
    @Test
    public void getPlayingMoviesSuccess(){
        APIMovieDao playingMovies =  new APIMovieDao();
        for ( PlayingMovies item: playingMovies.getPlayingMovies()) {
//            assertTrue(item.getTotalPages() == 58);
            assertTrue(item.getResults().size() == 20);
            assertNotNull(item.getResults());
        }
    }

    @Test
    public void getPopularMoviesSuccess(){
        APIMovieDao popularMovies =  new APIMovieDao();
        for ( PopularMovies item: popularMovies.getPopularMovies()) {
//            assertTrue(item.getTotalPages() == 58);
            assertTrue(item.getResults().size() == 20);
            assertNotNull(item.getResults());
        }
    }
    @Test
    public void getTopRatedMoviesSuccess(){
        APIMovieDao popularMovies =  new APIMovieDao();
        for ( PopularMovies item: popularMovies.getTopRatedMovies()) {
//            assertTrue(item.getTotalPages() == 58);
            assertTrue(item.getResults().size() == 20);
            assertNotNull(item.getResults());
        }
    }
    @Test
    public void getUpcomingMoviesSuccess(){
        APIMovieDao popularMovies =  new APIMovieDao();
        for ( PopularMovies item: popularMovies.getUpcomingMovies()) {
//            assertTrue(item.getTotalPages() == 58);
            assertTrue(item.getResults().size() == 20);
            assertNotNull(item.getResults());
        }
    }

}