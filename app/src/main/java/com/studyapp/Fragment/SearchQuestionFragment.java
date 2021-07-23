package com.studyapp.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.studyapp.Activity.MainActivity;
import com.studyapp.Controller.QuestionController;
import com.studyapp.Adapter.QuestionAdapter;
import com.studyapp.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class SearchQuestionFragment extends Fragment {
    ListView lvQuestion;
    QuestionController questionController;
    QuestionAdapter adapter;
    EditText edtSearch;
    ImageButton imgFilter;
    String subject;

    public SearchQuestionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Search");
        return inflater.inflate(R.layout.fragment_search_question, container, false);
    }

    public void begin(){
        subject="";
        lvQuestion = (ListView) getActivity().findViewById(R.id.lvQuestion);
        imgFilter = (ImageButton) getActivity().findViewById(R.id.imageFilter);
        edtSearch = (EditText) getActivity().findViewById(R.id.edtSearch);
        questionController = new QuestionController();
        listCursor(questionController.getAllCursor());
        lvQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor item = (Cursor) lvQuestion.getItemAtPosition(position);
                //System.out.println(item.getString(1));
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View alert = LayoutInflater.from(getContext()).inflate(R.layout.questionsearchdialog,null);
                builder.setView(alert);
                TextView tvQues = (TextView) alert.findViewById(R.id.tvContentItem);
                TextView tvAns = (TextView) alert.findViewById(R.id.tvAnsItem);
                tvQues.setText(item.getString(1));
                switch (item.getString(6).toUpperCase()){
                    case "A":
                        tvAns.setText("Answer: "+item.getString(2));
                        break;
                    case "B":
                        tvAns.setText("Answer: "+item.getString(3));
                        break;
                    case "C":
                        tvAns.setText("Answer: "+item.getString(4));
                        break;
                    case "D":
                        tvAns.setText("Answer: "+item.getString(5));
                        break;
                }
                builder.create().show();
            }
        });
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        begin();
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(subject.equals("")){
                    listCursor(questionController.getByQuestion(edtSearch.getText().toString()));
                }else{
                    listCursor(questionController.getSearch(subject,edtSearch.getText().toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        imgFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });
    }

    public void listCursor(Cursor cursor){
        adapter = new QuestionAdapter(getActivity(),cursor,true);
        lvQuestion.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void showMenu(View v){
        PopupMenu popupMenu = new PopupMenu(getActivity(),v);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.quesAll:
                        subject="";
                        if(edtSearch.getText().toString().equals("")){
                            listCursor(questionController.getAllCursor());
                        }else{
                            listCursor(questionController.getByQuestion(edtSearch.getText().toString()));
                        }
                        break;
                    case R.id.quesJava:
                        subject = "Java";
                        if(edtSearch.getText().toString().equals("")) {
                            listCursor(questionController.getSearch(subject, ""));
                        }else{
                            listCursor(questionController.getSearch(subject, edtSearch.getText().toString()));
                        }
                        break;
                    case R.id.quesCSharp:
                        subject = "CSharp";
                        if(edtSearch.getText().toString().equals("")) {
                            listCursor(questionController.getSearch(subject, ""));
                        }else{
                            listCursor(questionController.getSearch(subject, edtSearch.getText().toString()));
                        }
                        break;
                    case R.id.quesJS:
                        subject = "JavaScript";
                        if(edtSearch.getText().toString().equals("")) {
                            listCursor(questionController.getSearch(subject, ""));
                        }else{
                            listCursor(questionController.getSearch(subject, edtSearch.getText().toString()));
                        }
                        break;
                    case R.id.quesPython:
                        subject="Python";
                        if(edtSearch.getText().toString().equals("")) {
                            listCursor(questionController.getSearch(subject, ""));
                        }else{
                            listCursor(questionController.getSearch(subject, edtSearch.getText().toString()));
                        }
                        break;

                }
                return false;
            }
        });
        popupMenu.inflate(R.menu.menu_question);
        setForceShowIcon(popupMenu);
        popupMenu.show();
    }

    //Hiện thị icon trên popupMenu Field
    public static void setForceShowIcon(PopupMenu popupMenu) {
        try {
            Field[] fields = popupMenu.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popupMenu);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper
                            .getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod(
                            "setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}