package com.exs.util;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {

	public static final String PREFS_NAME = "MyPrefsFile";
	SharedPreferences settings;
	
	public void init(Context c){
		settings = c.getSharedPreferences(PREFS_NAME, 0);
	}
	
	//1.Set value to sharedpreffered
	public void putString(String object, String data){
		SharedPreferences.Editor editor = settings.edit();
		editor.remove(object);
		editor.putString(object,data);
		editor.commit();
	}
	
	//2.Get value from sharedpreffered
	public String getStringShared(String object){
		String data = settings.getString(object, "");
		return data;
	}
	
	//3.Remove key sharedpreffered
	public void removeString(String object){
		SharedPreferences.Editor editor = settings.edit();
		editor.remove(object);
		editor.commit();
	}
	
	//4.Remove all key
	public void removeAllString(){
		settings.edit().clear().commit();
	}

}
