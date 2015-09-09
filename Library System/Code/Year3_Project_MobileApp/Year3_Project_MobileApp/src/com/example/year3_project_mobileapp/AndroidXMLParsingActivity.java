package com.example.year3_project_mobileapp;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


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

public class AndroidXMLParsingActivity extends ListActivity
{
	static final String URL = "http://3rdyearprojectlibraryweb.azurewebsites.net/api-GetAllBooks";
	//static final String URL = "dummyurl";
	//XML node Keys
	static final String KEY_ITEM = "Book";
	static final String KEY_ISBN = "Id";
	static final String KEY_TITLE = "Title";
	static final String KEY_GENRE = "Genre";
	static final String KEY_AUTHOR = "Author";


public void onCreate(Bundle savedInstanceState) 
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();
    
    System.out.println("MSG- AndroidXMLParsingActivity Launch!");
    
    WebAPIConnect parser = new WebAPIConnect();
    String xml = parser.getXmlFromUrl(URL); // getting XML
    Document doc = parser.getDomElement(xml); // getting DOM element

    NodeList nl = doc.getElementsByTagName(KEY_ITEM);
    // looping through all item nodes <item>
    for (int i = 0; i < nl.getLength(); i++) 
    {
        // creating new HashMap
        HashMap<String, String> map = new HashMap<String, String>();
        Element e = (Element) nl.item(i);
        // adding each child node to HashMap key => value
        map.put(KEY_ISBN, parser.getValue(e, KEY_ISBN));
        map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
        map.put(KEY_GENRE, "Rs." + parser.getValue(e, KEY_GENRE));
        map.put(KEY_AUTHOR, parser.getValue(e, KEY_AUTHOR));

        // adding HashList to ArrayList
        menuItems.add(map);
    }

    // Adding menuItems to ListView
    /*
    ListAdapter adapter = new SimpleAdapter(this, menuItems,
            R.layout.list_item,
            new String[] { KEY_TITLE, KEY_GENRE, KEY_AUTHOR }, new int[] {
                    R.id.title, R.id.genre, R.id.author });
*/
    //setListAdapter(adapter);

    // selecting single ListView item
    ListView lv = getListView();
            // listening to single listitem click
    lv.setOnItemClickListener(new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
            // getting values from selected ListItem
            String name = ((TextView) view.findViewById(R.id.title)).getText().toString();
            String cost = ((TextView) view.findViewById(R.id.genre)).getText().toString();
            String description = ((TextView) view.findViewById(R.id.author)).getText().toString();
             
         /*   
            // Starting new intent
            Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
            in.putExtra(KEY_TITLE, name);
            in.putExtra(KEY_GENRE, cost);
            in.putExtra(KEY_AUTHOR, description);
            startActivity(in);
		*/
        }
    });
}
}
