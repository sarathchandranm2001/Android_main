package com.example.login_register;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "user_db";

    private static final int DB_VERSION=1;
    private static final String TABLE_NAME="user_details";
    private static final String ID = "id";
    private static final String NAME= "name";
    private static final String USERNAME = "username";
    private static final String PASSWORD= "password";

    public DBHandler(Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE " +TABLE_NAME+ "("
                +ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +NAME+ " TEXT,"
                +USERNAME+ " TEXT," +PASSWORD+ " TEXT)";
        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addNewUser(String name, String uname, String pwd)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(NAME,name);
        values.put(USERNAME,uname);
        values.put(PASSWORD,pwd);

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public Boolean checkEmailPassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor;
        cursor= db.rawQuery("Select * from "+TABLE_NAME+" where "+USERNAME+" = ? and "+PASSWORD+" = ?", new String[]{username, password});


        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
