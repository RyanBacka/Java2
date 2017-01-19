// Ryan Backa
// Jav2 1612
// AttendantFragment

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

import com.ce02.java2.backaryan.backaryan_ce02.Objects.People;
import com.ce02.java2.backaryan.backaryan_ce02.Objects.Pilot;
import com.ce02.java2.backaryan.backaryan_ce02.R;
import com.ce02.java2.backaryan.backaryan_ce02.ReadData;
import com.ce02.java2.backaryan.backaryan_ce02.SaveData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendantFragment extends Fragment {


    public AttendantFragment() {
        // Required empty public constructor
    }

    public static AttendantFragment newInstance(){
        AttendantFragment fragment = new AttendantFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attendant, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button submit = (Button) getView().findViewById(R.id.attenSubmitButton);
        if (submit != null) {
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String firstName = ((EditText) getView().findViewById(R.id.attenLastName)).getText().toString();
                    String lastName = ((EditText)getView().findViewById(R.id.attenLastName)).getText().toString();
                    String employeeNo = ((EditText)getView().findViewById(R.id.attenEmployeeNo)).getText().toString();
                    String destination = ((EditText)getView().findViewById(R.id.attenDestination)).getText().toString();
                    if(firstName.isEmpty()||lastName.isEmpty()||employeeNo.isEmpty()||destination.isEmpty()) {
                        Toast.makeText(getContext(), "One or more fields is empty", Toast.LENGTH_SHORT).show();
                    }else{
                        Pilot pilot = new Pilot(firstName,lastName,employeeNo,destination);
                        ReadData read = new ReadData();
                        ArrayList<People> people = read.readData(getActivity().getApplicationContext());
                        people.add(pilot);
                        SaveData save = new SaveData();
                        save.saveData(people, getActivity().getApplicationContext());
                    }
                    ((EditText) getView().findViewById(R.id.attenFirstName)).setText("");
                    ((EditText) getView().findViewById(R.id.attenLastName)).setText("");
                    ((EditText) getView().findViewById(R.id.attenEmployeeNo)).setText("");
                    ((EditText) getView().findViewById(R.id.attenDestination)).setText("");
                }
            });
        }
    }
}
