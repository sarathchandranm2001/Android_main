package com.example.loginapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "user_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";

    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, "
                + PASSWORD + " TEXT)";
        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Inserting into DB
    public void insert(String name, String pwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(PASSWORD, pwd);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Boolean checkCredentials(String name,String pwd){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor;
        cursor=db.rawQuery("SELECT * FROM" +TABLE_NAME+ " WHERE " +NAME+" ")
    }
}


