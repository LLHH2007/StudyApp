package com.studyapp.Activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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
import com.studyapp.Model.*;
import com.studyapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{
    public static DatabaseHelper databaseHelper;
    public static String username;
    public static String subjectName;
    public static String name;
    private int role;
    private boolean check;
    private Question item;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Tao database
        databaseHelper = new DatabaseHelper(this,"DB.sqlite",null,1);
//        //Tao bang
        databaseHelper.QueryData("CREATE TABLE IF NOT EXISTS question(_id INTEGER PRIMARY KEY AUTOINCREMENT, question VARCHAR(1000) NOT NULL,ans_a VARCHAR(1000) NOT NULL,ans_b VARCHAR(1000) NOT NULL,ans_c VARCHAR(1000) NOT NULL,ans_d VARCHAR(1000) NOT NULL,result VARCHAR(1000) NOT NULL,subject VARCHAR(1000) NOT NULL)");
        databaseHelper.QueryData("CREATE TABLE IF NOT EXISTS users(_id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(100) NOT NULL,password VARCHAR(100) NOT NULL,name VARCHAR(100) NOT NULL, role int not null)");
        databaseHelper.QueryData("CREATE TABLE IF NOT EXISTS score(_id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(100) NOT NULL,subject VARCHAR(1000) NOT NULL,score double NOT NULL,date DATETIME default CURRENT_DATE)");
//        //Them du lieu vao bang
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'How do you insert COMMENTS in Python code?','/*This is a comment*/','//This is a comment','#This is a comment','@This is a comment','C','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which one is NOT a legal variable name?','_myvar','Myvar','my_var','my-var','D','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'What is the correct file extension for Python files?','.pyt','.py','.pt','.pyth','B','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'What is the correct syntax to output the type of a variable or object in Python?','print(typeOf(x))','print(typeof(x))','print(type(x))','print(typeof x)','C','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which method can be used to remove any whitespace from both the beginning and the end of a string?','len()','ptrim()','strip()','trim()','C','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which method can be used to return a string in upper case letters?','toUpperCaseCase()','upper()','upperCase()','uppercase()','B','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which method can be used to replace parts of a string?','repl()','switch()','replaceString()','replace()','D','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which operator is used to multiply numbers?','*','x','#','%','A','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which operator can be used to compare two values?','><','=','<>','==','D','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which collection is ordered, changeable, and allows duplicate members?','TUPLE','SET','DICTIONARY','LIST','D','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'How do you start writing a while loop in Python?','while x > y {','x > y while {','while (x > y) {','while x > y:','D','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which statement is used to stop a loop?','stop','return','exit','break','D','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which function can be used on the file to display a dialog for saving a file?','Filename = savefilename()','Filename = asksavefilename()','Filename = asksaveasfilename()','No such option in python','C','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'A Python script file has ____ as its file extension.','py','python','pyth','pi','A','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'What values do True and False represent?','0:True, 1:False','None of these','1:True, 0:False','1:True, -1:False','C','Python')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'How do you insert COMMENTS in CSharp code?','/*This is a comment*/','//This is a comment','#This is a comment','@This is a comment','B','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which data type is used to create a variable that should store text?','myString','Txt','str','string','D','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'How do you create a variable with the numeric value 5?','num x = 5','int x = 5','double x = 5','x = 5','B','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'How do you create a variable with the floating number 2.8?','double x = 2.8D','int x = 2.8','int x = 2.8D','byte x = 2.8','A','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which property can be used to find the length of a string?','length()','Length','getLength()','length','B','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which operator is used to add together two values?','The * sign','The & sign','The + sign','The && sign','C','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'To declare an array in CSharp, define the variable type with:','{}','[]','()','||','B','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Array indexes start with:','1','-1','0','Other answer','C','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which operator can be used to compare two values?','><','=','<>','==','D','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'How do you create a method in CSharp?','MyMethod()','(MyMethod)','MyMethod','MyMethod[]','A','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'How do you call a method in CSharp?','(MyMethod)','MyMethod()','MyMethod','MyMethod[]','B','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which keyword is used to create a class in CSharp?','class','className','MyClass','class()','A','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'What is the correct way to create an object called myObj of MyClass?','MyClass myObj = new MyClass()','class myObj = new MyClass()','new myObj = MyClass()','class MyClass = new myObj()','A','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'What is the name of the special class that represents a group of constants?','void','const','special','enum','D','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which access modifier makes the code only accessible within the same class?','abstract','public','final','private','D','CSharp')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Java: int','Integer','Int','Interger','gerTein','B','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Computer can execute the code in ______','machine language','high-level language','assembly language','none of the above','A','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'______ translates high-level language program into machine language program.','The operating system','An assembler','A compiler','CPU','C','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Java was developed by ______.','Sun Microsystems','Cisco Systems','Microsoft','Oracle','A','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Java ______ can run from a Web browser.','Micro Edition programs','applets','applications','servlets','B','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'________is a technical definition of the language that includes the syntax and semantics of the Java programming language.','Java JDK','Java API','Java language specification','Java IDE','C','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'______ contains predefined classes and interfaces for developing Java programs.','Java API','Java IDE','Java JDK','Java language specification','A','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'______ consists of a set of separate programs for developing and testing Java programs, each of which is invoked from a command line.','Java JDK','Java IDE','Java language specification','Java API','A','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Java compiler translates Java source code into ______.','another high-level language code','Java bytecode','assembly code','machine code','B','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,' ______ is a software that interprets Java bytecode.','Java debugger','Java compiler','Java API','Java virtual machine','D','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,' The extension name of a Java bytecode file is','.class','.exe','.java','.obj','A','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,' The extension name of a Java source code file is','.class','.exe','.java','.obj','C','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Based on the naming recommendations in the book, which of the following is a good identifier for a variable that will be used to hold an employees phone number?','employee_phone_number','Emphonumber','employeePhoneNumber','EmployeePhoneNumber','C','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,' Which of these data types are used to store numbers with decimal positions?','float','byte','short','long','A','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which of these data types are used to store whole numbers (no decimal positions)?','int','long','short','All of the above','D','Java')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Every valid web page can be represented as a tree. This tree is referred to as the','JavaScript','API','DOM','Batch','C','JavaScript')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'JavaScript uses what kind of interface to access the DOM structure?','CSS3','an API','HTML5','JS','B','JavaScript')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which of the following is not a valid method for generating output to the screen?','document.write','print','alert','prompt','B','JavaScript')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which of these options does NOT require the use of parentheses?','console.log','prompt','alert','innerHTML','D','JavaScript')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which of the following does not generate output directly to the screen?','console.log(message);','document.write(message);','element.innerHTML = message;','element.innerHTML = message;','A','JavaScript')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'How does prompt differ from alert?','Only alert uses parentheses.','The alert will return a value, prompt does not.','Only prompt uses parentheses.','The prompt will return a value, alert does not.','D','JavaScript')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'What does it mean that variables are case-sensitive?','That all variables must use uppercase letters','That all variables must use lowercase letters','That the computer does not think that the variables name and Name are the same thing.','','C','JavaScript')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which of the following is not a valid variable name?','variableOne','oneVariable','variable1','1variable','D','JavaScript')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which of the following is not a valid variable name?','variable-2','variable_2','variable$2','variable$3','A','JavaScript')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'When a function returns a node from the DOM, it is of type','Number','Object','String','Boolean','B','JavaScript')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'A function that wants to return multiple values at once (such as document.getElementsByTagName) will return a/an','Number','Array','String','Boolean','B','JavaScript')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which of the following is not a valid operator?','+=','==','++','=+','D','JavaScript')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which tag is used to let the browser know that it is about to see JavaScript code?','script','head','js','body','A','JavaScript')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Which word is used to define a function in JavaScript?','function','func','define','definition','A','JavaScript')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'Where can you put JavaScript?','In the head and body section','Just in the <head> section','Just in the <body> section','None of the above','A','JavaScript')");
//        databaseHelper.QueryData("INSERT INTO question VALUES(null,'test','In the head and body section','Just in the <head> section','Just in the <body> section','None of the above','A','JavaScript')");
        databaseHelper.QueryData("INSERT INTO question VALUES(null,'test2','In the head and body section','Just in the <head> section','Just in the <body> section','None of the above','A','JavaScript')");

//
//        databaseHelper.QueryData("Insert into users values (null,'admin','admin','Admin',0)");
//        databaseHelper.QueryData("Insert into users values (null,'longh','llhh2007','LLHH',1)");

        super.onCreate(savedInstanceState);


        //Before login
        login();
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentManager manager = getSupportFragmentManager();
        Button btnLogout = (Button)findViewById(R.id.btnLogout2) ;
        switch (id){
            case R.id.nav_home:
                HomeFragment homeFragment = new HomeFragment();
                manager.beginTransaction().replace(R.id.content_main,homeFragment,homeFragment.getTag()).commit();
                btnLogout.setVisibility(View.VISIBLE);
                break;
            case R.id.nav_java:
                JavaFragment javaFragment = new JavaFragment();
                manager.beginTransaction().replace(R.id.content_main,javaFragment,javaFragment.getTag()).commit();
                btnLogout.setVisibility(View.GONE);
                subjectName="Java";
                break;
            case R.id.nav_python:
                PythonFragment pythonFragment = new PythonFragment();
                manager.beginTransaction().replace(R.id.content_main,pythonFragment,pythonFragment.getTag()).commit();
                btnLogout.setVisibility(View.GONE);
                subjectName="Python";
                break;
            case R.id.nav_csharp:
                CsharpFragment csharpFragment = new CsharpFragment();
                manager.beginTransaction().replace(R.id.content_main,csharpFragment,csharpFragment.getTag()).commit();
                btnLogout.setVisibility(View.GONE);
                subjectName="CSharp";
                break;
            case R.id.nav_javascript:
                JavascriptFragment javascriptFragment = new JavascriptFragment();
                manager.beginTransaction().replace(R.id.content_main,javascriptFragment,javascriptFragment.getTag()).commit();
                btnLogout.setVisibility(View.GONE);
                subjectName="JavaScript";
                break;
            case R.id.nav_viewScore:
                ScoreFragment scoreFragment = new ScoreFragment();
                manager.beginTransaction().replace(R.id.content_main,scoreFragment,scoreFragment.getTag()).commit();
                btnLogout.setVisibility(View.GONE);
                break;
            case R.id.search:
                SearchQuestionFragment searchQuestionFragment = new SearchQuestionFragment();
                manager.beginTransaction().replace(R.id.content_main,searchQuestionFragment,searchQuestionFragment.getTag()).commit();
                btnLogout.setVisibility(View.GONE);
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
                            name=user.getName();
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
                            Button btnLogout = (Button)findViewById(R.id.btnLogout2);
                            btnLogout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    login();
                                }
                            });
                            return;
                        }else{
                            check = true;
                            role=0;
                            setContentView(R.layout.admin_activity);
                            Button btnLogout = (Button) findViewById(R.id.btnLogout);
                            btnLogout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    login();
                                }
                            });
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

                            EditText edtFindAdmin = (EditText) findViewById(R.id.edtFindAdmin);
                            edtFindAdmin.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        ListView listView = (ListView)findViewById(R.id.listView);
                                        List<Question> questions = new QuestionController().getByQuestionList(edtFindAdmin.getText().toString());
                                        Question[] ques = new Question[questions.size()];
                                        int i = 0;
                                        for (Question question:questions) {
                                            ques[i]=question;
                                            i++;
                                        }
                                        ArrayAdapter<Question> arrayAdapter
                                                = new ArrayAdapter<Question>(MainActivity.this, android.R.layout.simple_list_item_1 , ques);
                                        listView.setAdapter(arrayAdapter);
                                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            item = (Question) listView.getItemAtPosition(position);

                                            builder = new AlertDialog.Builder(MainActivity.this);
                                            View alert = LayoutInflater.from(MainActivity.this).inflate(R.layout.questionsearchdialogadmin,null);
                                            builder.setView(alert);
                                            EditText edtQues = (EditText) alert.findViewById(R.id.edtQuesAdmin);
                                            EditText edtA = (EditText) alert.findViewById(R.id.edtAAdmin);
                                            EditText edtB = (EditText) alert.findViewById(R.id.edtBAdmin);
                                            EditText edtC = (EditText) alert.findViewById(R.id.edtCAdmin);
                                            EditText edtD = (EditText) alert.findViewById(R.id.edtDAdmin);
                                            EditText edtResult = (EditText) alert.findViewById(R.id.edtResultAdmin);
                                            edtQues.setText(item.getQuestion());
                                            edtA.setText(item.getAns_a());
                                            edtB.setText(item.getAns_b());
                                            edtC.setText(item.getAns_c());
                                            edtD.setText(item.getAns_d());
                                            edtResult.setText(item.getResult());
                                            List<String> list = new ArrayList<>();
                                            list.add("Java");
                                            list.add("C#");
                                            list.add("Python");
                                            list.add("JavaScript");
                                            Spinner spinner = (Spinner) alert.findViewById(R.id.spinnerAdmin);
                                            ArrayAdapter<String> adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item,list);
                                            adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                                            spinner.setAdapter(adapter);
                                            switch (item.getSubject()){
                                                case "Java":
                                                    spinner.setSelection(0);
                                                    break;
                                                case "CSharp":
                                                    spinner.setSelection(1);
                                                    break;
                                                case "Python":
                                                    spinner.setSelection(2);
                                                    break;
                                                case "JavaScript":
                                                    spinner.setSelection(3);
                                                    break;
                                            }
                                            Button btnDelete = (Button) alert.findViewById(R.id.btnDeleteAdmin);
                                            btnDelete.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    new QuestionController().delete(item.getId());
                                                    Toast.makeText(MainActivity.this,"Delete success!",Toast.LENGTH_LONG).show();
                                                    builder.setView(LayoutInflater.from(MainActivity.this).inflate(R.layout.backupdelete,null));
                                                    builder.create().show();

                                                    ListView listView = (ListView)findViewById(R.id.listView);
                                                    List<Question> questions = new QuestionController().getByQuestionList(edtFindAdmin.getText().toString());
                                                    Question[] ques = new Question[questions.size()];
                                                    int i = 0;
                                                    for (Question question:questions) {
                                                        ques[i]=question;
                                                        i++;
                                                    }
                                                    ArrayAdapter<Question> arrayAdapter
                                                            = new ArrayAdapter<Question>(MainActivity.this, android.R.layout.simple_list_item_1 , ques);
                                                    listView.setAdapter(arrayAdapter);

                                                }
                                            });
                                            Button btnUpdate = (Button) alert.findViewById(R.id.btnUpdateAdmin);
                                            btnUpdate.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    if(edtQues.getText().toString().equals("")||edtQues.getText().toString().equals("")||edtQues.getText().toString().equals("")||edtQues.getText().toString().equals("")||edtQues.getText().toString().equals("")||edtQues.getText().toString().equals("")){
                                                        Toast.makeText(MainActivity.this,"Please fill all fields!",Toast.LENGTH_LONG).show();
                                                    }else if(!(edtResult.getText().toString().toUpperCase().equals("A")||edtResult.getText().toString().toUpperCase().equals("B")||edtResult.getText().toString().toUpperCase().equals("C")||edtResult.getText().toString().toUpperCase().equals("D"))){
                                                        Toast.makeText(MainActivity.this,"Please fill A,B,C,D in result!",Toast.LENGTH_LONG).show();
                                                    }else{
                                                        switch (spinner.getSelectedItemPosition()){
                                                            case 0:
                                                                item = new Question(item.getId(),edtQues.getText().toString(),edtA.getText().toString(),edtB.getText().toString(),edtC.getText().toString(),edtD.getText().toString(),edtResult.getText().toString().toUpperCase(),"Java");
                                                                break;
                                                            case 1:
                                                                item = new Question(item.getId(),edtQues.getText().toString(),edtA.getText().toString(),edtB.getText().toString(),edtC.getText().toString(),edtD.getText().toString(),edtResult.getText().toString().toUpperCase(),"CSharp");

                                                                break;
                                                            case 2:
                                                                item = new Question(item.getId(),edtQues.getText().toString(),edtA.getText().toString(),edtB.getText().toString(),edtC.getText().toString(),edtD.getText().toString(),edtResult.getText().toString().toUpperCase(),"Python");

                                                                break;
                                                            case 3:
                                                                item = new Question(item.getId(),edtQues.getText().toString(),edtA.getText().toString(),edtB.getText().toString(),edtC.getText().toString(),edtD.getText().toString(),edtResult.getText().toString().toUpperCase(),"JavaScript");
                                                                break;
                                                        }
                                                        new QuestionController().update(item.getId(),item.getQuestion(),item.getAns_a(),item.getAns_b(),item.getAns_c(),item.getAns_d(),item.getResult(),item.getSubject());
                                                        Toast.makeText(MainActivity.this,"Update success!",Toast.LENGTH_LONG).show();
                                                        ListView listView = (ListView)findViewById(R.id.listView);
                                                        List<Question> questions = new QuestionController().getByQuestionList(edtFindAdmin.getText().toString());
                                                        Question[] ques = new Question[questions.size()];
                                                        int i = 0;
                                                        for (Question question:questions) {
                                                            ques[i]=question;
                                                            i++;
                                                        }
                                                        ArrayAdapter<Question> arrayAdapter
                                                                = new ArrayAdapter<Question>(MainActivity.this, android.R.layout.simple_list_item_1 , ques);
                                                        listView.setAdapter(arrayAdapter);
                                                    }
                                                }
                                            });
                                            builder.create().show();

                                        }

                                    });

                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });




                            listView.setAdapter(arrayAdapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    item = (Question) listView.getItemAtPosition(position);

                                    builder = new AlertDialog.Builder(MainActivity.this);
                                    View alert = LayoutInflater.from(MainActivity.this).inflate(R.layout.questionsearchdialogadmin,null);
                                    builder.setView(alert);
                                    EditText edtQues = (EditText) alert.findViewById(R.id.edtQuesAdmin);
                                    EditText edtA = (EditText) alert.findViewById(R.id.edtAAdmin);
                                    EditText edtB = (EditText) alert.findViewById(R.id.edtBAdmin);
                                    EditText edtC = (EditText) alert.findViewById(R.id.edtCAdmin);
                                    EditText edtD = (EditText) alert.findViewById(R.id.edtDAdmin);
                                    EditText edtResult = (EditText) alert.findViewById(R.id.edtResultAdmin);
                                    edtQues.setText(item.getQuestion());
                                    edtA.setText(item.getAns_a());
                                    edtB.setText(item.getAns_b());
                                    edtC.setText(item.getAns_c());
                                    edtD.setText(item.getAns_d());
                                    edtResult.setText(item.getResult());
                                    List<String> list = new ArrayList<>();
                                    list.add("Java");
                                    list.add("C#");
                                    list.add("Python");
                                    list.add("JavaScript");
                                    Spinner spinner = (Spinner) alert.findViewById(R.id.spinnerAdmin);
                                    ArrayAdapter<String> adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item,list);
                                    adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                                    spinner.setAdapter(adapter);
                                    switch (item.getSubject()){
                                        case "Java":
                                            spinner.setSelection(0);
                                            break;
                                        case "CSharp":
                                            spinner.setSelection(1);
                                            break;
                                        case "Python":
                                            spinner.setSelection(2);
                                            break;
                                        case "JavaScript":
                                            spinner.setSelection(3);
                                            break;
                                    }
                                    Button btnDelete = (Button) alert.findViewById(R.id.btnDeleteAdmin);
                                    btnDelete.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            new QuestionController().delete(item.getId());
                                            Toast.makeText(MainActivity.this,"Delete success!",Toast.LENGTH_LONG).show();
                                            builder.setView(LayoutInflater.from(MainActivity.this).inflate(R.layout.backupdelete,null));
                                            builder.create().show();
                                            ListView listView = (ListView)findViewById(R.id.listView);
                                            List<Question> questions = new QuestionController().getByQuestionList(edtFindAdmin.getText().toString());
                                            Question[] ques = new Question[questions.size()];
                                            int i = 0;
                                            for (Question question:questions) {
                                                ques[i]=question;
                                                i++;
                                            }
                                            ArrayAdapter<Question> arrayAdapter
                                                    = new ArrayAdapter<Question>(MainActivity.this, android.R.layout.simple_list_item_1 , ques);
                                            listView.setAdapter(arrayAdapter);
                                        }
                                    });
                                    Button btnUpdate = (Button) alert.findViewById(R.id.btnUpdateAdmin);
                                    btnUpdate.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if(edtQues.getText().toString().equals("")||edtQues.getText().toString().equals("")||edtQues.getText().toString().equals("")||edtQues.getText().toString().equals("")||edtQues.getText().toString().equals("")||edtQues.getText().toString().equals("")){
                                                Toast.makeText(MainActivity.this,"Please fill all fields!",Toast.LENGTH_LONG).show();
                                            }else if(!(edtResult.getText().toString().toUpperCase().equals("A")||edtResult.getText().toString().toUpperCase().equals("B")||edtResult.getText().toString().toUpperCase().equals("C")||edtResult.getText().toString().toUpperCase().equals("D"))){
                                                Toast.makeText(MainActivity.this,"Please fill A,B,C,D in result!",Toast.LENGTH_LONG).show();
                                            }
                                            else{
                                                switch (spinner.getSelectedItemPosition()){
                                                    case 0:
                                                        item = new Question(item.getId(),edtQues.getText().toString(),edtA.getText().toString(),edtB.getText().toString(),edtC.getText().toString(),edtD.getText().toString(),edtResult.getText().toString().toUpperCase(),"Java");
                                                        break;
                                                    case 1:
                                                        item = new Question(item.getId(),edtQues.getText().toString(),edtA.getText().toString(),edtB.getText().toString(),edtC.getText().toString(),edtD.getText().toString(),edtResult.getText().toString().toUpperCase(),"CSharp");

                                                        break;
                                                    case 2:
                                                        item = new Question(item.getId(),edtQues.getText().toString(),edtA.getText().toString(),edtB.getText().toString(),edtC.getText().toString(),edtD.getText().toString(),edtResult.getText().toString().toUpperCase(),"Python");

                                                        break;
                                                    case 3:
                                                        item = new Question(item.getId(),edtQues.getText().toString(),edtA.getText().toString(),edtB.getText().toString(),edtC.getText().toString(),edtD.getText().toString(),edtResult.getText().toString().toUpperCase(),"JavaScript");
                                                        break;
                                                }
                                                new QuestionController().update(item.getId(),item.getQuestion(),item.getAns_a(),item.getAns_b(),item.getAns_c(),item.getAns_d(),item.getResult(),item.getSubject());
                                                Toast.makeText(MainActivity.this,"Update success!",Toast.LENGTH_LONG).show();
                                                ListView listView = (ListView)findViewById(R.id.listView);
                                                List<Question> questions = new QuestionController().getByQuestionList(edtFindAdmin.getText().toString());
                                                Question[] ques = new Question[questions.size()];
                                                int i = 0;
                                                for (Question question:questions) {
                                                    ques[i]=question;
                                                    i++;
                                                }
                                                ArrayAdapter<Question> arrayAdapter
                                                        = new ArrayAdapter<Question>(MainActivity.this, android.R.layout.simple_list_item_1 , ques);
                                                listView.setAdapter(arrayAdapter);
                                            }
                                        }
                                    });
                                    builder.create().show();

                                }
                            });
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

}