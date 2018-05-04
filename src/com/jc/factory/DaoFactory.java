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
                case "Note":
                    dao = noteDao;
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
