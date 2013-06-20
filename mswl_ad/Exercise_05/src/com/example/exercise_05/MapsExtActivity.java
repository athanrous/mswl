package com.example.exercise_05;

import java.util.List;

import com.example.exercise_04.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MapsExtActivity extends MapActivity {

	 private MapView mapView;
	 private MapController mapController;
	 private GeoPoint mGeoPoint;
	 private MapNode mNodeMap;
	 private TextView textViewLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps_ext);
		
		
        mapView = (MapView) findViewById(R.id.mapView);
        textViewLocation = (TextView) findViewById(R.id.textViewLocation);
        mapView.setBuiltInZoomControls(true);
        mapView.setClickable(true);
        mapController = mapView.getController();
        
        initializeValuesFromIntent();

        refreshMap();
	}
	
	
	   private void initializeValuesFromIntent() {
	   
	        Intent intentFromActivity = getIntent();

	        if (intentFromActivity != null) {

	            mNodeMap = intentFromActivity.getParcelableExtra("node");

	        }

	    }

	   
	   private void refreshMap() {
	        if (mNodeMap == null) {
	            Toast.makeText(getBaseContext(), "The location is not available!",
	                    Toast.LENGTH_LONG).show();

	            return;
	        }

	        mGeoPoint = new GeoPoint((int) (mNodeMap.mapLat * 1E6),
	                (int) (mNodeMap.mapLon * 1E6));

	        mapController.setZoom(18);
	        mapController.animateTo(mGeoPoint);

	        MapOverlay myMapOver = new MapOverlay();

	        Drawable drawable = getResources().getDrawable(mNodeMap.mapImage);
	        drawable.setBounds(0, 0, 50, 50);

	        myMapOver.setDrawable(drawable);
	        myMapOver.setGeoPoint(mGeoPoint);
	        myMapOver.setTextToShow(mNodeMap.mapTitle);

	        final List<Overlay> overlays = mapView.getOverlays();
	        overlays.clear();

	        overlays.add(myMapOver);

	        mapView.setBuiltInZoomControls(true);

	        mapView.setClickable(true);

	        // Edw prepei na kanw allages
	        textViewLocation.setText("Location:"
	                + String.valueOf(mGeoPoint.getLatitudeE6()) + " , "
	                + String.valueOf(mGeoPoint.getLongitudeE6()) + " "
	                + "Temprature:"
	                + mNodeMap.Temp + ""
	                
	        		);
	    }
   

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.maps_ext, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
