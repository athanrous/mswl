package com.example.exer_04;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MapOverlay extends Overlay {
	
	
	private Drawable mDrawMark;
    private int mMarkerX;
    private int mMarkerY;
    private String textShow;
    private GeoPoint mGeoPoint;
    
    public void setGeoPoint(GeoPoint geoPoint) {
        mGeoPoint = geoPoint;
    }
    
    public void setDrawable(Drawable draw) {
        mDrawMark = draw;
    }
    
    public void setTextToShow(String textShow) {
        this.textShow = textShow;
    }
    
    
    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {
        if (!shadow) {

            // Make sure to give mMarker bounds so it will draw in the overlay
            final int intrinsicWidth = 50;
            final int intrinsicHeight = 50;
            mDrawMark.setBounds(0, 0, intrinsicWidth, intrinsicHeight);

            mMarkerX = -(intrinsicWidth / 2);
            mMarkerY = -(intrinsicHeight / 2);

            Paint paint = new Paint();
            paint.setARGB(250, 0, 0, 0);
            paint.setAntiAlias(true);
            paint.setFakeBoldText(true);

            Point point2 = new Point();
            Projection p = mapView.getProjection();
            p.toPixels(mGeoPoint, point2);

            canvas.drawText(textShow, point2.x + mMarkerX/2, point2.y + 50, paint);
            super.draw(canvas, mapView, shadow);
            drawAt(canvas, mDrawMark, point2.x + mMarkerX, point2.y
                    + mMarkerY, shadow);

        }
    }


}
