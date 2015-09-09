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
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseFeedParser implements FeedParser
{

	//names of XML tags
	static final String isbn = "Id";
	static final String title = "Title";
	static final String author = "Author";
	static final String genre = "Genre";
	
	final URL feedUrl;
	
	protected BaseFeedParser(String feedUrl)
	{
		try 
		{
            this.feedUrl = new URL(feedUrl);
        } 
		catch (MalformedURLException e) 
		{
            throw new RuntimeException(e);
        }
		
	}
	
	protected InputStream getInputStream() 
	{
        try 
        {
            return feedUrl.openConnection().getInputStream();
        } 
        catch (IOException e) 
        {
            throw new RuntimeException(e);
        }
    }
}*/
