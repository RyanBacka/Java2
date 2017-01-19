// Ryan Backa
// JAV2-1612
// DetailsActivity

package com.ce08.java2.backaryan.backaryan_ce08.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.ce08.java2.backaryan.backaryan_ce08.R;
import com.ce08.java2.backaryan.backaryan_ce08.fragments.DetailsFragment;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        DetailsFragment fragment = DetailsFragment.newInstance();
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
