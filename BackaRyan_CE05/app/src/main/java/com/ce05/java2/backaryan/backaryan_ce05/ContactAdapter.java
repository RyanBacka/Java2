package com.ce05.java2.backaryan.backaryan_ce05;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ce05.java2.backaryan.backaryan_ce05.objects.Contact;

import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Created by ryankbacka on 12/5/16.
 */

public class ContactAdapter extends BaseAdapter {

    private static final int ID_CONSTANT = 0x01000000;
    private  static final String TAG = "ContactAdapter";
    private ArrayList<Contact> contacts;
    private Context context;

    public ContactAdapter(Context _context, ArrayList<Contact> _contacts){
        contacts = _contacts;
        context = _context;
    }

    @Override
    public int getCount() {
        if(contacts!=null){
            return contacts.size();
        } else{
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (contacts != null && position >= 0 && position < contacts.size()) {
            return contacts.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        if (contacts != null && position >= 0 && position < contacts.size()) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_layout,parent,false);
            holder.name = ((TextView)convertView.findViewById(R.id.contactsName));
            holder.phoneNumber = ((TextView)convertView.findViewById(R.id.contactsPhoneNum));
            holder.picture = ((ImageView)convertView.findViewById(R.id.contactsImage));
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }
        Contact contact = (Contact) getItem(position);
        holder.name.setText(contact.getName());
        holder.phoneNumber.setText(contact.getPhoneNum());
        if(contact.getPicture()!=null) {
            holder.picture.setImageBitmap(contact.getPicture());
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView phoneNumber;
        ImageView picture;
    }
}
