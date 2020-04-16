package com.streammedia.utility;
import java.io.*;
import java.util.*;

/** Represents the class that loads all the properties in a given file
 * with properties extension
 * @author Jeanne
 *
 */
public interface PropertiesLoader {

    default Properties loadProperties(String propertiesFilePath) throws Exception {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw ioException;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
        return properties;
    }
}
