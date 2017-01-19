//Ryan Backa
//Jav2-1612
//FormFragment

package com.ce04.java2.backaryan.backaryan_ce04.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.ce04.java2.backaryan.backaryan_ce04.R;
import com.ce04.java2.backaryan.backaryan_ce04.objects.DbHelper;

public class FormFragment extends Fragment implements AdapterView.OnClickListener {


    public FormFragment() {
        // Required empty public constructor
    }

    public static FormFragment newInstance() {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_form, container, false);
        rootView.findViewById(R.id.submitButton).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        String firstName = ((EditText) getActivity().findViewById(R.id.firstNameEdit)).getText().toString();
        String lastName = ((EditText) getActivity().findViewById(R.id.lastNameEdit)).getText().toString();
        String employeeId = ((EditText) getActivity().findViewById(R.id.employeeIdEdit)).getText().toString();
        String hireDate = ((EditText) getActivity().findViewById(R.id.hireDateEdit)).getText().toString();
        String employmentStatus = ((EditText) getActivity().findViewById(R.id.employmentStatusEdit)).getText().toString();

        if (firstName.isEmpty() || lastName.isEmpty() || employeeId.isEmpty() || hireDate.isEmpty() || employmentStatus.isEmpty()) {
            Toast.makeText(getContext(), "One or more fields are empty", Toast.LENGTH_SHORT).show();
        } else {
            //Todo: add data to SQLite database
            DbHelper.submitClick(getContext(), firstName, lastName, Integer.parseInt(employeeId), hireDate, employmentStatus);

            EmployeeListFragment listFragment = EmployeeListFragment.newInstance();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, listFragment)
                    .commit();
        }
    }

}
