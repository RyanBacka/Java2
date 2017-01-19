// Ryan Backa
// JAV2 - 1612
// FormFragment

package com.ce07.java2.backaryan.backaryan_ce07.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.ce07.java2.backaryan.backaryan_ce07.R;
import com.ce07.java2.backaryan.backaryan_ce07.ReadData;
import com.ce07.java2.backaryan.backaryan_ce07.SaveData;
import com.ce07.java2.backaryan.backaryan_ce07.objects.Person;

import java.util.ArrayList;

public class FormFragment extends Fragment {


    public FormFragment() {
        // Required empty public constructor
    }

    public static FormFragment newInstance(){
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
        String firstName = ((EditText)getView().findViewById(R.id.firstNameForm)).getText().toString();
        String lastName = ((EditText)getView().findViewById(R.id.lastNameForm)).getText().toString();
        String birthday = ((EditText)getView().findViewById(R.id.birthdayForm)).getText().toString();
        String ageString = ((EditText)getView().findViewById(R.id.ageForm)).getText().toString();
        if(firstName.isEmpty() || lastName.isEmpty() || birthday.isEmpty() || ageString.isEmpty()){
            Toast.makeText(getContext(), "One or more fields is empty", Toast.LENGTH_SHORT).show();
        } else {
            int age = Integer.parseInt(ageString);
            Person person = new Person(firstName,lastName,birthday,age);
            ReadData readData = new ReadData();
            ArrayList<Person> people = readData.readData(getActivity().getApplicationContext());
            people.add(person);
            SaveData saveData = new SaveData();
            saveData.saveData(people,getActivity().getApplicationContext());
            getActivity().finish();
        }


        return super.onOptionsItemSelected(item);
    }
}
