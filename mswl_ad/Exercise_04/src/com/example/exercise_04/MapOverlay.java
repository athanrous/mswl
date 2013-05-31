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
	
	private Drawable mapDraw;
	private int mapMarkerX;
	private int mapMarkerY;
	private String textToShow;
	private GeoPoint mapGeoPoint;
	    
	    
	public void setGeoPoint(GeoPoint geoPoint) {
		
	        mapGeoPoint = geoPoint;
	    }

	public void setDrawable(Drawable draw) {
		
	        mapDraw = draw;
	        
	    }

	public void setTextToShow(String textToShow) {
		
	        this.textToShow = textToShow;
	        
	    }
	
	
	
    @Override
    public void draw(Canvas mapCanvas, MapView mapView, boolean show) {
        if (!show) {

            
            final int intrinsicWidth = 50;
            final int intrinsicHeight = 50;
            mapDraw.setBounds(0, 0, intrinsicWidth, intrinsicHeight);

            mapMarkerX = -(intrinsicWidth / 2);
            mapMarkerY = -(intrinsicHeight / 2);

            Paint paint = new Paint();
            paint.setARGB(250, 0, 0, 0);
            paint.setAntiAlias(true);
            paint.setFakeBoldText(true);

            Point point2 = new Point();
            Projection p = mapView.getProjection();
            p.toPixels(mapGeoPoint, point2);

            mapCanvas.drawText(textToShow, point2.x + mapMarkerX/2, point2.y + 50, paint);
            super.draw(mapCanvas, mapView, show);
            drawAt(mapCanvas, mapDraw, point2.x + mapMarkerX, point2.y
                    + mapMarkerY, show);

        }
    }

}
