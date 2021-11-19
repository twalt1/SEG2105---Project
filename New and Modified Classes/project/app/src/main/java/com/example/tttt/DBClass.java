package com.example.tttt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBClass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Class.db";
    public static final String TABLE_NAME = "Class_table";
    public static final String COL_TYPE = "Type";
    public static final String COL_1 = "Title";
    public static final String COL_2 = "Description";
    public static final String COL_3 = "Difficulty";
    public static final String COL_4 = "Capacity";
    public static final String COL_5 = "Date";
    public static final String COL_6 = "Time";
    public static final String COL_INSTRUCTOR = "Instructor";
    //public static final String COL_7 = "Status";
    Class newclass;

    public DBClass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +" TITLE TEXT, TYPE TEXT, DESCRIPTION TEXT, DIFFICULTY TEXT, CAPACITY INTEGER, DATE TEXT, TIME TEXT, INSTRUCTOR TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    public boolean insertData(String title, String type, String description, String difficulty, Integer capacity, String date, String time, String instructor) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, title);
        contentValues.put(COL_TYPE, type);
        contentValues.put(COL_2, description);
        contentValues.put(COL_3, difficulty);
        contentValues.put(COL_4, capacity);
        contentValues.put(COL_5, date);
        contentValues.put(COL_6, time);
        contentValues.put(COL_INSTRUCTOR, instructor);

        newclass = new Class(title, type, description, difficulty, capacity, date, time, instructor);

        long res = db.insert(TABLE_NAME, null, contentValues);

        if(res == -1) {

            return false;

        }   else {

            return true;

        }


    }

    public Integer deleteData (String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});

    }

    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;

    }

    public boolean updateName(String id, String name){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, name);
        database.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
        return true;
    }

    public boolean updateDescription(String id, String description){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, description);
        database.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
        return true;
    }

    public void updateCapacity(String id, int capacity){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_4, capacity);
        database.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
    }

}