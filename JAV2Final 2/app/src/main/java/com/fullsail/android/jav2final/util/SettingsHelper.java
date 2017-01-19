// Ryan Backa
// JAV2 - 1612
// SettingsHelper


package com.fullsail.android.jav2final.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class SettingsHelper {

    private static final String PREF_LIST_TYPE = "";

    public static final int LIST_TYPE_DETAILED = 0;
    public static final int LIST_TYPE_SIMPLE = 1;

    public static int getListType(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String type = prefs.getString(PREF_LIST_TYPE, "TYPE_DETAILED");
        if(type.equals("TYPE_DETAILED")) {
            return LIST_TYPE_DETAILED;
        } else {
            return LIST_TYPE_SIMPLE;
        }
    }
}
