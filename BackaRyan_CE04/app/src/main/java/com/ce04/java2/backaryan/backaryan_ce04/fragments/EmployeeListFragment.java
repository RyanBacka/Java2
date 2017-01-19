//Ryan Backa
//Jav2-1612
//EmployeeListFragment

package com.ce04.java2.backaryan.backaryan_ce04.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.ce04.java2.backaryan.backaryan_ce04.EmployeeAdapter;
import com.ce04.java2.backaryan.backaryan_ce04.R;
import com.ce04.java2.backaryan.backaryan_ce04.objects.DbHelper;
import com.ce04.java2.backaryan.backaryan_ce04.objects.Employee;
import com.ce04.java2.backaryan.backaryan_ce04.objects.EmployeeSelectionListener;

import java.util.ArrayList;

public class EmployeeListFragment extends ListFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final String TAG = "EmployeeListFragment";
    private EmployeeSelectionListener mListener;

    public EmployeeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof EmployeeSelectionListener){
            mListener = (EmployeeSelectionListener) context;
        } else {
            throw new IllegalArgumentException("Containing context does not implement EmployeeSelectionListener");
        }

    }

    public static EmployeeListFragment newInstance() {
        EmployeeListFragment fragment = new EmployeeListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_employee_list, container, false);
        rootView.findViewById(R.id.addButton).setOnClickListener(this);
        ((Spinner)rootView.findViewById(R.id.infoSpinner)).setOnItemSelectedListener(this);
        ((Spinner)rootView.findViewById(R.id.orderSpinner)).setOnItemSelectedListener(this);
        Button settings = (Button)rootView.findViewById(R.id.settingsButton);
        if(settings!=null) {
            settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyPreferenceFragment prefFrag = new MyPreferenceFragment();
                    getActivity().getFragmentManager().beginTransaction()
                            .replace(R.id.container, prefFrag)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        FormFragment formFragment = FormFragment.newInstance();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, formFragment)
                .addToBackStack(null)
                .commit();

    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        String order = "";
        String info = "";
        if (spinner.getId() == R.id.infoSpinner) {
            info = spinner.getSelectedItem().toString();
            order = ((Spinner) getActivity().findViewById(R.id.orderSpinner)).getSelectedItem().toString();
        } else if (spinner.getId() == R.id.orderSpinner) {
            order = spinner.getSelectedItem().toString();
            info = ((Spinner) getActivity().findViewById(R.id.infoSpinner)).getSelectedItem().toString();
        }
        ArrayList<Employee> employees;
        DbHelper.getEmployees(getContext(), info, order);
        employees = DbHelper.getEmployeesArray();
        EmployeeAdapter adapter = new EmployeeAdapter(getContext(),employees,info);
        adapter.notifyDataSetChanged();
        setListAdapter(adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Employee chosen = (Employee) l.getItemAtPosition(position);
        if(chosen != null){
            mListener.populateDetailswithEmployee(chosen);
        }

    }

}
