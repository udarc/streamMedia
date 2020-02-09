package com.streammedia.utility;
import java.io.*;
import java.util.*;

/**Represnts the class that loads all the properties in a given file
 *with properties extenstion.
 * @author Jeanne d'ARc Uwimana
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
