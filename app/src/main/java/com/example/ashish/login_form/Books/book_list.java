package com.example.ashish.login_form.Books;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashish.login_form.Books.BooksInterface.Books_interface;
import com.example.ashish.login_form.Books.Books_Model.BooksModel;
import com.example.ashish.login_form.Books.books_adapter.BookAdapter;
import com.example.ashish.login_form.DBhelper.Database_tbls;
import com.example.ashish.login_form.R;
import com.example.ashish.login_form.Student.StudentList.Profile.student_profile;
import com.example.ashish.login_form.Student.StudentList.student_adapter.adapter;
import com.example.ashish.login_form.Student.StudentList.student_list;
import com.example.ashish.login_form.Student.StudentList.student_model.model;
import com.example.ashish.login_form.Teacher.profile;
import com.example.ashish.login_form.shered.shared_pre;

import java.util.ArrayList;
import java.util.List;

public class book_list extends AppCompatActivity implements Books_interface {
    RecyclerView recyclerview;
    BookAdapter bookAdapter;
    List<BooksModel> booksModels;
    Database_tbls database_tbls;
    TextView BookName,book_author,book_edition;
    shared_pre shared_pre;
    List<model> studentModel;
    adapter studentAdapter;
    String studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        recyclerview=findViewById(R.id.recyclerview);
        booksModels=new ArrayList<>();
        bookAdapter=new BookAdapter(this,booksModels,this);
       // studentAdapter=new adapter(this,studentModel,this);
        RecyclerView.LayoutManager mlayoutmanager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(mlayoutmanager);
        database_tbls=new Database_tbls(this);
        BookName=findViewById(R.id.book_name);
        shared_pre=new shared_pre(this);
        recyclerview.setAdapter(bookAdapter);
        book_author=findViewById(R.id.book_author);
        book_edition=findViewById(R.id.book_edition);
        studentID = getIntent().getStringExtra("studentID");

       getBookList();
        //insertBooks();

    }
    private void getBookList() {
        Cursor data= database_tbls.getBooks();

        if (data!=null && data.moveToFirst()){
            do{
                //Log.d("DBlog", "showDAtaBAse: "+data.getString(1));
                Log.d("key","showbooks:----"+data.getString(1)+"----"+data.getString(2)+"----"+data.getString(3)+"----"+data.getString(4)+"----"+data.getString(0)+"----"+data.getString(5));
                booksModels.add(new BooksModel(
                        data.getInt(0),
                        data.getString(1),
                        data.getString(2),
                        data.getString(3),
                        data.getString(4),
                        data.getInt(5)
                ));

                bookAdapter.notifyDataSetChanged();
            }while (data.moveToNext());
        }else{
            Toast.makeText(book_list.this, "error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onitemclick(View view, int i) {
        //Toast.makeText(this, ""+i, Toast.LENGTH_SHORT).show();
        String Student_id=String.valueOf(getIntent().getStringExtra("studentID"));
       if(!database_tbls.bookstatus(Student_id,booksModels.get(i).getBookid())) {
           Log.d("tag", "onitemclick:"+booksModels.get(i).getNo_of_books_available());
           if(database_tbls.avilableBooks(booksModels.get(i).getBookid(),booksModels.get(i).getNo_of_books_available()))
           {
               if (database_tbls.insertBookDetail(Integer.parseInt(studentID),booksModels.get(i).getBookid(),shared_pre.getusername())){
                   finish();
               }else{
                   Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
               }
           }else
           {
               Toast.makeText(this, "this book is  not available in library", Toast.LENGTH_SHORT).show();
           }
       }else{
           Toast.makeText(this, "this book is already issued ", Toast.LENGTH_SHORT).show();
       }
  }
}





