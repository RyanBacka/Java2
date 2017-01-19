// Ryan Backa
// Jav2 1612
// Attendant

package com.ce02.java2.backaryan.backaryan_ce02.Objects;

/**
 * Created by ryankbacka on 11/23/16.
 */

public class Attendant extends People {
    String employeeNo;

    public Attendant(String _firstName, String _lastName, String _destination, String _employeeNo) {
        super(_firstName, _lastName, _destination);
        employeeNo = _employeeNo;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getDestination() {
        return destination;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }
}
