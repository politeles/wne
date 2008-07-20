/**
 * 
 */
package com.google.android.wne;

import java.io.FileNotFoundException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Jose Enrique
 * Clase para el acceso a los datos
 */
public class DataAccess {
	// la base de datos:
	private SQLiteDatabase mDb;
	//el contexto
	private final Context mCtx;
	// la sentencia de creación de la base de datos:
	private String mDbCreate;
	private int mDbVersion;
	private String mDbName;
	
	/**
	 * @return the mDbName
	 */
	public String getMDbName() {
		return mDbName;
	}

	/**
	 * @param dbName the mDbName to set
	 */
	public void setMDbName(String dbName) {
		mDbName = dbName;
	}

	/**
	 * Constructor de la clase
	 * @param ctx
	 */
	public DataAccess(Context ctx){
		this.mCtx = ctx;
		mDbCreate = null;
		mDbVersion = 0;
	}
	
	/**
	 * @return the mDbCreate
	 */
	public String getMDbCreate() {
		return mDbCreate;
	}

	/**
	 * @param dbCreate the mDbCreate to set
	 */
	public void setMDbCreate(String dbCreate) {
		mDbCreate = dbCreate;
	}

	/**
	 * Función para abrir la base de datos
	 */
	public DataAccess open() throws SQLException{
		try{
			 mDb = mCtx.openDatabase(mDbName, null);
		}catch(FileNotFoundException e){
			try{
				mDb = mCtx.createDatabase(mDbName, mDbVersion, 0, null);
              mDb.execSQL(mDbCreate);
			}catch(FileNotFoundException e1){
				throw new SQLException("Could not create database");
			}
		}
		
		return this;
	}

	/**
	 * @return the mDbVersion
	 */
	public int getMDbVersion() {
		return mDbVersion;
	}

	/**
	 * @param dbVersion the mDbVersion to set
	 */
	public void setMDbVersion(int dbVersion) {
		mDbVersion = dbVersion;
	}
	/**
	*
	*/
	 public void close() {
	        mDb.close();
	    }



	public Cursor query(String[] fields,String compare,String dbTable )throws SQLException{
		Cursor result;
		result = mDb.query(true, dbTable, fields,compare, null, null,
            null, null);
		if ((result.count() == 0) || !result.first()) {
            throw new SQLException("No result! ");
        }
		return result;
	}
	public long insert(String table,ContentValues initialValues){
		return mDb.insert(table, null, initialValues);
	}
	public boolean update(String table,ContentValues args,String condition ){
		return mDb.update(table, args, condition, null) > 0;
	}
	public boolean delete(String table,String condition){
		return mDb.delete(table, condition, null)>0;
	}
}


