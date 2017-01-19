// Ryan Backa
// Jav2 1612
// Pilot

package com.ce02.java2.backaryan.backaryan_ce02.Objects;

/**
 * Created by ryankbacka on 11/23/16.
 */

public class Pilot extends People {
    private String licenseNo;

    public Pilot(String _firstName, String _lastName, String _destination, String _liscenceNo) {
        super(_firstName, _lastName, _destination);
        licenseNo = _liscenceNo;
    }


    public String getName() {
        return firstName + " " + lastName;
    }

    public String getDestination() {
        return destination;
    }

    public String getLicenseNo() {
        return licenseNo;
    }
}