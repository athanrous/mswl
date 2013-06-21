package com.example.exercise_05;

import com.example.exercise_04.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class AppSettings extends PreferenceActivity {
	
	
	public EditTextPreference mEdit;
	public EditTextPreference nEdit;
	
	private static final String USER_NAME = "User";
	private static final String USER_TOWN = "Town";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_app_settings);
		addPreferencesFromResource(R.layout.activity_app_settings);
		mEdit = (EditTextPreference)findPreference("User Name");
		
		nEdit = (EditTextPreference)findPreference("User Town");	
		
	}
	
	
	public static String getUserName(Context context) {
		
		SharedPreferences _sharedPreferences  = PreferenceManager.getDefaultSharedPreferences(context);
		String Uname = _sharedPreferences.getString("User Name", "NA");
		
		return Uname;
	}
	
	public static String getUserTown(Context context) {
		
		SharedPreferences _sharedPreferences  = PreferenceManager.getDefaultSharedPreferences(context);
		String Tname = _sharedPreferences.getString("User Town", "NA");
		
		return Tname;
	}
	
	

}
