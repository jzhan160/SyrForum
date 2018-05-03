//////////////////////////////////////////////////////////
// ItemDao.java   the data access object to the user table  //
// ver 1.0                                              //
//                                                      //
//////////////////////////////////////////////////////////
/*
 * This package provides one Java class which is a data access
 * object to the items table.
 *
 *
 *
 *
 * */
package com.jc.service;

import com.jc.dao.Dao;
import com.jc.dao.TopicDao;
import com.jc.entity.*;
import com.jc.factory.ConnectionFactory;
import com.jc.factory.DaoFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Service {

    //check userName and password when logging in
    public boolean check(User user){
        Connection conn = null;
        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);
            Dao userDao =  DaoFactory.getInstance().makeDao("User");
            ResultSet rs = userDao.read(conn,user);
            conn.commit();
            while (rs.next()){
                return true;
            }

        }catch(Exception ex){
            try{
                conn.rollback();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
    //register
    public void register(User user) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try{
            conn.setAutoCommit(false);
            Dao userDao =  DaoFactory.getInstance().makeDao("User");
            userDao.create(conn,user);
            conn.commit();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void changePassword(User user){
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try{
            conn.setAutoCommit(false);
            Dao userDao =  DaoFactory.getInstance().makeDao("User");
            userDao.update(conn,user);
            conn.commit();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // get user information to show in the profile
    public User getUser(User user) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try
        {
            conn.setAutoCommit(false);
            Dao userDao =  DaoFactory.getInstance().makeDao("User");
            ResultSet res = userDao.read(conn,user);
            if(res.next()){
                user.setId(res.getInt("UserID"));
                user.setCreateTime(res.getString("CreateTime"));
                user.setUserBirthday(res.getString("UserBirthday"));
                user.setGender(res.getString("Gender"));
                user.setEmail(res.getString("Email"));
            }

            conn.commit();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public List<Topic> getPreviousTopics(User user){
        Connection conn = null;
        List<Topic> topicList  = new ArrayList<>();
        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);
            Dao userDao = DaoFactory.getInstance().makeDao("User");
            ResultSet rs = userDao.read(conn, user);
            while (rs.next()){  //get userID by userName
                user.setId(rs.getInt("UserID"));
            }
            Topic topic= new Topic();
            List<Integer> ids = new ArrayList<>();
            topic.setUsers_UserID(user.getId());
            Dao topicDao = DaoFactory.getInstance().makeDao("Topic");
            ResultSet topicRs = topicDao.read(conn, topic);
            while (topicRs.next()){
               ids.add(topicRs.getInt("TopicID"));
            }
            for (int i = 0; i <ids.size() ; i++) {
                Topic prevTopic =  readOneTopicByTopicId(ids.get(i));
                prevTopic.setId(ids.get(i));
                topicList.add(prevTopic);
            }
            conn.commit();
        }catch(Exception ex){
            try{
                conn.rollback();
            }catch (Exception e){
                e.printStackTrace();
            }
        }finally {
            return topicList;
        }
    }

    public void addTopic(Topic topic, String userName,List<Item> items){
        Connection conn = null;
        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);
            Dao userDao = DaoFactory.getInstance().makeDao("User");
            User user = new User();
            user.setUserName(userName);
            ResultSet rs = userDao.read(conn, user);
            while (rs.next()){
                topic.setUsers_UserID(rs.getInt("UserID"));
            }
           // System.out.println("userID: "+ topic.getUsers_UserID());
            Dao topicDao =  DaoFactory.getInstance().makeDao("Topic");
            topicDao.create(conn,topic);
            conn.commit();
            //System.out.println("commit items");
            Dao itemDao = DaoFactory.getInstance().makeDao("Item");
            for(Item item : items) {
                System.out.println("Item:"+item);
                itemDao.create(conn, item);
                conn.commit();
            }
        }catch(Exception ex){
            try{
                System.out.println("roll back");
                conn.rollback();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void deleteTopic(int topicId){
        Connection conn = null;
        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            conn.setAutoCommit(false);
            Dao topicDao = DaoFactory.getInstance().makeDao("Topic");
            Topic topic = new Topic();
            topic.setId(topicId);
            topicDao.delete(conn, topic);
            conn.commit();
        }catch(Exception ex){
            try{
                System.out.println("roll back");
                conn.rollback();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    //-----------------< return all topics have the item with this carId s>--------------------------
    public List<Topic> readAllTopic(int catId){
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        List<Topic> topics = new ArrayList<>();
        try{
            conn.setAutoCommit(false);
            Dao itemDao = DaoFactory.getInstance().makeDao("Item");
            Item item = new Item();
            item.setCatID(catId);
            ResultSet rs = itemDao.read(conn,item);
            Set<Integer> idSet = new HashSet<>();
            while(rs.next()) {
                idSet.add(rs.getInt("TopicID"));
            }

            for(int i : idSet){
                Dao topicDao = DaoFactory.getInstance().makeDao("Topic");
                Topic topic = new Topic();
                topic.setId(i);
                ResultSet topicRs = topicDao.read(conn, topic);
                while (topicRs.next()){
                    topic.setTitle(topicRs.getString("Title"));
                    topic.setCreateTime(topicRs.getString("CreateTime"));
                    topic.setAddress(topicRs.getString("Address"));
                    topic.setContact(topicRs.getString("Contact"));
                }
                topics.add(topic);
            }
            conn.commit();

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            return topics;
        }
    }

    public Topic readOneTopicByTopicId(int topicId) {
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        Topic topic = new Topic();
        try {
            conn.setAutoCommit(false);
            Dao topicDao = DaoFactory.getInstance().makeDao("Topic");

            topic.setId(topicId);
            ResultSet rs = topicDao.read(conn, topic);
            while (rs.next()) {
                topic.setTitle(rs.getString("Title"));
                topic.setCreateTime(rs.getString("CreateTime"));
                topic.setContact(rs.getString("Contact"));
                topic.setAddress(rs.getString("Address"));
            }

            conn.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return topic;
    }

    public int countComments(int itemId){
        int count = 0;
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try {
            conn.setAutoCommit(false);
            Dao commentDao = DaoFactory.getInstance().makeDao("Comment");
           Comment comment = new Comment();
            comment.setItemID(itemId);
            ResultSet rs = commentDao.read(conn, comment);
            if(rs.next()) {
                count = rs.getInt("CommentsCount");
            }
            conn.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;

    }

    public Topic readOneTopicByItemId(int itemId) {
        Topic topic = new Topic();
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try {
            conn.setAutoCommit(false);
            Dao itemDao = DaoFactory.getInstance().makeDao("Item");
            Item item = new Item();
            item.setId(itemId);
            ResultSet rs = itemDao.read(conn, item);
            if(rs.next()) {
                int id = rs.getInt("TopicID");
                Dao topicDao = DaoFactory.getInstance().makeDao("Topic");
                topic.setId(id);
                ResultSet topicResult = topicDao.read(conn, topic);

                if (topicResult.next()) {
                    topic.setCreateTime(topicResult.getString("CreateTime"));
                    topic.setContact(topicResult.getString("Contact"));
                    topic.setAddress(topicResult.getString("Address"));
                    topic.setId(topicResult.getInt("TopicID"));
                    topic.setUsers_UserID(topicResult.getInt("Users_UserID"));
                }
            }
            conn.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return topic;
    }



    public List<Item> readItemsByTopicId(int topicId){
        List<Item> items = new ArrayList<>();
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try{
            conn.setAutoCommit(false);
            Dao itemDao = DaoFactory.getInstance().makeDao("Item");
            Item temp = new Item();
            temp.setTopicID(topicId);
            ResultSet rs = itemDao.read(conn,temp);
            while(rs.next()){
                Item item =new Item();
                item.setItemName(rs.getString("ItemName"));
                item.setDescription(rs.getString("Description"));
                item.setPrice(rs.getDouble("Price"));
                item.setImagePath(rs.getString("ImagePath"));
                items.add(item);
            }
            conn.commit();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return items;
    }

    public Item readOneItemByItemId(int itemId){
        Item item = new Item();
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try{
            conn.setAutoCommit(false);
            Dao itemDao = DaoFactory.getInstance().makeDao("Item");
            Item temp = new Item();
            temp.setId(itemId);
            ResultSet rs = itemDao.read(conn,temp);
            int count = 0;
            if(rs.next()){
                item.setItemName(rs.getString("ItemName"));
                item.setDescription(rs.getString("Description"));
                item.setPrice(rs.getDouble("Price"));
                item.setImagePath(rs.getString("ImagePath"));
                count = rs.getInt("ReadingTimes");
                item.setReadingTimes(++count);
            }
            temp.setReadingTimes(count);
            itemDao.update(conn,temp);
            conn.commit();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return item;
    }


    //-----------------< return all topics have the item with this carId s>--------------------------
    public List<Item> readAllItems(int catId){
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        List<Item> items = new ArrayList<>();
        try{
            conn.setAutoCommit(false);
            Dao itemDao = DaoFactory.getInstance().makeDao("Item");
            Item item = new Item();
            item.setCatID(catId);
            ResultSet rs = itemDao.read(conn,item);
            while(rs.next()) {
                Item getItem = new Item();
                getItem.setId(rs.getInt("ItemID"));
                getItem.setItemName(rs.getString("ItemName"));
                getItem.setReadingTimes(rs.getInt("ReadingTimes"));
                items.add(getItem);
            }
            conn.commit();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            return items;
        }
    }


    public List<Item> searchItems(String keyword){
        List<Item> items = new ArrayList<>();
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try{
            conn.setAutoCommit(false);
            Dao itemDao = DaoFactory.getInstance().makeDao("Item");
            Item temp = new Item();
            temp.setItemName(keyword);
            temp.setDescription(keyword);
            ResultSet rs = itemDao.read(conn,temp);
            while(rs.next()){
                Item item =new Item();
                item.setId(rs.getInt("ItemID"));
                item.setItemName(rs.getString("ItemName"));
               // item.setDescription(rs.getString("Description"));
               // item.setPrice(rs.getDouble("Price"));
                item.setCatID(rs.getInt("CatID"));
             //   item.setImagePath(rs.getString("ImagePath"));
                items.add(item);
            }
            conn.commit();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return items;
    }


    public List<Comment> readComments(int itemId){
        List<Comment> comments = new ArrayList<>();
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try{
            conn.setAutoCommit(false);
            Dao commentDao = DaoFactory.getInstance().makeDao("Comment");
            Comment comment = new Comment();
            comment.setItemID(itemId);
            ResultSet rs = commentDao.read(conn,comment);
            while (rs.next()){
                Comment comt = new Comment();
                comt.setContent(rs.getString("Content"));
                comt.setUsers_UserID(rs.getInt("Users_UserID"));
                comt.setId(rs.getInt("CommentID"));
                comt.setCreateTime(rs.getString("CreateTime"));
                comments.add(comt);
            }
            conn.commit();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return comments;
    }

    //-----------------< add a comment in item page >-----------------------------------
    public void addComment(Comment comment,String userName){
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        try{
            conn.setAutoCommit(false);
            Dao userDao = DaoFactory.getInstance().makeDao("User");
            User user = new User();
            user.setUserName(userName);
            ResultSet rs = userDao.read(conn, user);
            while (rs.next()){
                comment.setUsers_UserID(rs.getInt("UserID"));
            }
            Dao commentDao = DaoFactory.getInstance().makeDao("Comment");
            commentDao.create(conn, comment);
            conn.commit();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //------------------< read a user name by its id> -------------------------------
    public String getUserById(int UserId){
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        String name = "";
        try{
            conn.setAutoCommit(false);
            Dao userDao = DaoFactory.getInstance().makeDao("User");
            User user = new User();
            user.setId(UserId);
            ResultSet rs = userDao.read(conn, user);
            if (rs.next()){
                name = rs.getString("UserName");
            }
            conn.commit();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return name;
    }

    //------------------< add a favorite > ------------------------------------------
    public void addFavorite(int userId, int itemId){
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        Dao favorite = DaoFactory.getInstance().makeDao("Favorite");
        Favorite fac = new Favorite();
        fac.setUserID(userId);
        fac.setItemID(itemId);
        try {
            favorite.create(conn,fac);
            conn.commit();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    //------------------< check if a favorite exist > -------------------------------
    public boolean isFavExist(int userId, int itemId){
        boolean flag = false;
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        Dao favorite = DaoFactory.getInstance().makeDao("Favorite");
        Favorite fac = new Favorite();
        fac.setUserID(userId);
        fac.setItemID(itemId);
        ResultSet rs = null;
        try {
            rs = favorite.read(conn,fac);
            if(rs.next()){
                flag = true;
            }
            conn.commit();
        } catch (SQLException e) {
            e.getMessage();
        }
        return flag;
    }

    //------------------< delete a favorite > -------------------------------
    public void delFavorite(int userId, int itemId){
        Connection conn = ConnectionFactory.getInstance().makeConnection();
        Dao favorite = DaoFactory.getInstance().makeDao("Favorite");
        Favorite fac = new Favorite();
        fac.setUserID(userId);
        fac.setItemID(itemId);
        try {
            favorite.delete(conn,fac);
            conn.commit();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
