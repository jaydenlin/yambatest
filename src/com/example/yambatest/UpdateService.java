package com.example.yambatest;

import java.util.List;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

public class UpdateService extends Service{
	
	
	private final String TAG = this.getClass().getSimpleName();
	
	private Updater updater;
	private YambaApplication yambaApplication;
	private boolean runFlag;
	SQLiteDatabase db;
	DBHelper dbHelper;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		yambaApplication = (YambaApplication)getApplication();
		updater = new Updater();
		
	}


	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		updater.interrupt();
		updater =null;
		yambaApplication.setServiceRunning(false);
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		updater.start();
		runFlag = true;
		yambaApplication.setServiceRunning(true);
		return super.onStartCommand(intent, flags, startId);
		
	}


	class Updater extends Thread{
		List<Twitter.Status> timeline;
		public Updater(){
			super("Updater Thread");//just for debug
		}

		@Override
		public void run() {
			UpdateService updateService = UpdateService.this;
			Log.w(TAG,"start service");
			while(updateService.runFlag==true){
				try{
					try{
						timeline = yambaApplication.getTwitter().getFriendsTimeline();
						for(Twitter.Status status:timeline){
							Log.w(TAG,"print timline");
							Log.w(TAG,String.format("%s: %s", status.user.name,status.text));
						}
						//
						Log.w(TAG,"start db");
						ContentValues values = new ContentValues();
						values.put(dbHelper.ID, 1);
						values.put(dbHelper.USER, "Jayden");
						values.put(dbHelper.TEXT, "So handsome");
						values.put(dbHelper.CREATE_AT, 201311);
						dbHelper = new DBHelper(UpdateService.this, "timeline.db");
						db=dbHelper.getWritableDatabase();
						db.insertOrThrow(dbHelper.TABLE, null, values);
						
						Cursor cursor=db.query(dbHelper.TABLE, null, null, null, null, null, null);
						while(cursor.moveToNext()){
							String user = cursor.getString(cursor.getColumnIndex(dbHelper.USER));
							Log.w(TAG,user);
						}
						
					}catch(TwitterException exception){
						Log.w(TAG, "Fail to connect to Twitter");
					}
					
					
					Thread.sleep(10000);
				}catch(InterruptedException interruptedException){
					updateService.runFlag =false;
				}
				
			}
			
		}
		
	}

}
