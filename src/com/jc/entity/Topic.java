/////////////////////////////////////////////////////////////////////////////
// Topic.java   A JavaBean class which encapsulate Topic information       //
// ver 1.0                                                                 //
// Author: Biao A                                                          //
/////////////////////////////////////////////////////////////////////////////
/*
 * This package provides one Java class which is a JavaBean class encapsulating
 * Topic information.
 *
 * Maintenance History:
 * -------------------
 * May 4th
 * version 1.0
 *
 *
 * */
package com.jc.entity;

public class Topic implements Entity{
    private String Title;
    private String CreateTime;
    private int Users_UserID;
    private String Contact;
    private String Address;
    private int ID;

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
        return "Topic{" +
                "Title='" + Title + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", Users_UserID=" + Users_UserID +
                ", Contact='" + Contact + '\'' +
                ", Address='" + Address + '\'' +
                ", ID=" + ID +
                '}';
    }

    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public String getCreateTime() {
        return CreateTime.substring(0,16);
    }
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
    public int getUsers_UserID() {
        return Users_UserID;
    }
    public void setUsers_UserID(int users_UserID) {
        Users_UserID = users_UserID;
    }
    public String getContact() {
        return Contact;
    }
    public void setContact(String contact) {
        Contact = contact;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }
}
