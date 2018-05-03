package com.jc.entity;

public class User implements Entity{
    private String UserName;
    private String UserPassword;
    private String Email;
    private String Gender;
    private String UserBirthday;
    private String CreateTime;
    private int IsAdmin;
    private int ID;
    private int ImageID;

    public int getImageID() {
        return ImageID;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }

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
        return "User{" +
                "UserName='" + UserName + '\'' +
                ", UserPassword='" + UserPassword + '\'' +
                ", Email='" + Email + '\'' +
                ", Gender='" + Gender + '\'' +
                ", UserBirthday='" + UserBirthday + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", IsAdmin=" + IsAdmin +
                ", ID=" + ID +
                ", ImageID=" + ImageID +
                '}';
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getUserBirthday() {
        return UserBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        UserBirthday = userBirthday;
    }

    public String getCreateTime() {
        return CreateTime.substring(0,10);
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public int getIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        IsAdmin = isAdmin;
    }
}
