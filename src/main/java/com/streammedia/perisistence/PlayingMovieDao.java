package com.streammedia.perisistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streammedia.RestApi.PlayingMovies;
import com.streammedia.RestApi.ResultsItem;
import com.streammedia.utility.PropertiesLoader;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Log4j2
public class PlayingMovieDao implements PropertiesLoader {
    public List<PlayingMovies> getPlayingMovies(){
        Client client = ClientBuilder.newClient();
        PlayingMovies movies =  null;
        List<PlayingMovies> items =  new ArrayList<>();
        try {
            Properties restAPi = loadProperties("/restapi.properties");
            ObjectMapper mapper = new ObjectMapper();
            for (int i = 1; i <=2; i++) {
                String URL = restAPi.getProperty("playing.movies") + "&page=" + i;
                WebTarget webTarget =  client.target(URL);
                String response = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
                movies = mapper.readValue(response,PlayingMovies.class);
                items.add(movies);
            }
        }catch (JsonProcessingException e) {
            log.error(e);
        } catch (Exception e) {
            log.error(e);
        }
        return items;
    }
}
