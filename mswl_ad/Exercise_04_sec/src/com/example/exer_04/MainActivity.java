package com.example.exer_04;

import android.os.Bundle;
import android.app.Activity;
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

import com.example.exer_04.MapNode;
import com.example.exer_04.MapOverlay;


public class MainActivity extends Activity {

	private MapView mpView;
    private MapController mpControl;
    private GeoPoint mGeoPoint;
    private MapNode mNodeMap;
    private TextView textVLoc;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mpView = (MapView) findViewById(R.id.mapView);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
