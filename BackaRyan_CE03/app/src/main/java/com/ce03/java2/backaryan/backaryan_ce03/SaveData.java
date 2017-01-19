//Ryan Backa
//Java 1612
//SaveData

package com.ce03.java2.backaryan.backaryan_ce03;

import android.content.Context;

import com.ce03.java2.backaryan.backaryan_ce03.Objects.Music;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by ryankbacka on 10/31/16.
 */

public class SaveData {

    public void saveData(ArrayList<Music> music, Context context){
        try {
            FileOutputStream fos = context.openFileOutput("music",Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(music);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}