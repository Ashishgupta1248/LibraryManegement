package com.example.ashish.login_form;

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
import com.example.ashish.login_form.Books.book_list;
import com.example.ashish.login_form.DBhelper.Database_tbls;
import com.example.ashish.login_form.Student.StudentList.Profile.student_profile;
import com.example.ashish.login_form.Student.StudentList.student_model.model;

import java.util.ArrayList;
import java.util.List;

public class search_student_account extends AppCompatActivity {
    EditText student_roll_number;
    Button search;
    Database_tbls database_tbls;
    List<model> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student_account);
        student_roll_number=findViewById(R.id.student_roll_number);
        search=findViewById(R.id.search);
        modelList=new ArrayList<>();
        database_tbls=new Database_tbls(this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if(database_tbls.getStudentAccount(student_roll_number.getText().toString())){
                    Intent intent=new Intent(search_student_account.this,student_profile.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(search_student_account.this, "student is not exist", Toast.LENGTH_SHORT).show();
                }*/

                Cursor data= database_tbls.getStudentAccount(student_roll_number.getText().toString());

                if (data!=null && data.moveToFirst()){
                    do{
                        //Log.d("DBlog", "showDAtaBAse: "+data.getString(1));
                        Log.d("getStudentData","show:----"+data.getString(2));
                        Intent intent=new Intent(search_student_account.this,student_profile.class);
                        intent.putExtra("name",data.getString(1));
                        intent.putExtra("class",data.getString(4));
                        intent.putExtra("roll_number",data.getString(2));
                        intent.putExtra("email_id",data.getString(3));
                        intent.putExtra("phone_number",data.getString(5));
                        startActivity(intent);

                    }while (data.moveToNext());
                }else{
                    Toast.makeText(search_student_account.this, "student not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
