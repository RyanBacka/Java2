//Ryan Backa
//Jav2-1612
//MyPreferenceFragment

package com.ce04.java2.backaryan.backaryan_ce04.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ce04.java2.backaryan.backaryan_ce04.R;
import com.ce04.java2.backaryan.backaryan_ce04.objects.DbHelper;

/**
 * Created by ryankbacka on 11/4/16.
 */

public class MyPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    public MyPreferenceFragment(){

    }

    public static MyPreferenceFragment newInstance(){
        MyPreferenceFragment fragment = new MyPreferenceFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_preference);
        Preference deleteClick = findPreference("Delete Employee");
        deleteClick.setOnPreferenceClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if(view!=null) {
            view.setBackgroundColor(getResources().getColor(R.color.prefColor));
        }
        return view;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Delete Entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DbHelper.deleteAll(getActivity().getApplicationContext());
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
        return false;
    }
}
