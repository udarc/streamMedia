package com.streammedia.RestApi;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.streammedia.entity.User;
import com.streammedia.perisistence.GenericDao;

import lombok.extern.log4j.Log4j2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import java.io.IOException;
import java.util.*;


/**
 * https://www.connect2java.com/webservices/jax-rs-applicationpath-annotationno-web-xml-example-in-jersey/
 * The link below is a Good tutorial for creating REST API
 * https://www.javaguides.net/2018/09/jax-rs-tutorial.html
 */

@Log4j2
@Path("/users")
public class UserResource {
    private GenericDao userDao = new GenericDao(User.class);
    ObjectMapper mapper;
        @GET
        @Produces({MediaType.APPLICATION_JSON})
        public Response getAllUsers() throws IOException {
            List<User> userList = userDao.getAll();
            log.debug("REST API Data");
            mapper =  new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
           //GenericEntity<List<User>> list =  new GenericEntity<List<User>>(userList) {};
            String users = mapper.writeValueAsString(userList);

            return Response.status(200).entity(users).build();
        }

    @GET
    @Path("/username/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByUserName(@PathParam("username") String username) throws JsonProcessingException {
        User user = (User) userDao.getByPropertyEqual("username",username).get(0);
        mapper = new ObjectMapper();
        if(!user.equals(null)){
            String objUser = mapper.writer().writeValueAsString(user);
            System.out.println(objUser);
            return Response.status(200).entity(objUser).build();
        }
        return Response.noContent().build();
    }
    @GET
    @Path("{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserById(@PathParam("userId") int id) throws JsonProcessingException {
        User user = (User) userDao.getById(id);
        mapper = new ObjectMapper();
        if(!user.equals(null)){
            String objUser = mapper.writer().writeValueAsString(user);;
            return Response.status(200).entity(objUser).build();
        }
        return Response.noContent().build();
    }
}
