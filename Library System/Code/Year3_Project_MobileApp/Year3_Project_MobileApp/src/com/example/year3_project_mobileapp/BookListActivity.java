package com.example.year3_project_mobileapp;
//AUTHOR: X00084900 Christopher Newell
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BookListActivity extends Activity
{
		static final String URL = "http://test3rdyearprojectlibrary.azurewebsites.net/api/books";
		
		//node Keys
		static final String KEY_ITEM = "Book";
		static final String KEY_ISBN = "Id";
		static final String KEY_TITLE = "Title";
		static final String KEY_GENRE = "Genre";
		static final String KEY_AUTHOR = "Author";
		static final String KEY_ONSHELF = "OnShelf";
		
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_list_layout);
		
		System.out.println("MSG- android launch BookListActivity");
		
		WebAPIConnect connection = new WebAPIConnect();
		JSONArray jsonArray;
		
		Intent i = getIntent();
		if(i.getExtras() != null) //intent came from BookSearchActivity
		{	
			System.out.println("Launching search by criteria");
			String queryString = constructQuery(i);
			System.out.println("query by: " + queryString);
			jsonArray = connection.GetJSONFromUrl(URL + queryString);
		}
		else
		{
			System.out.println("retrieving all books");
			jsonArray = connection.GetJSONFromUrl(URL);
		}
		
		
		ArrayList book_details = getListData(jsonArray);
		final ListView bklv = (ListView) findViewById(R.id.bookList);
		bklv.setAdapter(new CustomListAdapter(this, book_details));
		
		bklv.setOnItemClickListener(new OnItemClickListener() //when a single item is clicked, move onto the details screen.
		{
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) 
			{
				Object o = bklv.getItemAtPosition(position);
				BookListItem bookItem = (BookListItem) o;
				
				//Retrieve data to be displayed.
				String titleToSend = bookItem.getTitle();
				String genreToSend = bookItem.getGenre();
				String authorToSend = bookItem.getAuthor();
				String onShelfToSend = bookItem.getOnShelf();
				
				//build the Intent with the gathered information.
				Intent i = new Intent(getApplicationContext(),SingleBookListItem.class);
				i.putExtra("title", titleToSend);
				i.putExtra("genre", genreToSend);
				i.putExtra("author", authorToSend);
				i.putExtra("onShelf", onShelfToSend);
				
				startActivity(i);
			}	
		});
	}
	
	private String constructQuery(Intent i) 
	{
		String queryData = "";
		StringBuilder strBuild = new StringBuilder();
		strBuild.append("?");
		String searchBy = i.getStringExtra("selection").toString();
		String searchCriteria = i.getStringExtra("criteria").toString();
		strBuild.append(searchBy + "=" + searchCriteria);
		
		/* THIS CODE IS USED FOR WHEN A SEARCH FOR MULTIPLE CRITERIA IS MADE.
		if(!i.getStringExtra("title").equals(""))
		{
			String bookItemTitle = i.getStringExtra("title").toString();
			strBuild.append("Title=" + bookItemTitle + "&");
		}
		if(!i.getStringExtra("genre").equals(""))
		{
			String bookItemGenre = i.getStringExtra("genre");
			strBuild.append("Genre=" + bookItemGenre + "&");
		}
		if(!i.getStringExtra("author").equals(""))
		{
			String bookItemAuthor = i.getStringExtra("author");
			strBuild.append("Author=" + bookItemAuthor + "&");
		}

		*/
		
		
		//Replace spaces with '+' character for query
		queryData = strBuild.toString().replace(' ', '+');
		return queryData;
	}

	private ArrayList getListData(JSONArray jsonArray)
	{
		System.out.println("Getting List Data");
		
		ArrayList menuItems = new ArrayList();
		for(int i  = 0; i < jsonArray.length(); i++)
		{
			JSONObject json = null;
			BookListItem bkItem = new BookListItem();
			
			try
			{
				json = jsonArray.getJSONObject(i);
				
				System.out.println("Getting Book Title");
				bkItem.setTitle(json.getString(KEY_TITLE));
				System.out.println("Getting Book Genre");
				bkItem.setGenre(json.getString(KEY_GENRE));
				System.out.println("Getting Book Author");
				bkItem.setAuthor(json.getString(KEY_AUTHOR));
				System.out.println("Getting Book onShelf");
				bkItem.setOnShelf(json.getString(KEY_ONSHELF).toString());
				
				menuItems.add(i,bkItem);
				System.out.println("Number of items retrieved so far: " + i);
			}
			catch(JSONException e)
			{
				System.out.println("ERROR: Could not create objects from JSON data!");
				e.printStackTrace();
			}
			catch(Exception e)
			{
				System.out.println("ERROR: The data could not be retrieved!");
				e.printStackTrace();
			}
		}
		
		
		int count = 0;
		for(Object obj : menuItems)
		{
			System.out.println(menuItems.get(count).toString());
			count++;
		}
		
		System.out.println("Successfully created book objects!");
		return menuItems;
	}
}
