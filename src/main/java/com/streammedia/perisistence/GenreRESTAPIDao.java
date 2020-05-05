package com.streammedia.perisistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streammedia.restapi.*;
import com.streammedia.utility.PropertiesLoader;
import lombok.extern.log4j.Log4j2;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Properties;

@Log4j2
public class GenreRESTAPIDao implements PropertiesLoader {
    public Genres getGenres(){
        Client client = ClientBuilder.newClient();
        Genres genres= null;
        try {
            Properties restAPis = loadProperties("/rest-api.properties");
            WebTarget target =
                    client.target(restAPis.getProperty("genres.url"));
            String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
            ObjectMapper mapper = new ObjectMapper();
            genres = mapper.readValue(response, Genres.class);
        } catch (JsonProcessingException e) {
            log.error(e);
        }catch (Exception e) {
            log.error(e);
        }
        return genres;
    }
}
