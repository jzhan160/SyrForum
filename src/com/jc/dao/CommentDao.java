////////////////////////////////////////////////////////////////////
// CommentDao.java   the data access object to the comments table //
// ver 1.0                                                        //
// Author: Biao A                                                 //
////////////////////////////////////////////////////////////////////
/*
 * This package provides one Java class which is a data access
 * object to the comments table.
 * It provides methods to:
 * (1)insert a new comment record into the comments table
 * (2)read comments by ItemID, CommentID
 * (3)update: only content now
 * (4)delete a comment record by a specific CommentID
 *
 * maintenance history:
 * May 4
 * version 1.0
 *
 *
 * */
package com.jc.dao;

import com.jc.entity.Comment;
import com.jc.entity.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentDao implements Dao {

    //-------------<insert a new comment record into the comments table>-----------------------
    @Override
    public void create(Connection conn, Entity entity) throws SQLException {
        Comment comment = (Comment)entity;
        String insertSql = "INSERT INTO comments (ItemID, Content,Users_UserID,CreateTime)" +
                "VALUES (?,?,?,now());";
        PreparedStatement ps = conn.prepareCall(insertSql);
        ps.setInt(1,comment.getItemID());
        ps.setString(2,comment.getContent());
        ps.setInt(3,comment.getUsers_UserID());
        System.out.println(ps);
        ps.execute();
    }

    @Override
    // ----------------< read comments by ItemID, CommentID>----------------
    public ResultSet read(Connection conn, Entity entity) throws SQLException {
        Comment comment = (Comment)entity;
        String readSql = "SELECT * FROM comments WHERE ";
        String condition = "";
        condition+=comment.getId()==0?"CommentID = CommentID":"CommentID = "+comment.getId();
        condition+=comment.getItemID()==0?"":" AND ItemID = "+comment.getItemID();
        condition+=";";
        readSql+=condition;
        PreparedStatement ps = conn.prepareCall(readSql);
        return ps.executeQuery();
    }

    @Override
    //----------------< update: only content now >-----------------------
    public void update(Connection conn, Entity entity) throws SQLException {
        Comment comment = (Comment)entity;
        String updateSql = "UPDATE comments SET Content = ? WHERE CommentID = ?;";
        PreparedStatement ps = conn.prepareCall(updateSql);
        ps.setString(1,comment.getContent());
        ps.setInt(2,comment.getId());
        ps.execute();
    }

    @Override
    //----------------< delete a comment record by a specific CommentID >-----------------------
    public void delete(Connection conn, Entity entity) throws SQLException {
        Comment comment = (Comment)entity;
        String deleteSql = "DELETE FROM comments " +
                "WHERE CommentID = ?;";
        PreparedStatement ps =  conn.prepareCall(deleteSql);
        ps.setInt(1,comment.getId());
        ps.execute();
    }


}
