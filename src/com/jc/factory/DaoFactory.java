////////////////////////////////////////////////////////////////
// DaoFactory.java    factory to create Daos                  //
// ver 1.0                                                    //
// Jiacheng Zhang                                             //
////////////////////////////////////////////////////////////////
/*
 * This package provides one Java class which is a factory to
 * create different Daos.
 *
 * Maintenance History:
 * --------------------
 * 04/10/2018
 * ver 1.0
 *
 *
 * */
package com.jc.factory;

import com.jc.dao.*;

public class DaoFactory {
    private static final UserDao userDao = new UserDao();
    private static final TopicDao topicDao = new TopicDao();
    private static final CommentDao commentDao = new CommentDao();
    private static final ItemDao itemDao = new ItemDao();
    private static final FavoriteDao favoriteDao = new FavoriteDao();
    private static final NoteDao noteDao = new NoteDao();
    private static DaoFactory factory = new DaoFactory();

    //-------------<return different dao according to a parameter>----------------------
    public Dao makeDao(String DaoImpl) {
        Dao dao = null;
        try {
            switch (DaoImpl) {
                case "User":
                    dao = userDao;
                    break;
                case "Topic":
                    dao = topicDao;
                    break;
                case "Item":
                    dao = itemDao;
                    break;
                case "Comment":
                    dao = commentDao;
                    break;
                case "Favorite":
                    dao = favoriteDao;
                    break;
                case "Note":
                    dao = noteDao;
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dao;
    }

    public static DaoFactory getInstance() {
        return factory;
    }

}
