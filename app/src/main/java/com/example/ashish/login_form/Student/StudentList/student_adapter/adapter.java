package com.example.ashish.login_form.Student.StudentList.student_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ashish.login_form.R;
import com.example.ashish.login_form.Student.StudentList.Interface.student_interface;
import com.example.ashish.login_form.Student.StudentList.student_model.model;

import java.util.List;

public class adapter  extends RecyclerView.Adapter<adapter.MyViewHolder>  {
    private Context context;
    public List<model> studentlist;
    student_interface studentInterface;

    public adapter(Context context, List<model> studentlist, student_interface studentInterface) {
        this.context = context;
        this.studentlist = studentlist;
        this.studentInterface = studentInterface;
    }



    @Override
    public adapter.MyViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_student_data,viewGroup,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(adapter.MyViewHolder myViewHolder, final int i) {
        model model1=studentlist.get(i);
       // myViewHolder.roll.setText(model1.getRoll_number());
        myViewHolder.student_list.setText(model1.getName());
        myViewHolder.viewClass.setText(model1.getCls());
        myViewHolder.studenDetailHolder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                studentInterface.onitemclick(view,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView student_list,viewClass;
        LinearLayout studenDetailHolder;
        public MyViewHolder(View itemView) {
            super(itemView);
           // roll=itemView.findViewById(R.id.roll_number);
            student_list=itemView.findViewById(R.id.view_data);
            viewClass=itemView.findViewById(R.id.view_class);
            studenDetailHolder = itemView.findViewById(R.id.studenDetailHolder);
        }
    }
}
