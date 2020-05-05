package com.streammedia.restapi;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/books")
@Stateless
public class BookResource {
    @GET
    public Response getAllBKCategories() {
        return Response.accepted().build();
    }
}
