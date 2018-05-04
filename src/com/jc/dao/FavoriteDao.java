//////////////////////////////////////////////////////////////////////
// FavoriteDao.java   the data access object to the favorites table //
// ver 1.0                                                          //
// Author: Biao A                                                   //
//////////////////////////////////////////////////////////////////////
/*
 * This package provides one Java class which is a data access
 * object to the favorite table.
 * It provides methods to:
 * (1)insert a new favorite record into the favorites table
 * (2)read favorites by ItemID, UserID
 * (3)update: no need to implement now
 * (4)delete a Favorite record by a specific UserID and ItemID
 *
 * Maintenance History:
 * --------------------
 * 05/4/2018
 * ver 1.0
 *
 *
 * */
package com.jc.dao;

import com.jc.entity.Entity;
import com.jc.entity.Favorite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FavoriteDao implements Dao{

    //----------<insert a new favorite record into the favorites table>-------
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

    //----------<read favorites by ItemID, UserID>-------
    public ResultSet read(Connection conn, Entity entity) throws SQLException{
        Favorite favorite = (Favorite) entity;
        String readSql = "SELECT * FROM favorites WHERE ";
        String condition = "";

        condition += favorite.getUserID()== 0? "UserID = UserID":(" UserID = " + favorite.getUserID());
        condition += favorite.getItemID() == 0?" ":(" AND ItemID = " + favorite.getItemID());
        readSql+= condition + ";";

        System.out.println(readSql);
        PreparedStatement ps = conn.prepareCall(readSql);
        return ps.executeQuery();
    }

    //----------<no need to implement now>-------
    public void update(Connection conn, Entity entity) throws SQLException{

    }

    //----------<delete a Favorite record by a specific UserID and ItemID>-------
    public void delete(Connection conn, Entity entity) throws SQLException{
        Favorite favorite = (Favorite) entity;
        String readSql = "DELETE From favorites WHERE UserID = ? AND ItemID = ?;";
        PreparedStatement ps = conn.prepareCall(readSql);
        ps.setInt(1,favorite.getUserID());
        ps.setInt(2,favorite.getItemID());
        ps.execute();
    }
}
