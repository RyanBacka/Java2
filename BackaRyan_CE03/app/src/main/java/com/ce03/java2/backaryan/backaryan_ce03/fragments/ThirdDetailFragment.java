//Ryan Backa
//Java 1612
//ThirdDetailFragment
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

public class ThirdDetailFragment extends Fragment implements ListSelectionListener {

    Music music;

    public ThirdDetailFragment() {
        // Required empty public constructor
    }

    public static ThirdDetailFragment newInstance() {
        ThirdDetailFragment fragment = new ThirdDetailFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (music!=null){
            TextView name = (TextView) getActivity().findViewById(R.id.thirdArtist);
            TextView genres = (TextView) getActivity().findViewById(R.id.thirdGenre);
            TextView followers = (TextView) getActivity().findViewById(R.id.thirdFollowers);
            TextView popularity = (TextView) getActivity().findViewById(R.id.thirdPopularity);
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
