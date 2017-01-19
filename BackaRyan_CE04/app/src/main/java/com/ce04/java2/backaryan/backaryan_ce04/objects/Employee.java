//Ryan Backa
//Jav2-1612
//Employee

package com.ce04.java2.backaryan.backaryan_ce04.objects;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ryankbacka on 11/2/16.
 */

public class Employee implements Serializable{
    String firstName;
    String lastName;
    int employeeNum;
    String hireDate;
    String emplomentStatus;

    public Employee(String _firstName, String _lastName, int _employeeNum, String _hireDate, String _emplomentStatus){
        firstName = _firstName;
        lastName = _lastName;
        employeeNum = _employeeNum;
        hireDate = _hireDate;
        emplomentStatus = _emplomentStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getEmployeeNum() {
        return employeeNum;
    }

    public String getHireDate() {
        return hireDate;
    }

    public String getEmplomentStatus() {
        return emplomentStatus;
    }
}
