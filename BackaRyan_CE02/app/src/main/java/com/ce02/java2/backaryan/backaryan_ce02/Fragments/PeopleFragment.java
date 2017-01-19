// Ryan Backa
// Jav2 1612
// PeopleFragment

package com.ce02.java2.backaryan.backaryan_ce02.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ce02.java2.backaryan.backaryan_ce02.Objects.People;
import com.ce02.java2.backaryan.backaryan_ce02.PeopleAdapter;
import com.ce02.java2.backaryan.backaryan_ce02.R;
import com.ce02.java2.backaryan.backaryan_ce02.ReadData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends ListFragment {


    public PeopleFragment() {
        // Required empty public constructor
    }

    public static PeopleFragment newInstance(){
        PeopleFragment fragment = new PeopleFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_people, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ReadData readData = new ReadData();
        ArrayList<People> people = readData.readData(getActivity().getApplicationContext());
        PeopleAdapter adapter = new PeopleAdapter(getActivity().getApplicationContext(),people);
        if(people!=null){
            setListAdapter(adapter);
        }
    }
}
