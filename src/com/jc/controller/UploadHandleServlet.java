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
    private String saveSpec = "/upload";
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


    private HttpServletRequest uploadFile(HttpServletRequest request, String tempPath, String savePath) {

        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            tmpFile.mkdir();
        }
        String message = "";
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            factory.setSizeThreshold(1024 * 100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
            //设置上传时生成的临时文件的保存目录
            factory.setRepository(tmpFile);
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //监听文件上传进度
            upload.setProgressListener(new ProgressListener() {
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    //System.out.println("file size：" + pContentLength + ",done：" + pBytesRead);
                }
            });
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(request)) {
                return request;
            }

            //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
            upload.setFileSizeMax(1024 * 1024);
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
            upload.setSizeMax(1024 * 1024 * 10);
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            System.out.println("list size:"+ list.size());
            Topic topic = new Topic();
            List<Item> items = new ArrayList<>();
            Item item = new Item();
            String userName = request.getParameter("userName");
            for (FileItem fileItem : list) {
                //如果fileitem中封装的是普通输入项的数据
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

                } else {
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

    private String makeFileName(String filename) {  //2.jpg
        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return UUID.randomUUID().toString() + "_" + filename;
    }

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


