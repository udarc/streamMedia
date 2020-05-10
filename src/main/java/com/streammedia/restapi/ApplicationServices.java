package com.streammedia.restapi;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.*;

/**
 * https://www.logicbig.com/tutorials/java-ee-tutorial/jax-rs/path-annotion-resource-mapping.html
 */
@ApplicationPath("/api")
public class ApplicationServices extends Application {
    /**
     * Gets classes.
     *
     * @return the classes
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet classes = new HashSet<Class<?>>();
        classes.add(UserResource.class);
        classes.add(GenreResource.class);
        return classes;
    }
}
