//Ryan Backa
//Jav2-1612
//EmployeeAdapter

package com.ce04.java2.backaryan.backaryan_ce04;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ce04.java2.backaryan.backaryan_ce04.objects.Employee;

import java.util.ArrayList;

/**
 * Created by ryankbacka on 11/2/16.
 */

public class EmployeeAdapter extends BaseAdapter {

    private static final int ID_CONSTANT = 0x01000000;
    private static final String TAG = "EmployeeAdapter";
    private ArrayList<Employee> employees;
    private Context context;
    private String choice;

    public EmployeeAdapter(Context _context, ArrayList<Employee> _employees, String _choice) {
        employees = _employees;
        context = _context;
        choice = _choice;
    }

    @Override
    public int getCount() {
        if (employees != null) {
            return employees.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (employees != null && position >= 0 && position < employees.size()) {
            return employees.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        if (employees != null && position >= 0 && position < employees.size()) {
            return ID_CONSTANT + position;
        } else {
            return 0;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
            holder.firstName = ((TextView) convertView.findViewById(R.id.firstNameList));
            holder.lastName = ((TextView) convertView.findViewById(R.id.lastNameList));
            holder.choice = ((TextView) convertView.findViewById(R.id.choice));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Employee employee = (Employee) getItem(position);
        Log.d(TAG,choice);
        if (choice.equalsIgnoreCase("Employment Status")) {
            setFields(employee.getFirstName(), employee.getLastName(), employee.getEmplomentStatus(), holder);
        } else if (choice.equalsIgnoreCase("Hire Date")) {
            setFields(employee.getFirstName(), employee.getLastName(), employee.getHireDate(), holder);
        } else {
            return null;
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView firstName;
        TextView lastName;
        TextView choice;
    }

    private void setFields(String first, String last, String choice, ViewHolder holder) {
        holder.firstName.setText(first);
        holder.lastName.setText(last);
        holder.choice.setText(choice);
    }
}
