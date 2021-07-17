package com.studyapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.studyapp.Dao.DatabaseHelper;

import com.studyapp.Fragment.CsharpFragment;
import com.studyapp.Fragment.HomeFragment;
import com.studyapp.Fragment.JavaFragment;
import com.studyapp.Fragment.JavascriptFragment;
import com.studyapp.Fragment.PythonFragment;
import com.studyapp.Fragment.ScoreFragment;
import com.studyapp.Fragment.SearchQuestionFragment;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{
    public static DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Tao database
        databaseHelper = new DatabaseHelper(this,"DB.sqlite",null,1);
//        //Tao bang
//        databaseHelper.QueryData("CREATE TABLE IF NOT EXISTS question(_id INTEGER PRIMARY KEY AUTOINCREMENT, question VARCHAR(1000) NOT NULL,ans_a VARCHAR(1000) NOT NULL,ans_b VARCHAR(1000) NOT NULL,ans_c VARCHAR(1000) NOT NULL,ans_d VARCHAR(1000) NOT NULL,result VARCHAR(1000) NOT NULL,subject VARCHAR(1000) NOT NULL)");
//        databaseHelper.QueryData("CREATE TABLE IF NOT EXISTS users(_id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(100) NOT NULL,password VARCHAR(100) NOT NULL,name VARCHAR(100) NOT NULL)");
//        databaseHelper.QueryData("CREATE TABLE IF NOT EXISTS score(_id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(100) NOT NULL,subject VARCHAR(1000) NOT NULL,score double NOT NULL,date DATETIME default CURRENT_DATE)");
//        //Them du lieu vao bang
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Print syntax in Java?','sysout','System.out.println()','cin','print','B','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'1+1','2','3','4','1','A','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'int','Integer','Int','Interger','gerTein','B','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'2+2','3','4','5','6','B','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'1+2','5','6','7','3','D','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'C# question','sysout','System.out.println()','cin','print','B','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'C# question2','2','3','4','1','A','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'C# question3','Integer','Int','Interger','gerTein','B','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'C# question4','3','4','5','6','B','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'C# question5','5','6','7','3','D','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Python question','sysout','System.out.println()','cin','print','B','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Python question2','2','3','4','1','A','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Python question3','Integer','Int','Interger','gerTein','B','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Python question4','3','4','5','6','B','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Python question5','5','6','7','3','D','Python')");

        super.onCreate(savedInstanceState);

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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById((R.id.nav_view));
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager manager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        manager.beginTransaction().replace(R.id.content_main,homeFragment,homeFragment.getTag()).commit();
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
}