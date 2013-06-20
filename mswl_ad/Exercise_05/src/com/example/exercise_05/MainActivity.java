package com.example.exercise_05;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
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
	
	public static final String TAG_DATA = "data";
	public static final String TAG_CITY = "city";
	public static final String TAG_LATITUDE = "lat";
	public static final String TAG_LONGITUDE = "lon";
	public static final String TAG_AVG_TEMP = "avg_temp";
	public static final String TAG_POPULATION = "pop";
	public String connectionType;
	public ConnectivityManager conInfo;
	public State mobileConn;
	public State wifiConn;
	
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
	            
	            //Here we get information about the type of connection
	            
	            conInfo = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
	            mobileConn = conInfo.getNetworkInfo(0).getState();
	            wifiConn = conInfo.getNetworkInfo(1).getState();
	           
				if (mobileConn == NetworkInfo.State.CONNECTED)
	            		connectionType = "Mobile/Data Roaming";
	            else if (wifiConn == NetworkInfo.State.CONNECTED)
	            		connectionType = "Wifi Connection";
				
				System.out.println(connectionType);

	            MapNode selectedNode = mapArray.get(pos);
	            Intent intentMapsExercise = new Intent(MainActivity.this,
	                    MapsExtActivity.class);
	            intentMapsExercise.putExtra("node", selectedNode);
	            
	            MapNode ATH = mapArray.get(0); // Get the first element of the MapArray (Athens)
	            JSONArray ATHdata = null;
	    		ArrayList<HashMap<String, String>> ATHList = new ArrayList<HashMap<String, String>>();
	    		
	    		//We create an ArrayList in order to store the JSONArray and it's elements
	    		
	    		JSONParser ATHParser = new JSONParser();
	            JSONObject ATHjson = ATHParser.getJSONFromUrl(ATH.node_url);
	            
	            try {
	            	
	            	ATHdata = ATHjson.getJSONArray(TAG_DATA);
	                for(int i = 0; i < ATHdata.length(); i++){
	                	 JSONObject cATH = ATHdata.getJSONObject(i);    			
	        			// Storing each json item in variable
	        			String Acity = cATH.getString(TAG_CITY);
	        			String Alat = cATH.getString(TAG_LATITUDE);
	        			String Alon = cATH.getString(TAG_LONGITUDE);
	        			String Aavg_temp = cATH.getString(TAG_AVG_TEMP);
	        			String Apopulation = cATH.getString(TAG_POPULATION);	
					//We receive the value as string and transform it to Float
					
					float ATHLatitude = Float.parseFloat(Alat);		
					float ATHLongitude = Float.parseFloat(Alon);
				
					ATH.mapLat = ATHLatitude;
					ATH.mapLon = ATHLongitude;
					ATH.Temp = Aavg_temp;	
					
					
	                }
	                
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
	           
	        	          
	        	MapNode MAD = mapArray.get(1); // Get the second element of the MapArray (Madrid)
	        	
	        	 JSONArray MADdata = null;
		    	 ArrayList<HashMap<String, String>> MADList = new ArrayList<HashMap<String, String>>();
		    	 
		    	//We create an ArrayList in order to store the JSONArray and it's elements
		    		
		    	JSONParser MADParser = new JSONParser();
		        JSONObject MADjson = MADParser.getJSONFromUrl(MAD.node_url);
	        	
		        try {
	            	
	            	MADdata = MADjson.getJSONArray(TAG_DATA);
	                for(int i = 0; i < MADdata.length(); i++){
	                	 JSONObject cMAD = MADdata.getJSONObject(i);    			
	        			// Storing each json item in variable
	        			String Mcity = cMAD.getString(TAG_CITY);
	        			String Mlat = cMAD.getString(TAG_LATITUDE);
	        			String Mlon = cMAD.getString(TAG_LONGITUDE);
	        			String Mavg_temp = cMAD.getString(TAG_AVG_TEMP);
	        			String Mpopulation = cMAD.getString(TAG_POPULATION);
	        			
					
					//We receive the value as string and transform it to Float
					
					float MADLatitude = Float.parseFloat(Mlat);		
					float MADLongitude = Float.parseFloat(Mlon);
				
					MAD.mapLat = MADLatitude;
					MAD.mapLon = MADLongitude;
					MAD.Temp = Mavg_temp;	
					
					
	                }
	                
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	
	        	
	        	MapNode NYC = mapArray.get(2); // Get the third element of the MapArray (New York)
	        	
	        	JSONArray NYCdata = null;
		    	ArrayList<HashMap<String, String>> NYCList = new ArrayList<HashMap<String, String>>();
	        	
		    	//We create an ArrayList in order to store the JSONArray and it's elements
	    		
		    	JSONParser NYCParser = new JSONParser();
		        JSONObject NYCjson = NYCParser.getJSONFromUrl(NYC.node_url);
		        
		        try {
	            	
	            	NYCdata = NYCjson.getJSONArray(TAG_DATA);
	                for(int i = 0; i < NYCdata.length(); i++){
	                	 JSONObject cNYC = NYCdata.getJSONObject(i);    			
	        			// Storing each json item in variable
	        			String Ncity = cNYC.getString(TAG_CITY);
	        			String Nlat = cNYC.getString(TAG_LATITUDE);
	        			String Nlon = cNYC.getString(TAG_LONGITUDE);
	        			String Navg_temp = cNYC.getString(TAG_AVG_TEMP);
	        			String Npopulation = cNYC.getString(TAG_POPULATION);
	        			
					
					//We receive the value as string and transform it to Float
					
					float NYCLatitude = Float.parseFloat(Nlat);		
					float NYCLongitude = Float.parseFloat(Nlon);
				
					NYC.mapLat = NYCLatitude;
					NYC.mapLon = NYCLongitude;
					NYC.Temp = Navg_temp;	
					
					
	                }
	                
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
		      
		      
	            
	            startActivity(intentMapsExercise);
	            
	        }
	    }
	
	 private void setData() {

	        mapArray.clear();

	        MapNode myMnode = new MapNode();

	        
	        myMnode.mapTitle = this.getResources().getString(R.string.title1);
	        myMnode.mapDesc = this.getResources().getString(
	                R.string.description1);
	        myMnode.mapImage = R.drawable.athens;
	        myMnode.node_url = "http://zoumpis.eu/json/athens.json";
	        
	        // Athens	        
	        mapArray.add(myMnode);
	                
	        MapNode mynode2 = new MapNode();

	        mynode2.mapTitle = this.getResources().getString(R.string.title2);
	        mynode2.mapDesc = this.getResources().getString(
	                R.string.description2);
	        mynode2.mapImage = R.drawable.madrid;
	        mynode2.node_url="http://zoumpis.eu/json/madrid.json";
	        // Madrid
        
	        mapArray.add(mynode2);

	        MapNode mynode3 = new MapNode();

	        mynode3.mapTitle = this.getResources().getString(R.string.title3);
	        mynode3.mapDesc = this.getResources().getString(
	                R.string.description3);
	        mynode3.mapImage = R.drawable.nyc;
	        mynode3.node_url= "http://zoumpis.eu/json/nyc.json";
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
