package com.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper{

	public final static String NAME = "factory";
	public final static int VERSION = 1;
	
	public DBManager(Context context) {
		super(context, NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String sql =  " CREATE TABLE cars ( "
			  + " idCar   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
			  + " name    VARCHAR(50) NOT NULL, "
			  + " year    INT         NOT NULL, "
			  + " factory VARCHAR(25)  NOT NULL);"; 
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = " DROP TABLE cars;";
		db.execSQL(sql);
	}
}
