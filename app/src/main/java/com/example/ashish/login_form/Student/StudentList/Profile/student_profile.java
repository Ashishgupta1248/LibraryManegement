package com.example.ashish.login_form.Student.StudentList.Profile;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashish.login_form.Books.BooksInterface.Books_interface;
import com.example.ashish.login_form.Books.BooksInterface.return_book_interface;
import com.example.ashish.login_form.Books.Books_Model.BooksModel;
import com.example.ashish.login_form.Books.Books_Model.getbooksModel;
import com.example.ashish.login_form.Books.book_list;
import com.example.ashish.login_form.Books.books_adapter.BookAdapter;
import com.example.ashish.login_form.Books.books_adapter.issuedBooksAdapter;
import com.example.ashish.login_form.DBhelper.Database_tbls;
import com.example.ashish.login_form.R;
import com.example.ashish.login_form.shered.shared_pre;

import java.util.ArrayList;
import java.util.List;

public class student_profile extends AppCompatActivity implements return_book_interface {
    TextView name,cls,roll_number,email_id,phone_number,bookNAme,TeacherName,studentId;
     Button IssueBook;
     Database_tbls database_tbls;
     RecyclerView recyclerView;
     List<BooksModel> bookList;
     issuedBooksAdapter issuedBooksAdapter;
     shared_pre shared_pre;
     String student_id;
     List<getbooksModel> getbooksModels;
     int count=1;
    private static final int Request_code = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("STUDENT PROFILE");
        database_tbls=new Database_tbls(this);
        bookNAme=findViewById(R.id.bookNAme);
        TeacherName=findViewById(R.id.TeacherName);
        studentId=findViewById(R.id.date);

        shared_pre=new shared_pre(this);
        name=findViewById(R.id.name);
        roll_number=findViewById(R.id.roll_number);
        cls=findViewById(R.id.cls);
        email_id=findViewById(R.id.email_id);
        phone_number=findViewById(R.id.phone_number);
        IssueBook=findViewById(R.id.issue_book);
        recyclerView=findViewById(R.id.recyclerview);
        bookList=new ArrayList<>();
        getbooksModels=new ArrayList<>();
        issuedBooksAdapter=new issuedBooksAdapter(this,getbooksModels,this);

        RecyclerView.LayoutManager mlayout=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mlayout);
        recyclerView.setAdapter(issuedBooksAdapter);


        name.setText(getIntent().getStringExtra("name"));
        roll_number.setText(String.valueOf(getIntent().getIntExtra("roll_number",0)));
        cls.setText(getIntent().getStringExtra("class"));
        email_id.setText(getIntent().getStringExtra("email_id"));
        phone_number.setText(getIntent().getStringExtra("phone_number"));

        student_id = String.valueOf(getIntent().getIntExtra("userId",0));
        //getAllissuedBooks(student_id);
       // getBooks();

        IssueBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(student_profile.this,book_list.class);
                intent.putExtra("studentID",student_id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllissuedBooks(student_id);
    }
    /* protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Request_code:
                if (resultCode == RESULT_OK) {
                    //String studentId = data.getStringExtra("studentId");
                    Integer bookid = data.getIntExtra("bookid",0);
                   // Toast.makeText(this, ""+bookid, Toast.LENGTH_SHORT).show();
                    Log.d("student_profile", "onActivityResult: "+bookid);

                }
                break;
        }
    }*/

    private void getAllissuedBooks(String userid) {
        getbooksModels.clear();
        issuedBooksAdapter.notifyDataSetChanged();
        Cursor data = database_tbls.getIssuedBookByStudentId(userid);
        Log.d("key", "show: count"+data.getCount());
        if (data.moveToFirst()){
            do{

                //Log.d("DBlog", "showDAtaBAse: "+data.getString(1));
                Log.d("key","show:----"+"===" +data.getInt(0)+"===="+data.getString(13)+"-----"+data.getString(7)+"----"+data.getString(8)+"-----"+data.getString(9)+"----"+data.getString(10));
                getbooksModels.add(new getbooksModel(
                        data.getInt(0),
                        data.getString(13),
                        data.getString(3),
                        data.getInt(12),
                        data.getInt(6),
                        data.getInt(7),
                        data.getString(4)
                ));
                issuedBooksAdapter.notifyDataSetChanged();
            }while (data.moveToNext());

        }else{
            Toast.makeText(student_profile.this, "No book found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onitemClick(View view, int i) {
        //Toast.makeText(this, "hELOO", Toast.LENGTH_SHORT).show();
        getbooksModel  model = getbooksModels.get(i);
        if (database_tbls.updateReturnStatus(model.getBookId(),model.getStudentId())){
                getAllissuedBooks(student_id);
        }else{
            Toast.makeText(this, "Sorry", Toast.LENGTH_SHORT).show();
        }
    }

}
