package com.streammedia.perisistence;

import com.streammedia.utility.PropertiesLoader;
import lombok.extern.java.Log;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
@Log
public class Database implements PropertiesLoader {
    private static Database instance =  new Database();
    private Properties  properties;
    private Connection connection;

    // private constructor prevents instantiating this class anywhere else
    private Database(){
        try {
            this.properties = loadProperties("../recources/database.properties");
        } catch (Exception exc){
           log.info("Unable to Access Properties" + exc);
        }

    }
    public static Database getInstance(){
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
   public void connect() throws Exception{
        if(connection != null){
            return;
        }
        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException cnfe) {
            throw new  Exception("Database.connect() Error: MySQL Driver not found!");
        }

        String url = properties.getProperty("url");
        connection = DriverManager.getConnection(
                url,properties.getProperty("username")
                ,properties.getProperty("password"));
   }
   public void disconnect() {
        if(connection !=null){
            try {
                connection.close();
            } catch (SQLException sqle){
                log.info("Cannot close connection" + sqle);
            }
        }
        connection = null;
   }
}
