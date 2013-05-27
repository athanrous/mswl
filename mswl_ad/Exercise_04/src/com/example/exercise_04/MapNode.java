package com.example.exercise_04;

import android.os.Parcel;
import android.os.Parcelable;

public class MapNode implements Parcelable  {
	
	public String MapTitle;
    public String MapDescription;
    public Integer MapImageResource;
    public double MapLatitude;
    public double MapLongititude;
    
    public MapNode() {
    	
    	super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}

}
