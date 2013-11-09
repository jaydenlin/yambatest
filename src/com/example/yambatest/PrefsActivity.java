package com.example.yambatest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.preference.PreferenceFragment;

@SuppressLint("ValidFragment")
public class PrefsActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		FragmentManager fragmentManager = getFragmentManager();
		PrefsFragment prefsFragment = new PrefsFragment();
		fragmentManager.beginTransaction().replace(android.R.id.content, prefsFragment).commit();
		//addPreferencesFromResource(R.xml.prefs);
	}

	
	public class PrefsFragment extends PreferenceFragment{
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.prefs);
		}
		
	}

	
}
