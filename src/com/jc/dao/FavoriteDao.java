/////////////////////////////////////////
// Dao.java   the data access object   //
// ver 1.0                             //
// Biao A                              //
/////////////////////////////////////////
/*
 * This package provides one Java class which is a data access
 * object to the Favorite table.
 *
 * Maintenance History:
 * --------------------
 * 04/10/2018
 * ver 1.0
 *
 *
 * */
package com.jc.dao;

import com.jc.entity.Comment;
import com.jc.entity.Entity;
import com.jc.entity.Favorite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FavoriteDao implements Dao{

    public void create(Connection conn, Entity entity) throws SQLException{
        Favorite favorite = (Favorite) entity;
        String insertSql = "INSERT INTO favorites (UserID, ItemID)" +
                "VALUES (?,?);";
        PreparedStatement ps = conn.prepareCall(insertSql);
        ps.setInt(1,favorite.getUserID());
        ps.setInt(2,favorite.getItemID());
        System.out.println(ps);
        ps.execute();
    }

    public ResultSet read(Connection conn, Entity entity) throws SQLException{
        Favorite favorite = (Favorite) entity;
        String readSql = "SELECT * FROM favorites WHERE ";
        String condition = "";

        condition += favorite.getUserID()== 0? "UserID = UserID":(" UserID = " + favorite.getUserID());
        condition += favorite.getItemID() == 0?" ":(" AND ItemID = " + favorite.getItemID());
        readSql+= condition + ";";

        PreparedStatement ps = conn.prepareCall(readSql);
        System.out.println(ps);
        return ps.executeQuery();
    }

    public void update(Connection conn, Entity entity) throws SQLException{

    }

    public void delete(Connection conn, Entity entity) throws SQLException{
        Favorite favorite = (Favorite) entity;
        String readSql = "DELETE From favorites WHERE UserID = ? AND ItemID = ?;";
        PreparedStatement ps = conn.prepareCall(readSql);
        ps.setInt(1,favorite.getUserID());
        ps.setInt(2,favorite.getItemID());
        ps.execute();
    }
}
