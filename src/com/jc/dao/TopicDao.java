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
import com.jc.entity.Item;
import com.jc.entity.Topic;
import com.jc.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        readSql += condition + " ORDER BY CreateTime DESC;";
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
        //find items in this topic
        String findItems = "SELECT ItemID FROM items WHERE TopicID ="+topic.getId();
        PreparedStatement psAllItems = conn.prepareCall(findItems);
        ResultSet resultSet = psAllItems.executeQuery();
        List<Integer> items = new ArrayList<Integer>();
        while(resultSet.next()){
            items.add(resultSet.getInt("ItemID"));
        }
        //delete foreign key
        for(int id : items){
            String deleteFKComment = "DELETE FROM comments WHERE ItemID = ?;";
            PreparedStatement psComment = conn.prepareCall(deleteFKComment);
            psComment.setInt(1,id);
            psComment.execute();
        }

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
