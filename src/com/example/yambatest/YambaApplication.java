package com.example.yambatest;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

public class YambaApplication extends Application implements OnSharedPreferenceChangeListener{
	
	private boolean isServiceRunning =false;
	public Twitter twitter;
	private SharedPreferences prefs;
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
	}

	public boolean isServiceRunning() {
		return isServiceRunning;
	}

	public void setServiceRunning(boolean isServiceRunning) {
		this.isServiceRunning = isServiceRunning;
	}
	
	public synchronized Twitter getTwitter(){
		if(twitter==null){
			String name = prefs.getString("username", "");
			String password = prefs.getString("password", "");
			
			if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
				twitter = new Twitter(name,password);
				twitter.setAPIRootUrl("http://yamba.marakana.com/api");
				Log.w("", name);
				Log.w("", password);
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
