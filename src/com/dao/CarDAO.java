package com.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.model.Car;

public class CarDAO {

	private final String TABLENAME = "cars";
	private final DBManager connectionInstance;
	private SQLiteDatabase writable;
	private SQLiteDatabase readable;
	private Cursor cursor;
	private ContentValues contentValues;
	private Context ctx;
	
	public CarDAO(DBManager db, Context ctx){
		connectionInstance = db;
		this.ctx = ctx;
		writable = db.getWritableDatabase();
		readable = db.getReadableDatabase();
	}
	
	public ArrayList<Car> selectAllCars(){
		
		ArrayList<Car> cars;
		cars = new ArrayList<Car>();
		
		String sql = "SELECT * FROM "+TABLENAME;
		cursor = readable.rawQuery(sql, null);
		
		if(cursor.getCount() == 0){
			Toast.makeText(ctx, "Table: "+TABLENAME+" is empty!", Toast.LENGTH_SHORT).show();
		}else{
			Car car;
			cursor.moveToFirst();
			do{
				car = new Car();
				car.setIdCar(cursor.getInt(0));
				car.setName(cursor.getString(1));
				car.setYear(cursor.getInt(2));
				car.setFactory(cursor.getString(3));
				
				cars.add(car);
			}while(cursor.moveToNext());
		}
		return cars;
	}
	
	public void insertCar(Car car){
		
		try{
			contentValues = new ContentValues();
			contentValues.put("name", car.getName());
			contentValues.put("year", car.getYear());
			contentValues.put("factory", car.getFactory());
			
			writable.insert(TABLENAME, null, contentValues);
			Toast.makeText(ctx, "Car successfully inserted!", Toast.LENGTH_SHORT).show();
		}catch(Exception err){
			Toast.makeText(ctx, "There was an error inserting the car", Toast.LENGTH_SHORT).show();
		}
		//---------------------------------------------------------------------------
		//------------------------JUST FOR TESTS------------------------------
		//---------------------------------------------------------------------------
		String sql = "SELECT *FROM "+TABLENAME+";";
		cursor = readable.rawQuery(sql, null);
		
		if(cursor.getCount() == 0){
			Toast.makeText(ctx, "Table: "+TABLENAME+" is empty!", Toast.LENGTH_SHORT).show();
		}else{
			
			cursor.moveToFirst();
			do{
				Log.i("id", ""+cursor.getInt(0));
				Log.i("name", cursor.getString(1));
				Log.i("year", ""+cursor.getInt(2));
				Log.i("factory", cursor.getString(3));
			}while(cursor.moveToNext());
		}
	}
	//---------------------------------------------------------------------------
	//---------------------------------------------------------------------------
	public void deleteCar(Car car){
		try{
			writable.delete(TABLENAME, "idCar="+car.getIdCar(), null);
		}catch(Exception err){
			Toast.makeText(ctx, "Error: "+err.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
}