package com.studyapp.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.studyapp.R;

public class QuestionAdapter extends CursorAdapter {
    public QuestionAdapter(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_list_question,parent,false);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            LinearLayout linQues = (LinearLayout) view.findViewById(R.id.linQues);
            TextView tvQuestionSearch = (TextView) view.findViewById(R.id.tvQuestionSearch);

            if(cursor.getPosition()%2==0){
                linQues.setBackgroundColor(Color.parseColor("#FFE2DFDF"));
            }else{
                linQues.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            tvQuestionSearch.setText(cursor.getString(1));
        }
}
