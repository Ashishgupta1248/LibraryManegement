package com.example.ashish.login_form.Books;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ashish.login_form.Books.Books_Model.BooksModel;
import com.example.ashish.login_form.Books.books_adapter.BookAdapter;
import com.example.ashish.login_form.DBhelper.Database_tbls;
import com.example.ashish.login_form.DBhelper.books_helper;
import com.example.ashish.login_form.R;
import com.example.ashish.login_form.Teacher.profile;

import java.util.ArrayList;
import java.util.List;

public class add_books extends AppCompatActivity {
    EditText title_name,author_name,edition,publisher,No_of_books;
    Button done;
    Database_tbls database_tbls;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("ADD BOOKS");
        database_tbls=new Database_tbls(this);
        title_name=findViewById(R.id.title_name);
        author_name=findViewById(R.id.author_name);
        edition=findViewById(R.id.edition);
        publisher=findViewById(R.id.publisher);
        No_of_books=findViewById(R.id.No_of_books);
        done=findViewById(R.id.done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isinsert=database_tbls.insertBooks(title_name.getText().toString(),author_name.getText().toString(),edition.getText().toString(),publisher.getText().toString(),Integer.parseInt(No_of_books.getText().toString()));
              if(isinsert)
              {
                  Intent intent =new Intent(add_books.this,profile.class);
                  startActivity(intent);
                  Toast.makeText(add_books.this, "yes", Toast.LENGTH_SHORT).show();
              }else
              {
                  Toast.makeText(add_books.this, "error", Toast.LENGTH_SHORT).show();
              }
            }
        });
    }
}
