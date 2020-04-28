package com.streammedia.perisistence;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
public class GenreRESTAPIDaoTest {

    @Test
    public void getGenresSuccess(){

        GenreRESTAPIDao restapiDao =  new GenreRESTAPIDao();
        assertEquals(19,restapiDao.getGenres().getGenres().size());
        assertTrue("Adventure".equals(restapiDao.getGenres().getGenres().get(1).getName()));
    }

}