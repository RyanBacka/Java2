//Ryan Backa
//Java 1612
//FirstDetailFragment

package com.ce03.java2.backaryan.backaryan_ce03.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ce03.java2.backaryan.backaryan_ce03.Objects.ListSelectionListener;
import com.ce03.java2.backaryan.backaryan_ce03.Objects.Music;
import com.ce03.java2.backaryan.backaryan_ce03.R;

public class FirstDetailFragment extends Fragment implements ListSelectionListener{

    Music music;

    public FirstDetailFragment() {
        // Required empty public constructor
    }

    public static FirstDetailFragment newInstance() {
        FirstDetailFragment fragment = new FirstDetailFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (music!=null){
            TextView name = (TextView) getActivity().findViewById(R.id.firstArtist);
            TextView genres = (TextView) getActivity().findViewById(R.id.firstGenres);
            TextView followers = (TextView) getActivity().findViewById(R.id.firstFollowers);
            TextView popularity = (TextView) getActivity().findViewById(R.id.firstPopularity);
            name.setText(music.getName());
            genres.setText(music.getGenres());
            followers.setText(music.getFollowers());
            popularity.setText(music.getPopularity());
        }
    }


    @Override
    public void sendSelectedToFragment(Music selected) {
        music = selected;
    }
}
