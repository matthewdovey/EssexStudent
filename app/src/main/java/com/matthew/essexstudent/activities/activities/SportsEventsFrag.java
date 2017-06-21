package com.matthew.essexstudent.activities.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matthew.essexstudent.R;

/**
 * Created by matthew on 20/12/15.
 */
public class SportsEventsFrag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_sports_events,
                container, false);

        return view;
    }
}
