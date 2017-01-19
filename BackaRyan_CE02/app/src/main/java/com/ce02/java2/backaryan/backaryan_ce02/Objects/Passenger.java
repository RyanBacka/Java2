// Ryan Backa
// Jav2 1612
// Passenger

package com.ce02.java2.backaryan.backaryan_ce02.Objects;

/**
 * Created by ryankbacka on 11/23/16.
 */

public class Passenger extends People {
    String layover;

    public Passenger(String _firstName, String _lastName, String _destination, String _layover) {
        super(_firstName, _lastName, _destination);
        layover = _layover;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getDestination() {
        return destination;
    }

    public String getLayover() {
        return layover;
    }
}
