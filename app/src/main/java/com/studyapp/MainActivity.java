package com.studyapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.studyapp.Activity.TestDoneActivity;
import com.studyapp.Controller.QuestionController;
import com.studyapp.Controller.UserController;
import com.studyapp.Dao.DatabaseHelper;

import com.studyapp.Fragment.CsharpFragment;
import com.studyapp.Fragment.HomeFragment;
import com.studyapp.Fragment.JavaFragment;
import com.studyapp.Fragment.JavascriptFragment;
import com.studyapp.Fragment.PythonFragment;
import com.studyapp.Fragment.ScoreFragment;
import com.studyapp.Fragment.SearchQuestionFragment;
import com.studyapp.Model.Question;
import com.studyapp.Model.User;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.Ref;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{
    public static DatabaseHelper databaseHelper;
    public static String username;
    public static String subjectName;
    private int role;
    private boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Tao database
        databaseHelper = new DatabaseHelper(this,"DB.sqlite",null,1);
//        //Tao bang
        databaseHelper.QueryData("CREATE TABLE IF NOT EXISTS question(_id INTEGER PRIMARY KEY AUTOINCREMENT, question VARCHAR(1000) NOT NULL,ans_a VARCHAR(1000) NOT NULL,ans_b VARCHAR(1000) NOT NULL,ans_c VARCHAR(1000) NOT NULL,ans_d VARCHAR(1000) NOT NULL,result VARCHAR(1000) NOT NULL,subject VARCHAR(1000) NOT NULL)");
        databaseHelper.QueryData("CREATE TABLE IF NOT EXISTS users(_id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(100) NOT NULL,password VARCHAR(100) NOT NULL,name VARCHAR(100) NOT NULL, role int not null)");
        databaseHelper.QueryData("CREATE TABLE IF NOT EXISTS score(_id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(100) NOT NULL,subject VARCHAR(1000) NOT NULL,score double NOT NULL,date DATETIME default CURRENT_DATE)");
//        //Them du lieu vao bang
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which of the following option leads to the portability and security of Java?','Bytecode is executed by JVM','System.out.println()','cin','print','B','Java')");
//
//        databaseHelper.QueryData("Insert into users values (null,'admin','admin','Admin',0)");
//        databaseHelper.QueryData("Insert into users values (null,'longh','llhh2007','Long',1)");

        super.onCreate(savedInstanceState);


        //Before login
        login();
        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        EditText edUserName = (EditText) findViewById(R.id.edUserName);
        EditText edPassword = (EditText) findViewById(R.id.edPassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edUserName.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Please input username!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(edPassword.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Please input password!",Toast.LENGTH_LONG).show();
                    return;
                }
                UserController userController = new UserController();
                List<User> users = userController.getAll();
                for (User user:users) {
                    if(user.getUsername().equalsIgnoreCase(edUserName.getText().toString())&&user.getPassword().equals(edPassword.getText().toString())){
                        if(user.getRole()==1){
                            role=1;
                            check=true;
                            username = user.getUsername();
                            setContentView(R.layout.activity_main);
                            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                            setSupportActionBar(toolbar);
                            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                            fab.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            });
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                            drawer.setDrawerListener(toggle);
                            toggle.syncState();

                            NavigationView navigationView = (NavigationView) findViewById((R.id.nav_view));
                            navigationView.setNavigationItemSelectedListener(MainActivity.this);
                            navigationView.setItemIconTintList(null);

                            FragmentManager manager = getSupportFragmentManager();
                            HomeFragment homeFragment = new HomeFragment();
                            manager.beginTransaction().replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();
                            return;
                        }else{
                            check = true;
                            role=0;
                            setContentView(R.layout.admin_activity);

                            ListView listView = (ListView)findViewById(R.id.listView);
                            List<Question> questions = new QuestionController().getAll();
                            Question[] ques = new Question[questions.size()];
                            int i = 0;
                            for (Question question:questions) {
                                ques[i]=question;
                                i++;
                            }

                            ArrayAdapter<Question> arrayAdapter
                                    = new ArrayAdapter<Question>(MainActivity.this, android.R.layout.simple_list_item_1 , ques);

                            listView.setAdapter(arrayAdapter);
                            List<String> list = new ArrayList<>();
                            list.add("Java");
                            list.add("C#");
                            list.add("Python");
                            list.add("JavaScript");
                            Spinner spinner = (Spinner) findViewById(R.id.spinner);
                            ArrayAdapter<String> adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item,list);
                            adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                            spinner.setAdapter(adapter);
                            spinner.setSelection(0);
                            Button btnAddNewQuestion = (Button) findViewById(R.id.btnAddQuestion);
                            btnAddNewQuestion.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    EditText edtQuestion = (EditText) findViewById(R.id.edNewQues);
                                    EditText edtA = (EditText) findViewById(R.id.edNewAnsA);
                                    EditText edtB = (EditText) findViewById(R.id.edNewAnsB);
                                    EditText edtC = (EditText) findViewById(R.id.edNewAnsC);
                                    EditText edtD = (EditText) findViewById(R.id.edNewAnsD);
                                    EditText edtResult = (EditText) findViewById(R.id.edNewCorrectAns);
                                    if(edtQuestion.getText().toString().equals("")||edtA.getText().toString().equals("")||edtB.getText().toString().equals("")||edtC.getText().toString().equals("")||edtD.getText().toString().equals("")||edtResult.getText().toString().equals("")){
                                        Toast.makeText(MainActivity.this,"Please input all fields!",Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                    if(!(edtResult.getText().toString().equalsIgnoreCase("A")||edtResult.getText().toString().equalsIgnoreCase("B")||edtResult.getText().toString().equalsIgnoreCase("C")||edtResult.getText().toString().equalsIgnoreCase("D"))){
                                        Toast.makeText(MainActivity.this,"Please input A or B or C or D for result!",Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                    int position = spinner.getSelectedItemPosition();
                                    switch(position){
                                        case 0:
                                            new QuestionController().insertQuestion(edtQuestion.getText().toString(),edtA.getText().toString(),edtB.getText().toString(),edtC.getText().toString(),edtD.getText().toString(),edtResult.getText().toString().toUpperCase(),"Java");
                                            break;
                                        case 1:
                                            new QuestionController().insertQuestion(edtQuestion.getText().toString(),edtA.getText().toString(),edtB.getText().toString(),edtC.getText().toString(),edtD.getText().toString(),edtResult.getText().toString().toUpperCase(),"CSharp");
                                            break;
                                        case 2:
                                            new QuestionController().insertQuestion(edtQuestion.getText().toString(),edtA.getText().toString(),edtB.getText().toString(),edtC.getText().toString(),edtD.getText().toString(),edtResult.getText().toString().toUpperCase(),"Python");
                                            break;
                                        case 3:
                                            new QuestionController().insertQuestion(edtQuestion.getText().toString(),edtA.getText().toString(),edtB.getText().toString(),edtC.getText().toString(),edtD.getText().toString(),edtResult.getText().toString().toUpperCase(),"JavaScript");
                                            break;
                                    }

                                    edtQuestion.setText("");
                                    edtA.setText("");
                                    edtB.setText("");
                                    edtC.setText("");
                                    edtD.setText("");
                                    edtResult.setText("");
                                    spinner.setSelection(0);
                                    Toast.makeText(MainActivity.this,"Success!",Toast.LENGTH_LONG).show();
                                    ListView listView = (ListView)findViewById(R.id.listView);
                                    List<Question> questions = new QuestionController().getAll();
                                    Question[] ques = new Question[questions.size()];
                                    int i = 0;
                                    for (Question question:questions) {
                                        ques[i]=question;
                                        i++;
                                    }
                                    ArrayAdapter<Question> arrayAdapter
                                            = new ArrayAdapter<Question>(MainActivity.this, android.R.layout.simple_list_item_1 , ques);
                                    listView.setAdapter(arrayAdapter);
                                    return;
                                }
                            });
                            return;
                        }
                    }
                }
                Toast.makeText(MainActivity.this,"Wrong username or password!",Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentManager manager = getSupportFragmentManager();
        switch (id){
            case R.id.nav_home:
                HomeFragment homeFragment = new HomeFragment();
                manager.beginTransaction().replace(R.id.content_main,homeFragment,homeFragment.getTag()).commit();
                break;
            case R.id.nav_java:
                JavaFragment javaFragment = new JavaFragment();
                manager.beginTransaction().replace(R.id.content_main,javaFragment,javaFragment.getTag()).commit();
                break;
            case R.id.nav_python:
                PythonFragment pythonFragment = new PythonFragment();
                manager.beginTransaction().replace(R.id.content_main,pythonFragment,pythonFragment.getTag()).commit();
                break;
            case R.id.nav_csharp:
                CsharpFragment csharpFragment = new CsharpFragment();
                manager.beginTransaction().replace(R.id.content_main,csharpFragment,csharpFragment.getTag()).commit();
                break;
            case R.id.nav_javascript:
                JavascriptFragment javascriptFragment = new JavascriptFragment();
                manager.beginTransaction().replace(R.id.content_main,javascriptFragment,javascriptFragment.getTag()).commit();
                break;
            case R.id.nav_viewScore:
                ScoreFragment scoreFragment = new ScoreFragment();
                manager.beginTransaction().replace(R.id.content_main,scoreFragment,scoreFragment.getTag()).commit();
                break;
            case R.id.search:
                SearchQuestionFragment searchQuestionFragment = new SearchQuestionFragment();
                manager.beginTransaction().replace(R.id.content_main,searchQuestionFragment,searchQuestionFragment.getTag()).commit();
                break;
            default:
                break;
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void login(){
        check=false;
        role = -1;
        setContentView(R.layout.activity_login);
    }

}