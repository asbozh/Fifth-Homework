package com.asbozh.fifthhomework;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLHandler extends SQLiteOpenHelper {

    private SQLiteDatabase mDatabase;

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + SQLContract.ItemEntry.TABLE_NAME + " (" +
                    SQLContract.ItemEntry._ID + " INTEGER PRIMARY KEY," +
                    SQLContract.ItemEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    SQLContract.ItemEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    SQLContract.ItemEntry.COLUMN_NAME_ICON + TEXT_TYPE + COMMA_SEP +
                    SQLContract.ItemEntry.COLUMN_NAME_PRICE + TEXT_TYPE + " );";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SQLContract.ItemEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "grocery.db";

    public SQLHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public SQLHandler open() throws SQLException {
        mDatabase = this.getWritableDatabase();
        return this;
    }

    public long createEntry (String tableName, String title, String description, String icon, String price) {
        ContentValues cv = new ContentValues();
        cv.put(SQLContract.ItemEntry.COLUMN_NAME_TITLE, title);
        cv.put(SQLContract.ItemEntry.COLUMN_NAME_DESCRIPTION, description);
        cv.put(SQLContract.ItemEntry.COLUMN_NAME_ICON, icon);
        cv.put(SQLContract.ItemEntry.COLUMN_NAME_PRICE, String.valueOf(price));
        return mDatabase.insert(tableName, null, cv);
    }

    public ArrayList<Item> getAllEntries (String tableName) throws SQLException {
        ArrayList<Item> newList = new ArrayList<>();
        Cursor mCursor = null;
        try {
            mCursor = mDatabase.rawQuery("SELECT * FROM " + tableName, null);
            if (mCursor.moveToFirst()) {
                Item itemToAdd;
                while (!mCursor.isAfterLast()) {
                    itemToAdd = new Item(mCursor.getString(1), mCursor.getString(2), mCursor.getString(3), mCursor.getString(4));
                    newList.add(itemToAdd);
                    mCursor.moveToNext();
                }
            }
        } finally {
            if (mCursor != null) {
                mCursor.close();
            }
        }
        return newList;
    }

}
