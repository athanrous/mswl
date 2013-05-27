package com.example.exercise_04;

import java.util.List;
import android.os.Bundle;
import android.view.Menu;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.example.exercise_04.MapNode;
import com.example.exercise_04.MapOverlay;


public class Main extends MapActivity {
	
	private MapView mView;
	
	private MapController mControl;
	
	private MapNode sMap;
	
	private GeoPoint sPoint;
	
	private TextView tViewLoc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tViewLoc = (TextView) findViewById(R.id.tvlocation);
		
		mView = (MapView) findViewById(R.id.myMapView);
		
		mView.setBuiltInZoomControls(true);
		
		mView.setClickable(true);
		
		mControl = mView.getController();
		
		initValue();
		
		refreshMap();
	}
			
	 private void initValue() {
		 
		 Intent valueIntent = getIntent();
		 
		 if (valueIntent != null){
			 
			 sMap = valueIntent.getParcelableExtra("MapNode");
	 
			 
		 }
		 
	 }
	 	
	private void refreshMap (){
		
		if (sMap == null) {
            Toast.makeText(getBaseContext(), "Location not available!",
                    Toast.LENGTH_LONG).show();

            return;
        }
		
		 	sPoint = new GeoPoint((int) (sMap.MapLatitude * 1000000),
	                (int) (sMap.MapLongititude * 1000000));

	        mControl.setZoom(18);
	        mControl.animateTo(sPoint);

	        MapOverlay sMapOver = new MapOverlay();

	        Drawable drawable = getResources().getDrawable(sMap.MapImageResource);
	        drawable.setBounds(0, 0, 50, 50);

	        sMapOver.setDrawable(drawable);
	        sMapOver.setGeoPoint(sPoint);
	        sMapOver.setText(sMap.MapTitle);

	        final List<Overlay> overlays = mView.getOverlays();
	        overlays.clear();

	        overlays.add(sMapOver);

	        mView.setBuiltInZoomControls(true);

	        mView.setClickable(true);

	        tViewLoc.setText("Your Location is: \n"
	                + String.valueOf(sPoint.getLatitudeE6()) + " , "
	                + String.valueOf(sPoint.getLongitudeE6()));

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
