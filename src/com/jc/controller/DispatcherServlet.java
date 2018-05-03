//////////////////////////////////////////////////////////////
// DispatcherServlet.java  response to web requests         //
// ver 1.0                                                  //
// Author: Jiacheng Zhang, Cankan He, Biao A                //
//////////////////////////////////////////////////////////////
/*
 * This package provides one Java class DispatcherServlet
 * which extends HttpServlet. DispatcherServlet will receive
 * different requests from clients and dispatcher them by their
 * method types.
 *
 *
 *
 *
 *
 *
 * */
package com.jc.controller;

import com.jc.entity.*;
import com.jc.service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DispatcherServlet")
public class DispatcherServlet extends HttpServlet {

    private Service service = new Service();

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String method = req.getParameter("method");
        dispatcher(method, req, res);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    //----------------<assign requests to different services according to the method parameter>-------------------------------
    private void dispatcher(String method, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if ("register".equals(method)) {
                register(request, response);
            } else if ("login".equals(method)) {
                login(request, response);
            } else if ("showUsers".equals(method)){
                showUsers(request,response);
            }
            else if ("showItems".equals(method)){
                showItems(request,response);
            }else if ("deleteItem".equals(method)){
                deleteItem(request,response);
            }
            else if ("profile".equals(method)) {
                profile(request, response);
            } else if ("main".equals(method)) {
                main(request, response);
            } else if ("view".equals(method)) {
                view(request, response);
            } else if ("addComment".equals(method)) {
                addComment(request, response);
            } else if ("logout".equals(method)) {
                logout(request, response);
            } else if ("personalTopic".equals(method)) {
                personalTopic(request, response);
            } else if ("search".equals(method)) {
                search(request, response);
            } else if ("item".equals(method)) {
                item(request, response);
            } else if ("editPassword".equals(method)) {
                editPassword(request, response);
<<<<<<< HEAD
=======
            } else if ("deleteTopic".equals(method)) {
                deleteTopic(request, response);
            }else if("favorite".equals(method)){
                favorite(request,response);
>>>>>>> 4b008122afccbb4442e1f157fdd0ef9936552746
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //------------------<method to deal with the login request>----------------------------
    private void login(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, SQLException {
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("password");
        RequestDispatcher requestDispatcher = null;
        String forward = null;
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(passWord);

        boolean result = service.check(user);
        if (result) {
            if (userName.equals("admin"))
                forward = "admin.jsp";
            else
            //request redirect cannot catch form information
            //res.sendRedirect(req.getContextPath()+"/Pages/mainPage.jsp");
            //request dispatcher can take form information
            forward = "/Pages/HomePage/mainPage.jsp";
            // req.setAttribute("userName", userName);
        } else {
            //res.sendRedirect(req.getContextPath()+"/Pages/error.jsp");
            req.setAttribute("loginMsg", "error");
            forward = "/Pages/LoginAndRegister/login.jsp";
        }
        requestDispatcher = req.getRequestDispatcher(forward);
        requestDispatcher.forward(req, res);
        //requestDispatcher cannot go to address out of the current application
        //because req.getRequestDispatcher(arg) arg will get a suffix of current
        //application directory, e.g. localhost:8080/ServletDemo/
        //But sendRedirect can go outside.
    }

    private void admin(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, SQLException{

    }

    //------------------<method to deal with the register request>----------------------------
    private void register(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, SQLException {
        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String passWord = req.getParameter("password");
        String gender = req.getParameter("gender");
        RequestDispatcher requestDispatcher = null;
        String forward = null;
        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        boolean result = service.check(user);
        if (!result) {
            user.setUserPassword(passWord);
            user.setGender(gender);
            service.register(user);
            forward = "/Pages/HomePage/mainPage.jsp";
        } else {
            forward = "/Pages/LoginAndRegister/login.jsp";
            req.setAttribute("registerMsg", "error");
        }
        requestDispatcher = req.getRequestDispatcher(forward);
        requestDispatcher.forward(req, res);
    }

    //------------------<method to deal with the showing profile request>----------------------------
    private void profile(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, SQLException {
        String userName = req.getParameter("userName");
        User user = new User();
        user.setUserName(userName);
        user = service.getUsers(user).get(0);
        req.setAttribute("userName", userName);
        req.setAttribute("birthday", user.getUserBirthday());
        req.setAttribute("createTime", user.getCreateTime());
        req.setAttribute("email", user.getEmail());
        List<Item> items = service.getPreviousItems(user);
        List<ItemInfo> itemsInfo = new ArrayList<>();
        for (Item item: items){
            ItemInfo itemInfo = new ItemInfo();
            Topic topic = service.readOneTopicByItemId(item.getId());
            itemInfo.setItem(item);
            itemInfo.setCommentCount(service.readComments(item.getId()).size());
            itemInfo.setCreateTime(topic.getCreateTime());
            itemInfo.setUserName(service.getUserById(topic.getUsers_UserID()));
            itemsInfo.add(itemInfo);
        }
        req.setAttribute("itemsInfo", itemsInfo);
        RequestDispatcher rDispatcher = req.getRequestDispatcher("/Pages/Profile/myprofile.jsp");
        rDispatcher.forward(req, res);
    }


    //------------------<method to deal with the editing password request>----------------------------
    private void editPassword(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, SQLException {
        String newPassword = req.getParameter("password");
        String forward = "";
        RequestDispatcher rDispatcher = null;
        if (newPassword == null)
            forward = "/Pages/Profile/editpassword.jsp";
        else {
            String userName = req.getParameter("userName");
            User user = new User();
            user.setUserName(userName);
            user.setId(service.getUsers(user).get(0).getId());
            user.setUserPassword(newPassword);
            service.changePassword(user);
            forward = "DispatcherServlet?method=profile&userName=" + userName;
        }
        rDispatcher = req.getRequestDispatcher(forward);
        rDispatcher.forward(req, res);
    }

    //------------------<method to deal with the seeing previous posted topics request>----------------------------
    private void personalTopic(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, SQLException {
        int topicId = Integer.parseInt(req.getParameter("topicId"));
        Topic topic = service.readOneTopicByTopicId(topicId);
        topic.setId(topicId);
        List<Item> items = service.readItemsByTopicId(topicId);
        req.setAttribute("topic", topic);
        req.setAttribute("items", items);
        RequestDispatcher rDispatcher = req.getRequestDispatcher("/Pages/Views/personalTopic.jsp");
        rDispatcher.forward(req, res);
    }

    //------------------<method to  redirect to home page>----------------------------
    private void main(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, SQLException {
       // String userName = req.getParameter("userName");
        // req.setAttribute("userName",userName);
        RequestDispatcher rDispatcher = req.getRequestDispatcher("/Pages/HomePage/mainPage.jsp");
        rDispatcher.forward(req, res);
    }

    //------------------<method to  redirect to pages of different categories >----------------------------
    private void view(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, SQLException {
        String forward = null;
        RequestDispatcher requestDispatcher = null;
        String category = req.getParameter("category");
        String userName = req.getParameter("userName");
        req.setAttribute("userName", userName);
        req.setAttribute("category", category);
        int catId = 0;
        if (category.equals("books")) {
            catId = 2;
            forward = "/Pages/Views/books.jsp";
        } else if (category.equals("cars")) {
            catId = 1;
            forward = "/Pages/Views/cars.jsp";
        } else if (category.equals("furniture")) {
            catId = 3;
            forward = "/Pages/Views/furniture.jsp";
        }

        List<Item> items = service.readAllItems(catId);
        List<ItemInfo> itemsInfo = new ArrayList<>();
        for (Item item: items){
            ItemInfo itemInfo = new ItemInfo();
            Topic topic = service.readOneTopicByItemId(item.getId());
            itemInfo.setItem(item);
            itemInfo.setCommentCount(service.readComments(item.getId()).size());
            itemInfo.setCreateTime(topic.getCreateTime());
            itemInfo.setUserName(service.getUserById(topic.getUsers_UserID()));
            itemsInfo.add(itemInfo);
        }
        req.setAttribute("itemsInfo", itemsInfo);
        requestDispatcher = req.getRequestDispatcher(forward);
        requestDispatcher.forward(req, res);
    }

    //------------------<method to  add comments >----------------------------
    private void addComment(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, SQLException {
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        String contents = req.getParameter("contents");
        String userName = req.getParameter("userName");
        Comment comment = new Comment();
        comment.setContent(contents);
        comment.setItemID(itemId);
       // System.out.println(req.getParameter("userName"));
        service.addComment(comment, userName);
        RequestDispatcher requestDispatcher = null;
        String forward = null;

        forward = "DispatcherServlet?method=item&" +
                "itemId=" + itemId + "&userName=" + userName + "&category=" + req.getParameter("category");
        requestDispatcher = req.getRequestDispatcher(forward);
        requestDispatcher.forward(req, res);

    }

    //------------------<method to logout>----------------------------
    private void logout(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, SQLException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Pages/LoginAndRegister/login.jsp");
        requestDispatcher.forward(req, res);
    }

    //------------------<method to search items by keywords>----------------------------
    private void search(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, SQLException {
        String keyword = req.getParameter("keyword");
        Item item = new Item();
        item.setDescription(keyword);
        item.setItemName(keyword);
        List<Item> items = service.searchItems(keyword);
        if (items.size() == 0)
            System.out.println("NO found!");
        else {
            List<ItemInfo> itemsInfo = new ArrayList<>();
            for (Item it: items){
                ItemInfo itemInfo = new ItemInfo();
                Topic topic = service.readOneTopicByItemId(it.getId());
                itemInfo.setItem(it);
                itemInfo.setCommentCount(service.readComments(it.getId()).size());
                itemInfo.setCreateTime(topic.getCreateTime());
                itemInfo.setUserName(service.getUserById(topic.getUsers_UserID()));
                itemsInfo.add(itemInfo);
            }
            req.setAttribute("itemsInfo", itemsInfo);
        }
        req.setAttribute("userName", req.getParameter("userName"));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Pages/Views/searchItems.jsp");
        requestDispatcher.forward(req, res);
    }

    //------------------<method to show one item in detail>----------------------------
    private void item(HttpServletRequest req, HttpServletResponse res)//search topic by item id
            throws ServletException, IOException, SQLException {
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        RequestDispatcher requestDispatcher = null;
        String forward = null;

        User tempUser = new User();
        tempUser.setUserName(req.getParameter("userName"));
        int UserID = service.getUser(tempUser).getId();

        req.setAttribute("userName",req.getParameter("userName"));
        req.setAttribute("itemId",req.getParameter("itemId"));
        req.setAttribute("category",req.getParameter("category"));

        req.setAttribute("favExist",service.isFavExist(UserID,Integer.parseInt(req.getParameter("itemId")))?1:0);

        Topic topic = service.readOneTopicByItemId(itemId);
       //req.setAttribute("topicId", itemId);
        req.setAttribute("createTime", topic.getCreateTime());
        req.setAttribute("contact", topic.getContact());
        req.setAttribute("address", topic.getAddress());
        Item item = service.readOneItemByItemId(itemId);
        req.setAttribute("itemName", item.getItemName());
        req.setAttribute("itemDesc", item.getDescription());
        req.setAttribute("price", item.getPrice());
        req.setAttribute("itemId", itemId);
        req.setAttribute("imagePath", item.getImagePath());
        req.setAttribute("readingTimes",item.getReadingTimes());
        List<Comment> tempComments = service.readComments(itemId);

        Map<String, Comment> comments = new LinkedHashMap<>();
        int count = 1;
        for (Comment c : tempComments) {
            String name = service.getUserById(c.getUsers_UserID()) + " " + count;
            comments.put(name, c);
            count++;
        }
        req.setAttribute("comments", comments);
        req.setAttribute("author",req.getParameter("author"));
        req.setAttribute("commentCount",req.getParameter("commentCount"));
        if (req.getParameter("category").equals("books") || req.getParameter("category").equals("2")) {
            forward = "/Pages/Views/bookTopics.jsp";
        } else if (req.getParameter("category").equals("cars") || req.getParameter("category").equals("1"))
            forward = "/Pages/Views/carTopics.jsp";
        else if (req.getParameter("category").equals("furniture") || req.getParameter("category").equals("3"))
            forward = "/Pages/Views/furnitureTopics.jsp";

        requestDispatcher = req.getRequestDispatcher(forward);
        requestDispatcher.forward(req, res);
    }

<<<<<<< HEAD
    private void showUsers(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, SQLException{
        User mockUser = new User();
        List<User> users = service.getUsers(mockUser);
        req.setAttribute("users",users);
         RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin.jsp");
        requestDispatcher.forward(req, res);
    }

    private void showItems(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, SQLException{
        List<Item> items = service.getAllItems();
        List<ItemInfo> itemsInfo = new ArrayList<>();
        for(Item item : items){
            ItemInfo itemInfo = new ItemInfo();
            itemInfo.setItem(item);
            Topic topic = service.readOneTopicByItemId(item.getId());
            itemInfo.setCommentCount(service.readComments(item.getId()).size());
            itemInfo.setCreateTime(topic.getCreateTime());
            itemInfo.setUserName(service.getUserById(topic.getUsers_UserID()));
            itemsInfo.add(itemInfo);
        }
        req.setAttribute("itemsInfo",itemsInfo);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin.jsp");
        requestDispatcher.forward(req, res);


    }

    private void deleteItem(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, SQLException{

        int itemId = Integer.parseInt(req.getParameter("itemId"));
        service.deleteItem(itemId);
        if(!req.getParameter("userName").equals("admin"))
            profile(req,res);
        else
            showItems(req,res);
=======

    //------------------< add or delete a favorite >----------------------------
    private void favorite(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, SQLException{
        int itemID = Integer.parseInt(req.getParameter("itemId"));
        String userName = req.getParameter("userName");
        User tempUser = new User();
        tempUser.setUserName(userName);
        int UserID = service.getUser(tempUser).getId();
        System.out.println(service.isFavExist(UserID,itemID));

        if(req.getParameter("favorite").equals("add")){
            if(!service.isFavExist(UserID,itemID))
                service.addFavorite(UserID,itemID);
        }
        else if(req.getParameter("favorite").equals("del")){
            if(service.isFavExist(UserID,itemID))
                service.delFavorite(UserID,itemID);
        }

        RequestDispatcher requestDispatcher = null;
        String forward =
                "DispatcherServlet?method=item&itemId="+itemID+
                        "&userName="+userName+
                        "&category="+req.getParameter("category") +
                        "&author="+req.getParameter("author")+
                        "&commentCount="+req.getParameter("commentCount");

        requestDispatcher = req.getRequestDispatcher(forward);
        requestDispatcher.forward(req, res);
>>>>>>> 4b008122afccbb4442e1f157fdd0ef9936552746
    }

}


