package com.example.ashish.login_form.Books;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ashish.login_form.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class issue_book extends AppCompatActivity {
    TextView date,time;
    EditText book_name;
    Button add_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_book);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("ISSUE BOOK");
        date=findViewById(R.id.Date);
        time=findViewById(R.id.time);
        Date dt= Calendar.getInstance().getTime();
        SimpleDateFormat df =new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df1 =new SimpleDateFormat("HH:mm a");
        String formmatedDate =df.format(dt);
        String formmatedDate1 = df1.format(dt);
        date.setText(formmatedDate);
        time.setText(formmatedDate1);
        book_name=findViewById(R.id.book_name);
        add_book=findViewById(R.id.add_book);
        add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
