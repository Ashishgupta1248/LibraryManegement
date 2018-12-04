package com.example.ashish.login_form.Student.StudentList;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashish.login_form.DBhelper.Database_tbls;
import com.example.ashish.login_form.R;
import com.example.ashish.login_form.Teacher.profile;
import com.example.ashish.login_form.Student.StudentList.student_adapter.adapter;
import com.example.ashish.login_form.Student.StudentList.Interface.student_interface;
import com.example.ashish.login_form.Student.StudentList.student_model.model;
import com.example.ashish.login_form.Student.StudentList.Profile.student_profile;

import java.util.ArrayList;
import java.util.List;

public class student_list extends AppCompatActivity implements student_interface {
    RecyclerView recyclerView;
    List<model> modelList;
    TextView view,view_class;
    adapter student_adapter;


    Database_tbls database_tbls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("ALL STUDENT LIST");
        database_tbls = new Database_tbls(this);
        recyclerView=findViewById(R.id.recyclervieww);
        view=findViewById(R.id.view_data);
        view_class=findViewById(R.id.view_class);
        modelList=new ArrayList<>();
        student_adapter =new adapter(this,modelList,this);
        RecyclerView.LayoutManager mlayoutmanager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mlayoutmanager);
        recyclerView.setAdapter(student_adapter);
        getStudennt();
    }

    private void getStudennt() {
         Cursor data= database_tbls.getStudennt();

                if (data!=null && data.moveToFirst()){
                    do{
                        //Log.d("DBlog", "showDAtaBAse: "+data.getString(1));
                        Log.d("key","show:----"+data.getString(1)+"----"+data.getString(4)+"---"+data.getString(5));

                        modelList.add(new model(
                                data.getInt(0),
                                data.getString(1),
                                data.getInt(2),
                                data.getString(4),
                                data.getString(3),
                                data.getString(5)
                        ));
                        student_adapter.notifyDataSetChanged();
                    }while (data.moveToNext());
                }else{
                    Toast.makeText(student_list.this, "error", Toast.LENGTH_SHORT).show();
                }
    }

    @Override
    public void onitemclick(View v, int i) {
        Intent intent = new Intent(student_list.this,student_profile.class);
        intent.putExtra("name",modelList.get(i).getName());
        intent.putExtra("roll_number",modelList.get(i).getRoll_number());
        intent.putExtra("class",modelList.get(i).getCls());
        intent.putExtra("email_id",modelList.get(i).getEmailId());
        intent.putExtra("phone_number",modelList.get(i).getContact_number());
        intent.putExtra("userId",modelList.get(i).getID());
        startActivity(intent);
    }
}
