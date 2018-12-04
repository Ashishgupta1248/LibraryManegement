package com.example.ashish.login_form.Books.books_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ashish.login_form.Books.BooksInterface.Books_interface;
import com.example.ashish.login_form.Books.Books_Model.BooksModel;
import com.example.ashish.login_form.R;
import com.example.ashish.login_form.Student.StudentList.student_model.model;

import java.util.ConcurrentModificationException;
import java.util.List;

public class BookAdapter extends  RecyclerView.Adapter<BookAdapter.MyViewHolder> {
    public Context context;
    List<BooksModel> booksModel;
     Books_interface books_interface;

    public BookAdapter(Context context, List<BooksModel> booksModel, Books_interface books_interface) {
        this.context = context;
        this.booksModel = booksModel;
        this.books_interface = books_interface;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_books_list,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        BooksModel booksModel1=booksModel.get(i);
        myViewHolder.BookName.setText(booksModel1.getBookName());
        myViewHolder.book_author.setText(booksModel1.getBookAuthor());
        myViewHolder.book_edition.setText(booksModel1.getBookdition());
        myViewHolder.BooksDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                books_interface.onitemclick(view,i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return booksModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView BookName,book_author,book_edition,books_added;
        LinearLayout BooksDetail;
        public MyViewHolder( View itemView) {
            super(itemView);
            BookName=itemView.findViewById(R.id.book_name);
            book_author=itemView.findViewById(R.id.book_author);
            book_edition=itemView.findViewById(R.id.book_edition);
            BooksDetail=itemView.findViewById(R.id.booksDetail);
        }
    }
}
