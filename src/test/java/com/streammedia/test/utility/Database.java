package com.streammedia.test.utility;

import com.streammedia.utility.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * The type Database
 * Provides access the database
 * Created on 6/2/2020
 * @version V0.1
 * @author Jeanne
 */
public class Database  implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());
    // create an object of the class Database
    private static Database instance = new Database();

    private Properties properties;

    private Connection connection;

    // private constructor prevents instantiating this class anywhere else
    private Database() {
        try {
            properties = loadProperties("/database.properties");
        } catch (Exception e) {
            logger.debug("Exception error" + e);
        }

    }

//    private void loadProperties() {
//        properties = new Properties();
//        try {
//            properties.load (this.getClass().getResourceAsStream("/database.properties"));
//        } catch (IOException ioe) {
//            System.out.println("Database.loadProperties()...Cannot load the properties file");
//            ioe.printStackTrace();
//        } catch (Exception e) {
//            System.out.println("Database.loadProperties()..." + e);
//            e.printStackTrace();
//        }
//
//    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
// get the only Database object available
    public static Database getInstance() {
        return instance;
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Connect.
     *
     * @throws Exception the exception
     */
    public void connect() throws Exception {
        if (connection != null)
            return;

        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            throw new Exception("Database.connect()... Error: MySQL Driver not found");
        }

        String url = properties.getProperty("url");
        connection = DriverManager.getConnection(url, properties.getProperty("username"),  properties.getProperty("password"));
    }

    /**
     * Disconnect. Closes the connection to the database.
     */
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Cannot close connection" + e);
            }
        }

        connection = null;
    }

    /**
     * Run the sql.
     *
     * @param sqlFile the sql file to be read and executed line by line
     */
    public void runSQL(String sqlFile) {

        Statement stmt = null;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(sqlFile);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect();
            stmt = connection.createStatement();

            while (true) {
                String sql = br.readLine();
                if (sql == null) {
                    break;
                }
                stmt.executeUpdate(sql);

            }
        } catch (SQLException se) {
            logger.error(se);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            disconnect();
        }

    }
}