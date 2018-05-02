//////////////////////////////////////////////////////////////
// UploadHandleServlet.java  response to post requests      //
// ver 1.0                                                  //
// Author: Jiacheng Zhang                                   //
//////////////////////////////////////////////////////////////
/*
 * This package provides one Java class
 *
 *
 *
 *
 *
 *
 * */
package com.jc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jc.entity.Item;
import com.jc.entity.Topic;
import com.jc.service.Service;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class UploadHandleServlet extends HttpServlet {
    private Service service = new Service();
    private String saveSpec = "/upload"; //directory that saves images post by users
    private String tempSpec = "/temp";

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String savePath = this.getServletContext().getRealPath(saveSpec);
       // System.out.println(savePath);
        String tempPath = this.getServletContext().getRealPath(tempSpec);
       // System.out.println(tempPath);
        request = uploadFile(request, tempPath, savePath);
        request.getRequestDispatcher("/Pages/HomePage/mainPage.jsp").forward(request, response);
    }

    //-------------------<accept data from the form and images>----------------------
    private HttpServletRequest uploadFile(HttpServletRequest request, String tempPath, String savePath) {

        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            tmpFile.mkdir();
        }
        String message = "";
        try {

            DiskFileItemFactory factory = new DiskFileItemFactory();
            //set a upper bound of the image size if going beyond, move to temp dir
            factory.setSizeThreshold(1024 * 100);

            factory.setRepository(tmpFile);

            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setProgressListener(new ProgressListener() {
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    //System.out.println("file size：" + pContentLength + ",done：" + pBytesRead);
                }
            });
            upload.setHeaderEncoding("UTF-8");
            //detect whether data is from the form
            if (!ServletFileUpload.isMultipartContent(request)) {
                return request;
            }

            upload.setFileSizeMax(1024 * 1024);
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
            upload.setSizeMax(1024 * 1024 * 10);
            List<FileItem> list = upload.parseRequest(request);
            System.out.println("list size:"+ list.size());
            Topic topic = new Topic();
            List<Item> items = new ArrayList<>();
            Item item = new Item();
            String userName = request.getParameter("userName");
            for (FileItem fileItem : list) {
                //for common data
                if (fileItem.isFormField()) {
                    String itemName = fileItem.getFieldName();
                    if (itemName.equals("Title"))
                        topic.setTitle(fileItem.getString("UTF-8"));
                    else if (itemName.equals("Address"))
                        topic.setAddress(fileItem.getString("UTF-8"));
                    else if (itemName.equals("Contact"))
                        topic.setContact(fileItem.getString("UTF-8"));
                    else {
                        System.out.println("item name:" + itemName);
                        if (itemName.substring(0, 7).equals("itemdes")) {
                            item.setDescription(fileItem.getString("UTF-8"));
                        } else if (itemName.substring(0, 7).equals("itemnam")) {
                            item.setItemName(fileItem.getString("UTF-8"));
                        } else if (itemName.substring(0, 7).equals("itempri")) {
                            item.setPrice(Double.parseDouble(fileItem.getString("UTF-8")));
                        } else if (itemName.substring(0, 7).equals("itemtyp")) {
                            System.out.println("=============="+fileItem.getString("UTF-8"));
                            if (fileItem.getString("UTF-8").equals("book"))
                                item.setCatID(2);
                            else if (fileItem.getString("UTF-8").equals("car"))
                                item.setCatID(1);
                            else if (fileItem.getString("UTF-8").equals("furniture"))
                                item.setCatID(3);
                        }
                    }

                } else {//for images
                    String filename = fileItem.getName();
                    //System.out.println(filename);
                    if (filename == null || filename.trim().equals("")) {
                        item.setImagePath("");
                        items.add(item);
                        System.out.println(item);
                        item = new Item();
                        continue;
                    }
                    filename = filename.substring(filename.lastIndexOf("\\") + 1); //remove path to get file name
                    //  String fileExtName = filename.substring(filename.lastIndexOf(".")+1); //get extension which can be used to limit file types

                    InputStream in = fileItem.getInputStream();
                    String saveFilename = makeFileName(filename);
                    String[] getPath = makePath(saveFilename, savePath);
                    String realSavePath = getPath[0];
                    FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
                    byte buffer[] = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    item.setImagePath(getPath[1] + "\\" + saveFilename);
                    items.add(item);
                    System.out.println(item);
                    item = new Item();
                    in.close();
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    //item.delete();
                    message = "Upload succeeds！";
                }
            }
            service.addTopic(topic, userName, items);
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            message = "A single file exceeds the maximum size！";
        } catch (FileUploadBase.SizeLimitExceededException e) {
            message = "The total file size exceeds the maximum！";
            e.printStackTrace();
        } catch (Exception e) {
            message = "upload failed！";
            e.printStackTrace();
        } finally {
            request.setAttribute("message", message);
            return request;
        }
    }


    //-------------------<generate a file name>-------------------------------
    private String makeFileName(String filename) {
        return UUID.randomUUID().toString() + "_" + filename;
    }

    //-------------------<generate a unique dir for each user>-------------------------------
    private String[] makePath(String filename, String savePath) {
        int hashcode = filename.hashCode();
        int dir1 = hashcode & 0xf;  //0--15
        int dir2 = (hashcode & 0xf0) >> 4;  //0-15
        String[] dirs = new String[2];
        dirs[0] = savePath + "\\" + dir1 + "\\" + dir2;  //absolute path
        dirs[1] = "\\upload" + "\\" + dir1 + "\\" + dir2;  //relative path for saving in the db

        File file = new File(dirs[0]);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dirs;
    }


}


