package com.studyapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.studyapp.Model.Question;
import com.studyapp.R;

import java.util.ArrayList;

public class CheckAnswerAdapter extends BaseAdapter {
    ArrayList lsData;
    LayoutInflater inflater;

    public CheckAnswerAdapter(ArrayList lsData, Context context) {
        this.lsData = lsData;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lsData.size();
    }

    @Override
    public Object getItem(int position) {
        return lsData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Question data = (Question) getItem(position);
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView=inflater.inflate(R.layout.item_gridview_list_answer,null);
            holder.tvNumAns=(TextView) convertView.findViewById(R.id.tvNumAns);
            holder.tvYourAns=(TextView) convertView.findViewById(R.id.tvAns);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        int i = position + 1;
        holder.tvNumAns.setText("Question "+i+":");
        holder.tvYourAns.setText(data.getAnswer());
        return convertView;
    }

    private static class ViewHolder{
        TextView tvNumAns, tvYourAns;

        public TextView getTvNumAns() {
            return tvNumAns;
        }

        public void setTvNumAns(TextView tvNumAns) {
            this.tvNumAns = tvNumAns;
        }

        public TextView getTvYourAns() {
            return tvYourAns;
        }

        public void setTvYourAns(TextView tvYourAns) {
            this.tvYourAns = tvYourAns;
        }
    }
}
