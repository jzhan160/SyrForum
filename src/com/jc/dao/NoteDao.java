package com.jc.dao;

import com.jc.entity.Entity;
import com.jc.entity.Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteDao implements Dao {
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
    // ----------------< read by topic id>----------------
    public ResultSet read(Connection conn, Entity entity) throws SQLException {
        //Notification notification = (Notification)entity;
        String readSql = "SELECT * FROM notifications WHERE Viewed = 0 ORDER BY NoteID;";
        PreparedStatement ps = conn.prepareCall(readSql);
        //System.out.println(readSql);
        return ps.executeQuery();
    }

    @Override
    //----------------< update: only content now >-----------------------
    public void update(Connection conn, Entity entity) throws SQLException {
        Notification notification = (Notification) entity;
        String updateSql = "UPDATE notifications SET Viewed = 1 WHERE NoteID <= ?;";
        PreparedStatement ps = conn.prepareCall(updateSql);
        ps.setInt(1, notification.getId());
        ps.execute();
    }

    @Override
    public void delete(Connection conn, Entity entity) throws SQLException {
        String deleteSql = "DELETE FROM notification WHERE Viewed = 1;";
        PreparedStatement ps = conn.prepareCall(deleteSql);
        ps.execute();
    }

}
