//Ryan Backa
//Java 1612
//OptionsFragment

package com.ce03.java2.backaryan.backaryan_ce03.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ce03.java2.backaryan.backaryan_ce03.MainActivity;
import com.ce03.java2.backaryan.backaryan_ce03.Objects.ListSelectionListener;
import com.ce03.java2.backaryan.backaryan_ce03.Objects.Music;
import com.ce03.java2.backaryan.backaryan_ce03.R;
import com.ce03.java2.backaryan.backaryan_ce03.ReadData;

import java.util.ArrayList;

public class OptionsFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private static final String ARG_PARAM1 = "names";
    private ListSelectionListener selectionListener;

    public OptionsFragment() {
        // Required empty public constructor
    }

    public static OptionsFragment newInstance(ArrayList<String> names) {
        OptionsFragment fragment = new OptionsFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, names);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_options, container, false);
        final ListView list = (ListView) view.findViewById(android.R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReadData readData = new ReadData();
                ArrayList<Music> music = readData.readData(getContext());
                String selectedFromList = list.getItemAtPosition(position).toString();
                Music selected = findMusic(selectedFromList, music);
                if(selected!=null) {
                    selectionListener.sendSelectedToFragment(selected);
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<String> names = getArguments().getStringArrayList(ARG_PARAM1);
        ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);

    }



    Music findMusic(String name, ArrayList<Music> music){
        Music selected = music.get(1);
        for(Music m : music){
            if(m.getName().equalsIgnoreCase(name)){
                selected = m;
            } else {
                selected = null;
            }
        }
        return selected;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
