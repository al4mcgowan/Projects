package com.example.year3_project_mobileapp;
//AUTHOR: X00084900 Christopher Newell
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;


import android.util.Log;
import android.view.ContextThemeWrapper;

public class DatabaseConnect //REDUNDANT CLASS, REMADE INTO WebAPIConnect
{

	public String[] RetrieveAllBooks()
	{
		String connectionString = "http://3rdyearprojectlibraryweb.azurewebsites.net/api/GetAllBooks";
		
		String responseArr[] = new String[10];
		
		DefaultHttpClient client = new DefaultHttpClient();
		
		HttpEntity httpEntity = null;
		
		try 
		{
			HttpGet request = new HttpGet(connectionString);
			
			HttpResponse response = client.execute(request);
			
			httpEntity = response.getEntity();
			String xml = EntityUtils.toString(httpEntity);
			/*
			BufferedReader rd = new BufferedReader(new InputStreamReader(null, response.getEntity().getContent().toString()));
			
			String line = " ";
			int counter = 0;
			while((line = rd.readLine()) != null || counter < 5)
			{
				responseArr[counter] = line;
				counter++;
			}
			*/
		} 
		catch (ClientProtocolException e) 
		{
			e.printStackTrace(); 
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return responseArr;
	}
	/*
	public String getItemsFromXML(Activity activity, String xml ) throws XmlPullParserException, IOException
	{
		StringBuffer stringBuffer = new StringBuffer();
		//Resources res = activity.getResources();
		//Resources res = httpEntity.getResources();
		//XmlResourceParser xpp = res.getXml(httpEntity);
		//XmlResourceParser xpp = httpEntity.getXml();
		
		xpp.next();
		int eventType = xpp.getEventType();
		while(eventType != XmlPullParser.END_DOCUMENT)
		{
			if(eventType == XmlPullParser.START_TAG)
			{
				if(xpp.getName().equals("Title"))
				{
					stringBuffer.append(xpp.getAttributeValue(null, "title") + "\n");
				}
			}
			eventType = xpp.next();
		}
		return stringBuffer.toString();
	}
	*/
	public JSONArray GetAllBooks()
	{
		
		//String connectionString = "http://localhost/webService/getAllBooks.php";
		//String connectionString = "http://192.168.1.5/webService/getAllBooks.php";
		//String connectionString = "http://192.168.1.5/xampp/htdocs/webservice/getAllBooks.php";
		//String connectionString = "http://95.45.80.125/webService/getAllBooks.php";
		
		String connectionString = "http://3rdyearprojectlibraryweb.azurewebsites.net/api/GetAllBooks";
		
		HttpEntity httpEntity = null;
		
		try
		{
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(connectionString);
			
			HttpResponse httpResponse = httpClient.execute(httpGet);
			
			httpEntity = httpResponse.getEntity();
		} 
		catch(ClientProtocolException e) 
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{	
			e.printStackTrace();
		}
		
		JSONArray jsonArray = null;
		
		if(httpEntity != null)
		{
			try
			{
				String entityResponse = EntityUtils.toString(httpEntity);
				
				Log.e("Entity Response: ", entityResponse);
				
				jsonArray = new JSONArray(entityResponse);
				
			}
			catch(JSONException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return jsonArray;
	}
}
