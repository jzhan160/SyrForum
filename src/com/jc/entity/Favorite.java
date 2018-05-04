///////////////////////////////////////////////////////////////////////////////
// Favorite.java   A JavaBean class which encapsulate Favorite information   //
// ver 1.0                                                                   //
// Author: Biao A                                                            //
///////////////////////////////////////////////////////////////////////////////
/*
 * This package provides one Java class which is a JavaBean class encapsulating
 * Favorite information.
 *
 * Maintenance History:
 * -------------------
 * May 4th
 * version 1.0
 *
 *
 * */
package com.jc.entity;

public class Favorite implements Entity {
    int ID;
    int UserID;
    int ItemID;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "ID=" + ID +
                ", UserID=" + UserID +
                ", ItemID=" + ItemID +
                '}';
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }

    public int getId() {

        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }
}
