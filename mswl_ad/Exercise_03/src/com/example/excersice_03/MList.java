package com.example.excersice_03;

import java.util.ArrayList;

import com.example.exercise_03.R;
import com.example.exercise_03.MList.MyAdapter;
import com.example.exercise_03.MList.Node;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ArrayAdapter;
//import android.widget.ListAdapter;
//import android.widget.ListView;


public class MList extends ListActivity {
	
	private MyAdapter mAdapter = null ;	
	
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
	
			
		    private void setData ()
		    {

		            mArray.clear();

		            Node mynode = new Node();
		            

		            // Restaurant 1  
		            //mynode.mTitle = "Title1" ;
			        //mynode.mDescription = "Description1" ;		
		            
		            mynode.mTitle = this.getResources().getString(R.string.title1);
		            mynode.mDescription = this.getResources().getString(R.string.description1);
		            mynode.mImageResource = R.drawable.image_01;
		            mArray.add(mynode);

		            //Restaurant 2
		            //mynode2.mTitle = "Title2" ;
		            //mynode2.mDescription = "Description2" ;	
		            
		            Node mynode2 = new Node();
		           
		            mynode2.mTitle = this.getResources().getString(R.string.title2);
		            mynode2.mDescription = this.getResources().getString(R.string.description2);	
		            mynode2.mImageResource = R.drawable.image_02;
		            mArray.add(mynode2);

		            
		            //Restaurant 3
		            // mynode3.mTitle = "Title3" ;
			        // mynode3.mDescription = "Description3" ;
		            
		            Node mynode3 = new Node();
		            mynode3.mTitle = this.getResources().getString(R.string.title3);
		            mynode3.mDescription = this.getResources().getString(R.string.description3);
		            mynode3.mImageResource = R.drawable.image_03;

		            mArray.add(mynode3);
		            
		         //   for(int i=1; i<10; i++){
		          //  	mArray.addAll(mArray);   
		            
		            mArray.addAll(mArray);  
		            	
		         //   }	
		            

		    }
	
	

	
}
