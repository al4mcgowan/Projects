package com.example.year3_project_mobileapp;
//AUTHOR: X00084900 Christopher Newell
public class MobileUserItem 
{
	//THIS CLASS IS USED WHEN BUILDING THE LISTVIEW FOR VIEWING A SPECIFIC USERS LIST OF BOOKS AFTER LOGGING IN
	private String username;
	private String password;
	private String bookList;
	
	
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	public String getBookList()
	{
		return bookList;	
	}
	
	public void setUserName(String theUsername)
	{
		username = theUsername;
	}
	public void setPassword(String thePassword)
	{
		password = thePassword;
	}
	public void setBooklist(String theBookList)
	{
		bookList = theBookList;
	}
}
