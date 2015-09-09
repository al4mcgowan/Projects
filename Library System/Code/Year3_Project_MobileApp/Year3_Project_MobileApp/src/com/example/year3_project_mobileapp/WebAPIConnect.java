package com.example.year3_project_mobileapp;
//AUTHOR: X00084900 Christopher Newell
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

public class WebAPIConnect 
{
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public String getXmlFromUrl(String url)
	{
		String xml = null;
		System.out.println("MSG- XMLParser::getXmlFromURL Launch!");
		try{
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			xml = EntityUtils.toString(httpEntity);
			
		}
		catch (UnsupportedEncodingException e) 
		{
			System.out.println("UnsupportedEncodingException");
            e.printStackTrace();
        } 
		catch (ClientProtocolException e) 
		{
			System.out.println("ClientProtocolException");
            e.printStackTrace();
        } 
		catch (IOException e) 
		{	
			System.out.println("IOException");
            e.printStackTrace();
        }
		catch(Exception e)
		{
			System.out.println("Exception caught, Website currently not available.");
			e.printStackTrace();
		}
		
		System.out.println("MSG- XMLParser::getXmlFromURL End!");
		System.out.println(xml);
		return xml;
	}
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public JSONArray GetJSONFromUrl(String url)
	{
		HttpEntity httpEntity = null;
		
		try
		{
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			
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
				System.out.println("Converting entity response to jsonarray");
				String entityResponse = EntityUtils.toString(httpEntity);
				Log.e("Entity Response: ", entityResponse);
				jsonArray = new JSONArray(entityResponse);
				System.out.println("finished Converting entity response to jsonarray");
				
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
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public JSONObject retrieveJSONObjectFromURL(String url)
	{
		HttpEntity httpEntity = null;
		JSONObject jsonObj = null;
		try
		{
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			
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
		
		
		
		if(httpEntity != null)
		{
			try
			{
				System.out.println("Converting entity response to jsonObject");
				String entityResponse = EntityUtils.toString(httpEntity);
				Log.e("Entity Response: ", entityResponse);
				jsonObj = new JSONObject(entityResponse);
				System.out.println("finished Converting entity response to jsonObject");
				
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
		return jsonObj;
	}
	
	public Document getDomElement(String xml)
	{
		System.out.println("MSG- XMLParser::getDomElement Launch!");
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			doc = db.parse(is);
		}catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        }
		System.out.println("MSG- XMLParser::getDomElement End!");
		return doc;
	}
	
	public String getValue(Element item, String str)
	{
		NodeList n = item.getElementsByTagName(str);
		return this.getElementValue(n.item(0));
	}
	
	public final String getElementValue(Node elem)
	{
		Node child;
		if(elem != null)
		{
			if(elem.hasChildNodes())
			{
				for(child = elem.getFirstChild(); child != null; child = child.getNextSibling())
				{
					if(child.getNodeType() == Node.TEXT_NODE)
					{
						return child.getNodeValue();
					}
				}
			}
		}
		return "";
	}
	
	
}
