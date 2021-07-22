package com.studyapp.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.studyapp.Controller.ScoreController;
import com.studyapp.MainActivity;
import com.studyapp.Model.Question;
import com.studyapp.Model.Score;
import com.studyapp.R;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class TestDoneActivity extends AppCompatActivity {
    ArrayList<Question> arr_QuesBegin = new ArrayList<Question>();
    int numNoAns = 0;
    int numTrue=0;
    int numFalse = 0;
    double totalScore = 0;
    TextView tvTrue, tvFalse, tvNotAns, tvTotalScore;
    Button btnAgain, btnSave, btnExit;
    ScoreController scoreController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_done);
        scoreController = new ScoreController();

        Intent intent = getIntent();
        arr_QuesBegin = (ArrayList<Question>) intent.getExtras().getSerializable("arr_Ques");
        begin();
        checkResult();
        tvNotAns.setText(""+numNoAns);
        tvFalse.setText(""+numFalse);
        tvTrue.setText(""+numTrue);
        totalScore=Double.valueOf(numTrue*10/(numFalse+numTrue+numNoAns));
        tvTotalScore.setText(""+totalScore);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TestDoneActivity.this);
                builder.setIcon(R.drawable.exit);
                builder.setMessage("Do you want to exit?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TestDoneActivity.this);
                LayoutInflater inflater = TestDoneActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.alert_dialog_save_score,null);
                builder.setView(view);
                TextView tvNameSave = (TextView)view.findViewById(R.id.tvNameSave);
                TextView tvScoreSave = (TextView)view.findViewById(R.id.tvScoreSave);
                TextView tvSubjectSave = (TextView)view.findViewById(R.id.tvSubjectSave);
                TextView tvDateSave = (TextView)view.findViewById(R.id.tvDateSave);
                tvNameSave.setText("LLHH");
                tvScoreSave.setText(totalScore+"");
                tvSubjectSave.setText("Java");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                tvDateSave.setText(formatter.format(date));
                builder.setTitle("Save score");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scoreController.insertScore(MainActivity.username,MainActivity.subjectName,totalScore);
                        Toast.makeText(TestDoneActivity.this,"Save success",Toast.LENGTH_LONG).show();
                        finish();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog b = builder.create();
                b.show();
            }
        });

        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(TestDoneActivity.this, ScreenSlideActivity.class);
                refresh();
                intent2.putExtra("arr_Ques", arr_QuesBegin);
                intent2.putExtra("test","no");
                startActivity(intent2);
            }
        });

    }

    public void begin(){
        tvFalse = (TextView)findViewById(R.id.tvFalse);
        tvTrue = (TextView)findViewById(R.id.tvTrue);
        tvNotAns = (TextView)findViewById(R.id.tvNotAns);
        tvTotalScore = (TextView)findViewById(R.id.tvTotalScore);
        btnExit = (Button)findViewById(R.id.btnExit);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnAgain = (Button) findViewById(R.id.btnAgain);
    }

    //check ket qua
    public void checkResult(){
        for(int i = 0; i<arr_QuesBegin.size();i++){
            if(arr_QuesBegin.get(i).getAnswer().equals("")){
                numNoAns++;
            }else if(arr_QuesBegin.get(i).getResult().equals(arr_QuesBegin.get(i).getAnswer())){
                numTrue++;
            }else{
                numFalse++;
            }
        }
    }

    public void refresh(){
        for(int i = 0; i < arr_QuesBegin.size(); i++){
            arr_QuesBegin.get(i).setAnswer("");
        }
    }
}