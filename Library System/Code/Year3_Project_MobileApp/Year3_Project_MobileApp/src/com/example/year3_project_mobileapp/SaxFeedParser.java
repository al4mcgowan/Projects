package com.example.year3_project_mobileapp;
//AUTHOR: X00084900 Christopher Newell
/***************************************************************************************
*    Title: <Working with XML on Android>
*    Author: <Michael Galpin>
*    Date: <February 2013 (First published 23 June 2009)>
*    Code version: <1>
*    Availability: <http://www.ibm.com/developerworks/library/x-android/> 
*
***************************************************************************************/
//THIS CLASS IS NOT USED IN THE CURRENT VERSION OF THE PROJECT, 
//BUT WAS USED IN THE RESEARCH PHASE TO SOLVE THE PROLEM OF PARSING RETRIEVED DATA FROM A WEBSERVER,
//IN THE CASE THAT THE RETRIEVED RESPONSE WAS RETURNED IN XML FORMAT.
/*
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxFeedParser extends BaseFeedParser 
{

    protected SaxFeedParser(String feedUrl)
    {
        super(feedUrl);
    }
    
    public List<Message> parse() 
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
        {
            SAXParser parser = factory.newSAXParser();
            RssHandler handler = new RssHandler();
            parser.parse(this.getInputStream(), handler);
            return handler.getMessages();
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);
        } 
    }
}
*/