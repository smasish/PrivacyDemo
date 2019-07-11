
package cacs.ul.privacydemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_COMMENTS = "testtable";
	public static final String COLUMN_ID = "_id";

	public static final String COLUMN_startplace = "dhaka";
	public static final String COLUMN_startKM = "sylhet";
	public static final String COLUMN_startdatetime = "startdate";

	public static final String COLUMN_endplace = "endplace";
	public static final String COLUMN_endKM = "endkm";
	public static final String COLUMN_enddatetime = "endtime";

	private static final String DATABASE_NAME = "testdb.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_COMMENTS + "( " + COLUMN_ID
			+ " integer primary key autoincrement, "+ COLUMN_startplace
			+ " text not null, "+ COLUMN_startKM
			+ " text not null, " + COLUMN_startdatetime
			+ " text not null, " + COLUMN_endplace
			+ " text not null, "+ COLUMN_endKM
			+ " text not null, "+ COLUMN_enddatetime
			+ " text not null);";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_COMMENTS);
		onCreate(db);
	}



}
