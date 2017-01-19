//Ryan Backa
//Java 1612
//ReadData

package com.ce03.java2.backaryan.backaryan_ce03;

import android.content.Context;

import com.ce03.java2.backaryan.backaryan_ce03.Objects.Music;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by ryankbacka on 10/31/16.
 */

public class ReadData {

    public ArrayList<Music> readData(Context context){
        ArrayList<Music> medias = new ArrayList<>();
        try {
            FileInputStream fis = context.getApplicationContext().openFileInput("music");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            ois.close();
            fis.close();
            if(obj instanceof ArrayList<?>){
                ArrayList<?> list = (ArrayList<?>) obj;
                if(!list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++){
                        Object myObj = list.get(i);
                        if(myObj instanceof Music){
                            Music media = (Music) myObj;
                            medias.add(media);
                        }
                    }
                }
            }
        } catch (IOException |ClassNotFoundException e) {
            e.printStackTrace();
        }
        return medias;
    }
}