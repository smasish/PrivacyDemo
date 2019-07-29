
package cacs.ul.privacydemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class QueryDataSource {
	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_startplace,MySQLiteHelper.COLUMN_startKM,MySQLiteHelper.COLUMN_startdatetime,MySQLiteHelper.COLUMN_endplace,MySQLiteHelper.COLUMN_endKM,MySQLiteHelper.COLUMN_enddatetime,MySQLiteHelper.COLUMN_longi };

	public QueryDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public ProfileData createComment(String startplace, String startKM, String startdatetime, String endplace, String endkm, String enddatetime,String longi) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_startplace, startplace);
		values.put(MySQLiteHelper.COLUMN_startKM, startKM);
		values.put(MySQLiteHelper.COLUMN_startdatetime, startdatetime);

		values.put(MySQLiteHelper.COLUMN_endplace, endplace);
		values.put(MySQLiteHelper.COLUMN_endKM, endkm);
		values.put(MySQLiteHelper.COLUMN_enddatetime, enddatetime);
		values.put(MySQLiteHelper.COLUMN_longi, longi);

		long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
				values);
		Log.d("...//....", "..."+values);
		// To show how to query
		Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
				allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		return cursorToComment(cursor);
	}

	public void updateOrderItems(String orderId, String endplace, String endkm,
                                 String enddatetime) {
		Cursor cursor = database.rawQuery("SELECT COUNT(1) FROM testtable WHERE _id = "
				+ orderId , null);
		cursor.moveToFirst();
		String count = cursor.getString(0);
		Log.d("...//update...."+endplace+".."+endkm, "..."+count);
		if (Integer.valueOf(count) > 0) {
			Log.d("...//update....", "..."+count);
			ContentValues values = new ContentValues();
			values.put(MySQLiteHelper.COLUMN_endplace, endplace);
			values.put(MySQLiteHelper.COLUMN_endKM, endkm);
			values.put(MySQLiteHelper.COLUMN_enddatetime, enddatetime);
			database.update(MySQLiteHelper.TABLE_COMMENTS, values, MySQLiteHelper.COLUMN_ID + " = " + orderId, null);
		} else {
			ContentValues values = new ContentValues();
			values.put(MySQLiteHelper.COLUMN_endplace, endplace);
			values.put(MySQLiteHelper.COLUMN_endKM, endkm);
			values.put(MySQLiteHelper.COLUMN_enddatetime, enddatetime);
			long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
					values);
			Log.d("...//....", "..."+values);

			database.update(MySQLiteHelper.TABLE_COMMENTS, values, MySQLiteHelper.COLUMN_ID + " = " + orderId, null);
			// To show how to query
			cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
					allColumns, MySQLiteHelper.COLUMN_ID + " = " + orderId, null,
					null, null, null);
			cursor.moveToFirst();
			//database.insert(MySQLiteHelper.TABLE_COMMENTS, null, values);
		}

	}

	public void deleteAll() {

		System.out.println("ProfileData deleted with id: " );
//		database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
//				+ " = " + id, null);
			database.delete(MySQLiteHelper.TABLE_COMMENTS, null, null);
	}

	public void deleteComment(ProfileData profileData) {
		long id = profileData.getId();
		System.out.println("ProfileData deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	//	database.delete(MySQLiteHelper.TABLE_COMMENTS, null, null);
	}

	public List<ProfileData> getAllComments() {
		List<ProfileData> profileData = new ArrayList<ProfileData>();//new ArrayList<ProfileData>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
				allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			ProfileData profileData1 = cursorToComment(cursor);
			//String name=cursor.getString(cursor.getColumnIndex("dhaka"));
			profileData.add(profileData1);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return profileData;
	}

	private ProfileData cursorToComment(Cursor cursor) {
		ProfileData profileData = new ProfileData();
		profileData.setId(cursor.getLong(0));

		profileData.setStartplace(cursor.getString(1));
		profileData.setStartkm(cursor.getString(2));
		profileData.setStartdate(cursor.getString(3));
		profileData.setEndplace(cursor.getString(4));
		profileData.setEndKM(cursor.getString(5));
		profileData.setEnddate(cursor.getString(6));
		profileData.setLongi(cursor.getString(7));
		//Log.d("...//...."+cursor.getString(2), "..."+cursor.getString(1));
		return profileData;
	}
}
