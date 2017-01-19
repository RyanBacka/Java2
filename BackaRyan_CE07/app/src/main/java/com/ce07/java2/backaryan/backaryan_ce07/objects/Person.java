// Ryan Backa
// JAV2 - 1612
// Person

package com.ce07.java2.backaryan.backaryan_ce07.objects;

import java.io.Serializable;


public class Person implements Serializable {
    private String firstName;
    private String lastName;
    private String birthday;
    private int age;

    public Person(String _first, String _last, String _birthday, int _age){
        firstName =_first;
        lastName = _last;
        birthday = _birthday;
        age = _age;
    }

    public String getName() {
        return firstName +" "+lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getAge() {
        return age;
    }
}
