// Ryan Backa
// JAV2 - 1612
// DisplayDataFragment

package com.ce07.java2.backaryan.backaryan_ce07.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ce07.java2.backaryan.backaryan_ce07.PeopleAdapter;
import com.ce07.java2.backaryan.backaryan_ce07.R;
import com.ce07.java2.backaryan.backaryan_ce07.ReadData;
import com.ce07.java2.backaryan.backaryan_ce07.activities.DetailsActivity;
import com.ce07.java2.backaryan.backaryan_ce07.objects.Person;

import java.util.ArrayList;

public class DisplayDataFragment extends ListFragment {

    public static final int REQUST_NEXT = 1;

    public DisplayDataFragment() {
        // Required empty public constructor
    }

    public static DisplayDataFragment newInstance(){
        return new DisplayDataFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_data, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ReadData readData = new ReadData();
        ArrayList<Person> people = readData.readData(getActivity().getApplicationContext());
        PeopleAdapter adapter = new PeopleAdapter(getActivity().getApplicationContext(),people);
        if(people!=null){
            setListAdapter(adapter);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Bundle extras = new Bundle();
        Person chosen = (Person) l.getItemAtPosition(position);
        if(chosen!=null) {
            extras.putSerializable("Person", chosen);
        }
        Intent intent = new Intent(getActivity().getApplicationContext(), DetailsActivity.class);
        intent.putExtra("PERSON", chosen);
        startActivityForResult(intent,REQUST_NEXT);
    }
}
