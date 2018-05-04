/////////////////////////////////////////////////////////////////////////////
// Category.java   A JavaBean class which encapsulate category information //
// ver 1.0                                                                 //
// Author: Biao A                                                          //
/////////////////////////////////////////////////////////////////////////////
/*
 * This package provides one Java class which is a JavaBean class encapsulating
 * category information.
 *
 * Maintenance History:
 * -------------------
 * May 4th
 * version 1.0
 *
 *
 * */
package com.jc.entity;

public class Category implements Entity{
    private String Name;
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
        return "Category [Name=" + Name + "]";
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
