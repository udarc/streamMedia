package com.streammedia.perisistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streammedia.RestApi.*;
import lombok.extern.log4j.Log4j2;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Log4j2
public class GenreRESTAPIDao {
    Genres getGenres(){
        Client client = ClientBuilder.newClient();
        // TODO read in the uri from a properties file
        WebTarget target =
                client.target("https://api.themoviedb.org/3/genre/movie/list?api_key=37ebc95bb594a84cef6e96dacce6ffa2&language=en-US");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Genres genres= null;
        try {
            genres = mapper.readValue(response, Genres.class);
        } catch (JsonProcessingException e) {
            log.error(e);
        }
        return genres;
    }
}
