/////////////////////////////////////////
// TestDao.java   test class           //
// ver 1.0                             //
// Biao A                              //
/////////////////////////////////////////
/*
 *
 * Maintenance History:
 * --------------------
 * 05/02/2018
 * ver 1.0
 *
 *
 * */
package com.jc.test;
import com.jc.dao.Dao;
import com.jc.entity.*;
import com.jc.factory.ConnectionFactory;
import com.jc.factory.DaoFactory;
import com.mysql.jdbc.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDao {
    static void TestTopicDao(Connection conn) throws SQLException{
        Dao topicDao =  DaoFactory.getInstance().makeDao("Topic");
        Topic topic = new Topic();
        topic.setAddress("Lafayette 221");
        topic.setContact("31555555");
        topic.setTitle("Book: Test1");
        topic.setUsers_UserID(2);
        topic.setId(3);
        //topicDao.create(conn,topic);
        //topicDao.delete(conn,topic);
        //topicDao.update(conn,topic);
        //ResultSet rset = topicDao.read(conn, topic);
        //all passed
    }

    static void TestItemDao(Connection conn) throws SQLException{
        Dao itemDao =  DaoFactory.getInstance().makeDao("Item");
        Item item = new Item();
        item.setCatID(1);
        item.setTopicID(1);
        item.setItemName("book");
        item.setDescription("%demo%");
        item.setImagePath("demo");
        item.setPrice(99);
        item.setId(4);
        //itemDao.create(conn,item); passed
        //ResultSet rset = itemDao.read(conn,item); passed
        //itemDao.update(conn,item); passed
        //itemDao.delete(conn,item);passed
    }

    static void TestCommentDao(Connection conn) throws SQLException{
        Dao commentDao =  DaoFactory.getInstance().makeDao("Comment");
        Comment comment = new Comment();
        comment.setContent("demodddddddd");
        comment.setUsers_UserID(2);
        comment.setId(4);
        //commentDao.create(conn,comment); passed
        //commentDao.update(conn,comment); passed
        //ResultSet rset = commentDao.read(conn,comment); passed
        //commentDao.delete(conn,comment); passed

    }

    static void TestUserDao(Connection conn) throws SQLException{
        Dao userDao = DaoFactory.getInstance().makeDao("User");
        User user = new User();
        //user.setId(7);
        user.setUserName("deee");
        user.setEmail("ba100@syr.edu");
        user.setUserPassword("sssssss");
        user.setUserBirthday("1994-01-01");
        userDao.update(conn,user);
    }

    static void TestFavorite(Connection conn) throws SQLException{
        Dao favorite = DaoFactory.getInstance().makeDao("Favorite");
        Favorite fac = new Favorite();
        fac.setUserID(1);
        fac.setItemID(38);
        favorite.create(conn,fac);
    }

    public static void main(String[] args){
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try{
            conn.setAutoCommit(false);
            //TestTopicDao(conn);
            //TestItemDao(conn);
            //TestCommentDao(conn);
            //TestFavorite(conn);
            conn.commit();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
