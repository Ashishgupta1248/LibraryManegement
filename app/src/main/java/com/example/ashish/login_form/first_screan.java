package com.example.ashish.login_form;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ashish.login_form.Teacher.login;

public class first_screan extends AppCompatActivity {
    ImageView student,admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screan);
    student=findViewById(R.id.student);
    admin=findViewById(R.id.admin);
    admin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent =new Intent(first_screan.this,login.class);
            startActivity(intent);
        }
    });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(first_screan.this,search_student_account.class);
        startActivity(intent);
            }
        });

    }
}
