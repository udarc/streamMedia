package com.streammedia.restapi;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * The type Book resource.
 */
@Path("/books")
@Stateless
public class BookResource {
    /**
     * Gets all bk categories.
     *
     * @return the all bk categories
     */
    @GET
    public Response getAllBKCategories() {
        return Response.accepted().build();
    }
}
