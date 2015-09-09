package com.example.year3_project_mobileapp;
//AUTHOR: X00084900 Christopher Newell

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.year3_project_mobileapp.CustomListAdapter.ViewHolder;

//THIS CLASS IS THE CUSTOM ADAPTER FOR THE LIST OF BOOKS RETREIVED WHEN A USER LOGS IN, DISPLAYING THAT SPECIFIC USERS LIST OF BOOKS ON LOAN
public class UserBooksCustomListAdapter extends BaseAdapter
{
	private ArrayList userBooks;
	private LayoutInflater LayoutInfl;
	
	
	public UserBooksCustomListAdapter(Context theContext, ArrayList theListData)
	{
		userBooks = theListData;
		LayoutInfl = LayoutInflater.from(theContext);
	}
	
	@Override
	public int getCount() 
	{
		return userBooks.size();
	}

	@Override
	public Object getItem(int position) 
	{
		return userBooks.get(position);
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
			convertView = LayoutInfl.inflate(R.layout.user_book_list_row_layout, null);
			holder = new ViewHolder();
			holder.titleView = (TextView) convertView.findViewById(R.id.userBookItemText);
			convertView.setTag(holder);
			
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();	
		}
		
		
		holder.titleView.setText(((userBooksListItem) userBooks.get(position)).getUserBookTitle());
		
		return convertView;
	}
	static class ViewHolder
	{
		TextView titleView;
	}
	
}
