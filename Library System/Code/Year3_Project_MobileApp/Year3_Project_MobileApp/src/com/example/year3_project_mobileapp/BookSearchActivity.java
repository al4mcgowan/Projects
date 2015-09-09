package com.example.year3_project_mobileapp;
//AUTHOR: X00084900 Christopher Newell
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class BookSearchActivity extends Activity
{
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_search_layout);
		
		final EditText searchCriteria = (EditText) findViewById(R.id.searchCriteria);
		final Spinner searchSpinner = (Spinner) findViewById(R.id.searchSpinner);
		
		Button confirmSearchButton = (Button) findViewById(R.id.searchButton_confirm);
		confirmSearchButton.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent i = new Intent(getApplicationContext(),BookListActivity.class);
				String selection = String.valueOf(searchSpinner.getSelectedItem());
				System.out.println("Spinner selection: " + selection);
				
				String criteria = searchCriteria.getText().toString();
				System.out.println("Search Criteria: " + criteria);
				
				i.putExtra("selection", selection);
				i.putExtra("criteria", criteria);
				startActivity(i);
			}
		});
		/*
		 * PREVIOUS VERSION OF SCREEN
		final EditText titleSearch = (EditText) findViewById(R.id.titleSearch);
		final EditText genreSearch = (EditText) findViewById(R.id.genreSearch);
		final EditText authorSearch = (EditText) findViewById(R.id.authorSearch);
		
		
		Button confirmSearchButton = (Button) findViewById(R.id.searchButton_confirm);
		confirmSearchButton.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent i = new Intent(getApplicationContext(),BookListActivity.class);
				
				if(!titleSearch.getText().equals(""))
				{
					String titleStr = titleSearch.getText().toString();
					i.putExtra("title", titleStr);
				}
				if(genreSearch.getText() != null)
				{
					String genreStr = genreSearch.getText().toString();
					i.putExtra("genre", genreStr);
				}
				if(authorSearch.getText() != null)
				{
					String authorStr = authorSearch.getText().toString();
					i.putExtra("author", authorStr);
				}
				
				System.out.println(i.getStringExtra("title") + " " + i.getStringExtra("genre") + " " + i.getStringExtra("author"));
				
				startActivity(i);
			}
		});*/
	}
}
