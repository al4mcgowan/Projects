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
import static org.developerworks.android.BaseFeedParser.*;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RssHandler extends DefaultHandler
{
    private List<Message> messages;
    private Message currentMessage;
    private StringBuilder builder;
    
    public List<Message> getMessages()
    {
        return this.messages;
    }
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException 
            {
        super.characters(ch, start, length);
        builder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException 
            {
        super.endElement(uri, localName, name);
        if (this.currentMessage != null)
        {
            if (localName.equalsIgnoreCase(ID))
            {
                currentMessage.setISBN(builder.toString());
            } 
            else if (localName.equalsIgnoreCase(TITLE))
            {
                currentMessage.setTitle(builder.toString());
            } 
            else if (localName.equalsIgnoreCase(GENRE)
            		){
                currentMessage.setGenre(builder.toString());
            } 
            else if (localName.equalsIgnoreCase(AUTHOR))
            {
                currentMessage.setAuthor(builder.toString());
            } 
            else if (localName.equalsIgnoreCase(ITEM))
            {
                messages.add(currentMessage);
            }
            builder.setLength(0);    
        }
    }

    @Override
    public void startDocument() throws SAXException 
    {
        super.startDocument();
        messages = new ArrayList<Message>();
        builder = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String name,
            Attributes attributes) throws SAXException 
            {
        super.startElement(uri, localName, name, attributes);
        if (localName.equalsIgnoreCase(ITEM))
        {
            this.currentMessage = new Message();
        }
    }
}*/
