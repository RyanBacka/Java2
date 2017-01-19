//Ryan Backa
//Jav2-1612
//MainActivity

package com.ce04.java2.backaryan.backaryan_ce04;

import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ce04.java2.backaryan.backaryan_ce04.fragments.DetailsFragment;
import com.ce04.java2.backaryan.backaryan_ce04.fragments.EmployeeListFragment;
import com.ce04.java2.backaryan.backaryan_ce04.fragments.FormFragment;
import com.ce04.java2.backaryan.backaryan_ce04.fragments.MyPreferenceFragment;
import com.ce04.java2.backaryan.backaryan_ce04.objects.Employee;
import com.ce04.java2.backaryan.backaryan_ce04.objects.EmployeeSelectionListener;

public class MainActivity extends AppCompatActivity implements EmployeeSelectionListener, View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EmployeeListFragment listFrag = EmployeeListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, listFrag)
                .commit();

    }

    @Override
    public void populateDetailswithEmployee(Employee employee) {
        Log.d(TAG,employee.getEmployeeNum()+"");
        DetailsFragment detailFrag = DetailsFragment.newInstance(employee);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,detailFrag)
                .commit();
    }

    @Override
    public void onClick(View v) {

        MyPreferenceFragment prefFrag = new MyPreferenceFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.container, prefFrag)
                .addToBackStack(null)
                .commit();
        Button settings = (Button)findViewById(R.id.settingsButton);
    }
}
