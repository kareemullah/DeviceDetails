package com.androidctsit.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseUtil{
	private static final String TAG = "DatabaseUtil";
	private static final String DATABASE_NAME = "testdb.db";
	private static final int DATABASE_VERSION = 1;
	
	private static final String TABLE_USER = "User_Details";
	private static final String TABLE_VIEW_DETAILS = "View_Details";

	public static final String KEY_NAME = "name";
	public static final String KEY_ROWID = "_id";
	public static final String KEY_PASS = "password";
	public static final String KEY_ADD = "address";
	public static final String KEY_PHONE = "phone_no";
	public static final String KEY_EMAIL = "email";
	
	public static final String VIEW_ROWID = "id";
	public static final String VIEW_CAR = "car";
	public static final String VIEW_PHONE = "phone";
	public static final String VIEW_BIKE = "bike";
	public static final String VIEW_USERID = "user_id";
	
	private static final String CREATE_USER_TABLE =
		"create table " + TABLE_USER + " (" + KEY_ROWID + " integer primary key autoincrement, "
				+ KEY_NAME +" text not null, " + KEY_PASS + " text not null,"+KEY_ADD+" text not null,"
				+KEY_PHONE+" text not null,"+KEY_EMAIL+" text not null);";
	private static final String CREATE_VIEW_TABLE =
			"create table " + TABLE_VIEW_DETAILS + " (" + VIEW_ROWID + " integer primary key autoincrement, "+VIEW_CAR+" text not null , "
				+VIEW_PHONE+" text not null , "+VIEW_BIKE+" text not null ,"+VIEW_USERID+" text not null, " +
						"FOREIGN KEY(user_id) REFERENCES User_Details(_id));";

	private final Context mCtx;
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.i(TAG, "Creating DataBase: " + CREATE_USER_TABLE);
			db.execSQL(CREATE_USER_TABLE);
			Log.i(TAG, "Creating DataBase: " + CREATE_VIEW_TABLE);
			db.execSQL(CREATE_VIEW_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion);
		}
	}

	public DatabaseUtil(Context ctx) {
		this.mCtx = ctx;
	}

	public DatabaseUtil open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
	}

	public long createUser(String name, String password, String add, String phone, String email) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_PASS, password);
		initialValues.put(KEY_ADD, add);
		initialValues.put(KEY_PHONE, phone);
		initialValues.put(KEY_EMAIL, email);
		return mDb.insert(TABLE_USER, null, initialValues);
		
	}
	public long createView(String car, String phone, String bike, String id) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(VIEW_CAR, car);
		initialValues.put(VIEW_PHONE, phone);
		initialValues.put(VIEW_BIKE, bike);
		initialValues.put(VIEW_USERID, id);
		return mDb.insert(TABLE_VIEW_DETAILS, null, initialValues);
	}
	
	public Cursor fetchLoginDetails() {
		Cursor c=null;
		c = mDb.query(TABLE_USER, new String[] {KEY_NAME,
		        KEY_PASS, KEY_ROWID, KEY_PHONE, KEY_ADD, KEY_EMAIL}, null, null, null, null, null);
		return c;
	}
	
	public Cursor unUsedFuction() {
		Cursor c=null;
		c = mDb.rawQuery("select b.car, b.phone, b.bike from User_Details as a, View_Details as b where a._id = b.user_id" , null);
		return c;
	}
	
	public Cursor getDetailsByID(String id) {
		Cursor c=null;
		Log.i("DatabaseUtil" , "Before Query");
		c = mDb.rawQuery("select * from View_Details where user_id="+id, null);
		Log.i("DatabaseUtil" , "After Query" + c.getCount());
		return c;
	}
	
	public void deleteViewByID(int id) {
//		mDb.de("delete from View_Details where id="+id , null);
		mDb.delete(TABLE_VIEW_DETAILS, VIEW_ROWID+"="+id, null);
	}
	
	public void update_byID(Integer id, String car, String phone, String bike, String user_id){
		  ContentValues values = new ContentValues();
		  values.put(VIEW_CAR, car);
		  values.put(VIEW_PHONE, phone);
		  values.put(VIEW_BIKE, bike);
		  values.put(VIEW_USERID, user_id);
		  mDb.update(TABLE_VIEW_DETAILS, values, VIEW_ROWID+"="+id, null);
		 }

}