package com.streammedia.perisistence;

import com.streammedia.RestApi.PlayingMovies;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayingMovieDaoTest {
    @Test
    public void getPlayingMoviesSuccess(){
        APIMovieDao playingMovies =  new APIMovieDao();
        for ( PlayingMovies item: playingMovies.getPlayingMovies()) {
            assertTrue(item.getTotalPages() == 58);
            assertTrue(item.getResults().size() == 20);
            assertNotNull(item.getResults());
        }
    }



}