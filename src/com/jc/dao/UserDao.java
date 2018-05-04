//////////////////////////////////////////////////////////////
// UserDao.java   the data access object to the user table  //
// ver 1.0                                                  //
// Author: Biao A, Jiacheng Zhang                           //
//////////////////////////////////////////////////////////////
/*
 * This package provides one Java class which is a data access
 * object to the users table.
 * It provides methods to:
 * (1)insert a new user record into the users table
 * (2)read user information
 * (3)update user information
 * (4)delete a specific user from the users table
 *
 * Maintenance History:
 * --------------------
 * 05/4/2018
 * ver 1.0
 *
 * */
package com.jc.dao;

import com.jc.entity.Entity;
import com.jc.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements Dao {

    //----------------<Insert new entity into the users table>-----------------------
    @Override
    public void create(Connection conn, Entity entity) throws SQLException {
        User user = (User)entity;
        String insertSql = "INSERT INTO users(UserName,UserPassword,CreateTime,IsAdmin) VALUES (?,?,now(),0);";
        PreparedStatement ps = conn.prepareCall(insertSql);
        ps.setString(1,user.getUserName());
        ps.setString(2,user.getUserPassword());
        ps.execute();

    }


    //----------------<update information in the users table>-----------------------
    public void update(Connection conn,Entity entity) throws SQLException {
        User user = (User)entity;
        String condition = "";
        condition += user.getUserName() == null ? " UserName = UserName" : ("UserName = '" + user.getUserName()+ "'");
        condition += user.getUserPassword() == null? " ":(" , UserPassword = '" + user.getUserPassword()+ "'");
        condition += user.getEmail() == null?" ":(" , Email = '" + user.getEmail()+ "'");
        condition += user.getUserBirthday() == null? " ":(" , UserBirthday = '" + user.getUserBirthday()+ "'");
        String updateSql = "UPDATE users SET " + condition + " WHERE UserID = " + user.getId();
        PreparedStatement ps = conn.prepareCall(updateSql);
        ps.execute();

    }


    //----------------<delete the entity from the users table>-----------------------
    @Override
    public void delete(Connection conn,Entity entity) throws SQLException {
        User user = (User)entity;
        String deleteSql = "DELETE FROM users WHERE UserName = ?";
        PreparedStatement ps = conn.prepareCall(deleteSql);
        ps.setString(1,user.getUserName());
        ps.execute();
    }

    //----------------<search entity information from the users table>-----------------------
    @Override
    public ResultSet read(Connection conn, Entity entity) throws SQLException {
        User user = (User) entity;
        String checkSql = "SELECT * FROM users WHERE ";
        String condition = "";
        condition += user.getUserName() == null ?" UserName = UserName":
                (" UserName = '" + user.getUserName() + "'");
        condition += user.getId() == 0? "": (" AND UserID = " + user.getId());
        condition += user.getUserPassword() == null ? "" :
                (" AND UserPassword = '" + user.getUserPassword() + "'");
        condition += user.getEmail() == null ? "" :
                (" OR Email = '" + user.getEmail() + "'");
        checkSql += condition + ";";

        System.out.println(checkSql);
        PreparedStatement ps = conn.prepareCall(checkSql);
        return ps.executeQuery();
    }

}
