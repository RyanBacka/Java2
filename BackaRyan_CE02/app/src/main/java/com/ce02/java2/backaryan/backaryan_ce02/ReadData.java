// Ryan Backa
// Jav2 1612
// ReadData

package com.ce02.java2.backaryan.backaryan_ce02;

import android.content.Context;

import com.ce02.java2.backaryan.backaryan_ce02.Objects.People;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by ryankbacka on 11/23/16.
 */

public class ReadData {

    public ArrayList<People> readData(Context context) {
        ArrayList<People> people = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput("people");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object object = ois.readObject();
            ois.close();
            fis.close();
            if (object instanceof ArrayList<?>) {
                ArrayList<?> list = (ArrayList) object;
                if (!list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        Object myObject = list.get(i);
                        if (myObject instanceof People) {
                            People person = (People) myObject;
                            people.add(person);
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return people;
    }
}
