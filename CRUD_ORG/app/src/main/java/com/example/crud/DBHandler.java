package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "student_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "student_details";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String COURSE = "course";
    private static final String SEMESTER = "semester";

    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + COURSE + " TEXT, " + SEMESTER + " INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addstudent(String name, String course, int semester) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(COURSE, course);
        values.put(SEMESTER, semester);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Student findstud(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + NAME + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{name});

        Student student = null;

        if (cursor.moveToFirst()) {
            student = new Student();
            student.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
            student.setCourse(cursor.getString(cursor.getColumnIndexOrThrow(COURSE)));
            student.setSemester(cursor.getInt(cursor.getColumnIndexOrThrow(SEMESTER)));
        }

        cursor.close();
        db.close();
        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                student.setCourse(cursor.getString(cursor.getColumnIndexOrThrow(COURSE)));
                student.setSemester(cursor.getInt(cursor.getColumnIndexOrThrow(SEMESTER)));
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return studentList;
    }

    public void delete_s(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,NAME+ "=?",new String[]{name});
        db.close();

    }
    public  void update_s(String old,String new_name,String new_course,String new_sem)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NAME,new_name);
        values.put(COURSE,new_course);
        values.put(SEMESTER,new_sem);
        db.update(TABLE_NAME,values,NAME+ "=?",new String[]{old});
    }
}
