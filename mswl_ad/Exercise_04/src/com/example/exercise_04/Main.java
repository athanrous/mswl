package com.example.exercise_04;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

//import com.example.mymaps.R;
//import com.example.mymaps.Main.MyLocationListener;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;


public class Main extends MapActivity {
	
	private MapView mapview=null;
	
	private MapController mapControl=null;
	
	
	LocationManager mLocationManager;
	LocationListener mLocationListener;
	
	private Location mLoc = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mapview = (MapView) findViewById(R.id.myMapView);
		
		mapview.setBuiltInZoomControls(true);
		
		mapview.setClickable(true);
		
		mapControl = mapview.getController();
		
		GeoPoint geoPoint = new GeoPoint(
				
				(int) (41.136343 * 1000000),
				
				(int) (24.891948* 1000000));
		
		mapControl.setZoom(18);
		
		mapControl.animateTo(geoPoint);
		
		setLocationListener();
	}
	
	private void refreshMap (){



		GeoPoint geoPoint = new GeoPoint(

				(int) (mLoc.getLatitude() * 1000000),

				(int) (mLoc.getLongitude()* 1000000));

		mapControl.setZoom(18);

		mapControl.animateTo(geoPoint);



	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
		mLocationManager.removeUpdates(mLocationListener);
		super.onDestroy();
	}
	
	private void setLocationListener()
	{
		

		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		mLocationListener = new MyLocationListener();
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				5000, 15, mLocationListener);

	}
	
	public class MyLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			
			mLoc = location;
			Log.d("Location" , String.valueOf(mLoc.getLatitude()) + " " + String.valueOf(mLoc.getLongitude()));
			
			refreshMap();

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


	}
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
