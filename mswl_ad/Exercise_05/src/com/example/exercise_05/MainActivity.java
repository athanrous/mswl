package com.example.exercise_05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.exercise_05.AppSettings;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
	public String line;
	public StringBuilder builder;
	
	private static ArrayList<MapNode> mapArray = new ArrayList<MapNode>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new MainAsyncTask().execute();
		mAdapter = new MyAdapter(this);
		setListAdapter(mAdapter);		
		setData();
		
	}

	
	
	
	public class MainAsyncTask extends AsyncTask<Void, Void, Void>{
				
	     ProgressDialog pd_main = null;
	         
	     protected void onPreExecute()
	     {
	    	 
	    	 conInfo = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
	         mobileConn = conInfo.getNetworkInfo(0).getState();
	         wifiConn = conInfo.getNetworkInfo(1).getState();
	        
				if (mobileConn == NetworkInfo.State.CONNECTED)
	         		connectionType = "Mobile/Data Roaming";
	         else if (wifiConn == NetworkInfo.State.CONNECTED)
	         		connectionType = "Wifi Connection";
	     pd_main = ProgressDialog.show(MainActivity.this, "Network Connection Info",
	     "The Network Connection is:" + connectionType);
	    
	     }
	    
	     protected Void doInBackground(Void... arg0) {
	     // No interact with the UI in this method
	    

	     // Wait 3 seconds
	     try {
	Thread.sleep(3000);
	} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	    
	     return null;
	     }
	     protected void onPostExecute(Void unused)
	        {
	         pd_main.dismiss();
	        }

	    }
	
	public class ListAsyncTask extends AsyncTask<Void, Void, Void>{
		
		 String LoggedUserName = AppSettings.getUserName(getBaseContext());
		
		 String LoggedUserTown = AppSettings.getUserTown(getBaseContext());

	     ProgressDialog pd_entry = null;
	   
	    
	     protected void onPreExecute()
	     {
	     pd_entry = ProgressDialog.show(MainActivity.this, "User Info",
	     "User:" + "  " + LoggedUserName + "Town:" + "  " + LoggedUserTown );
	         
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
     	     
	     
	     }
	    
	     protected Void doInBackground(Void... arg0) {
	     // No interact with the UI in this method
	    

	     // Wait for 5 seconds
	     try {
	Thread.sleep(5000);
	} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	    
	     return null;
	     }
	     protected void onPostExecute(Void unused)
	        {
	         pd_entry.dismiss();
	        }

	    }
	
	 protected void onListItemClick(ListView l, View v, int pos, long id) {

	        boolean spam = ((pos + 1) % 3 == 0);

	        if (spam) {

	            Toast.makeText(this,
	                    this.getResources().getString(R.string.pub),
	                    Toast.LENGTH_SHORT).show();

	        } else {

	            pos = pos - ((pos + 1) / 3);
	            
	            
	            new ListAsyncTask().execute();
	            MapNode selectedNode = mapArray.get(pos);
	            Intent intentMapsExercise = new Intent(MainActivity.this,
	                    MapsExtActivity.class);
	            intentMapsExercise.putExtra("node", selectedNode);
	            	            
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
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem Mitem) {
		// TODO Auto-generated method stub
			
		//Due to error 'No key found for id 0' and 
		//we were enable to launch the AppSettings activity we add this method here
		
		
		switch (Mitem.getItemId()) {
		case R.id.action_settings:
			startActivity(new Intent(this, AppSettings.class));
			return true;
		}
		return false;
	}

}
