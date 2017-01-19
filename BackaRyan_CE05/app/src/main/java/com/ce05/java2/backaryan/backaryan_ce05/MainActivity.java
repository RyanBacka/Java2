// Ryan Backa
// JAV2 - 1612
// MainActivity

package com.ce05.java2.backaryan.backaryan_ce05;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ce05.java2.backaryan.backaryan_ce05.fragments.ContactListFragment;
import com.ce05.java2.backaryan.backaryan_ce05.fragments.DetailsFragment;
import com.ce05.java2.backaryan.backaryan_ce05.objects.ContactSelectionListener;

public class MainActivity extends AppCompatActivity implements ContactSelectionListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT == 23) {
            int hasPermission = checkSelfPermission(Manifest.permission.READ_CONTACTS);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                int REQUEST_CODE_ASK_PERMISSION = 123;
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE_ASK_PERMISSION);
            }
        }

        ContactListFragment listFragment = ContactListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.list_container, listFragment)
                .commit();

    }

    @Override
    public void populateDetailsWithContact(String id) {
        DetailsFragment detailFrag = DetailsFragment.newInstance(id);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.details_container, detailFrag)
                .commit();
    }
}