package com.example.year3_project_mobileapp;
//AUTHOR: X00084900 Christopher Newell
public class Message implements Comparable<Message>
{
	private String isbn;
	private String title;
	private String genre;
	private String author;
	
	public void setISBN(String theISBN)
	{
		isbn = theISBN;
	}
	
	public void setTitle(String theTitle)
	{
		title = theTitle;
	}
	
	public void setGenre(String theGenre)
	{
		genre = theGenre;
	}
	
	public void setAuthor(String theAuthor)
	{
		author = theAuthor;
	}
	
	public String getISBN()
	{
		return isbn;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getGenre()
	{
		return genre;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	@Override
	public int compareTo(Message message) 
	{
		if(message == null) return 1;
		//sort most recent first.
		return 0;
	}
}

