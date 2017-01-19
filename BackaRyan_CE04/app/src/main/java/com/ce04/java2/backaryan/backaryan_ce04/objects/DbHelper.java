//Ryan Backa
//Jav2-1612
//DbHelper

package com.ce04.java2.backaryan.backaryan_ce04.objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ryankbacka on 11/2/16.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Employees";
    private static final int VERSION = 1;
    private static final String TAG = "DbHelper";
    private static final ArrayList<Employee> results = new ArrayList<>();

    private DbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public static void submitClick(final Context context, final String _firstName, final String _lastName, final int _employeeNum, final String _hireDate, final String _status) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ContentValues contentValues = new ContentValues();
                contentValues.put(SubmitClickContract.FIRST_NAME, _firstName);
                contentValues.put(SubmitClickContract.LAST_NAME, _lastName);
                contentValues.put(SubmitClickContract.EMPLOYEE_NUMBER, _employeeNum);
                contentValues.put(SubmitClickContract.HIRE_DATE, _hireDate);
                contentValues.put(SubmitClickContract.EMPLOYMENT_STATUS, _status);
                DbHelper dbHelper = new DbHelper(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Long row = db.insert(SubmitClickContract.TABLE_NAME, null, contentValues);
                Log.d(TAG, "Successfully inserted row: " + row);
                db.close();
            }
        });
        thread.start();
    }

    public static void getEmployees(final Context context, final String info, final String order) {
        //Thread thread = new Thread(new Runnable() {
        // @Override
            //public void run() {
                results.clear();
                String choices = "";
                if (info.equalsIgnoreCase("Employment Status") && order.equalsIgnoreCase("Ascending")) {
                    choices = SubmitClickContract.EMPLOYMENT_STATUS + " ASC";
                } else if (info.equalsIgnoreCase("Employment Status") && order.equalsIgnoreCase("Descending")) {
                    choices = SubmitClickContract.EMPLOYMENT_STATUS + " DESC";
                } else if (info.equalsIgnoreCase("Hire Date") && order.equalsIgnoreCase("Ascending")) {
                    choices = SubmitClickContract.HIRE_DATE + " ASC";
                } else {
                    choices = SubmitClickContract.HIRE_DATE + " DESC";
                }
                DbHelper dbHelper = new DbHelper(context);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String[] columns = {SubmitClickContract.FIRST_NAME, SubmitClickContract.LAST_NAME,
                        SubmitClickContract.EMPLOYEE_NUMBER, SubmitClickContract.HIRE_DATE,
                        SubmitClickContract.EMPLOYMENT_STATUS};
                Cursor cursor = db.query(
                        SubmitClickContract.TABLE_NAME, //Table
                        columns, //Columns
                        null, //Selection
                        null,//Selection Arguments
                        null,//groupBy
                        null,//Having
                        choices,//orderBy
                        null//limit
                );

                int firstNameIndex = cursor.getColumnIndex(SubmitClickContract.FIRST_NAME);
                int lastnameIndex = cursor.getColumnIndex(SubmitClickContract.LAST_NAME);
                int employeeNumIndex = cursor.getColumnIndex(SubmitClickContract.EMPLOYEE_NUMBER);
                int hireDateIndex = cursor.getColumnIndex(SubmitClickContract.HIRE_DATE);
                int employmentStatusIndex = cursor.getColumnIndex(SubmitClickContract.EMPLOYMENT_STATUS);

                while (cursor.moveToNext()) {
                    Employee employee = new Employee(
                            cursor.getString(firstNameIndex),
                            cursor.getString(lastnameIndex),
                            cursor.getInt(employeeNumIndex),
                            cursor.getString(hireDateIndex),
                            cursor.getString(employmentStatusIndex)
                    );
                    results.add(employee);
                }
                Log.d(TAG, results.toString());
                cursor.close();
                db.close();
            //}
        //});
        //thread.start();
    }

    public static void deleteEntry(final Context context, final int employeeNum){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete(SubmitClickContract.TABLE_NAME, SubmitClickContract.EMPLOYEE_NUMBER + "=" + employeeNum, null);
    }

    public static void deleteAll(final Context context){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.execSQL("delete from " + SubmitClickContract.TABLE_NAME);
    }
    public static ArrayList<Employee> getEmployeesArray() {
        return results;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO: Create Table
        Log.d(TAG, "Creating Table:\n" + SubmitClickContract.CREATE_STATEMENT);
        db.execSQL(SubmitClickContract.CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Called when version changes
    }
}
