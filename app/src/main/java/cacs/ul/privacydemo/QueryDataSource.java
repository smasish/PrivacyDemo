/*
 * Copyright (c) 2010, Sony Ericsson Mobile Communication AB. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 *
 *    * Redistributions of source code must retain the above copyright notice, this 
 *      list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation
 *      and/or other materials provided with the distribution.
 *    * Neither the name of the Sony Ericsson Mobile Communication AB nor the names
 *      of its contributors may be used to endorse or promote products derived from
 *      this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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
			MySQLiteHelper.COLUMN_startplace,MySQLiteHelper.COLUMN_startKM,MySQLiteHelper.COLUMN_startdatetime,MySQLiteHelper.COLUMN_endplace,MySQLiteHelper.COLUMN_endKM,MySQLiteHelper.COLUMN_enddatetime };

	public QueryDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public TripData createComment(String startplace, String startKM, String startdatetime, String endplace, String endkm, String enddatetime) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_startplace, startplace);
		values.put(MySQLiteHelper.COLUMN_startKM, startKM);
		values.put(MySQLiteHelper.COLUMN_startdatetime, startdatetime);

		values.put(MySQLiteHelper.COLUMN_endplace, endplace);
		values.put(MySQLiteHelper.COLUMN_endKM, endkm);
		values.put(MySQLiteHelper.COLUMN_enddatetime, enddatetime);

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

	public void deleteComment(TripData tripData) {
		long id = tripData.getId();
		System.out.println("TripData deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	//	database.delete(MySQLiteHelper.TABLE_COMMENTS, null, null);
	}

	public List<TripData> getAllComments() {
		List<TripData> tripDatas = new ArrayList<TripData>();//new ArrayList<TripData>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
				allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			TripData tripData = cursorToComment(cursor);
			//String name=cursor.getString(cursor.getColumnIndex("dhaka"));
			tripDatas.add(tripData);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return tripDatas;
	}

	private TripData cursorToComment(Cursor cursor) {
		TripData tripData = new TripData();
		tripData.setId(cursor.getLong(0));

		tripData.setStartplace(cursor.getString(1));
		tripData.setStartkm(cursor.getString(2));
		tripData.setStartdate(cursor.getString(3));
		tripData.setEndplace(cursor.getString(4));
		tripData.setEndKM(cursor.getString(5));
		tripData.setEnddate(cursor.getString(6));
		//Log.d("...//...."+cursor.getString(2), "..."+cursor.getString(1));
		return tripData;
	}
}
