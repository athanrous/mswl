package com.example.final_exer;

import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class TwitterMapActivity extends MapActivity {

	private MapView mapView = null;				
	private MapController mapControl = null;	
	private TextView tvLocation = null;			
	private String mapLatitude = null;			
	private String mapLongitude = null;			

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twitter_map);
		
		tvLocation = (TextView) this.findViewById(R.id.tvMap);
		mapView = (MapView) findViewById(R.id.TTSMapView);
		String mapLat = null;						
		String mapLon = null;						
						
		Intent i = getIntent();					
		
		  
        if (i != null)  						
		{
			mapLatitude = i.getStringExtra(TwitterListActivity.MPLAT); 	
			mapLongitude = i.getStringExtra(TwitterListActivity.MPLON); 	

			

			if (mapLatitude == null)  			
			{
				mapLatitude = TwitterListActivity.LAT_DEF;	
			}

			if (mapLongitude == null)  			
			{
				mapLongitude = TwitterListActivity.LON_DEF;	
			}
		}
		
		
		
		if (mapLatitude.length()>12) {
			mapLat = mapLatitude.substring(0, 12);
		} else {
			mapLat = mapLatitude;
		}
		
		if (mapLongitude.length()>12) {
			mapLon = mapLongitude.substring(0, 12);
		} else {
			mapLon = mapLongitude;
		}
		
			
		tvLocation.setText("Lat: " + mapLat + ", Long: " + mapLon);	
		mapView.setBuiltInZoomControls(true); 	
		mapView.setClickable(true);				
		mapControl = mapView.getController();	
	
		GeoPoint geoPoint = new GeoPoint (		
			(int) (Double.valueOf(mapLatitude) * 1000000),	
			(int) (Double.valueOf(mapLongitude) * 1000000));	
		
		mapControl.setZoom(14);					
		mapControl.animateTo(geoPoint);			
		
		TwitterMapOverlay myMapOver = new TwitterMapOverlay();	// Makes a map layout
		myMapOver.setDrawable(getResources().getDrawable(R.drawable.image_01));	// Image to print in map
		myMapOver.setGeoPoint(geoPoint);			
		myMapOver.setTexto("oSC13");				// Text to print in map

		final List<Overlay> overlays = mapView.getOverlays();
		overlays.clear();

		overlays.add(myMapOver);
			
	}

	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
