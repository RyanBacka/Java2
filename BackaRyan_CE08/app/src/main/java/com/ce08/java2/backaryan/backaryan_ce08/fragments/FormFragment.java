// Ryan Backa
// JAV2-1612
// FormFragment

package com.ce08.java2.backaryan.backaryan_ce08.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.ce08.java2.backaryan.backaryan_ce08.R;

import static android.app.Activity.RESULT_OK;

public class FormFragment extends Fragment {


    private static final String TAG = "Form Fragment";
    public static final String FIRST_NAME = "com.fullsail.android.jav2ce08.EXTRA_STRING_FIRST_NAME";
    public static final String LAST_NAME = "com.fullsail.android.jav2ce08.EXTRA_STRING_LAST_NAME";
    public static final String AGE = "com.fullsail.android.jav2ce08.EXTRA_INT_AGE";

    public FormFragment() {
        // Required empty public constructor
    }

    public static FormFragment newInstance() {
        return new FormFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int age = 0;
        String first = ((EditText) getActivity().findViewById(R.id.formFirstName)).getText().toString();
        String last = ((EditText) getActivity().findViewById(R.id.formLastName)).getText().toString();
        String ageString = ((EditText) getActivity().findViewById(R.id.formAge)).getText().toString();
        if (first.isEmpty() || last.isEmpty() || ageString.isEmpty()) {
            Toast.makeText(getActivity(), "One or more fields is empty", Toast.LENGTH_SHORT).show();
        } else {
            age = Integer.parseInt(ageString);
        }
        Intent intent = new Intent();
        Bundle extras = new Bundle();
        extras.putString(FIRST_NAME, first);
        extras.putString(LAST_NAME, last);
        extras.putInt(AGE, age);
        intent.putExtras(extras);
        getActivity().setResult(RESULT_OK, intent);
        getActivity().finish();

        return super.onOptionsItemSelected(item);
    }


}
