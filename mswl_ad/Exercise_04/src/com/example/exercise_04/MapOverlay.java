package com.example.exercise_04;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MapOverlay extends Overlay {

	private Drawable mMarker;
	private int mMarkerX;
	private int mMarkerY;
	private String text;

	private GeoPoint mGeo;

	public void setGeoPoint (GeoPoint geoPoint)
	{
	mGeo = geoPoint;	
	}
	
	//We created this method in order to set text in a generic way
	
	public void setText(String text){ 
		this.text = text;
	}
	
	public void setDrawable(Drawable draw)
	{
	mMarker = draw;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
	         
		if (!shadow) {
	         
	     
	// Make sure to give mMarker bounds so it will draw in the overlay
	        	 
	        	 final int intrinsicWidth = mMarker.getIntrinsicWidth();
	        	 final int intrinsicHeight = mMarker.getIntrinsicHeight();
	        	 mMarker.setBounds(0, 0, intrinsicWidth, intrinsicHeight);

	        	 mMarkerX = -(intrinsicWidth / 2);
	        	 mMarkerY = -(intrinsicHeight / 2);

	        	 Paint paint = new Paint();
	        	 paint.setARGB(250,0,0,0);
	        	 paint.setAntiAlias(true);
	        	 paint.setFakeBoldText(true);

	             Point point2 = new Point();
	             Projection p = mapView.getProjection();
	             p.toPixels(mGeo, point2);
	     
	             canvas.drawText(text, point2.x - intrinsicWidth , point2.y + intrinsicHeight, paint);
	             super.draw(canvas, mapView, shadow);
	             drawAt(canvas, mMarker, point2.x + mMarkerX, point2.y + mMarkerY, shadow);

	         }
	}
	 }


