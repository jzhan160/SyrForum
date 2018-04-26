package com.jc.entity;

import java.util.HashMap;
import java.util.Map;

public class Comment implements Entity{
    private int ID;
    private int ItemID;
    private String Content;
    private int Users_UserID;
    private String CreateTime;


    @Override
    public int getId() {
        return ID;
    }

    @Override
    public void setId(int id) {
        ID = id;
    }

    @Override
    public String toString() {
        return "Comment [ItemID=" + ItemID + ", Content=" + Content + ", Users_UserID=" + Users_UserID
                + ", CreateTime=" + CreateTime + "]";
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getUsers_UserID() {
        return Users_UserID;
    }

    public void setUsers_UserID(int users_UserID) {
        Users_UserID = users_UserID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
}
