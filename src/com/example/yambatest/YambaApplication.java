package com.example.yambatest;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.text.TextUtils;

public class YambaApplication extends Application implements OnSharedPreferenceChangeListener{
	
	private boolean isServiceRunning =false;
	public Twitter twitter;
	private SharedPreferences prefs;
	
	
	public boolean isServiceRunning() {
		return isServiceRunning;
	}

	public void setServiceRunning(boolean isServiceRunning) {
		this.isServiceRunning = isServiceRunning;
	}
	
	public synchronized Twitter getTwitter(){
		if(twitter==null){
			String name = prefs.getString("userName", "");
			String password = prefs.getString("password", "");
			
			if(!TextUtils.isEmpty(name)&&TextUtils.isEmpty(password)){
				twitter = new Twitter(name,password);
				twitter.setAPIRootUrl("http://yamba.marakana.com/api");
			}
		}
		return twitter;
	}
	
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub
		
	}

}
