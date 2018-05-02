package com.jc.entity;

public class Item implements Entity{
    private String ItemName;
    private String Description;
    private int CatID;
    private String ImagePath;
    private int TopicID;
    private double Price;
    private int ID;
    private int readingTimes;

    public int getReadingTimes() {
        return readingTimes;
    }

    public void setReadingTimes(int readingTimes) {
        this.readingTimes = readingTimes;
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
        return "Item{" +
                "ItemName='" + ItemName + '\'' +
                ", Description='" + Description + '\'' +
                ", CatID=" + CatID +
                ", ImagePath='" + ImagePath + '\'' +
                ", TopicID=" + TopicID +
                ", Price=" + Price +
                ", ID=" + ID +
                ", readingTimes=" + readingTimes +
                '}';
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getCatID() {
        return CatID;
    }

    public void setCatID(int catID) {
        CatID = catID;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public int getTopicID() {
        return TopicID;
    }

    public void setTopicID(int topicID) {
        TopicID = topicID;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
