package com.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DAOFactory {

	private Context ctx;
	private DBManager connection;
 	
	public DAOFactory(Context ctx){
		this.ctx = ctx;
	}
	//-------------------------------------------------------------------
	public CarDAO createCarDAO(){
		if(connection == null){
			Toast.makeText(ctx, "You must open a connection first!", Toast.LENGTH_SHORT).show();
			throw new IllegalStateException("You must open a connection first!");
		}else{
			CarDAO carDAO = new CarDAO(connection, ctx);
			return carDAO;
		}
	}
	//-------------------------------------------------------------------
	public void openConnection(){
		try{
			connection = ConnectionFactory.getConnection(ctx);
		}catch(Exception err){
			Toast.makeText(ctx, " Error: "+err.getMessage(), Toast.LENGTH_SHORT).show();
		}	
	}
	//-------------------------------------------------------------------
	public void closeConnection(){
		if(connection == null){
			Toast.makeText(ctx, "You must open a connection first!", Toast.LENGTH_SHORT).show();
			throw new IllegalStateException("You must open a connection first!");
		}else{
			connection.close();
		}
	}
	//-------------------------------------------------------------------
}
