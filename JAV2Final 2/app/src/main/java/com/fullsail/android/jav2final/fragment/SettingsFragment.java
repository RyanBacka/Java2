// Ryan Backa
// JAV2 - 1612
// SettingsFragment

package com.fullsail.android.jav2final.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.fullsail.android.jav2final.R;
import com.fullsail.android.jav2final.util.PoliticiansHelper;


public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    public static final String TAG = "SettingsFragment.TAG";

    private static final String PREF_DELETE_ALL = "com.fullsail.android.PREF_DELETE_ALL";

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public boolean onPreferenceClick(Preference preference) {

        if(preference.getKey().equals(PREF_DELETE_ALL)) {

            new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.delete_all)
                    .setMessage(R.string.delete_all_confirm)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            PoliticiansHelper.deleteAllFavorites(getActivity());
                        }
                    })
                    .setNegativeButton(R.string.no, null)
                    .show();

            return true;
        }
        return false;
    }
}
