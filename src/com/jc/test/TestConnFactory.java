////////////////////////////////////////////////////////////////////
// TestConnFactory.java   test class                              //
// ver 1.0                                                        //
// Author: Group Work                                             //
////////////////////////////////////////////////////////////////////
/*
 * This package provides one Java class TestConnFactory which test
 * the connection factory in this website.
 *
 * Maintenance history
 * Version 1.0
 * 05/04/2018
 *
 * */

package com.jc.test;
import com.jc.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestConnFactory {
    public static  void main(String[] args) throws SQLException {
 /*       ConnectionFactory factory = ConnectionFactory.getInstance();
        Connection conn  = factory.makeConnection();
        System.out.println(conn.getAutoCommit());*/
        List<Integer> list1 = new ArrayList<>();
        list1.add(0);
        for(int i = 1;i<5;i++){
            List<Integer> list2 = getList(i);
            list1.addAll(list2);
        }
        for(Integer i:list1)
            System.out.println(i);
    }
    private static  List<Integer> getList(int i){
        List<Integer> list = new ArrayList<>();
        list.add(i);
        return list;
    }

}
