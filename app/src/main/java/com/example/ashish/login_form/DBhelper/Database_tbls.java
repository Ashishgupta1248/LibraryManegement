package com.example.ashish.login_form.DBhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Database_tbls extends SQLiteOpenHelper {
    private static final String TAG = "Database_tbl";

    public static final String DATABASE_NAME = "libraryManagement";

    /*teachers tables*/
    public static final String Teacher_TABLE = "Teacher_TBL";
    public static final String FIRST_NAME = "fisrt_name";
    public static final String LAST_NAME= "last_name";
    public static final String EMAIL_ID= "email_id";
    public static final String PHONE_NUMBER= "phone_number";
    public static final String PASSWORD= "password";

    /*books tables*/
    public static final String BOOK_TABLE = "Book_TbL";
    public static final String BOOK_TITLE = "title_name";
    public static final String BOOK_AUTHOR_NAME="author_name";
    public static final String BOOK_EDITION= "edition_name";
    public static final String BOOK_PUBLISHER= "publisher_name";
    public static final String NO_OF_BOOKS= "no_of_books";

    /*Student Tables*/
    public static final String STUDENT_TABLE = "Student_TBL";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_ROLL_NUMBER="roll_number";
    public static final String STUDENT_EMAIL_ID= "email_id";
    public static final String STUDENT_CLASS= "Student_class";
    public static final String STUDENT_PHONE= "phone_number";

    public static final String Issued_book_TABLE = "Issued_book_TBL";
    public static final String STUDENT_ID = "Student_Id";
    public static final String BOOK_ID="book_id";
    public static final String TEACHER_ID= "Teacher_email_id";
    public static final String ISSUE_DATE= "IssueDate";
    public static final String RETURN= "return";


    private Context context;
    public Database_tbls(Context context){
        super(context, DATABASE_NAME,null,1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       /* sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS LOGIN_TABLE(id INTEGER PRIMARY KEY AUTOINCREMENT,fisrt_name TEXT NOT NULL,last_name TEXT NOT NULL,email_id TEXT NOT NULL ,phone_number NOT NULL,password NOT  NULL)"
        );*/
        String teacher = "CREATE TABLE IF NOT EXISTS "+Teacher_TABLE+"( id INTEGER PRIMARY KEY AUTOINCREMENT,"+FIRST_NAME+" TEXT NOT NULL,"+LAST_NAME+" TEXT NOT NULL,"+EMAIL_ID+" TEXT NOT NULL ,"+PHONE_NUMBER+" NOT NULL,"+PASSWORD+" NOT  NULL)";
        String student = "CREATE TABLE IF NOT EXISTS "+STUDENT_TABLE+"(id INTEGER PRIMARY KEY AUTOINCREMENT,"+STUDENT_NAME+" TEXT NOT NULL,"+STUDENT_ROLL_NUMBER+" TEXT NOT NULL,"+STUDENT_CLASS+" TEXT NOT NULL ,"+STUDENT_EMAIL_ID+" TEXT NOT NULL,"+STUDENT_PHONE+" TEXT NOT  NULL)";
        String book = "CREATE TABLE IF NOT EXISTS "+BOOK_TABLE+"(id INTEGER PRIMARY KEY AUTOINCREMENT,"+BOOK_TITLE+" TEXT, "+BOOK_AUTHOR_NAME+" TEXT, "+BOOK_EDITION+" TEXT,"+BOOK_PUBLISHER+" TEXT,"+NO_OF_BOOKS+" INTEGER NOT NULL )";
        String issued_book="CREATE TABLE IF NOT EXISTS "+Issued_book_TABLE+"(id INTEGER PRIMARY KEY AUTOINCREMENT," +BOOK_ID+" INTEGER NOT NULL, "+STUDENT_ID+" INTEGER NOT NULL,"+TEACHER_ID+" TEXT NOT NULL,"+ISSUE_DATE+" DATETIME,"+RETURN+" TEXT NOT NULL)";
        sqLiteDatabase.execSQL(teacher);
        sqLiteDatabase.execSQL(student);
        sqLiteDatabase.execSQL(book);
        sqLiteDatabase.execSQL(issued_book);
        /*String[] statements = new String[]{teacher,student,book};
        for(String sql : statements){
        }*/
        //
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Boolean InsertTeacher(String fName, String lNmae, String Email,String phone, String pass) {
        Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(FIRST_NAME,fName);
        contentValues.put(LAST_NAME,lNmae);
        contentValues.put(EMAIL_ID,Email);
        contentValues.put(PHONE_NUMBER,phone);
        contentValues.put(PASSWORD,pass);
        long result=db.insert(Teacher_TABLE,null,contentValues);
        if(result==-1){
          return false;
        }else{
          return true;
        }
    }

    public Boolean insertBooks( String TITLE, String AUTHOR, String EDITION, String PUBLISHR,Integer NOOFBOOKS) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(BOOK_TITLE,TITLE);
        contentValues.put(BOOK_AUTHOR_NAME,AUTHOR);
        contentValues.put(BOOK_EDITION,EDITION);
        contentValues.put(BOOK_PUBLISHER,PUBLISHR);
        contentValues.put(NO_OF_BOOKS,NOOFBOOKS);
        long result=db.insert(BOOK_TABLE,null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean insertStudent( String Name, String RN,String Email, String clss, String contact ){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(STUDENT_NAME,Name);
        contentValues.put(STUDENT_ROLL_NUMBER,RN);
        contentValues.put(STUDENT_EMAIL_ID,Email);
        contentValues.put(STUDENT_CLASS,clss);
        contentValues.put(STUDENT_PHONE,contact);
        long result=db.insert(STUDENT_TABLE,null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean insertBookDetail( int studentid, int bookId,String Teacherid ){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(STUDENT_ID,studentid);
        contentValues.put(BOOK_ID,bookId);
        contentValues.put(TEACHER_ID,Teacherid);
        contentValues.put(ISSUE_DATE,getDateTime());
        contentValues.put(RETURN,"0");
        long result=db.insert(Issued_book_TABLE,null,contentValues);
        Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getIssuedBookByStudentId(String userID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM "+ Issued_book_TABLE +
                " INNER JOIN "+STUDENT_TABLE+" ON "+Issued_book_TABLE+"."+STUDENT_ID+" = "+ STUDENT_TABLE+".id " +
                "INNER JOIN "+BOOK_TABLE+" ON "+Issued_book_TABLE+"."+BOOK_ID+"="+BOOK_TABLE+".id "+
                "INNER JOIN "+Teacher_TABLE+" ON "+Teacher_TABLE+"."+EMAIL_ID +"="+ Issued_book_TABLE+"."+TEACHER_ID+
                " WHERE "+Issued_book_TABLE+"."+STUDENT_ID +"=? AND "+Issued_book_TABLE+"."+RETURN+"=?";
        //String query = "SELECT * FROM "+ Issued_book_TABLE +" WHERE "+STUDENT_ID +"=?";
        return sqLiteDatabase.rawQuery(query,new String[] {userID, String.valueOf(0)});
    }

    public Cursor getStudennt() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor data= sqLiteDatabase.rawQuery("SELECT * FROM "+STUDENT_TABLE,null);
        return data;
    }

    public Cursor getTeachers() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+ Teacher_TABLE,null);
    }

    public Cursor getBooks() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor data= sqLiteDatabase.rawQuery("SELECT * FROM "+BOOK_TABLE,null);
        return data;
    }


    public boolean loginUser(String username, String password) {
        boolean user = false;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sqlQuery = "SELECT * FROM "+Teacher_TABLE +" WHERE "+EMAIL_ID+"=? AND "+PASSWORD+"=?";
        Log.d(TAG, "loginUser: "+sqlQuery);
        Cursor data = sqLiteDatabase.rawQuery(sqlQuery,new String[] {username.trim(), password.trim()});
        if (data !=null){
            if(data.getCount()>0){
                user = true;
            }else{
                user = false;
            }
            data.close();
        }
        return user;
    }
    public Cursor getStudentAccount(String roll_number)
    {

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String sqlQuery=" SELECT *FROM "+STUDENT_TABLE+" WHERE "+STUDENT_ROLL_NUMBER+"=?";
        Cursor data = sqLiteDatabase.rawQuery(sqlQuery,new String[] {roll_number.trim()});

        return data;

    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


    public boolean updateReturnStatus(int bookId, int studentid) {
        /*SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String sql = "UPDATE "+Issued_book_TABLE +" SET "+RETURN +"= "+String.valueOf(1) +" WHERE "+STUDENT_ID +"= ? AND "+BOOK_ID +" =?";
        return sqLiteDatabase.rawQuery(sql,new String[] {String.valueOf(studentid),String.valueOf(bookId)});*/

        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(RETURN,"1");
        long result = db.update(Issued_book_TABLE,contentValues,STUDENT_ID +"= ? AND "+BOOK_ID +" =?",new String[] {String.valueOf(studentid),String.valueOf(bookId)});
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public boolean bookstatus(String student_id, Integer bookid) {
        boolean books = false;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String bookstatus="SELECT * FROM "+Issued_book_TABLE+" WHERE "+STUDENT_ID+"= ? AND "+BOOK_ID+"= ? AND "+RETURN+"=?";
       // Log.d(TAG, "bookstatus: "+bookstatus);
        Cursor data = sqLiteDatabase.rawQuery(bookstatus,new String[] {student_id,String.valueOf(bookid),String.valueOf(0)});
        if (data!=null){
            books = data.moveToFirst();
            data.close();
        }
        return books;
    }

    public boolean avilableBooks(Integer book_id,Integer no_of_Books) {
        boolean available =true;
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String booksCount="SELECT * FROM "+BOOK_TABLE+" WHERE "+NO_OF_BOOKS+"= ?";
        Cursor data1=sqLiteDatabase.rawQuery(booksCount,new String[] {String.valueOf(no_of_Books)});
        String bookedIssued="SELECT * FROM "+Issued_book_TABLE+" WHERE "+BOOK_ID+"= ? AND "+RETURN+"=?";
        Cursor data = sqLiteDatabase.rawQuery(bookedIssued,new String[] {String.valueOf(book_id),String.valueOf(0)});
        Log.d(TAG, "avilableBooks:"+data.getCount());
        //Log.d(TAG, "no_of_books:"+data1.getString(4));


        return  available;

    }


}
