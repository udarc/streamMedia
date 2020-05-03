package com.streammedia.perisistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streammedia.RestApi.APIMoviesDB;
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
public class APIMovieDao implements PropertiesLoader {
    public List<APIMoviesDB> getPlayingMovies(){
        String url = "playing.movies";
        return getApiMoviesDBS(url);
    }
    public List<APIMoviesDB> getPopularMovies(){
        String url = "popular.movies";
        return getApiMoviesDBS(url);
    }

    public List<APIMoviesDB> getTopRatedMovies(){
        String url = "top.rated.movies";
        return getApiMoviesDBS(url);
    }

    public List<APIMoviesDB> getUpcomingMovies(){
        String url = "upcoming.movies";
        return getApiMoviesDBS(url );
    }


    private List<APIMoviesDB> getApiMoviesDBS(String url) {
        Client client = ClientBuilder.newClient();
        APIMoviesDB movies = null;
        List<APIMoviesDB> items = new ArrayList<>();
        try {
            Properties restAPi = loadProperties("/rest-api.properties");
            getMovieList(url, client, items, restAPi);
        } catch (JsonProcessingException e) {
            log.error(e);
        } catch (Exception e) {
            log.error(e);
        }
        return items;
    }

    private void getMovieList(String url, Client client, List<APIMoviesDB> items, Properties restAPi) throws JsonProcessingException {
        APIMoviesDB movies;
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 1; i <= 2; i++) {
            String URL = restAPi.getProperty(url) + "&page=" + i;
            WebTarget webTarget = client.target(URL);
            String response = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
            movies = mapper.readValue(response, APIMoviesDB.class);
            items.add(movies);
        }
    }

}
