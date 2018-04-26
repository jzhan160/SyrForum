package com.jc.test;

import com.jc.factory.ConnectionFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUploadImage {
    //测试
    public static void main(String[] args) {
        ImageDemo.readImage2DB();
       // ImageDemo.readDB2Image();
    }
}

class ImageUtil {

    // 读取本地图片获取输入流
    public static FileInputStream readImage(String path) throws IOException {
        return new FileInputStream(new File(path));
    }

    // 读取表中图片获取输出流
    public static void readBin2Image(InputStream in, String targetPath) {
        File file = new File(targetPath);
        String path = targetPath.substring(0, targetPath.lastIndexOf("/"));
        if (!file.exists()) {
            new File(path).mkdir();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class ImageDemo {

    // 将图片插入数据库
    public static void readImage2DB() {
        String path = "D:/1.jpg";
        ConnectionFactory factory = ConnectionFactory.getInstance();
        Connection conn  = factory.makeConnection();
        PreparedStatement ps = null;
        FileInputStream in = null;
        try {
            in = ImageUtil.readImage(path);
            String sql = "insert into items (ItemName,Description,CatID,ImagePath,TopicID,Price) " +
                    "values(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "Bot");
            ps.setString(2,"fasfasf");
            ps.setInt(3, 1);
            ps.setBinaryStream(4, in, in.available());
            ps.setInt(5, 1);
            ps.setDouble(6, 45.6);
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // 读取数据库中图片
    public static void readDB2Image() {
        String targetPath = "E:/1.jpg";
        ConnectionFactory factory = ConnectionFactory.getInstance();
        Connection conn  = factory.makeConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String sql = "select * from items where ItemID =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                InputStream in = rs.getBinaryStream("ImagePath");
                ImageUtil.readBin2Image(in, targetPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                conn.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
