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


//    {
//        try (
//                InputStream input = getClass().getClassLoader().getResourceAsStream("contact.properties")) {
//
//            mailProperties = new Properties();
//
//            //load a properties file from class path, inside static method
//            mailProperties.load(input);
//            receiverEmail = mailProperties.getProperty("email");
//            senderPassword =  mailProperties.getProperty("contact-password");
//        } catch (
//                IOException ex) {
//            ex.printStackTrace();
//        }
//    }
}
