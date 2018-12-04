package com.example.ashish.login_form.Books.books_adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashish.login_form.Books.BooksInterface.return_book_interface;
import com.example.ashish.login_form.Books.Books_Model.BooksModel;
import com.example.ashish.login_form.Books.Books_Model.getbooksModel;
import com.example.ashish.login_form.Books.book_list;
import com.example.ashish.login_form.R;
import com.example.ashish.login_form.Student.StudentList.student_adapter.adapter;

import java.util.List;

public class issuedBooksAdapter extends RecyclerView.Adapter<issuedBooksAdapter.MyViewHolder> {
    private Context context;
    List<getbooksModel> booksModelList;
    return_book_interface return_book_interface;


    public issuedBooksAdapter(Context context, List<getbooksModel> booksModelList, com.example.ashish.login_form.Books.BooksInterface.return_book_interface return_book_interface) {
        this.context = context;
        this.booksModelList = booksModelList;
        this.return_book_interface = return_book_interface;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.booksadded,viewGroup,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        final getbooksModel booksModel = booksModelList.get(i);
        myViewHolder.bookname.setText(booksModel.getBookNAme());
        myViewHolder.Teachername.setText(booksModel.getTeacherNAme());
        myViewHolder.date.setText(String.valueOf(booksModel.getDATE()));
        myViewHolder.RETURN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return_book_interface.onitemClick(view,i);
                //Toast.makeText(context, ""+booksModel.getBookId()+"----"+booksModel.getStudentId(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return booksModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bookname,Teachername,date,RETURN;
        public MyViewHolder( View itemView) {
            super(itemView);
            bookname=itemView.findViewById(R.id.bookNAme);
            Teachername=itemView.findViewById(R.id.TeacherName);
            date=itemView.findViewById(R.id.date);
            RETURN=itemView.findViewById(R.id.RETURN);
        }
    }
}
