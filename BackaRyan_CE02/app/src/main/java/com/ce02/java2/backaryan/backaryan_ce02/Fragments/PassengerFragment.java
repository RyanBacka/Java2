// Ryan Backa
// Jav2 1612
// PassengerFragment

package com.ce02.java2.backaryan.backaryan_ce02.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ce02.java2.backaryan.backaryan_ce02.Objects.Passenger;
import com.ce02.java2.backaryan.backaryan_ce02.Objects.People;
import com.ce02.java2.backaryan.backaryan_ce02.Objects.Pilot;
import com.ce02.java2.backaryan.backaryan_ce02.R;
import com.ce02.java2.backaryan.backaryan_ce02.ReadData;
import com.ce02.java2.backaryan.backaryan_ce02.SaveData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PassengerFragment extends Fragment {


    public PassengerFragment() {
        // Required empty public constructor
    }

    public static PassengerFragment newInstance(){
        PassengerFragment fragment = new PassengerFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button submit = (Button) getView().findViewById(R.id.passSubmitButton);
        if (submit != null) {
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String firstName = ((EditText) getView().findViewById(R.id.passFirstName)).getText().toString();
                    String lastName = ((EditText)getView().findViewById(R.id.passLastName)).getText().toString();
                    String destination = ((EditText)getView().findViewById(R.id.passDestination)).getText().toString();
                    String layover = ((EditText)getView().findViewById(R.id.passLayover)).getText().toString();
                    if(firstName.isEmpty()||lastName.isEmpty()||destination.isEmpty()||layover.isEmpty()) {
                        Toast.makeText(getContext(), "One or more fields is empty", Toast.LENGTH_SHORT).show();
                    }else{
                        Passenger passenger = new Passenger(firstName,lastName,destination,layover);
                        ReadData read = new ReadData();
                        ArrayList<People> people = read.readData(getActivity().getApplicationContext());
                        people.add(passenger);
                        SaveData save = new SaveData();
                        save.saveData(people, getActivity().getApplicationContext());
                    }
                    ((EditText) getView().findViewById(R.id.passFirstName)).setText("");
                    ((EditText) getView().findViewById(R.id.passLastName)).setText("");
                    ((EditText) getView().findViewById(R.id.passDestination)).setText("");
                    ((EditText) getView().findViewById(R.id.passLayover)).setText("");
                }
            });
        }
    }
}
