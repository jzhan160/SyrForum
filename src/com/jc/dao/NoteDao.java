///////////////////////////////////////////////////////////////////////
// NoteDao.java   the data access object to the notifications table  //
// ver 1.0                                                           //
// Author: Jiacheng Zhang                                            //
///////////////////////////////////////////////////////////////////////
/*
 * This package provides one Java class which is a data access
 * object to the notifications table.
 * It provides methods to:
 * (1)insert a new notification record into the notifications table
 * (2)read all unviewed notifications
 * (3)mark notifications as viewed
 * (4)delete viewed notifications
 *
 * Maintenance History:
 * --------------------
 * 05/4/2018
 * ver 1.0
 *
 * */
package com.jc.dao;

import com.jc.entity.Entity;
import com.jc.entity.Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteDao implements Dao {

    //------------<insert a new notification record into the notifications table>--------------------
    @Override
    public void create(Connection conn, Entity entity) throws SQLException {
        Notification notification = (Notification) entity;
        String insertSql = "INSERT INTO notifications (UserID, CommentID, Viewed, Author) " +
                "VALUES (?,?,0,?);";
        PreparedStatement ps = conn.prepareCall(insertSql);
        ps.setInt(1, notification.getUserID());
        ps.setString(3, notification.getAuthor());
        String readSqlComment = "SELECT * FROM comments ORDER BY CreateTime DESC";
        PreparedStatement readComment = conn.prepareCall(readSqlComment);
        ResultSet rs = readComment.executeQuery();
        rs.next();
        int id = rs.getInt("CommentID");
        ps.setInt(2, id);

        System.out.println(ps);
        ps.execute();
    }

    @Override
    // ----------------< read all unviewed notifications >----------------
    public ResultSet read(Connection conn, Entity entity) throws SQLException {
        Notification notification = (Notification)entity;
        String readSql = "SELECT * FROM notifications WHERE Viewed = 0 AND UserID = "+ notification.getUserID()
                +" ORDER BY NoteID;";
        PreparedStatement ps = conn.prepareCall(readSql);
        //System.out.println(readSql);
        return ps.executeQuery();
    }

    @Override
    //----------------< mark notifications as viewed>-----------------------
    public void update(Connection conn, Entity entity) throws SQLException {
        Notification notification = (Notification) entity;
        String updateSql = "UPDATE notifications SET Viewed = 1 WHERE NoteID <= ?;";
        PreparedStatement ps = conn.prepareCall(updateSql);
        ps.setInt(1, notification.getId());
        ps.execute();
    }


    //---------------------------<delete viewed notifications>-------------------------------
    @Override
    public void delete(Connection conn, Entity entity) throws SQLException {
        String deleteSql = "DELETE FROM notification WHERE Viewed = 1;";
        PreparedStatement ps = conn.prepareCall(deleteSql);
        ps.execute();
    }

}
