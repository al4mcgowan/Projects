package com.example.year3_project_mobileapp;
//AUTHOR: X00084900 Christopher Newell

/***************************************************************************************
*    Title: <Building ListView using Custom Adapter>
*    Author: <Nilanchala>
*    Date: <May 6, 2013>
*    Code version: <1>
*    Availability: <http://javatechig.com/android/android-listview-tutorial#3-building-listviewusing-custom-adapter> 
*
***************************************************************************************/
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter
{
	private ArrayList bookListData;
	private LayoutInflater layoutInfl;
	
	public CustomListAdapter(Context theContext, ArrayList theListData)
	{
		bookListData = theListData;
		layoutInfl = LayoutInflater.from(theContext);
	}

	@Override
	public int getCount() 
	{
		return bookListData.size();
	}

	@Override
	public Object getItem(int position) 
	{
		return bookListData.get(position);
	}

	@Override
	public long getItemId(int position) 
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		ViewHolder holder;
		if(convertView == null)
		{
			convertView = layoutInfl.inflate(R.layout.book_list_row_layout, null);
			holder = new ViewHolder();
			holder.titleView = (TextView) convertView.findViewById(R.id.title);
			holder.authorView = (TextView) convertView.findViewById(R.id.author);
			holder.genreView = (TextView) convertView.findViewById(R.id.genre);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.titleView.setText(((BookListItem) bookListData.get(position)).getTitle());
		holder.authorView.setText( "By " + ((BookListItem) bookListData.get(position)).getAuthor());
		holder.genreView.setText(((BookListItem) bookListData.get(position)).getGenre());
		
		return convertView;
	}
	
	static class ViewHolder
	{
		TextView titleView;
		TextView authorView;
		TextView genreView;
		
	}
}
