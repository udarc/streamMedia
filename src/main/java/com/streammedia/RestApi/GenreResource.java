package com.streammedia.RestApi;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.streammedia.entity.Genre;
import com.streammedia.perisistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type Genre resource.
 * Fix om.fasterxml.jackson.databind.JsonMappingException: failed to lazily initialize a collection of
 * https://stackoverflow.com/questions/40807416/com-fasterxml-jackson-databind-jsonmappingexception-failed-to-lazily-initialize
 */
@Log4j2
@Path("/rest-genres")
public class GenreResource {
    /**
     * The Genre dao.
     */
    GenericDao genreDao =  new GenericDao(Genre.class);

    /**
     * Gets json genres.
     *
     * @return the json genres
     * @throws JsonProcessingException the json processing exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJSONGenres() throws JsonProcessingException {
        List<Genre> genres = genreDao.getAll();
        ObjectMapper mapper =  new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(genres);
        log.error(json);
        return Response.ok().entity(json).build();
    }
}
