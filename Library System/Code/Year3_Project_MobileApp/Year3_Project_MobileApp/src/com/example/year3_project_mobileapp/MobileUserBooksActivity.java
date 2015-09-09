package com.example.year3_project_mobileapp;
//AUTHOR: X00084900 CHRISTOPHER NEWELL
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
//THIS CLASS IS RESPONSIBLE FOR BUILDING THE LISTVIEW FOR A SPECIFIC USER'S BOOKS THEY HAVE ON LOAN
public class MobileUserBooksActivity extends Activity
{
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mobile_user_booklist_layout);
		
		Intent i = getIntent();
		final ArrayList user_Books = getUserBooks(i);
		final ListView userLv = (ListView) findViewById(R.id.user_List_View);
		userLv.setAdapter(new UserBooksCustomListAdapter(this, user_Books));
	}

	private ArrayList getUserBooks(Intent i) 
	{
		System.out.println("Getting List Data");
		
		String preProcessedString = i.getStringExtra("books").toString();
		ArrayList temp_List = new ArrayList();
		userBooksListItem userBLI = new userBooksListItem();
		StringBuilder strBuild = new StringBuilder();
		int count;
		int index = 0;
		
		for(count = 0; count < preProcessedString.length(); count++)
		{
			if(preProcessedString.charAt(count) == '~' || count == preProcessedString.length())
			{
					userBLI = new userBooksListItem();
					String insertString = strBuild.toString();
					System.out.println("setting userBookListItem");
					System.out.println("InsertString: " + insertString);
					userBLI.setUserBookTitle(insertString);
					System.out.println("Ibook title get: " + userBLI.getUserBookTitle());
					strBuild.setLength(0);
					++count;
					System.out.println("adding at index: " + index);
					temp_List.add(index, userBLI);	
					index++;
			}
			strBuild.append(preProcessedString.charAt(count));
			System.out.println(strBuild.toString());
		}
		userBLI = new userBooksListItem();
		System.out.println("adding at index: " + index);
		String insertString = strBuild.toString();
		System.out.println("setting userBookListItem");
		System.out.println("InsertString: " + insertString);
		userBLI.setUserBookTitle(insertString);
		System.out.println("Ibook title get: " + userBLI.getUserBookTitle());
		temp_List.add(index, userBLI);
		
		return temp_List;
	}
}
