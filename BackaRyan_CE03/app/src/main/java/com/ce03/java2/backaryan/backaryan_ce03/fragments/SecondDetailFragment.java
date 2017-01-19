//Ryan Backa
//Java 1612
//SecondDetailFragment

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

public class SecondDetailFragment extends Fragment implements ListSelectionListener {

    Music music;
    public SecondDetailFragment() {
        // Required empty public constructor
    }

    public static SecondDetailFragment newInstance() {
        SecondDetailFragment fragment = new SecondDetailFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (music!=null){
            TextView name = (TextView) getActivity().findViewById(R.id.secondArtist);
            TextView genres = (TextView) getActivity().findViewById(R.id.secondGenres);
            TextView followers = (TextView) getActivity().findViewById(R.id.secondFollowers);
            TextView popularity = (TextView) getActivity().findViewById(R.id.secondPopularity);
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
