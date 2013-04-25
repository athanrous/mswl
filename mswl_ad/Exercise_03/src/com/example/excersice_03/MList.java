package com.example.excersice_03;

import java.util.ArrayList;
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
		            mynode.mTitle = this.getResources().getString(R.string.title1);
		            mynode.mDescription = this.getResources().getString(R.string.description1);
		            mynode.mImageResource = R.drawable.image_01;
		            mArray.add(mynode);

		            Node mynode2 = new Node();
		            mynode2.mTitle = this.getResources().getString(R.string.title2);
		            mynode2.mDescription = this.getResources().getString(R.string.description2);	
		            mynode2.mImageResource = R.drawable.image_02;
		            mArray.add(mynode2);

		            Node mynode3 = new Node();
		            mynode3.mTitle = this.getResources().getString(R.string.title3);
		            mynode3.mDescription = this.getResources().getString(R.string.description3);
		            mynode3.mImageResource = R.drawable.image_03;

		            mArray.add(mynode3);
		            
		        
		            mArray.addAll(mArray);  
		    }
		    
			public static class MyAdapter extends BaseAdapter 
			{
		        private int final_size;
				
				private Context mContext;
				
				public MyAdapter(Context context)
				{
					
					mContext = context;
				}

				@Override
				public int getCount() {
					
					final_size = mArray.size() + (mArray.size() / 2) + (mArray.size() % 2);
					
					return final_size;
				}

				@Override
				public Object getItem(int position) {
					return mArray.get(position);
				}

				@Override
				public long getItemId(int position) {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public View getView(int position, View convertView, ViewGroup parent) {
					
					View view;
					
					int pubad;
										
					LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
									
					pubad = (position + 1) % 3; //Set advertisment position variable
					
					if (pubad==0){
						
						view = inflater.inflate(R.layout.publicity, null); 	
						
						if (view!=null) {
							
						TextView titleTextView = (TextView) view .findViewById(R.id.pubTexto);
						titleTextView.setText(R.string.pub_message);
						titleTextView.setBackgroundColor(Color.GREEN);
						} else {
							
							System.out.println("Error in the application");	
						}
						
											
					} else {
						
						position = position - ((position + 1) / 3);
						view = inflater.inflate(R.layout.mainlist_01, null);
						
						if (view!=null || position!=0) {					
						
						TextView tvTitle = (TextView) view.findViewById(R.id.title);
						
						tvTitle.setText(mArray.get(position).mTitle);
						
						TextView tvDescription = (TextView) view.findViewById(R.id.description);
						
						tvDescription.setText(mArray.get(position).mDescription);
						
						ImageView img = (ImageView) view.findViewById(R.id.image);
						
						img.setImageDrawable(mContext.getResources().getDrawable(mArray.get(position).mImageResource));
						
						} else {
								
							System.out.println("Error in the application");
							
						}
																	
					}						
					return view;
		
					}
			}
	
}
