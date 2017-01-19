// Ryan Backa
// Java2 1612
// MainActivity

package com.ce02.java2.backaryan.backaryan_ce02;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.ce02.java2.backaryan.backaryan_ce02.Fragments.AttendantFragment;
import com.ce02.java2.backaryan.backaryan_ce02.Fragments.PassengerFragment;
import com.ce02.java2.backaryan.backaryan_ce02.Fragments.PeopleFragment;
import com.ce02.java2.backaryan.backaryan_ce02.Fragments.PilotFragment;
import com.ce02.java2.backaryan.backaryan_ce02.Objects.Attendant;
import com.ce02.java2.backaryan.backaryan_ce02.Objects.Passenger;
import com.ce02.java2.backaryan.backaryan_ce02.Objects.Pilot;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner selectForm = (Spinner) findViewById(R.id.formSelect);
        if(selectForm!=null){
            selectForm.setOnItemSelectedListener(this);
        }
        ImageButton refresh = (ImageButton) findViewById(R.id.refresh);
        if(refresh!=null){
            refresh.setOnClickListener(this);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(position){
            case 0:
                Fragment pilotFragment = PilotFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.formContainer,pilotFragment)
                        .commit();
                break;
            case 1:
                Fragment attendantFragment = AttendantFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.formContainer,attendantFragment)
                        .commit();
                break;
            case 2:
                Fragment passengerFragment = PassengerFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.formContainer,passengerFragment)
                        .commit();
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        Fragment fragment = PeopleFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.listLayout,fragment)
                .commit();
    }
}
