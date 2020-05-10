package com.streammedia.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.streammedia.entity.*;
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
    private GenericDao roleDao = new GenericDao(Role.class);
    /**
     * The Mapper.
     */
    ObjectMapper mapper;

    /**
     * Gets all users.
     *
     * @return the all users
     * @throws IOException the io exception
     */
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

    /**
     * Gets all roles.
     *
     * @return the all roles
     * @throws IOException the io exception
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/roles")
    public Response getAllRoles() throws IOException {
        List<Role> roleList = roleDao.getAll();
        log.debug("REST API Data");
        mapper =  new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        //GenericEntity<List<User>> list =  new GenericEntity<List<User>>(userList) {};
        String roles = mapper.writeValueAsString(roleList);

        return Response.status(200).entity(roles).build();
    }

    /**
     * Gets user by user name.
     *
     * @param username the username
     * @return the user by user name
     * @throws JsonProcessingException the json processing exception
     */
    @GET
    @Path("/username/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByUserName(@PathParam("username") String username) throws JsonProcessingException {
        User user = (User) userDao.getByPropertyEqual("username",username).get(0);
        mapper = new ObjectMapper();
        if(!user.equals(null)){
            String objUser = mapper.writer().writeValueAsString(user);
            log.debug(objUser);
            return Response.status(200).entity(objUser).build();
        }
        return Response.noContent().build();
    }

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     * @throws JsonProcessingException the json processing exception
     */
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
