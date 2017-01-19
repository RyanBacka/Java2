package com.ce0.java2.backaryan.backaryan_ce06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ce0.java2.backaryan.backaryan_ce06.fragments.BookFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookFragment listFragment = BookFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, listFragment)
                .commit();
    }
}
