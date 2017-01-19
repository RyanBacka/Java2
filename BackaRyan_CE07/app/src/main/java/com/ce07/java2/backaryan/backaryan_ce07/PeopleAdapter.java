// Ryan Backa
// JAV2 - 1612
// PeopleAdapter

package com.ce07.java2.backaryan.backaryan_ce07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ce07.java2.backaryan.backaryan_ce07.objects.Person;

import java.util.ArrayList;

public class PeopleAdapter extends BaseAdapter {
    private static final int ID_CONSTANT = 0x01000000;
    private ArrayList<Person> people;
    private Context context;

    public PeopleAdapter(Context _context, ArrayList<Person> _people){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_layout,parent,false);
            holder.name = ((TextView)convertView.findViewById(R.id.listName));
            holder.age = ((TextView)convertView.findViewById(R.id.listAge));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Person person = (Person) getItem(position);
        holder.name.setText(person.getName());
        holder.age.setText(person.getAge()+"");
        return convertView;
    }

    private static class ViewHolder{
        TextView name;
        TextView age;
    }
}

