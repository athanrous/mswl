package com.example.exercise_05;


import android.os.Parcel;
import android.os.Parcelable;

public class MapNode implements Parcelable{
	
	public String mapTitle;
    public String mapDesc;
    public Integer mapImage;
    public Integer listRank;
    public double mapLat;
    public double mapLon;
    
    public MapNode() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public static final Parcelable.Creator<MapNode> CREATOR = new Creator<MapNode>() {
		
		@Override
		public MapNode[] newArray(int size) {
			// TODO Auto-generated method stub
			return new MapNode[size];
		}
		
		@Override
		public MapNode createFromParcel(Parcel mSource) {
			// TODO Auto-generated method stub
			return new MapNode(mSource);
		}
	};
	@Override 
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel mDest, int mFlag) {
		// TODO Auto-generated method stub
		mDest.writeString(mapTitle);
		mDest.writeString(mapDesc);
		mDest.writeInt(mapImage);
		mDest.writeDouble(mapLat);
		mDest.writeDouble(mapLon);
		mDest.writeInt(listRank);
	}
	
	public MapNode(Parcel mPar){
		
		mapTitle = mPar.readString();
		mapDesc = mPar.readString();
        mapImage = mPar.readInt();
        mapLat = mPar.readDouble();
        mapLon = mPar.readDouble();
        listRank = mPar.readInt();

	
	}
	

}
