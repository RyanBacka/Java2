// Ryan Backa
// JAV2 - 1612
// MainActivity

package com.fullsail.android.jav2final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.fullsail.android.jav2final.data.Politician;
import com.fullsail.android.jav2final.fragment.PoliticianListFragment;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener, PoliticianListFragment.PoliticianSelectedListener {

    private static final int REQUEST_VIEW_POLITICIAN = 0x03001;
    private static final int REQUEST_SETTINGS = 0x03002;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner)findViewById(R.id.spinner_selection);
        String[] choices = new String[] { getString(R.string.all), getString(R.string.favorites) };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, choices);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_VIEW_POLITICIAN || requestCode == REQUEST_SETTINGS) {
            PoliticianListFragment frag = (PoliticianListFragment)getFragmentManager()
                    .findFragmentByTag(PoliticianListFragment.TAG);
            if(frag != null) {
                frag.startTask();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String choice = (String)parent.getAdapter().getItem(position);
        int filter = PoliticianListFragment.FILTER_ALL;

        if(choice.equals(getString(R.string.all))) {
            filter = PoliticianListFragment.FILTER_ALL;
        } else if(choice.equals(getString(R.string.favorites))) {
            filter = PoliticianListFragment.FILTER_FAVORITES;
        }

        PoliticianListFragment frag = PoliticianListFragment.newInstance(filter);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, frag, PoliticianListFragment.TAG)
                .commit();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void politicianSelected(Politician politician) {
        Intent intent = new Intent(this, PoliticianActivity.class);
        intent.putExtra(PoliticianActivity.EXTRA_POLITICIAN,politician);
        startActivityForResult(intent, REQUEST_VIEW_POLITICIAN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }
}
