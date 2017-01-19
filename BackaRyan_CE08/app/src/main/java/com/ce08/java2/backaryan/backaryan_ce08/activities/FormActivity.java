// Ryan Backa
// JAV2-1612
// FormActivity

package com.ce08.java2.backaryan.backaryan_ce08.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.ce08.java2.backaryan.backaryan_ce08.R;
import com.ce08.java2.backaryan.backaryan_ce08.fragments.FormFragment;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Intent intent = getIntent();

        FormFragment fragment = FormFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.form_container, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.form_menu, menu);
        return true;
    }
}
