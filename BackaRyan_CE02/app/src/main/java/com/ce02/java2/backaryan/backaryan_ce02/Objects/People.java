// Ryan Backa
// Java2 1612
// People

package com.ce02.java2.backaryan.backaryan_ce02.Objects;

import java.io.Serializable;

/**
 * Created by ryankbacka on 11/23/16.
 */

public class People implements Serializable {

    String firstName;
    String lastName;
    String destination;

    public People(String _firstName, String _lastName, String _destination) {
        firstName = _firstName;
        lastName = _lastName;
        destination = _destination;
    }
}
