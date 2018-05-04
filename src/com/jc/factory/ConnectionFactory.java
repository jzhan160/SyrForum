/////////////////////////////////////////////////////////////////////
// ConnectionFactory.java  factory to create database connection   //
// ver 1.0                                                         //
// Jiacheng Zhang                                                  //
/////////////////////////////////////////////////////////////////////
/*
 * This package provides one Java class which is a factory to get
 * connection to the MySQL
 *
 * Maintenance History:
 * --------------------
 * 04/10/2018
 * ver 1.0
 *
 *
 * */
package com.jc.factory;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;
import com.mysql.jdbc.Connection;

public class ConnectionFactory {
    private static String driver;
    private static String db_url;
    private static String user;
    private static String password;
    private static final ConnectionFactory factory = new ConnectionFactory();
    private Connection connection;
    static {
        Properties properties = new Properties();
        try {
            InputStream in = ConnectionFactory.class.getClassLoader().getResourceAsStream("dbconfig.properties");
            properties.load(in);
        } catch (Exception e) {
            System.out.println("read error");
        }
        driver = properties.getProperty("driver");
        db_url = properties.getProperty("db_url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
    }

    private ConnectionFactory() {

    }

    //--------------------<return the instance of the connection factory>-------------------
    public static ConnectionFactory getInstance() {
        return factory;
    }

    //-------------------<register jdbc driver and get connection>-----------------------
    public Connection makeConnection() {
        try {
            Class.forName(driver);
            connection = (Connection) DriverManager.getConnection(db_url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
