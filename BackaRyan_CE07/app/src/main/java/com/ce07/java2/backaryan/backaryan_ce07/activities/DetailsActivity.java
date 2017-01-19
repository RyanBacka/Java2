// Ryan Backa
// JAV2 - 1612
// DetailsActivity

package com.ce07.java2.backaryan.backaryan_ce07.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ce07.java2.backaryan.backaryan_ce07.R;
import com.ce07.java2.backaryan.backaryan_ce07.fragments.DetailsFragment;
import com.ce07.java2.backaryan.backaryan_ce07.objects.Person;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent callingIntent = getIntent();
        Person person = (Person) callingIntent.getSerializableExtra("PERSON");
        DetailsFragment fragment = DetailsFragment.newInstance(person);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.details_container,fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu, menu);
        return true;
    }
}
