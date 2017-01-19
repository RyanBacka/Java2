// Ryan Backa
// JAV2 - 1612
// SaveData

package com.ce07.java2.backaryan.backaryan_ce07;

import android.content.Context;

import com.ce07.java2.backaryan.backaryan_ce07.objects.Person;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveData {
    public void saveData(ArrayList<Person> people, Context context){
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
