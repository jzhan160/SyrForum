///////////////////////////////////////////////////////////////
// ItemDao.java   the data access object to the items table  //
// ver 1.0                                                   //
// Author: Biao A                                            //
///////////////////////////////////////////////////////////////
/*
 * This package provides one Java class which is a data access
 * object to the items table.
 * It provides methods to:
 * (1)Insert s new item into the items table
 * (2)search entity information from the items table search by ID, Name or rex of description
 * (3)update information in the items table
 * (4)delete the item from the items table
 *
 * Maintenance History:
 * --------------------
 * 05/4/2018
 * ver 1.0
 *
 * */
package com.jc.dao;

import com.jc.entity.Entity;
import com.jc.entity.Item;


import javax.print.DocFlavor;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDao implements Dao {

    //----------------<Insert s new item into the items table>-----------------------
    @Override
    public void create(Connection conn,Entity entity) throws SQLException {
        Item item = (Item)entity;
        String insertSql = "INSERT INTO items (ItemName,Description,CatID,ImagePath,TopicID,Price)" +
                "VALUES (?,?,?,?,?,?);";
        PreparedStatement ps = conn.prepareCall(insertSql);
        ps.setString(1,item.getItemName());
        ps.setString(2,item.getDescription());
        ps.setInt(3,item.getCatID());
        ps.setString(4,item.getImagePath());

        if(item.getTopicID() == 0){
            String readSqlTopic = "SELECT * FROM topics ORDER BY CreateTime DESC";
            PreparedStatement readTopic = conn.prepareCall(readSqlTopic);
            ResultSet rs = readTopic.executeQuery();
            rs.next();
            int id = rs.getInt("TopicID");
            ps.setInt(5,id);
        }
        else
            ps.setInt(5,item.getTopicID());
        ps.setDouble(6,item.getPrice());
        ps.execute();
    }

    /*----------------<search entity information from the items table
                              search by ID, Name or rex of description>-----------------------*/
    @Override
    public ResultSet read(Connection conn, Entity entity) throws SQLException {
        Item item = (Item)entity;
        String readSql = "SELECT * FROM items WHERE ";
        String condition = "";
        condition += item.getId()== 0? "ItemID = ItemID":(" ItemID =" + item.getId());
        condition += item.getTopicID() == 0?" ":(" AND TopicID = " + item.getTopicID());
        condition += item.getItemName() == null? " ":(" AND (ItemName LIKE '%"+ item.getItemName()+ "%'");
        condition += item.getDescription() == null?" ":(" OR Description LIKE '%" + item.getDescription() + "%')");
        condition += item.getCatID() == 0?" ":(" AND CatID = " + item.getCatID());
        condition+=" ORDER BY ItemID DESC";
        readSql += condition + ";";
     //   System.out.println(readSql);
        PreparedStatement ps = conn.prepareCall(readSql);
        return  ps.executeQuery();
    }

    //----------------<update information in the items table>-----------------------
    @Override
    public void update(Connection conn, Entity entity) throws SQLException {
        Item item = (Item)entity;
        String insertSql = "UPDATE items SET ReadingTimes = ? " +
                "WHERE ItemID = ?;";
        PreparedStatement ps = conn.prepareCall(insertSql);
        ps.setInt(1,item.getReadingTimes());
        ps.setInt(2,item.getId());

        ps.execute();
    }

    //----------------<delete the item from the items table>-----------------------
    @Override
    public void delete(Connection conn, Entity entity) throws SQLException {
        Item item = (Item)entity;

        //delete FK item from favorite table
        String deleteFKFavorite = "DELETE FROM favorites WHERE ItemID = ?;";
        PreparedStatement psFavorite = conn.prepareCall(deleteFKFavorite);
        psFavorite.setInt(1,item.getId());
        psFavorite.execute();

        //find FK item from comments table
        String findComments = "SELECT CommentID FROM comments WHERE ItemID ="+item.getId();
        PreparedStatement psAllComments = conn.prepareCall(findComments);
        ResultSet resultSet = psAllComments.executeQuery();
        List<Integer> comments = new ArrayList<Integer>();
        while(resultSet.next()){
            comments.add(resultSet.getInt("CommentID"));
        }

        //delete FK comment from notification table
        for(int id : comments){
            String deleteFKNotification = "DELETE FROM notifications WHERE CommentID = " +id;
            PreparedStatement psNote = conn.prepareCall(deleteFKNotification);
            psNote.execute();
        }
        //delete item from items table
        String deleteSql = "DELETE FROM items WHERE ItemID = ?";
        PreparedStatement ps = conn.prepareCall(deleteSql);
        ps.setInt(1,item.getId());
        ps.execute();
    }


}
