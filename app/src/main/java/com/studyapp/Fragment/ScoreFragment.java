package com.studyapp.Fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.studyapp.Adapter.ScoreAdapter;
import com.studyapp.Controller.ScoreController;
import com.studyapp.MainActivity;
import com.studyapp.R;

import org.jetbrains.annotations.NotNull;


public class ScoreFragment extends Fragment {

    ListView lvScore;
    ScoreController scoreController;
    ScoreAdapter scoreAdapter;

    public ScoreFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("View Score");
        return inflater.inflate(R.layout.fragment_score,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        scoreController = new ScoreController();
        lvScore = (ListView) getActivity().findViewById(R.id.lvScores);
        Cursor cursor = scoreController.getByUserName(MainActivity.username);
        scoreAdapter = new ScoreAdapter(getActivity(),cursor,true);
        lvScore.setAdapter(scoreAdapter);
    }
}