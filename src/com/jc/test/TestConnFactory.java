package com.jc.test;

import com.jc.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnFactory {
    public static  void main(String[] args) throws SQLException {
        ConnectionFactory factory = ConnectionFactory.getInstance();
        Connection conn  = factory.makeConnection();
        System.out.println(conn.getAutoCommit());
    }
}
