///////////////////////////////////////////////////////////////////////////////////////////
// Notification.java   A JavaBean class which encapsulate Notification information       //
// ver 1.0                                                                               //
// Author: Jiacheng Zhang                                                                //
//////////////////////////////////////////////////////////////////////////////////////////
/*
 * This package provides one Java class which is a JavaBean class encapsulating
 * Notification information.
 *
 * Maintenance History:
 * -------------------
 * May 4th
 * version 1.0
 *
 *
 * */
package com.jc.entity;

public class Notification implements Entity{
    private int NoteID;
    private int UserID;
    private int CommentID;
    private String Author;
    private boolean Viewed;
    private String CommentContent;
    private String CreateTime;
    private String ItemName;
    private int ItemID;

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }

    public int getNoteID() {
        return NoteID;
    }

    public void setNoteID(int noteID) {
        NoteID = noteID;
    }

    public String getCommentContent() {
        return CommentContent;
    }

    public void setCommentContent(String commentContent) {
        CommentContent = commentContent;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    @Override
    public int getId() {
        return NoteID;

    }

    @Override
    public void setId(int id) {
             NoteID = id;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "NoteID=" + NoteID +
                ", UserID=" + UserID +
                ", CommentID=" + CommentID +
                ", Author='" + Author + '\'' +
                ", Viewed=" + Viewed +
                ", CommentContent='" + CommentContent + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", ItemID=" + ItemID +
                '}';
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getCommentID() {
        return CommentID;
    }

    public void setCommentID(int commentID) {
        CommentID = commentID;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public boolean isViewed() {
        return Viewed;
    }

    public void setViewed(boolean viewed) {
        Viewed = viewed;
    }
}
