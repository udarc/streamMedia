package com.streammedia.perisistence;

import com.streammedia.utility.PropertiesLoader;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UpcomingMoviesDao implements PropertiesLoader {

//    public List<UpComingMoviesDB> getUpcomingMovies(){
//        Client client = ClientBuilder.newClient();
//        UpComingMoviesDB movies =  null;
//        List<UpComingMoviesDB> items =  new ArrayList<>();
//        try {
//            Properties restAPi = loadProperties("/rest-api.properties");
//            ObjectMapper mapper = new ObjectMapper();
//            for (int i = 1; i <=2; i++) {
//                String URL = restAPi.getProperty("upcoming.movies") + "&page=" + i;
//                WebTarget webTarget =  client.target(URL);
//                String response = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
//                movies = mapper.readValue(response,UpComingMoviesDB.class);
//                items.add(movies);
//            }
//        }catch (JsonProcessingException e) {
//            log.error(e);
//        } catch (Exception e) {
//            log.error(e);
//        }
//        return items;
//    }
}
