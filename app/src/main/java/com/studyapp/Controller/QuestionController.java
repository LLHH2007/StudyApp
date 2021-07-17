package com.studyapp.Controller;

import android.database.Cursor;

import com.studyapp.Dao.DatabaseHelper;
import com.studyapp.MainActivity;
import com.studyapp.Model.Question;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class QuestionController {
    DatabaseHelper databaseHelper = MainActivity.databaseHelper;
    public QuestionController(){

    }

    public ArrayList<Question> getAll(){
        ArrayList<Question> list = new ArrayList<>();
        //Hien thi du lieu
        Cursor data = databaseHelper.GetData("Select * from question");
        while(data.moveToNext()){
            Question question = new Question(data.getInt(0),data.getString(1),data.getString(2),data.getString(3),data.getString(4),data.getString(5),data.getString(6),data.getString(7),"");
            list.add(question);
        }
        return list;
    }

    public Cursor getAllCursor(){
//        ArrayList<Question> list = new ArrayList<>();
        //Hien thi du lieu
        Cursor data = databaseHelper.GetData("Select * from question");
//        while(data.moveToNext()){
//            Question question = new Question(data.getInt(0),data.getString(1),data.getString(2),data.getString(3),data.getString(4),data.getString(5),data.getString(6),data.getString(7),"");
//            list.add(question);
//        }
        if(data!=null){
            data.moveToFirst();
        }
        return data;
    }

    public ArrayList<Question> getBySubject(String subject){
        ArrayList<Question> list = new ArrayList<Question>();
        //Hien thi du lieu
        Cursor data = databaseHelper.GetData("Select * from question where subject = '"+subject+"'");
        while(data.moveToNext()){
            Question question = new Question(data.getInt(0),data.getString(1),data.getString(2),data.getString(3),data.getString(4),data.getString(5),data.getString(6),data.getString(7),"");
            list.add(question);
        }
        return list;
    }

    public Cursor getByQuestion(String key){
        //Hien thi du lieu
        Cursor data = databaseHelper.GetData("Select * from question where question like '%"+key+"%'");
//        while(data.moveToNext()){
//            Question question = new Question(data.getInt(0),data.getString(1),data.getString(2),data.getString(3),data.getString(4),data.getString(5),data.getString(6),data.getString(7),"");
//            list.add(question);
//        }
        if(data!=null){
            data.moveToFirst();
        }
        return data;
    }

    public Cursor getSearch(String subject, String key){
        //Hien thi du lieu
        Cursor data = databaseHelper.GetData("Select * from question where question like '%"+key+"%' and subject='"+subject+"'");
//        while(data.moveToNext()){
//            Question question = new Question(data.getInt(0),data.getString(1),data.getString(2),data.getString(3),data.getString(4),data.getString(5),data.getString(6),data.getString(7),"");
//            list.add(question);
//        }
        if(data!=null){
            data.moveToFirst();
        }
        return data;
    }
}
