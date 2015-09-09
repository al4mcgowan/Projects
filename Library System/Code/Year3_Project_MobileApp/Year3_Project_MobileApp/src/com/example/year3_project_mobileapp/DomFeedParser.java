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
public class DomFeedParser extends BaseFeedParser 
{

    protected DomFeedParser(String feedUrl) {
        super(feedUrl);
    }

    public List<Message> parse() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<Message> messages = new ArrayList<Message>();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(this.getInputStream());
            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName(ITEM);
            for (int i=0;i<items.getLength();i++){
                Message message = new Message();
                Node item = items.item(i);
                NodeList properties = item.getChildNodes();
                for (int j=0;j<properties.getLength();j++){
                    Node property = properties.item(j);
                    String name = property.getNodeName();
                    if (name.equalsIgnoreCase(TITLE)){
                        message.setTitle(property.getFirstChild().getNodeValue());
                    } else if (name.equalsIgnoreCase(LINK)){
                        message.setLink(property.getFirstChild().getNodeValue());
                    } else if (name.equalsIgnoreCase(DESCRIPTION)){
                        StringBuilder text = new StringBuilder();
                        NodeList chars = property.getChildNodes();
                        for (int k=0;k<chars.getLength();k++){
                            text.append(chars.item(k).getNodeValue());
                        }
                        message.setDescription(text.toString());
                    } else if (name.equalsIgnoreCase(PUB_DATE)){
                        message.setDate(property.getFirstChild().getNodeValue());
                    }
                }
                messages.add(message);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
        return messages;
    }
}*/
