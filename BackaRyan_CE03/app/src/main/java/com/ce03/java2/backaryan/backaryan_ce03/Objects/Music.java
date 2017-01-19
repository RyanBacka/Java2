//Ryan Backa
//Java 1612
//Music

package com.ce03.java2.backaryan.backaryan_ce03.Objects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ryankbacka on 10/31/16.
 */

public class Music implements Serializable{
    private String name;
    private ArrayList<String> genres;
    private int followers;
    private int popularity;

    public Music(String _name, ArrayList<String> _genres, int _followers, int _popularity){
        name = _name;
        genres = _genres;
        followers = _followers;
        popularity = _popularity;
    }

    public String getName(){
        return name;
    }

    public String getGenres(){
        String genreList="";
        for(int i=0; i<genres.size(); i++){
            if(genreList.isEmpty()){
                genreList = genres.get(i)+", ";
            } else {
                genreList = genreList+genres.get(i)+", ";
            }
        }
        return genreList;
    }

    public int getFollowers(){
        return followers;
    }

    public int getPopularity(){
        return popularity;
    }
}
