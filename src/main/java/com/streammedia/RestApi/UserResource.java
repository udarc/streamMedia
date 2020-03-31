package com.streammedia.RestApi;

import com.streammedia.entity.User;
import com.streammedia.perisistence.GenericDao;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.*;

/**
 * https://www.connect2java.com/webservices/jax-rs-applicationpath-annotationno-web-xml-example-in-jersey/
 * The link below is a Good tutorial for creating REST API
 * https://www.javaguides.net/2018/09/jax-rs-tutorial.html
 */

@Log4j2
@Stateless
@Path("/users")
public class UserResource {
    private GenericDao userDao = new GenericDao(User.class);
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response listUser() {
            List<User> userList =  new ArrayList<>();
            userList.addAll(userDao.getAll());
//            return Response.status(200).entity(userList.toString()).build();
            return Response.status(200).entity(userList).build();
        }
    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByUserName(@PathParam("username") String username) {
        User user = (User) userDao.getByPropertyEqual("username",username).get(0);
        log.debug("User Resource: " + user.getUsername());
        GenericEntity<User> aUser  =  new GenericEntity<User>(user) {};
        if(!user.equals(null)){
            log.debug("User Resource By Username: " + aUser.getEntity().getFirstName());
            log.debug("Response Build:: " + aUser.getEntity());
//            return Response.status(200).entity(aUser.toString()).build();
            return Response.status(200).entity(aUser).build();
        }
        return Response.noContent().build();

    }
//    @GET
//    // The Java method will produce content identified by the MIME Media type "text/plain"
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("{name}")
//    public Response getMessage(@PathParam("name") String name) {
//        // Return a simple message
//        String output = "Hello World " + name ;
//        return Response.status(200).entity(output).build();
//    }
}
