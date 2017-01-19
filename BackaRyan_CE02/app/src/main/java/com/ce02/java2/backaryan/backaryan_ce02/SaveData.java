// Ryan Backa
// Jav2 1612
// Save Data

package com.ce02.java2.backaryan.backaryan_ce02;

import android.content.Context;

import com.ce02.java2.backaryan.backaryan_ce02.Objects.People;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by ryankbacka on 11/23/16.
 */

public class SaveData {

    public void saveData(ArrayList<People> people, Context context){
        try {
            FileOutputStream fos = context.openFileOutput("people", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(people);
            oos.close();
            fos.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
