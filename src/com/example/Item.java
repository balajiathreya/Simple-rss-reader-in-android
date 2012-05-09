package com.example;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Balaji Athreya
 * Date: 4/26/12
 * Time: 9:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class Item{

    private String title;
    private String link;

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

    public String toString(){
        return getTitle();
    }

    // same as above.

}
