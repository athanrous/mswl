package com.example.exercise_04;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
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
import com.example.exercise_04.MapNode;

public class MapListActiviy extends ListActivity {
	
	private MyAdapter mAdapter = null;
	
	// ArrayList
    private static ArrayList<MapNode> mArray = new ArrayList<MapNode>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_activiy);
		setData();
        
        mAdapter = new MyAdapter(this);
        setListAdapter(mAdapter);	                
	    	   
	}
			
	private void setData ()
    {

            mArray.clear();

            MapNode mynode = new MapNode();
            mynode.MapTitle = this.getResources().getString(R.string.title1);
            mynode.MapDescription = this.getResources().getString(R.string.description1);
            mynode.MapImageResource = R.drawable.image_01; 
            mynode.MapLatitude = 41.140932;
            mynode.MapLongititude = 24.889673;
            mArray.add(mynode);

            MapNode mynode2 = new MapNode();
            mynode2.MapTitle = this.getResources().getString(R.string.title2);
            mynode2.MapDescription = this.getResources().getString(R.string.description2);	
            mynode2.MapImageResource = R.drawable.image_02;
            mynode.MapLatitude = 41.142807;
            mynode.MapLongititude = 24.890789;
            mArray.add(mynode2);

            MapNode mynode3 = new MapNode();
            mynode3.MapTitle = this.getResources().getString(R.string.title3);
            mynode3.MapDescription = this.getResources().getString(R.string.description3);
            mynode3.MapImageResource = R.drawable.image_03; 
            mynode.MapLatitude = 41.144374;
            mynode.MapLongititude = 24.886883;
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
				
				tvTitle.setText(mArray.get(position).MapTitle);
				
				TextView tvDescription = (TextView) view.findViewById(R.id.description);
				
				tvDescription.setText(mArray.get(position).MapDescription);
				
				ImageView img = (ImageView) view.findViewById(R.id.image);
				
				img.setImageDrawable(mContext.getResources().getDrawable(mArray.get(position).MapImageResource));
				
				} else {
						
					System.out.println("Error in the application");
					
				}
															
			}						
			return view;

			}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_activiy, menu);
		return true;
	}

}
