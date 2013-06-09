package com.example.final_exer;

import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TwitterMainActivity extends Activity implements LocationListener {
	
	public static String DATA = "DATA_TO_SEARCH";
	public static String LAT = "LATITUD";
	public static String LON = "LONGITUD";
	public String data = null;
	private LocationManager myLocMan;
	private String myProvider;
	private String myLatitud;
	private String myLongitud;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twitter_main);
		
		myLocMan = (LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		myProvider = myLocMan.getBestProvider(criteria, true); 
		
		
		Toast.makeText(getBaseContext(), getString(R.string.location_provider)+ " " + myProvider, Toast.LENGTH_SHORT).show();
		
		
        Button bt1 = (Button) this.findViewById(R.id.butSearch);
        
        Button btBrowser = (Button) this.findViewById(R.id.butBrowse);
         
		if (bt1 != null)										
		{
			bt1.setOnClickListener(new OnClickListener() { 			
				
				@Override
				public void onClick(View v) {					
					data = "oSC13";  
					data = data.replace(" ", "+");					
					
					if (data.equals("")) {							
						Toast.makeText(getBaseContext(), getString(R.string.empty_query), Toast.LENGTH_SHORT).show();
					} else {
						
						
						
						if (myLatitud == null || myLongitud == null) {
							Toast.makeText(getBaseContext(), getString(R.string.no_gps_location), Toast.LENGTH_SHORT).show();
						}
								
						Intent intent = new Intent(getBaseContext(), TwitterListActivity.class); 
						intent.putExtra(DATA, data);				
						intent.putExtra(LAT, myLatitud);			
						intent.putExtra(LON, myLongitud);			
						startActivity(intent);					
						
					}
					
				}
			} );
		}
		
		if (btBrowser != null)										
		{
			
			btBrowser.setOnClickListener( new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent browserIntent = 	new Intent(Intent.ACTION_VIEW, Uri.parse("https://conference.opensuse.org"));
					startActivity(browserIntent);
					
				}
			});
			
			
			
		}
	}

	
	@Override    
    protected void onResume() {
          super.onResume();
          
          myLocMan.requestLocationUpdates(myProvider, 10000, 15, this); 
    }

	

	
	 @Override    
	    protected void onPause() {
	          super.onPause();
	          
	          myLocMan.removeUpdates(this);
	    }
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		myLatitud = String.valueOf(location.getLatitude());
        myLongitud = String.valueOf(location.getLongitude());
        Toast.makeText(getBaseContext(), "Loc: " + myLatitud + "," + myLongitud, Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.twitter_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case R.id.action_settings:
			startActivity(new Intent(this, TwitterPrefs.class));
			return true;
		}
		return false;
	}

}
