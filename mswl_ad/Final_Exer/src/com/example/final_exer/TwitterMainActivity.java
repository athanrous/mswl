package com.example.final_exer;

import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TwitterMainActivity extends Activity {
	
	public static String DATA = "DATA_TO_SEARCH";
	public static String LAT = "LATITUD";
	public static String LON = "LONGITUD";
	public String data = null;
	private LocationManager myLocMgr;
	private String myProvider;
	private String myLatitud;
	private String myLongitud;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twitter_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.twitter_main, menu);
		return true;
	}

}
