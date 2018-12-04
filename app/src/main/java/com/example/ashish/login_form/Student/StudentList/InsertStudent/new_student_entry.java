package com.example.ashish.login_form.Student.StudentList.InsertStudent;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ashish.login_form.DBhelper.Database_tbls;
import com.example.ashish.login_form.DBhelper.student_helper;
import com.example.ashish.login_form.R;
import com.example.ashish.login_form.Teacher.login;

public class new_student_entry extends AppCompatActivity {
    EditText name, roll_number, cls, email_id, contact_number;
    Button register;
    Database_tbls database_tbls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student_entry);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("NEW STUDENT ENTRY ");
        name = findViewById(R.id.name);
        roll_number = findViewById(R.id.roll_number);
        cls = findViewById(R.id.cls);
        register = findViewById(R.id.Register);
        email_id = findViewById(R.id.email_id);
        contact_number = findViewById(R.id.contact_number);
        database_tbls=new Database_tbls(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isinsert= database_tbls.insertStudent(name.getText().toString(),roll_number.getText().toString(),cls.getText().toString(),email_id.getText().toString(),contact_number.getText().toString());
                if(isinsert)
                {

                    Intent intent=new Intent(new_student_entry.this,login.class);
                    startActivity(intent);
                    Toast.makeText(new_student_entry.this, "yes", Toast.LENGTH_SHORT).show();
                }
            else
                {
                    Toast.makeText(new_student_entry.this, "ERROR", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}




