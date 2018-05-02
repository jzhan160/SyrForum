package com.jc.entity;

public class ItemInfo{
    private Item item;
    private String userName;
    private String createTime;
    private int commentCount;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
                "item=" + item +
                ", userName='" + userName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", commentCount=" + commentCount +
                '}';
    }
}
