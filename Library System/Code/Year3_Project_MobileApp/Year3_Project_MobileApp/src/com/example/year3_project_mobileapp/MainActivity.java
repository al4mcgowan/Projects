package com.example.year3_project_mobileapp;
//AUTHOR: X00084900 Christopher Newell
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	private TextView responseTextView;
	
	/* OLD WEBSITE URL */
	//static final String URL = "http://3rdyearprojectlibraryweb.azurewebsites.net/api-GetAllBooks";
	//static final String URL = "http://3rdyearprojectlibraryweb.azurewebsites.net/api/books";
	
	/* NEW WEBSITE URL */
	//static final String URL = "http://test3rdyearprojectlibrary.azurewebsites.net/api/books";
	
	/* ITERATION 5 WEBSITE URL */
	static final String URL = "http://year3projectlibrarywebsite.azurewebsites.net/api/books";
	
	//node Keys
	static final String KEY_ITEM = "Book";
	static final String KEY_ISBN = "Id";
	static final String KEY_TITLE = "Title";
	static final String KEY_GENRE = "Genre";
	static final String KEY_AUTHOR = "Author";
	
	//WHEN TRYING TO LOG IN WHILE APP IS RUNNING THE FOLLOWING 3 USER ACCOUNTS ARE CURRENTLY STORED ON THE WEB SERVER.
	//EACH ACCOUNT HAS A DIFFERENT LIST OF BOOKS ASSOCIATED WITH IT
	
	//USERNAME: Chris
	//PASSWORD: admin
	
	//USERNAME: Charles
	//PASSWORD: admin
	
	//USERNAME: Shane
	//PASSWORD: admin
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button getBooksButton = (Button) findViewById(R.id.GetAllBooksButton);
		getBooksButton.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent i = new Intent(getApplicationContext(),BookListActivity.class);
				startActivity(i);
			}
		});
		
		Button searchBooksButton = (Button) findViewById(R.id.searchBooksButton);
		searchBooksButton.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent i = new Intent(getApplicationContext(),BookSearchActivity.class);
				startActivity(i);
			}
		});
		
		
		Button loginButton = (Button) findViewById(R.id.login_Button);
		loginButton.setOnClickListener(new View.OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(i);	
			}
		});
	}

	private class RetrieveXMLTask extends AsyncTask<WebAPIConnect,Long, String> //NOT REQUIRED - THIS IS FOR XML RESULT
	{
		
		String retrievedXml;
		protected String doInBackground(WebAPIConnect... params) 
		{
			WebAPIConnect parser = new WebAPIConnect();
			retrievedXml = parser.getXmlFromUrl(URL);
			
			return retrievedXml;
		}
		
		protected void onPostExecute()
		{
			//WHATEVER YOU NEED TO DO AFTER YOU RETRIEVE THE XML DATA, DO IT HERE.
		}
		
	}
}



