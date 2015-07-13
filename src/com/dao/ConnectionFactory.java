package com.dao;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

public class ConnectionFactory {

	private static DBManager database;
	
	public static DBManager getConnection(Context ctx){
		try{
			database = new DBManager(ctx);
		}catch(Exception err){
			Toast.makeText(ctx, "Não foi possível estabelecer uma conexão com a banco interno do aparelho!", Toast.LENGTH_SHORT).show();
		}
		return database;
	}
}
