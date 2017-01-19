// Ryan Backa
// JAV2 - 1612
// ReadData

package com.ce07.java2.backaryan.backaryan_ce07;

import android.content.Context;

import com.ce07.java2.backaryan.backaryan_ce07.objects.Person;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReadData {

    public ArrayList<Person> readData(Context context) {
        ArrayList<Person> people = new ArrayList<>();
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
                        if (myObject instanceof Person) {
                            Person person = (Person) myObject;
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
