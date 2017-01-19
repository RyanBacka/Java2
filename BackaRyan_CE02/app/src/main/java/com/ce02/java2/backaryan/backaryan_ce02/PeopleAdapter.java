// Ryan Backa
// Jav2 1612
// PeopleAdapter

package com.ce02.java2.backaryan.backaryan_ce02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ce02.java2.backaryan.backaryan_ce02.Objects.Attendant;
import com.ce02.java2.backaryan.backaryan_ce02.Objects.Passenger;
import com.ce02.java2.backaryan.backaryan_ce02.Objects.People;
import com.ce02.java2.backaryan.backaryan_ce02.Objects.Pilot;

import java.util.ArrayList;

/**
 * Created by ryankbacka on 11/23/16.
 */

public class PeopleAdapter extends BaseAdapter {
    private static final int ID_CONSTANT = 0x01000000;
    private ArrayList<People> people;
    private Context context;

    public PeopleAdapter(Context _context, ArrayList<People> _people){
        people = _people;
        context = _context;
    }

    @Override
    public int getCount(){
        if (people != null) {
            return people.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position){
        if (people!=null && position>=0 && position<people.size()){
            return people.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        if (people != null && position >= 0 && position < people.size()) {
            return ID_CONSTANT + position;
        } else {
            return 0;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.people_layout,parent,false);
            holder.firstName = ((TextView)convertView.findViewById(R.id.listName));
            holder.destination = ((TextView)convertView.findViewById(R.id.listDestination));
            holder.finalContainer = ((TextView)convertView.findViewById(R.id.finalContainer));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        People people = (People) getItem(position);
        if(people instanceof Pilot){
            Pilot pilot = (Pilot)people;
            setFields(pilot.getName(),pilot.getDestination(),pilot.getLicenseNo(),holder);
        }else if(people instanceof Attendant){
            Attendant attendant = (Attendant)people;
            setFields(attendant.getName(),attendant.getDestination(),attendant.getEmployeeNo(),holder);
        }else if(people instanceof Passenger){
            Passenger passenger = (Passenger)people;
            setFields(passenger.getName(),passenger.getDestination(),passenger.getLayover(),holder);
        }else{
            return null;
        }
        return convertView;
    }

    private static class ViewHolder{
        TextView firstName;
        TextView destination;
        TextView finalContainer;
    }

    private void setFields(String _name, String _destination, String value4, ViewHolder holder){
        holder.firstName.setText(_name);
        holder.destination.setText(_destination);
        holder.finalContainer.setText(value4);
    }
}
