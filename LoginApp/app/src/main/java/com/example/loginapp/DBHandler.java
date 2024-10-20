package com.example.loginapp;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteCursor;
import android.content.ContentValues;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME="user_db";
    private static final int DB_VERSION=1;
    private static final String TABLE_NAME="users"
    private static final String ID="id";
    private static final String NAME="name";
    private static final String PASSWORD="password";


    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHandler(Login context) {
        super();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qurey1="CREATE TABLE " +TABLE_NAME+ " (" +ID+ " INTEGER AUTOINCREMENT" +NAME+ " TEXT" +PASSWORD+ " TEXT)";
        db.execSQL(qurey1);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    //inserting to db
    public void insert(String nam,String unam,String pwd){}
    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues values=new ContentValues();
    values.put(NAME,nam);
    values.put(USERNAME,unam);
    values.put(PASSWORD,pwd);

}


