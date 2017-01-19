//Ryan Backa
//Jav2-1612
//SubmitClickContract

package com.ce04.java2.backaryan.backaryan_ce04.objects;

import android.provider.BaseColumns;

/**
 * Created by ryankbacka on 11/2/16.
 */

public class SubmitClickContract implements BaseColumns {

    public static final String TABLE_NAME = "employeeTable";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMPLOYEE_NUMBER = "employeeNumber";
    public static final String HIRE_DATE = "hireDate";
    public static final String EMPLOYMENT_STATUS = "employmentStatus";


    /* CREATE TABLE IF NOT EXISTS employeeTable (_id INTEGER PRIMARY KEY AUTOINCREMENT,
    firstName TEXT NOT NULL, lastName TEXT NOT NULL,
    employeeNumber INTEGER NOT NULL, hireDate DATETIME NOT NULL,
    employmentStatus TEXT NOT NULL );
     */
    public static final String CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FIRST_NAME + " TEXT NOT NULL, " +
            LAST_NAME + " TEXT NOT NULL, " +
            EMPLOYEE_NUMBER + " INTEGER NOT NULL, " +
            HIRE_DATE + " TEXT NOT NULL, " +
            EMPLOYMENT_STATUS + " TEXT NOT NULL " +
            ");";
}
