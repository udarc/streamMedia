package com.streammedia.utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;

/**
 * Represents the class that loads all the properties in a given file
 * with properties extension
 *
 * @author Jeanne
 */
public interface PropertiesLoader {
    /**
     * The constant logger.
     */
    final Logger logger = LogManager.getLogger("PropertiesLoader");

    /**
     * Load properties properties.
     *
     * @param propertiesFilePath the properties file path
     * @return the properties
     * @throws Exception the exception
     */
    default Properties loadProperties(String propertiesFilePath) throws Exception {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (IOException ioException) {
            logger.error(ioException);
            throw ioException;
        } catch (Exception exception) {
            logger.error(exception);
            throw exception;
        }
        return properties;
    }
}
