package com.example.ashish.login_form.Teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ashish.login_form.Books.book_list;
import com.example.ashish.login_form.R;
import com.example.ashish.login_form.Student.StudentList.InsertStudent.new_student_entry;
import com.example.ashish.login_form.shered.shared_pre;

public class profile extends AppCompatActivity {
      ImageView   add_student,add_books,student_list,BooksLIst;
    shared_pre shared_pre;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Profile");
        add_student=findViewById(R.id.add_student);
        add_books=findViewById(R.id.add_books);
        student_list=findViewById(R.id.student);
        BooksLIst=findViewById(R.id.BooksLIst);
        shared_pre=new shared_pre(this);

        add_student.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         intent=new Intent(profile.this,new_student_entry.class);
                startActivity(intent);
            }
        });
        student_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(profile.this, com.example.ashish.login_form.Student.StudentList.student_list.class);
                startActivity(intent);
            }
        });
        add_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(profile.this, com.example.ashish.login_form.Books.add_books.class);
                startActivity(intent);
            }
        });
        BooksLIst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(profile.this,book_list.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.icon,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.logout)
        {
            intent=new Intent(profile.this,login.class);
            shared_pre.savedata("","");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}


















