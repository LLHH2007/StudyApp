package com.studyapp.Controller;

import android.database.Cursor;

import com.studyapp.Dao.DatabaseHelper;
import com.studyapp.MainActivity;
import com.studyapp.Model.Question;
import com.studyapp.Model.User;

import java.util.ArrayList;

public class UserController {
    DatabaseHelper databaseHelper = MainActivity.databaseHelper;
    public UserController(){

    }

    public ArrayList<User> getAll(){
        ArrayList<User> list = new ArrayList<>();
        //Hien thi du lieu
        Cursor data = databaseHelper.GetData("Select * from users");
        while(data.moveToNext()){
            User user = new User(data.getString(1),data.getString(2),data.getString(3),data.getInt(4));
            list.add(user);
        }
        return list;
    }
}
