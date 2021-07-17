package com.studyapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.studyapp.Model.Exam;
import com.studyapp.R;


import java.util.ArrayList;

public class ExamAdapter extends ArrayAdapter {

    public ExamAdapter(@NonNull Context context, ArrayList<Exam> exam) {
        super(context, 0, exam);
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_gridview,parent,false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tvNumExam);
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.imgExam);
        Exam p = (Exam) getItem(position);
        if(p!=null){
            tvName.setText(""+p.getName());
            imgIcon.setImageResource(R.drawable.testavatar);
        }
        return convertView;
    }
}
