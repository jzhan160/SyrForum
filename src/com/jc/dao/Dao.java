/////////////////////////////////////////
// Dao.java   the data access object   //
// ver 1.0                             //
// Jiacheng Zhang                      //
/////////////////////////////////////////
/*
* This package provides one Java class which is an abstract interface to the database.
* It provides some specific data operations of CRUD
* Dao which is short for data access object. And it not only contains some specific data
* operations but also hides details about SQL statements of the database.
* This kind of isolation is an implementation of  the single responsibility principle.
* Because it separates data access that  the application needs, in terms of domain-specific
* objects and data types (the public interface of the DAO), from how these needs can be satisfied
* with a specific DBMS, database schema, etc. (the implementation of the DAO).
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
