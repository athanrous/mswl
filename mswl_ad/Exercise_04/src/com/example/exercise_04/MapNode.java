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
		
		dest.writeString(MapTitle);
		dest.writeString(MapDescription);
	    dest.writeInt(MapImageResource);
	    dest.writeDouble(MapLatitude);
	    dest.writeDouble(MapLongititude);
		
		// TODO Auto-generated method stub
		
	}
	
	public MapNode(Parcel mp) {
		MapTitle = mp.readString();
		MapDescription = mp.readString();
		MapImageResource = mp.readInt();
		MapLatitude = mp.readDouble();
		MapLongititude = mp.readDouble();
	}

}
