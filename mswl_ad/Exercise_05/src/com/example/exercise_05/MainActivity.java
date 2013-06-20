package com.example.exercise_05;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exercise_04.R;
import com.example.exercise_05.MapNode;
import com.example.exercise_05.JSONParser;

public class MainActivity extends ListActivity {
	

	private MyAdapter mAdapter = null;
	
	public static final String TAG_CONTACTS = "contacts";
	public static final String TAG_COORDINATES = "coord";
	public static final String TAG_LATITUDE = "lat";
	public static final String TAG_LONGITUDE = "lot";
	public static final String TAG_SYS = "sys";	
	public static final String TAG_COUNTRY = "country";	
	public static final String TAG_SUNRISE = "sunrise";	
	public static final String TAG_SUNSET  = "sunset";	
	public static final String TAG_WEATHER = "weather";	
	public static final String TAG_ID = "id";	
	public static final String TAG_MAIN = "main";	
	public static final String TAG_DESCRIPTION = "description";	
	public static final String TAG_ICON = "icon";	
	public static final String TAG_BASE = "base";	
	public static final String TAG_MAIN_01 ="main_01";	
	public static final String TAG_TEMP = "temp";	
	public static final String TAG_HUMIDITY = "humidity";	
	public static final String TAG_PRESSURE = "pressure";	
	public static final String TAG_TEMP_MIN ="temp_min"; 
	public static final String TAG_TEMP_MAX = "temp_max";
	
	
	private static ArrayList<MapNode> mapArray = new ArrayList<MapNode>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mAdapter = new MyAdapter(this);
		setListAdapter(mAdapter);
		setData();
		
	}

	 protected void onListItemClick(ListView l, View v, int pos, long id) {

	        boolean spam = ((pos + 1) % 3 == 0);

	        if (spam) {

	            Toast.makeText(this,
	                    this.getResources().getString(R.string.pub),
	                    Toast.LENGTH_SHORT).show();

	        } else {

	            pos = pos - ((pos + 1) / 3);

	            MapNode selectedNode = mapArray.get(pos);
	            Intent intentMapsExercise = new Intent(MainActivity.this,
	                    MapsExtActivity.class);
	            intentMapsExercise.putExtra("node", selectedNode);
	            
	            MapNode ATH = mapArray.get(0); // Get the first element of the MapArray (Athens)
	            ATH.node_url = "http://api.openweathermap.org/data/2.5/find?q=New%20York&mode=json";
	            JSONParser ATHParser = new JSONParser();
	            JSONObject ATHjson = ATHParser.getJSONFromUrl(ATH.node_url);
	            
	            try {
					JSONObject coordObj = ATHjson.getJSONObject(TAG_COORDINATES);
					
					String lat_str = ATHjson.getString(TAG_LATITUDE); //We receive the value as string and transform it to Float
					
					float Latitude = Float.parseFloat(lat_str);
					
					String long_str = ATHjson.getString(TAG_LONGITUDE);
					
					float Longitude = Float.parseFloat(long_str);
					
					
					ATH.mapLat = Latitude;
					
					ATH.mapLon = Longitude;
					
					ATH.Temp = 10;
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
	            	
	            
		        
		        
	            	
	            
	        	
	        	
	        	  
	        
	          
	        	MapNode MAD = mapArray.get(1); // Get the second element of the MapArray (Madrid)
	        	MAD.mapLon = -3.70256;
	        	MAD.mapLat = 40.4165;
	        	MAD.Temp = 12;
	        	
	        	
	        	MapNode NYC = mapArray.get(2); // Get the third element of the MapArray (New York)
	        	NYC.mapLon = -74.005966;
		        NYC.mapLat = 40.714272;
		        NYC.Temp = 13;
	        	
		        
	        	
	        	
	            // startActivity(intent ); 
	            //Prwta thn Activity me to JSON kai meta methn maps.
	            //Mesa me to JSON tha elegxei kai internet connectivity
	            
	            startActivity(intentMapsExercise);
	            
	        }
	    }
	
	
	
	// public  getJSONData(){
		 
	//	 return null;
		 
	// }
	 private void setData() {

	        mapArray.clear();

	        MapNode myMnode = new MapNode();

	        
	        myMnode.mapTitle = this.getResources().getString(R.string.title1);
	        myMnode.mapDesc = this.getResources().getString(
	                R.string.description1);
	        myMnode.mapImage = R.drawable.athens;
	        
	        // Athens	        
	        mapArray.add(myMnode);
	                
	        MapNode mynode2 = new MapNode();

	        mynode2.mapTitle = this.getResources().getString(R.string.title2);
	        mynode2.mapDesc = this.getResources().getString(
	                R.string.description2);
	        mynode2.mapImage = R.drawable.madrid;
	        // Madrid
        
	        mapArray.add(mynode2);

	        MapNode mynode3 = new MapNode();

	        mynode3.mapTitle = this.getResources().getString(R.string.title3);
	        mynode3.mapDesc = this.getResources().getString(
	                R.string.description3);
	        mynode3.mapImage = R.drawable.nyc;
	        // New York

	        mapArray.add(mynode3);

	        mapArray.addAll(mapArray);

	    } 

	 
	   public static class MyAdapter extends BaseAdapter {

	        private Context mapContext;

	        public MyAdapter(Context context) {
	            // TODO Auto-generated constructor stub
	           
	            mapContext = context;
	        }

	        @Override
	        public int getCount() {
	            return mapArray.size() + (mapArray.size() / 2) + (mapArray.size() % 2);
	        }

	        @Override
	        public Object getItem(int position) {
	            return mapArray.get(position);
	        }

	        @Override
	        public long getItemId(int position) {
	            // TODO Auto-generated method stub
	            
	            return 0;
	        }

	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {

	            View view;

	            LayoutInflater inflater = (LayoutInflater) mapContext
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	            boolean advertise = ((position + 1) % 3 == 0);

	            if (advertise) {

	                view = inflater.inflate(R.layout.pub_item, null);

	                TextView titleTextView = (TextView) view
	                        .findViewById(R.id.pubs);
	                titleTextView.setText(R.string.pub);
	                titleTextView.setBackgroundColor(Color.YELLOW);

	            } else {

	                position = position - ((position + 1) / 3);

	                view = inflater.inflate(R.layout.main_list, null);
	                TextView titleTextView = (TextView) view
	                        .findViewById(R.id.title);
	                titleTextView.setText(mapArray.get(position).mapTitle);

	                TextView descriptionTextView = (TextView) view
	                        .findViewById(R.id.description);
	                descriptionTextView.setText(mapArray.get(position).mapDesc);

	                ImageView imageViewLogo = (ImageView) view
	                        .findViewById(R.id.image);
	                imageViewLogo.setImageDrawable(mapContext.getResources()
	                        .getDrawable(mapArray.get(position).mapImage));

	            }
	            return view;
	        }

	    }
			
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
