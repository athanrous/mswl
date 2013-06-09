package com.example.final_exer;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;


public class TwitterPrefs extends PreferenceActivity {
	
	private static final String OPT_SEARCH = "Search";
	private static final String OPT_SEARCH_DEF = "http://search.twitter.com/search.json?q=";
	private static final String OPT_RANGE = "Distance";
	private static final String OPT_RANGE_DEF = "3000";
	private static final String OPT_UNITS = "Unities";
	private static final String OPT_UNITS_DEF = "km";
	private static final String OPT_RPP = "ResultsPerPage";
	private static final String OPT_RPP_DEF = "25";
	private static final String OPT_LOCMAN = "LocManual";
	private static final boolean OPT_LOCMAN_DEF = true;
	private static final String OPT_LAT = "Latitude";
	private static final String OPT_LAT_DEF = "40.417";
	private static final String OPT_LON = "Longitude";
	private static final String OPT_LON_DEF = "-3.703";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.app_settings);
	}
	
	public static String getUrlSearch(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).getString(OPT_SEARCH, OPT_SEARCH_DEF);
	}
	
	public static String getDistance(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).getString(OPT_RANGE, OPT_RANGE_DEF);
	}
	
	public static String getUnities(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).getString(OPT_UNITS, OPT_UNITS_DEF);
	}
	
	public static String getResultPerPage(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).getString(OPT_RPP, OPT_RPP_DEF);
	}
	
	public static boolean getlocManual(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(OPT_LOCMAN, OPT_LOCMAN_DEF);
	}
		
	public static String getLatitude(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).getString(OPT_LAT, OPT_LAT_DEF);
	}
	
	public static String getLongitude(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).getString(OPT_LON, OPT_LON_DEF);
	}

}
