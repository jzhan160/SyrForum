/////////////////////////////////////////
// Dao.java   the data access object   //
// ver 1.0                             //
// Jiacheng Zhang                      //
/////////////////////////////////////////
/*
* This package provides one Java class which is an abstract interface to the database.
* It provides some specific data operations of CRUD
*
* Maintenance History:
* --------------------
* 04/10/2018
* ver 1.0
*
*
* */
package com.jc.dao;

import com.jc.entity.Entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Dao {
    public void create(Connection conn, Entity entity) throws SQLException;

    public ResultSet read(Connection conn, Entity entity) throws SQLException;

    public void update(Connection conn, Entity entity) throws SQLException;

    public void delete(Connection conn, Entity entity) throws SQLException;


}
