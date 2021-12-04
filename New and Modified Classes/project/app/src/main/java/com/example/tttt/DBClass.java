package com.example.tttt;

import static android.os.Build.ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBClass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Class.db";
    public static final String TABLE_NAME = "Class_table";
    public static final String COL_ID = "ID";
    public static final String COL_TYPE = "Type";
    public static final String COL_1 = "Title";
    public static final String COL_2 = "Description";
    public static final String COL_3 = "Difficulty";
    public static final String COL_4 = "Capacity";
    public static final String COL_5 = "Date";
    public static final String COL_6 = "Time";
    public static final String COL_INSTRUCTOR = "Instructor";
    public static final String COL_DAYOFWEEK = "DayOfWeek";
    public static final String COL_MEMBERLIST = "MemberList";
    //public static final String COL_7 = "Status";
    Class newclass;

    public DBClass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "(" + COL_ID + " INTEGER PRIMARY KEY,"
                + COL_1 + " TEXT, "
                + COL_TYPE + " TEXT, "
                + COL_2 + " TEXT, "
                + COL_3 + " TEXT, "
                + COL_4 + " TEXT, "
                + COL_5 + " TEXT, "
                + COL_6 + " TEXT, "
                + COL_INSTRUCTOR + " TEXT, "
                + COL_DAYOFWEEK + " TEXT, "
                + COL_MEMBERLIST + " TEXT "
                + ")";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    public boolean insertData(String title, String type, String description, String difficulty, Integer capacity, String date, String time, String instructor, String dayOfWeek, String MemberList) {

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
        contentValues.put(COL_DAYOFWEEK, dayOfWeek);
        contentValues.put(COL_MEMBERLIST, MemberList);

        //newclass = new Class(id, title, type, description, difficulty, capacity, date, time, instructor, dayOfWeek, MemberList);

        long res = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        if(res == -1) {

            return false;

        }   else {

            return true;

        }


    }

    //public Integer deleteData (String id) {

    //    SQLiteDatabase db = this.getWritableDatabase();
    //    db.close();
    //    return db.delete(TABLE_NAME, "ID = ?", new String[] {id});

    //}

    public boolean deleteData(String id, String emailOfDeleter) {
        //check the email of the deleter (the person who wants to delete a class)
        //if email not equal to the email of the instructor who created the class
        //do not delete. The TA said so
        //except the admin can delete any class regardless of creator


        SQLiteDatabase db = this.getWritableDatabase();
        boolean res = false;

        String query = "SELECT * FROM " + TABLE_NAME
                + " WHERE " + COL_ID
                + " =\"" + id + "\"";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            String str = cursor.getString(0);
            String emailOfClassCreator = cursor.getString(8);

            if (!emailOfDeleter.equals("admin") && !emailOfClassCreator.equals(emailOfDeleter)){
                return false;
            }
            db.delete(TABLE_NAME, COL_ID + " = " + str, null);
            cursor.close();
            res = true;

        }

        db.close();

        return res;

    }

    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        return cursor;

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

    public void updateDate(String id, String date){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_5, date);
        database.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
    }

    public void updateTime(String id, String time){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_6, time);
        database.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
    }

    public void updateDifficulty(String id, String difficulty){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_3, difficulty);
        database.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
    }

    public void updateType(String id, String type){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_TYPE, type);
        database.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
    }

    public void updateDayOfWeek(String id, String dayOfWeek){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_DAYOFWEEK, dayOfWeek);
        database.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
    }

    public int enrollMemberList(String id, String emailOfMember){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = 0;

        String query = "SELECT * FROM " + TABLE_NAME
                + " WHERE " + COL_ID
                + " =\"" + id + "\"";

        Cursor cursor = db.rawQuery(query, null);

        String stringOfMembers = "";

        if(cursor.moveToFirst()) {
            stringOfMembers = cursor.getString(10);

            String[] arrayOfMembers = stringOfMembers.split(",");
            int numMembers = arrayOfMembers.length;
            int classCap = cursor.getInt(10);

            //if this member already enrolled in this class
            if (stringOfMembers.contains(emailOfMember)) {
                cursor.close();
                return -1;
            }
            //if there is no comma
            if (!stringOfMembers.contains(",") && stringOfMembers.isEmpty()){
                stringOfMembers +=  emailOfMember;
            } else if (numMembers >= classCap){
                cursor.close();
                return -2;
            } else {
                stringOfMembers += ", " + emailOfMember;
            }
            cursor.close();
            result = 1;
            //if class is already at full capacity

        }
        ContentValues cv = new ContentValues();
        cv.put(COL_MEMBERLIST, stringOfMembers);
        db.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
        return result;
    }

    public int unenrollMemberList(String id, String emailOfMember){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = 0;

        String query = "SELECT * FROM " + TABLE_NAME
                + " WHERE " + COL_ID
                + " =\"" + id + "\"";

        Cursor cursor = db.rawQuery(query, null);

        String stringOfMembers = "";
        if(cursor.moveToFirst()) {
            stringOfMembers = cursor.getString(10);


            //if this member is not in this class. Cannot unenroll
            if (!stringOfMembers.contains(emailOfMember)) {
                cursor.close();
                return -1;
            }

            //if contains member, remove him/her
            if (stringOfMembers.contains(emailOfMember)) {
                //convert to list then remove
                String[] arrayOfMembers = stringOfMembers.split(",");
                List<String> listMembers = new ArrayList<String>();
                listMembers = Arrays.asList(arrayOfMembers);
                //new string
                String s = "";
                //traverse through list
                for (int i = 0; i < listMembers.size(); i++){
                    //get each index
                    String v = listMembers.get(i);
                    v = v.trim();
                    //if the string has characters (and not white space only)
                    if (v.length() > 0) {
                        //if we see the email of the member, skip it
                        if (v.equals(emailOfMember)) {
                            continue;
                        }
                        s += v;
                        //if index is not at the end, then make sure to add comma
                        if (i < listMembers.size() - 1) {
                            s += ", ";
                        }
                    }
                }
                //set to new string
                stringOfMembers = s;
                result = 1;
            }

            /*
            String[] arrayOfMembers = stringOfMembers.split(",");
            List<String> listMembers = new ArrayList<String>();
            listMembers = Arrays.asList(arrayOfMembers);
            String cleanedStringOfMembers = "";
            for (int i = 0; i < listMembers.size(); i++){
                String v = listMembers.get(i);
                //if the string has characters (and not white space only)
                if (v.trim().length() > 0) {
                    cleanedStringOfMembers += v;
                    //if index is not at the end, then make sure to add comma
                    if (i != listMembers.size() - 1) {
                        cleanedStringOfMembers += ", ";
                    }
                }
            }
            stringOfMembers = cleanedStringOfMembers;
            */


            cursor.close();
        }
        ContentValues cv = new ContentValues();
        cv.put(COL_MEMBERLIST, stringOfMembers);
        db.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
        return result;
    }

    /*
    public boolean addClass(Class aClass) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, aClass.getTitle());
        contentValues.put(COL_TYPE, aClass.getType());
        contentValues.put(COL_2, aClass.getDescription());
        contentValues.put(COL_3, aClass.getDifficulty());
        contentValues.put(COL_4, aClass.getCapacity());
        contentValues.put(COL_5, aClass.getDate());
        contentValues.put(COL_6, aClass.getTime());
        contentValues.put(COL_INSTRUCTOR, aClass.getInstructor());
        contentValues.put(COL_DAYOFWEEK, aClass.getDayOfWeek());

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        return result != -1;

    }*/

    /** Check whether conflict exists
     *
     * @param type Type of class
     * @param date Date of class
     * @return True if no conflict; False otherwise
     */

    public boolean checkExist(String type, String date) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL_TYPE+"=?" +" AND " + COL_5+"=?", new String[] {type,date});
        boolean res = !(c.getCount() > 0);
        c.close();

        return res;

    }

    public Integer getNum(String type, String date) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL_TYPE+"=?" +" AND " + COL_5+"=?", new String[] {type,date});
        return c.getCount();

    }

    /** To find a class in the database, return a empty class if not exist.
     *
     * @param title The title of the class (!Have not tested yet!)
     * @return  a type Class
     */

    public Class findClass(String title) {

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME
                + " WHERE " + COL_1
                + " = \"" + title + "\"";

        Cursor cursor = db.rawQuery(query, null);
        Class aClass = new Class();

        if (cursor.moveToFirst()) {

            aClass.setID(cursor.getString(0));
            aClass.setTitle(cursor.getString(1));
            aClass.setType(cursor.getString(2));
            aClass.setDescription(cursor.getString(3));
            aClass.setDifficulty(cursor.getString(4));
            aClass.setCapacity(Integer.parseInt(cursor.getString(5)));
            aClass.setDate(cursor.getString(6));
            aClass.setTime(cursor.getString(7));
            aClass.setInstructor(cursor.getString(8));
            aClass.setDayOfWeek(cursor.getString(9));
            cursor.close();

        }   else {

            aClass = null;

        }

        db.close();

        return aClass;

    }

    /** To delete a class by searching title.
     *
     * @param title A String as a title of the class (!Have not tested yet!)
     * @return  True if deleteClass successful; False otherwise
     */

    public boolean deleteClass(String title) {

        boolean res = false;

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME
                + " WHERE " + COL_1
                + " = \"" + title + "\"";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {

            String titleStr = cursor.getString(0);
            db.delete(TABLE_NAME, COL_1 + " = " + titleStr, null);
            cursor.close();
            res = true;

        }

        db.close();

        return res;

    }

    /*public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        return cursor;

    }*/

    public ArrayList<Class> getEnrolled(String userEmail) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c1 = getAllData();

        ArrayList<Class> classes = new ArrayList<Class>();

        Class aClass;

        while (c1.moveToNext()) {

            if (checkEnrolled(c1.getString(0), userEmail) == 1) {

                aClass = findClass(c1.getString(1));
                classes.add(aClass);

            }


        }
        c1.close();

        return classes;

    }

    public int checkEnrolled(String id, String emailOfMember) {

        SQLiteDatabase db = this.getWritableDatabase();
        int result = 0;

        String query = "SELECT * FROM " + TABLE_NAME
                + " WHERE " + COL_ID
                + " =\"" + id + "\"";

        Cursor cursor = db.rawQuery(query, null);

        String stringOfMembers = "";

        if(cursor.moveToFirst()) {
            stringOfMembers = cursor.getString(10);

            /*
            String[] arrayOfMembers = stringOfMembers.split(",");

            int numMembers = arrayOfMembers.length;
            int classCap = cursor.getInt(10);


            //if this member already enrolled in this class
            if (stringOfMembers.contains(emailOfMember)) {
                cursor.close();
                return -1;
            }
            //if there is no comma
            if (!stringOfMembers.contains(",") && stringOfMembers.isEmpty()){
                stringOfMembers +=  emailOfMember;
            } else if (numMembers >= classCap){
                cursor.close();
                return -2;
            } else {
                stringOfMembers += ", " + emailOfMember;
            }*/

            if(!stringOfMembers.contains(emailOfMember)) {

                return -1;

            }
            cursor.close();
            result = 1;
            //if class is already at full capacity

        }

        return result;

    }

}