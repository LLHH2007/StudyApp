package com.studyapp.Activity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.studyapp.Model.Question;
import com.studyapp.R;

import java.util.ArrayList;


public class ScreenSlideFragment extends Fragment {
    ArrayList<Question> questions = new ArrayList<Question>();
    public static final String ARG_PAGE = "page";
    public static final String ARG_CHECKANSWER = "checkAnswer";
    private int mPageNumber; //Vi tri trang hien tai
    private int checkAns;
    TextView tvNum, tvQuestion;
    RadioGroup radioGroup;
    RadioButton radA, radB, radC, radD;

    public ScreenSlideFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide, container, false);
        tvNum = (TextView) rootView.findViewById(R.id.tvNum);
        tvQuestion = (TextView) rootView.findViewById(R.id.tvQuestion);
        radA = (RadioButton) rootView.findViewById(R.id.radA);
        radB = (RadioButton) rootView.findViewById(R.id.radB);
        radC = (RadioButton) rootView.findViewById(R.id.radC);
        radD = (RadioButton) rootView.findViewById(R.id.radD);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radGroup);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        ScreenSlideActivity screenSlideActivity = (ScreenSlideActivity) getActivity();
        questions = screenSlideActivity.getData();
        checkAns = getArguments().getInt(ARG_CHECKANSWER);
    }

    public static ScreenSlideFragment create(int pageNumber, int checkAnswer) {
        ScreenSlideFragment fragment = new ScreenSlideFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putInt(ARG_CHECKANSWER,checkAnswer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvNum.setText("Question " + (mPageNumber + 1));
        tvQuestion.setText(questions.get(mPageNumber).getQuestion());
        radA.setText(getItem(mPageNumber).getAns_a());
        radB.setText(getItem(mPageNumber).getAns_b());
        radC.setText(getItem(mPageNumber).getAns_c());
        radD.setText(getItem(mPageNumber).getAns_d());
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                questions.get(mPageNumber).choiceID = checkedId;
                questions.get(mPageNumber).setAnswer(getChoiceFromId(checkedId));
            }
        });
        if(checkAns!=0){
            radA.setClickable(false);
            radB.setClickable(false);
            radC.setClickable(false);
            radD.setClickable(false);
            getCheckAns(getItem(mPageNumber).getResult().toString());
        }
    }

    public Question getItem(int position){
        return questions.get(position);
    }

    //Lay vi tri cua radiogroup chuyen thanh dap an A B C D
    private String getChoiceFromId(int id) {
        if (id == R.id.radA) {
            return "A";
        } else if (id == R.id.radB) {
            return "B";
        } else if (id == R.id.radC) {
            return "C";
        } else if (id == R.id.radD) {
            return "D";
        }
        return "";
    }

    //kiem tra cau dung, neu cau dung thi doi mau background radiobutton tuong ung
    private void getCheckAns(String ans){
        if(ans.equals("A")){
            radA.setBackgroundColor(Color.RED);
        }else if(ans.equals("B")){
            radB.setBackgroundColor(Color.RED);
        }else if(ans.equals("C")){
            radC.setBackgroundColor(Color.RED);
        }else if(ans.equals("D")){
            radD.setBackgroundColor(Color.RED);
        }else{
        }
    }
}