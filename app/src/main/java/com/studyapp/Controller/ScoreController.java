package com.studyapp.Controller;

import android.database.Cursor;

import com.studyapp.Dao.DatabaseHelper;
import com.studyapp.Activity.MainActivity;
import com.studyapp.Model.Question;
import com.studyapp.Model.Score;

import java.util.ArrayList;

public class ScoreController {
    DatabaseHelper databaseHelper = MainActivity.databaseHelper;
    public ScoreController(){

    }

    public Cursor getAll(){
        //ArrayList<Score> list = new ArrayList<>();
        //Hien thi du lieu
        Cursor data = databaseHelper.GetData("Select * from score");
//        while(data.moveToNext()){
//            Score score = new Score(data.getString(1),data.getString(2),data.getDouble(3),data.getString(4));
//            list.add(score);
//        }
        if(data!=null){
            data.moveToFirst();
        }
        return data;
    }

    public Cursor getByUserName(String username){
        ArrayList<Score> list = new ArrayList<Score>();
        //Hien thi du lieu
        Cursor data = databaseHelper.GetData("Select * from score where username = '"+username+"'");
//        while(data.moveToNext()){
//            Score score = new Score(data.getString(1),data.getString(2),data.getDouble(3),data.getString(4));
//            list.add(score);
//        }
        if(data!=null){
            data.moveToFirst();
        }
        return data;
    }

    public void insertScore(String username, String subject, Double score){
        databaseHelper.QueryData("Insert into score(username,subject,score) values ('"+username+"','"+subject+"',"+score+")");
    }
}
