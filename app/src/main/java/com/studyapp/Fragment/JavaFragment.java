package com.studyapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.studyapp.Activity.MainActivity;
import com.studyapp.Model.Exam;
import com.studyapp.R;

import java.util.ArrayList;

import com.studyapp.Adapter.ExamAdapter;
import com.studyapp.Activity.ScreenSlideActivity;


public class JavaFragment extends Fragment {
    ExamAdapter examAdapter;
    GridView gvExam;
    ArrayList<Exam> arr_exam = new ArrayList<Exam>();
    public static int NUM_PAGES;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Java");
        return inflater.inflate(R.layout.fragment_java, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gvExam = (GridView) getActivity().findViewById(R.id.gvExam);
        arr_exam.add(new Exam("Exam 1"));
        arr_exam.add(new Exam("Exam 2"));
        arr_exam.add(new Exam("Exam 3"));
        arr_exam.add(new Exam("Exam 4"));
        arr_exam.add(new Exam("Exam 5"));
        arr_exam.add(new Exam("Exam 6"));
        examAdapter = new ExamAdapter(getActivity(),arr_exam);
        gvExam.setAdapter(examAdapter);
        gvExam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),ScreenSlideActivity.class);
                intent.putExtra("num_exam",position+1);
                intent.putExtra("subject","Java");
//                switch (position){
//                    case 0:
//                        NUM_PAGES=5;
//                        break;
//                    case 1:
//                        NUM_PAGES=7;
//                        break;
//                    case 2:
//                        NUM_PAGES=8;
//                        break;
//                    case 3:
//                        NUM_PAGES=10;
//                        break;
//                    case 4:
//                        NUM_PAGES=12;
//                        break;
//                    case 5:
//                        NUM_PAGES=15;
//                        break;
//                }
                startActivity(intent);
            }
        });
    }
}