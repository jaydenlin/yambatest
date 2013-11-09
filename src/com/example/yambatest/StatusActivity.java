package com.example.yambatest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StatusActivity extends Activity implements OnClickListener{
	
	private final String TAG = this.getClass().getSimpleName();
	Button updateBotton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);
		updateBotton = (Button)findViewById(R.id.updateButton);
		updateBotton.setOnClickListener(this);
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.status, menu);
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.userSetting:
			startActivity(new Intent(this,PrefsActivity.class));
			break;
		case R.id.stratService:
			startService(new Intent(this,UpdateService.class));
			break;
		case R.id.stopService:
			stopService(new Intent(this,UpdateService.class));
			break;
		default:
			break;
			
		}
		
		return super.onOptionsItemSelected(item);
		
		
	}


	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		Log.w(TAG,"onClick");
		new PostOnTwitter().execute(updateBotton.getText().toString());
	}
	
	class PostOnTwitter extends AsyncTask<String, Integer, String>{

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		
	}

}
