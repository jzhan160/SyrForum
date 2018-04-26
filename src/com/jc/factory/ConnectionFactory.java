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
            System.out.println("读取错误");
        }
        driver = properties.getProperty("driver");
        db_url = properties.getProperty("db_url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
    }

    private ConnectionFactory() {

    }

    public static ConnectionFactory getInstance() {
        return factory;
    }
    public Connection makeConnection() { // 获取数据库连接
        try {
            Class.forName(driver);   //注册MySQL jdbc驱动程序
            connection = (Connection) DriverManager.getConnection(db_url, user, password);
            //获取数据库连接 url 用户名 密码
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
