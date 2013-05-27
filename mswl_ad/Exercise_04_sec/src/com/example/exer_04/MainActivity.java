package com.example.exer_04;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import com.example.exer_04.MapNode;
import com.example.exer_04.MapOverlay;


public class MainActivity extends Activity {

	private MapView myView;
    private MapController myControl;
    private GeoPoint myGeoPoint;
    private MapNode myNodeMap;
    private TextView textVLoc;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_maps);
	
		myView = (MapView) findViewById(R.id.mapView);
		textVLoc = (TextView) findViewById(R.id.tVLoc);
		myView.setBuiltInZoomControls(true);
        myView.setClickable(true);
        myControl = myView.getController();
        
        initValue();
        refreshMap(); 

	}
	
	private void initValue(){
		
		Intent myIntent = getIntent();
		
		if (myIntent!=null){
			
			myNodeMap = myIntent.getParcelableExtra("MapNode");
			
		}
	}
	
	private void refreshMap() {
		
		if (myNodeMap==null) {
			
			Toast.makeText(getBaseContext(), "Location not found!",
                    Toast.LENGTH_LONG).show();
			
			return;
		}
		
		myGeoPoint = new GeoPoint((int) (myNodeMap.mLat* 1E6), (int) (myNodeMap.mLon*1E6) );
		
		myControl.setZoom(18);
		
		myControl.animateTo(myGeoPoint);
		
		MapOverlay myOverlay = new MapOverlay();
		
		
		Drawable myDraw = getResources().getDrawable(myNodeMap.mImageResource);
        myDraw.setBounds(0, 0, 50, 50);
        
        myOverlay.setDrawable(myDraw);
        myOverlay.setGeoPoint(myGeoPoint);
        myOverlay.setTextToShow(myNodeMap.mTitle);
        
        final List<Overlay> myOverlays = myView.getOverlays();
        myOverlays.clear();
        
        myOverlays.add(myOverlay);
        
        myView.setBuiltInZoomControls(true);

        myView.setClickable(true);

        textVLoc.setText("Your Location is: \n"
                + String.valueOf(myGeoPoint.getLatitudeE6()) + " , "
                + String.valueOf(myGeoPoint.getLongitudeE6()));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
