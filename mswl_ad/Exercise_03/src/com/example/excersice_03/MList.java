package com.example.excersice_03;


import java.util.ArrayList;

import com.example.exercise_03.R;
import com.example.exercise_03.MList.MyAdapter;
import com.example.exercise_03.MList.Node;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MList extends ListActivity {
	

	private MyAdapter mAdapter = null;
	
	
	// We define a structure to save the data
	public class Node 
	{ 
		public String mTitle;
		public String mDescription;
		public Integer mImageResource;
	}
	
	// ArrayList
	private static ArrayList<Node> mArray = new ArrayList<Node>();
	

	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);                       
        setContentView(R.layout.activity_main);
        setData();
        
        mAdapter = new MyAdapter(this);
        setListAdapter(mAdapter);	                
	    	   
	}
	
	
    protected void onListItemClick(ListView l, View v, int position, long id) 
	{

    	// Create a new intent to call other Activity. 
    	// Using the methods "putExtra" we can
    	// send data to the new activity
    	   	
    	
    	Toast.makeText(this, mArray.get(position).mTitle, Toast.LENGTH_SHORT).show();
	}

}
