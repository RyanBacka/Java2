package com.ce0.java2.backaryan.backaryan_ce06;

import android.net.Uri;

/**
 * Created by ryankbacka on 12/6/16.
 */

public class MagazineContract {
    public static final String AUTHORITY = "com.fullsail.ce6.student.provider";
    public static final String DATA_SOURCE = "articles";
    public static final Uri CONTENT_URI;

    static {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content://")
                .authority(AUTHORITY)
                .path(DATA_SOURCE);
        CONTENT_URI = builder.build();
    }

    public static final String ID = "_id";
    public static final String TITLE = "title";
    public static final String THUMBNAIL = "thumbnail";
    public static final String BODY = "body";






}
