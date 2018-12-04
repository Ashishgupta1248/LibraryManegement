package com.example.ashish.login_form.DBhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class books_helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "logindata.db";
    public static final String BOOK_TABLE = "BOOKS_ENTRY";
    public static final String BOOK_TITLE = "title_name";
    public static final String BOOK_AUTHOR_NAME="author_name";
    public static final String BOOK_EDITION= "edition_name";
    public static final String BOOK_PUBLISHER= "publisher_name";
    public books_helper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS BOOK_TABLE(title_name TEXT NOT NULL,author_name TEXT NOT NULL," +
                "edition_name TEXT NOT NULL,publisher_name TEXT NOT NULL )");


    }

    public Boolean insertBooks(String TITLE,String AUTHOR,String EDITION, String PUBLISHR) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(BOOK_TITLE,TITLE);
        contentValues.put(BOOK_AUTHOR_NAME,AUTHOR);
        contentValues.put(BOOK_EDITION,EDITION);
        contentValues.put(BOOK_PUBLISHER,PUBLISHR);
        long result=db.insert(BOOK_TABLE,null,contentValues);
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
