package com.example;

/**
 * Created with IntelliJ IDEA.
 * User: Balaji Athreya
 * Date: 4/26/12
 * Time: 9:59 AM
 * To change this template use File | Settings | File Templates.
 */import java.util.ArrayList;

public class Feed {
    private String title;
    private String link;
    private String date;
    private ArrayList<Item> items;

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}


