// Ryan Backa
// JAV2 - 1612
// MainActivity

package com.fullsail.android.jav2ce09starter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.Snackbar;

import com.fullsail.android.jav2ce09starter.fragment.PersonListFragment;
import com.fullsail.android.jav2ce09starter.object.Person;
import com.fullsail.android.jav2ce09starter.util.PersonUtil;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends AppCompatActivity implements
        PersonListFragment.OnPersonInteractionListener, OnMenuTabClickListener {

    private static final int REQUEST_FORM = 0x01001;
    private static final String TAG = "MainActivity";

    private int mCurrentFilter;
    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomBar = BottomBar.attach(this, savedInstanceState);
            bottomBar.setItems(R.menu.bottom_navigation_main);
        bottomBar.setOnMenuTabClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        }

        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.action_add);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivityForResult(intent, REQUEST_FORM);
            }
        });

        // Assigning the default filter.
        mCurrentFilter = PersonListFragment.FILTER_ALL;

        // Adding our list fragment one time only.
        if(savedInstanceState == null) {
            PersonListFragment fragment = PersonListFragment.newInstance(mCurrentFilter);
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment, PersonListFragment.TAG)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Only refresh the list if a save operation was successful.
        if(requestCode == REQUEST_FORM && resultCode == RESULT_OK) {
            refreshList();
        }
    }

    @Override
    public void onPersonClicked(Person p) {
        Snackbar.make(findViewById(android.R.id.content),p.getFullName(),Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onPersonLongClicked(final Person p) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.confirm_delete)
                .setMessage(R.string.confirm_delete_message)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PersonUtil.deletePerson(MainActivity.this, p);
                        refreshList();
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    /**
     * Refreshes the current list fragment with the most recent filter.
     */
    private void refreshList() {
        PersonListFragment fragment = (PersonListFragment) getFragmentManager()
                .findFragmentByTag(PersonListFragment.TAG);
        if(fragment != null) {
            fragment.refresh(mCurrentFilter);
        }
    }

    @Override
    public void onMenuTabSelected(@IdRes int menuItemId) {
        Log.d(TAG,menuItemId+"");
        if(menuItemId==R.id.show_all){
            mCurrentFilter = 0;
            refreshList();
        } else if(menuItemId==R.id.under_thirty){
            mCurrentFilter = 1;
            refreshList();
        }else if(menuItemId==R.id.thirty_over){
            mCurrentFilter = 2;
            refreshList();
        }

    }

    @Override
    public void onMenuTabReSelected(@IdRes int menuItemId) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        bottomBar.onSaveInstanceState(outState);
    }
}
