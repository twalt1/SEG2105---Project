package com.example.tttt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper2 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Instructor.db";
    public static final String TABLE_NAME = "Instructor_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "EMAIL";
    public static final String COL_5 = "AGE";
    public static final String COL_6 = "PHONENUMBER";


    public DBHelper2(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +" USERNAME TEXT, PASSWORD TEXT, EMAIL TEXT, AGE TEXT, PHONENUMBER TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String username, String password, String email, String age, String phonenumber) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, password);
        contentValues.put(COL_4, email);
        contentValues.put(COL_5, age);
        contentValues.put(COL_6, phonenumber);

        long res = db.insert(TABLE_NAME, null, contentValues);

        if(res == -1) {

            return false;

        }   else {

            return true;

        }


    }

    public Boolean checkusername(String username) {

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM Instructor_table where USERNAME= "+username, null);

        if(cursor.getCount() > 0) {

            return true;

        }   else {

            return false;

        }

    }

    public Boolean checkusernamepassword(String username, String password) {

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM Instructor_table where USERNAME= "+username+ " and PASSWORD= "+password, null);

        if(cursor.getCount() > 0) {

            return true;

        }   else {

            return false;

        }

    }
}
