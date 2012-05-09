package com.example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Balaji Athreya
 * Date: 4/26/12
 * Time: 10:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class FeedHandler extends DefaultHandler {
    private Feed feed;
    ArrayList<Item> items;
    private Item item;
    boolean inItem;
    private StringBuffer buffer = new StringBuffer();

    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {
        buffer.setLength(0);
        if(localName.equalsIgnoreCase("rss")) {
            feed = new Feed();
            items = new ArrayList<Item>();
        }
        else if(localName.equalsIgnoreCase("item")) {
            item = new Item();
            inItem = true;
        }
    }

    public void endElement(String uri, String localName, String qName)throws SAXException {
        if(localName.equalsIgnoreCase("title")) {
            if(inItem){
                item.setTitle(buffer.toString());
            }
            else{
                feed.setTitle(buffer.toString());
            }
        }
        else if(localName.equalsIgnoreCase("link")) {
            if(inItem){
                item.setLink(buffer.toString());
            }
            else{
                feed.setLink(buffer.toString());
            }
        }
        else if(localName.equalsIgnoreCase("pubDate")) {
            feed.setDate(buffer.toString());
        }
        else if(localName.equalsIgnoreCase("item")) {
            items.add(item);
        }
        else if(localName.equalsIgnoreCase("rss")) {
            feed.setItems(items);
        }
    }

    public void characters(char[] ch, int start, int length) {
        buffer.append(ch, start, length);
    }

    public Feed getFeed(){
        return feed;
    }
}
