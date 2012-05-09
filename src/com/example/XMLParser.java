package com.example;

import org.xml.sax.helpers.DefaultHandler;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


/**
 * Created with IntelliJ IDEA.
 * User: Balaji Athreya
 * Date: 4/26/12
 * Time: 10:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class XMLParser {
    private XMLReader initializeReader() throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLReader xmlreader = parser.getXMLReader();
        return xmlreader;
    }
    public Feed getFeed(String xml){
        try {
            XMLReader xmlreader = initializeReader();
            FeedHandler handler = new FeedHandler();
            xmlreader.setContentHandler(handler);
            xmlreader.parse(new InputSource(new StringReader(xml)));
            return handler.getFeed();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
