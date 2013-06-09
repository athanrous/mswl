package com.example.final_exer;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class TwitterMapOverlay extends Overlay {
	
	private Drawable mapMarker;
	private int mapMarkerXOffset;
	private int mapMarkerYOffset;
	private GeoPoint mapGeoPoint;
	private String mapText;
	
	public void setDrawable(Drawable draw) {
		mapMarker = draw;
	}
	
	public void setGeoPoint(GeoPoint geoPoint) {
		mapGeoPoint = geoPoint;			   
	}

	public void setTexto(String text) {
		mapText = text;
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		
		if (!shadow) {
			
			final int intrinsicWidth = mapMarker.getIntrinsicWidth();		
			final int intrinsicHeight = mapMarker.getIntrinsicHeight();	
			mapMarker.setBounds(0, 0, intrinsicWidth, intrinsicHeight);	
			mapMarkerXOffset = (intrinsicWidth / 2);
			mapMarkerYOffset = (intrinsicHeight / 2);

			Paint mapPaint = new Paint();
			mapPaint.setARGB(250,255,0,0);		
			mapPaint.setAntiAlias(true);		
			mapPaint.setFakeBoldText(true);	
			mapPaint.setTextSize(22);			
			
			Point mapPoint = new Point();
			Projection mapProject = mapView.getProjection();	
			mapProject.toPixels(mapGeoPoint, mapPoint);			
     		
			
			canvas.drawText(mapText, (float) (mapPoint.x - mapMarkerXOffset * 6.0), (float) (mapPoint.y + (mapMarkerYOffset * 5.6)) , mapPaint);	
			
			
			super.draw(canvas, mapView, shadow);
			drawAt(canvas, mapMarker, mapPoint.x - mapMarkerXOffset, mapPoint.y - mapMarkerYOffset, shadow);
		}
	}
}
