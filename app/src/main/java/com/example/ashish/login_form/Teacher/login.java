package com.example.ashish.login_form.Teacher;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashish.login_form.DBhelper.Database_tbls;
import com.example.ashish.login_form.R;
import com.example.ashish.login_form.Student.StudentList.student_list;
import com.example.ashish.login_form.Student.StudentList.student_model.model;
import com.example.ashish.login_form.shered.shared_pre;

public class login extends AppCompatActivity {
    EditText username,password;
    ImageView login;
    TextView new_account;
    //SQLiteDatabase sqLiteDatabase;

    shared_pre shared_pre;
    Database_tbls database_tbls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("LOGIN");
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        new_account=findViewById(R.id.new_account);
        login=findViewById(R.id.login);
        shared_pre =new shared_pre(this);
        //sqLiteDatabase=openOrCreateDatabase("logindata",MODE_PRIVATE,null);

        database_tbls = new Database_tbls(this);

        if (!shared_pre.getusername().equals("")){
            Intent intent=new Intent(login.this,profile.class);
            startActivity(intent);
        }

        getTeachers();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(database_tbls.loginUser(username.getText().toString(),password.getText().toString())){
                    shared_pre.savedata(username.getText().toString(),password.getText().toString());
                    Intent intent=new Intent(login.this,profile.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(login.this, "Invalid User", Toast.LENGTH_SHORT).show();
                }
            }
        });
        new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getTeachers() {
        Cursor data= database_tbls.getTeachers();

        if (data!=null && data.moveToFirst()){
            do{
                //Log.d("DBlog", "showDAtaBAse: "+data.getString(1));
                Log.d("key","show:----"+data.getString(1));
              }while (data.moveToNext());
        }else{
            Toast.makeText(login.this, "error", Toast.LENGTH_SHORT).show();
        }
    }
}
