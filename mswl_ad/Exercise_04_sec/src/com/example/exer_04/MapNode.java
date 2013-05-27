package com.example.exer_04;

import android.os.Parcel;
import android.os.Parcelable;

public class MapNode implements Parcelable {

	
	public String mTitle;
    public String mDescription;
    public Integer mImageResource;
    public double mLat;
    public double mLon;
	
        
    public static final Parcelable.Creator<MapNode> CREATOR = new Creator<MapNode>(){

		@Override
		public MapNode createFromParcel(Parcel in) {
			// TODO Auto-generated method stub
			return new MapNode(in);
		}

		@Override
		public MapNode[] newArray(int size) {
			// TODO Auto-generated method stub
			return new MapNode[size];
		}
		
    };
    
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel fin, int flags) {
		// TODO Auto-generated method stub
		
		fin.writeString(mTitle);
        fin.writeString(mDescription);
        fin.writeInt(mImageResource);
        fin.writeDouble(mLat);
        fin.writeDouble(mLon);
		
	}
	
	public MapNode(Parcel in) {
		// TODO Auto-generated constructor stub
		
		 	mTitle = in.readString();
	        mDescription = in.readString();
	        mImageResource = in.readInt();
	        mLat = in.readDouble();
	        mLon = in.readDouble();
	}
	
	public MapNode(){
		
		super();
	}


}
