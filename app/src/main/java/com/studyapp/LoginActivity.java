package com.studyapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.studyapp.Dao.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    EditText username, password;
    Button btnLogin;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Tao database
        databaseHelper = new DatabaseHelper(this,"DB.sqlite",null,1);
        //Tao bang
        databaseHelper.QueryData("CREATE TABLE IF NOT EXISTS question(_id INTEGER PRIMARY KEY AUTOINCREMENT, question VARCHAR(1000) NOT NULL,ans_a VARCHAR(1000) NOT NULL,ans_b VARCHAR(1000) NOT NULL,ans_c VARCHAR(1000) NOT NULL,ans_d VARCHAR(1000) NOT NULL,result VARCHAR(1000) NOT NULL,subject VARCHAR(1000) NOT NULL)");
        databaseHelper.QueryData("CREATE TABLE IF NOT EXISTS users(_id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(100) NOT NULL,password VARCHAR(100) NOT NULL, name VARCHAR(100) NOT NULL,role BIT NOT NULL)");
        databaseHelper.QueryData("CREATE TABLE IF NOT EXISTS score(_id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(100) NOT NULL,subject VARCHAR(1000) NOT NULL,score double NOT NULL,date DATETIME default CURRENT_DATE)");
        //Them du lieu vao bang
        databaseHelper.QueryData("INSERT INTO users VALUES(null,'admin','admin','LongH','1')");
        databaseHelper.QueryData("INSERT INTO users VALUES(null,'student','student','TueNM','0')");
        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Print syntax in Java?','sysout','System.out.println()','cin','print','B','Java')");
        databaseHelper.QueryData("INSERT INTO question VALUES(null,'1+1','2','3','4','1','A','Java')");
        databaseHelper.QueryData("INSERT INTO question VALUES(null,'int','Integer','Int','Interger','gerTein','B','Java')");
        databaseHelper.QueryData("INSERT INTO question VALUES(null,'2+2','3','4','5','6','B','Java')");
        databaseHelper.QueryData("INSERT INTO question VALUES(null,'1+2','5','6','7','3','D','Java')");
        databaseHelper.QueryData("INSERT INTO question VALUES(null,'C# question','sysout','System.out.println()','cin','print','B','CSharp')");
        databaseHelper.QueryData("INSERT INTO question VALUES(null,'C# question2','2','3','4','1','A','CSharp')");
        databaseHelper.QueryData("INSERT INTO question VALUES(null,'C# question3','Integer','Int','Interger','gerTein','B','CSharp')");
        databaseHelper.QueryData("INSERT INTO question VALUES(null,'C# question4','3','4','5','6','B','CSharp')");
        databaseHelper.QueryData("INSERT INTO question VALUES(null,'C# question5','5','6','7','3','D','CSharp')");
        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Python question','sysout','System.out.println()','cin','print','B','Python')");
        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Python question2','2','3','4','1','A','Python')");
        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Python question3','Integer','Int','Interger','gerTein','B','Python')");
        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Python question4','3','4','5','6','B','Python')");
        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Python question5','5','6','7','3','D','Python')");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        username = findViewById(R.id.edt_name);
        password = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = findViewById(R.id.edt_name);
                password = findViewById(R.id.edt_password);

                String userType = databaseHelper.checkUser(username.getText().toString(), password.getText().toString());

                if (userType.equalsIgnoreCase("adm")) {
                    //navigate to admin page
                } else if (userType.equalsIgnoreCase("gst")) {
                    Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(mainActivityIntent);
                } else {
                    Toast.makeText(LoginActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void check(View view) {
//        username = findViewById(R.id.edt_name);
//        password = findViewById(R.id.edt_password);
//
//        if (databaseHelper.checkUser(username.getText().toString(), password.getText().toString()).equalsIgnoreCase("adm")) {
//            //navigate to admin page
//        } else if (databaseHelper.checkUser(username.getText().toString(), password.getText().toString()).equalsIgnoreCase("gst")) {
//            Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(mainActivityIntent);
//        } else {
//            Toast.makeText(LoginActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
//        }
    }

    protected void onPause() {
        super.onPause();
        username = findViewById(R.id.edt_name);
        password = findViewById(R.id.edt_password);
        username.getText().clear();
        password.getText().clear();

    }
}