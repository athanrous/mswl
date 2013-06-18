package com.example.exercise_05;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.exercise_04.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;


public class MapsActivity extends MapActivity {
	
	
	private MapView mapView;
    private MapController mapController;

    private Location mLocation;
    private LocationManager mLocationManager;
    private MyLocationListener mLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_ext);

        
        mapView = (MapView) findViewById(R.id.mapView);

        
        mapView.setBuiltInZoomControls(true);
        mapView.setClickable(true);
        mapController = mapView.getController();
        GeoPoint geoPoint = new GeoPoint((int) (0 * 1E6), (int) (0 * 1E6));
        mapController.setZoom(18);
        mapController.animateTo(geoPoint);

        setLocationListener();

    }

    private void refreshMap() {

        GeoPoint geoPoint = new GeoPoint((int) (mLocation.getLatitude() * 1E6),
                (int) (mLocation.getLongitude() * 1E6));

        mapController.setZoom(18);
        mapController.animateTo(geoPoint);
    }

    private void setLocationListener() {

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mLocationListener = new MyLocationListener();

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                5000, 15, mLocationListener);
    }

    public class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            mLocation = location;

            Log.d("MyLocationListener",
                    "Location:\n * Latitude:\t"
                            + String.valueOf(mLocation.getLatitude())
                            + "\n* Longitude:\t"
                            + String.valueOf(mLocation.getLongitude()));
            refreshMap();
        }
        
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

    
        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

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

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        mLocationManager.removeUpdates(mLocationListener);
        super.onDestroy();
    }

}
