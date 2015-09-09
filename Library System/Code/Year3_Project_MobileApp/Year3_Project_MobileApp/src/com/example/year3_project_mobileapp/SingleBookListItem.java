package com.example.year3_project_mobileapp;
//AUTHOR: X00084900 Christopher Newell
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

//THIS CLASS IS THE VIEW THAT IS DISPLAYED WHEN A SPECIFIC BOOK ITEM IS CLICKED WHILE IN THE LIST VIEW
public class SingleBookListItem extends Activity
{
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.single_book_item_layout);
		
		TextView txtBookItemTitle = (TextView) findViewById(R.id.bookItem_title);
		TextView txtBookItemGenre = (TextView) findViewById(R.id.bookItem_genre);
		TextView txtBookItemAuthor = (TextView) findViewById(R.id.bookItem_author);
		TextView txtBookItemOnShelf = (TextView) findViewById(R.id.bookItem_onShelf);
		
		Intent i = getIntent();
		//get attached intent data.
		String bookItemTitle = i.getStringExtra("title");
		String bookItemGenre = i.getStringExtra("genre");
		String bookItemAuthor = i.getStringExtra("author");
		String bookItemOnshelf = i.getStringExtra("onShelf");
		
		txtBookItemTitle.setText(bookItemTitle);
		txtBookItemGenre.setText(bookItemGenre);
		txtBookItemAuthor.setText(bookItemAuthor);
		
		if(bookItemOnshelf.equals("true"))
		{
			txtBookItemOnShelf.setText("This book is available to loan");
		}
		else
		{
			txtBookItemOnShelf.setText("Unfortunately, This book is not available");
		}
		
	}
}
