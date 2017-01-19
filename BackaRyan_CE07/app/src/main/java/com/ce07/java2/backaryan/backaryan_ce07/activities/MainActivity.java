// Ryan Backa
// JAV2 - 1612
// MainActivity

package com.ce07.java2.backaryan.backaryan_ce07.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ce07.java2.backaryan.backaryan_ce07.R;
import com.ce07.java2.backaryan.backaryan_ce07.fragments.DisplayDataFragment;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayDataFragment fragment = DisplayDataFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.listContainer,fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.data_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setContentView(R.layout.activity_main);
        DisplayDataFragment fragment = DisplayDataFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.listContainer,fragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this,FormActivity.class);
        startActivityForResult(intent,2);
        setContentView(R.layout.activity_form);
        return super.onOptionsItemSelected(item);
    }
}
