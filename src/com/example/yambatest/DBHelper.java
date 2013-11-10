package com.example.yambatest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBHelper extends SQLiteOpenHelper{
	
	static final String TABLE="timeline";
	static final String ID = BaseColumns._ID;
	static final String TEXT = "txt";
	static final String USER = "user";
	static final String CREATE_AT = "create_at";
	static final int VERSION =1;
	
	public DBHelper(Context context,String dbName) {
		super(context, dbName, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql="create table" + TABLE +"("+ID+" int primary key,"+CREATE_AT+" int, "+USER+" text, "+TEXT+" text)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
