package com.example.year3_project_mobileapp;
//AUTHOR: X00084900 Christopher Newell
public class BookListItem 
{
	//this class is used throughout the program but most importantly when creating ListViews to display data.
	private int isbn;
	private String title;
	private String author;
	private String genre;
	private String onShelf;
	 
	public int getISBN()
	{
		return isbn;
	}
	public String getOnShelf()
	{
		return onShelf;
	}
	public String getTitle()
	{
		return title;
	}
	public String getAuthor()
	{
		return author;
	}
	public String getGenre()
	{
		return genre;
	}
	
	public void setISBN(int theISBN)
	{
		isbn = theISBN;
	}
	public void setOnShelf(String theOnShelf)
	{
		onShelf = theOnShelf;
	}
	public void setTitle(String theTitle)
	{
		title = theTitle;
	}
	public void setAuthor(String theAuthor)
	{
		author = theAuthor;
	}
	public void setGenre(String theGenre)
	{
		genre = theGenre;
	}
	
	public String toString() {
        return "[ Title = " + getTitle() + ", Author = " +
        		getAuthor() + ", Genre = " + getGenre() + ", Onshelf = " +
        		getOnShelf() + "]";
    }
}
