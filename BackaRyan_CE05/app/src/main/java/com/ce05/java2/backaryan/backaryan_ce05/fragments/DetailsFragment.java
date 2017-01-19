// Ryan Backa
// JAV2 - 1612
// DetailsFragment

package com.ce05.java2.backaryan.backaryan_ce05.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ce05.java2.backaryan.backaryan_ce05.GetContacts;
import com.ce05.java2.backaryan.backaryan_ce05.R;

public class DetailsFragment extends Fragment {

    private static final String ID_PARAM = "id";

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(String id){
        DetailsFragment frag = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ID_PARAM,id);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageView photo = ((ImageView)getActivity().findViewById(R.id.contactPhoto));
        TextView name = ((TextView)getActivity().findViewById(R.id.contactFullName));
        TextView number = ((TextView)getActivity().findViewById(R.id.contactPhoneNumbers));
        TextView email = ((TextView)getActivity().findViewById(R.id.contactEmailAddresses));
        GetContacts getContacts = new GetContacts(getActivity().getContentResolver());
        String id = getArguments().getString(ID_PARAM);
        name.setText(getContacts.getName(id));
        number.setText(getContacts.getNumber(id));
        email.setText(getContacts.getEMailAddress(id));
        photo.setImageBitmap(getContacts.getPhoto(id));
    }
}
