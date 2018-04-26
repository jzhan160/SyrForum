//////////////////////////////////////////////////////////////
// TopicDao.java   the data access object to the user table //
// ver 1.0                                                  //
//                                                          //
//////////////////////////////////////////////////////////////
/*
 * This package provides one Java class which is a data access
 * object to the topics table.
 *
 *
 *
 *
 * */
package com.jc.dao;

import com.jc.entity.Entity;
import com.jc.entity.Topic;
import com.jc.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicDao implements Dao {

    @Override
    public void create(Connection conn, Entity entity) throws SQLException {
        Topic topic = (Topic)entity;
        String insertSql = "INSERT INTO topics (Title,CreateTime,Users_UserID,Contact,Address)" +
                " VALUES (?,now(),?,?,?);";
        PreparedStatement ps = conn.prepareCall(insertSql);
        ps.setString(1,topic.getTitle());
        ps.setInt(2,topic.getUsers_UserID());
        ps.setString(3,topic.getContact());
        ps.setString(4,topic.getAddress());
        ps.execute();
    }

    @Override
    //----------------< read by ID or User_UserID >-----------------------
    public ResultSet read(Connection conn, Entity entity) throws SQLException {
        Topic topic = (Topic)entity;
        String readSql = "SELECT * FROM topics WHERE ";
        String condition = "";
        condition += topic.getId()== 0? "TopicID = TopicID":(" TopicID =" + topic.getId());
        condition += topic.getUsers_UserID() == 0?"":(" AND Users_UserID =" + topic.getUsers_UserID());
        readSql += condition + ";";
        PreparedStatement ps = conn.prepareCall(readSql);
        return ps.executeQuery();
    }

    @Override
    //----------------< update: only Title, Contact and Address now >-----------------------
    public void update(Connection conn, Entity entity) throws SQLException {
        Topic topic = (Topic)entity;
        String insertSql = "UPDATE topics SET Title = ?, Contact = ?, Address = ? WHERE TopicID = ?;";
        PreparedStatement ps = conn.prepareCall(insertSql);
        ps.setString(1,topic.getTitle());
        ps.setString(2,topic.getContact());
        ps.setString(3,topic.getAddress());
        ps.setInt(4,topic.getId());
        System.out.println(ps.toString());
        ps.execute();
    }

    @Override
    //----------------< delete terms by TopicID
    //                         and also delete foreign terms in comments and items>-----------------------
    public void delete(Connection conn, Entity entity) throws SQLException {
        Topic topic = (Topic)entity;
        //delete foreign key
        String deleteFKComment = "DELETE FROM comments WHERE TopicID = ?;";
        PreparedStatement psComment = conn.prepareCall(deleteFKComment);
        psComment.setInt(1,topic.getId());
        psComment.execute();

        String deleteFKItem = "DELETE FROM items WHERE TopicID = ?;";
        PreparedStatement psItem = conn.prepareCall(deleteFKItem);
        psItem.setInt(1,topic.getId());
        psItem.execute();

        String insertSql = "DELETE FROM topics " +
                "WHERE TopicID = ?;";
        PreparedStatement ps = conn.prepareCall(insertSql);
        ps.setInt(1,topic.getId());
        ps.execute();
    }

}
