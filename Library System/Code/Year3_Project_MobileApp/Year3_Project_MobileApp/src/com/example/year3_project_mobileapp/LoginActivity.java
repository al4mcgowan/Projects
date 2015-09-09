package com.example.year3_project_mobileapp;
//AUTHOR: X00084900 Christopher Newell
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity
{
	/* OLD WEBSITE URL  */
	//static final String URL = "http://year3projectlibrarywebsite.azurewebsites.net/api/MobileUsers";
	
	/* NEW WEBSITE URL  */
	static final String URL = "http://test3rdyearprojectlibrary.azurewebsites.net/api/MobileUsers";
	
	/* USER DATA NODE KEYS  */
	static final String KEY_USERNAME = "UserName";
	static final String KEY_PASSWORD = "Password";
	static final String KEY_BOOKLIST = "listBooks";
	
	//WHEN TRYING TO LOG IN WHILE APP IS RUNNING THE FOLLOWING 3 USER ACCOUNTS ARE CURRENTLY STORED ON THE WEB SERVER.
	//EACH ACCOUNT HAS A DIFFERENT LIST OF BOOKS ASSOCIATED WITH IT
	
	//USERNAME: Chris
	//PASSWORD: admin
	
	//USERNAME: Charles
	//PASSWORD: admin
	
	//USERNAME: Shane
	//PASSWORD: admin
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		
		Button log_inButton = (Button) findViewById(R.id.loginButton_Confirm);
		final EditText username_Field = (EditText)findViewById(R.id.usernameField);
		final EditText password_Field = (EditText)findViewById(R.id.passwordField);
		
		log_inButton.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				WebAPIConnect connection = new WebAPIConnect();
				JSONArray jsonArray;
				
				String userNameAttempt = username_Field.getText().toString();
				String passwordAttempt = password_Field.getText().toString();
				String login_Query = "?Username=" + userNameAttempt + "&" + "Password=" + passwordAttempt;
				//jsonArray = connection.GetJSONFromUrl(URL + login_Query);
				getUserData(URL + login_Query);
			}
		});
	}
	private void getUserData(String loginQuery)
	{
		System.out.println("Getting User Data!");

		ArrayList userItems = new ArrayList();
		
		WebAPIConnect parser = new WebAPIConnect();
		JSONObject json = parser.retrieveJSONObjectFromURL(loginQuery);
		MobileUserItem mobItem = new MobileUserItem();
		
		System.out.println("Creating User object!");
		
		try
		{
			System.out.println("getting username");
			mobItem.setUserName(json.getString(KEY_USERNAME));
			System.out.println("getting password");
			mobItem.setPassword(json.getString(KEY_PASSWORD));
			System.out.println("getting book list");
			mobItem.setBooklist(json.getString(KEY_BOOKLIST));
		}
		catch (JSONException e) 
		{
            e.printStackTrace();
        }
		
		Intent i = new Intent(getApplicationContext(),MobileUserBooksActivity.class);
		i.putExtra("books", mobItem.getBookList());
		finish();
		startActivity(i);
	}
}
