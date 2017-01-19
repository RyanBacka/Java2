// Ryan Backa
// JAV2-1612
// DetailsFragment

package com.ce08.java2.backaryan.backaryan_ce08.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ce08.java2.backaryan.backaryan_ce08.R;

import static android.app.Activity.RESULT_OK;

public class DetailsFragment extends Fragment {

    public static final String FIRST_NAME = "com.fullsail.android.jav2ce08.EXTRA_STRING_FIRST_NAME";
    public static final String LAST_NAME = "com.fullsail.android.jav2ce08.EXTRA_STRING_LAST_NAME";
    public static final String AGE = "com.fullsail.android.jav2ce08.EXTRA_INT_AGE";
    private static final String TAG = "DetailsFragment";

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
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
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                Object value = bundle.get(key);
                Log.d(TAG, String.format("%s %s (%s)", key,
                        value.toString(), value.getClass().getName()));
            }
        }
        int age = bundle.getInt(AGE);
        String ageString = age + "";
        ((TextView) getActivity().findViewById(R.id.showString1)).setText(bundle.getString(FIRST_NAME));
        ((TextView) getActivity().findViewById(R.id.showString2)).setText(bundle.getString(LAST_NAME));
        ((TextView) getActivity().findViewById(R.id.showNumber)).setText(ageString);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent recievedIntent = getActivity().getIntent();
        Bundle bundle = recievedIntent.getExtras();
        String first = bundle.getString(FIRST_NAME);
        String last = bundle.getString(LAST_NAME);
        int age = bundle.getInt(AGE);

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
