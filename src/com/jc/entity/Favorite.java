package com.jc.entity;

public class Favorite implements Entity {
    int ID;
    int CommentID;
    int viewed;
    String Author;

    public int getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public int getCommentID() {
        return CommentID;
    }

    public void setCommentID(int commentID) {
        CommentID = commentID;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

}
