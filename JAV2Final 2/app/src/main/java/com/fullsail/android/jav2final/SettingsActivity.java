// Ryan Backa
// JAV2 - 1612
// SettingsActivity

package com.fullsail.android.jav2final;

import android.app.Activity;
import android.os.Bundle;

import com.fullsail.android.jav2final.fragment.SettingsFragment;


public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        if (savedInstanceState == null) {
            SettingsFragment frag = SettingsFragment.newInstance();
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, frag, SettingsFragment.TAG)
                    .commit();
        }
    }
}
