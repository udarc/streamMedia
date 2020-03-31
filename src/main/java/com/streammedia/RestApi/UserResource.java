package com.streammedia.RestApi;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
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
//        @GET
//        @Produces(MediaType.APPLICATION_JSON)
//        public Response listUser() throws IOException {
//            List<User> userList =  new ArrayList<>();
//            userList.addAll(userDao.getAll());
//            final ByteArrayOutputStream out = new ByteArrayOutputStream();
//            final ObjectMapper mapper = new ObjectMapper();
//
//            mapper.writeValue(out, userList);
//
//            final byte[] data = out.toByteArray();
////            return Response.status(200).entity(userList.toString()).build();
////            log.debug(String.format("Mapper Objects %s", mapper.writeValueAsString(userList)));
////            GenericEntity<List<User>> list = new GenericEntity<List<User>>(userList) {};
//            return Response.ok(new String(data)).build();
//        }
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response listUser() throws IOException {
    List<User> userList =  new ArrayList<>();
    userList.addAll(userDao.getAll());
    OutputStream out = new ByteArrayOutputStream();

    JsonFactory jfactory = new JsonFactory();
    JsonGenerator jGenerator = jfactory.createGenerator(out, JsonEncoding.UTF8);
    ObjectMapper mapper = new ObjectMapper();
    jGenerator.writeStartArray(); // [

    for (User event : userList) {
        String e = mapper.writeValueAsString(event);
        jGenerator.writeRaw(e);
        // here, big hassles to write a comma to separate json objects, when the last object in the list is reached, no comma
    }

    jGenerator.writeEndArray(); // ]

    jGenerator.close();

    System.out.println(out.toString());
//    final ObjectMapper mapper = new ObjectMapper();
////    final StringWriter sw =new StringWriter();
////    log.debug("User Resources List Before: " + new String(sw.toString()));
////    mapper.writeValue(sw, userList);
////    System.out.println(sw.toString());//use toString() to convert to JSON
//
////    sw.close();
////    log.debug("User Resources List: " + sw.toString());
////    return Response.ok(new String(sw.toString())).build();
//    String objUsers =  mapper.writeValueAsString(userList);
    return Response.ok(out.toString()).build();
}
    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByUserName(@PathParam("username") String username) throws JsonProcessingException {
        User user = (User) userDao.getByPropertyEqual("username",username).get(0);
        log.debug("User Resource: " + user.getUsername());
//        GenericEntity<User> aUser  =  new GenericEntity<User>(user) {};
        final ObjectMapper mapper = new ObjectMapper();
        log.debug(mapper.writeValueAsString(user));
        if(!user.equals(null)){
//            log.debug("User Resource By Username: " + aUser.getEntity().getFirstName());
//            log.debug("Response Build:: " + aUser.getEntity());
//            return Response.status(200).entity(aUser.toString()).build();
            String objUser = mapper.writeValueAsString(user);
            log.debug("OBj User : " + objUser);
            return Response.status(200).entity(objUser).build();
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
