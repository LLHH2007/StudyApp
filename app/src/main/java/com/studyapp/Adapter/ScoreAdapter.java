package com.studyapp.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.studyapp.R;

public class ScoreAdapter extends CursorAdapter {
    public ScoreAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_score,parent,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvSubject = (TextView) view.findViewById(R.id.tvSubject);
        TextView tvUserScore = (TextView) view.findViewById(R.id.tvUserScore);
        TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
        tvSubject.setText(cursor.getString(2));
        tvUserScore.setText(cursor.getDouble(3)+"");
        tvDate.setText((cursor.getString(4)));
    }
}
