package com.example.ashish.login_form.DBhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class student_helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "logindata.db";
    public static final String STUDENT_TABLE = "student_data";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_ROLL_NUMBER="roll_number";
    public static final String STUDENT_EMAIL_ID= "email_id";
    public static final String STUDENT_CLASS= "cls";
    public static final String STUDENT_PHONE= "phone_number";
    public student_helper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS STUDENT_TABLE(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,roll_number TEXT NOT NULL,cls TEXT NOT NULL ,email_id TEXT NOT NULL,phone_number TEXT NOT  NULL)"
        );

    }
    public Boolean insertStudent( String Name, String RN,String Email, String clss, String contact )
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(STUDENT_NAME,Name);
        contentValues.put(STUDENT_ROLL_NUMBER,RN);
        contentValues.put(STUDENT_EMAIL_ID,Email);
        contentValues.put(STUDENT_CLASS,clss);
        contentValues.put(STUDENT_PHONE,contact);
        long result=db.insert(STUDENT_TABLE,null,contentValues);
        if(result==-1)
        {
            return false;
        }else
        {

            return true;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
