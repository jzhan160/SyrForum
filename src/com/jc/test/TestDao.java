package com.jc.test;
import com.jc.dao.Dao;
import com.jc.entity.Comment;
import com.jc.entity.Item;
import com.jc.entity.Topic;
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


    public static void main(String[] args){
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try{
            conn.setAutoCommit(false);
            //TestTopicDao(conn);
            //TestItemDao(conn);
            //TestCommentDao(conn);
            conn.commit();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
