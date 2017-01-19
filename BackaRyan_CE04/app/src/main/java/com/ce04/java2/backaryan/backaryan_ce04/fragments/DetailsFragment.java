//Ryan Backa
//Jav2-1612
//DetailsFragment

package com.ce04.java2.backaryan.backaryan_ce04.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ce04.java2.backaryan.backaryan_ce04.R;
import com.ce04.java2.backaryan.backaryan_ce04.objects.DbHelper;
import com.ce04.java2.backaryan.backaryan_ce04.objects.Employee;
import com.ce04.java2.backaryan.backaryan_ce04.objects.EmployeeSelectionListener;

import java.io.Serializable;

public class DetailsFragment extends Fragment implements View.OnClickListener {

    public static final String EMPLOYEE = "Employee";

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(Employee employee) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(EMPLOYEE, employee);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        (rootView.findViewById(R.id.deleteButton)).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Employee employee = (Employee) getArguments().getSerializable(EMPLOYEE);
        if (employee!=null) {
            ((TextView) getActivity().findViewById(R.id.firstNameView)).setText(employee.getFirstName());
            ((TextView) getActivity().findViewById(R.id.lastNameView)).setText(employee.getLastName());
            ((TextView) getActivity().findViewById(R.id.hireDateView)).setText(employee.getHireDate());
            ((TextView) getActivity().findViewById(R.id.employeeNumView)).setText(String.valueOf(employee.getEmployeeNum()));
            ((TextView) getActivity().findViewById(R.id.employmentStatusView)).setText(employee.getEmplomentStatus());
        }

    }


    @Override
    public void onClick(View v) {
        final Employee employee = (Employee) getArguments().getSerializable(EMPLOYEE);
        new AlertDialog.Builder(getContext())
                .setTitle("Delete Entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(employee!=null) {
                            DbHelper.deleteEntry(getContext(), employee.getEmployeeNum());
                        }
                        EmployeeListFragment listFragment = EmployeeListFragment.newInstance();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, listFragment)
                                .commit();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EmployeeListFragment listFragment = EmployeeListFragment.newInstance();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, listFragment)
                                .commit();
                    }
                })
                .show();
    }
}
