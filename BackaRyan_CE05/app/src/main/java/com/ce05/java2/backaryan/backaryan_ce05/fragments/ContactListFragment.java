package com.ce05.java2.backaryan.backaryan_ce05.fragments;


import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ce05.java2.backaryan.backaryan_ce05.ContactAdapter;
import com.ce05.java2.backaryan.backaryan_ce05.GetContacts;
import com.ce05.java2.backaryan.backaryan_ce05.R;
import com.ce05.java2.backaryan.backaryan_ce05.objects.Contact;
import com.ce05.java2.backaryan.backaryan_ce05.objects.ContactSelectionListener;

import java.util.ArrayList;

public class ContactListFragment extends ListFragment {

    private ContactSelectionListener mListener;
    private ArrayList<Contact> contacts;

    public ContactListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ContactSelectionListener){
            mListener = (ContactSelectionListener) context;
        } else {
            throw  new IllegalArgumentException("Containing context does not implement ContactSelectionListener");
        }
    }

    public static ContactListFragment newInstance(){
        ContactListFragment frag = new ContactListFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contact_list, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ContentResolver cr = getActivity().getContentResolver();
        GetContacts getContacts = new GetContacts(cr);
        contacts=getContacts.readContacts();
        ContactAdapter adapter = new ContactAdapter(getContext(),contacts);
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Contact chosen = (Contact) l.getItemAtPosition(position);
        if(chosen!=null){
            mListener.populateDetailsWithContact(chosen.getId());
        }
    }


}
