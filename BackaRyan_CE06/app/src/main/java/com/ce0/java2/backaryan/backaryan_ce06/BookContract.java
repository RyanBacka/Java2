package com.ce0.java2.backaryan.backaryan_ce06;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.test.suitebuilder.annotation.Suppress;

/**
 * Created by ryankbacka on 12/6/16.
 */

public class BookContract {
    public static final String AUTHORITY = "com.fullsail.ce6.provider";
    public static final Uri CONTENT_URI;
    public static final String DATA_SOURCE = "books";

    //Build Uri
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
    public static final String DESCRIPTION = "Description";
    public static final String BOOK_ID = "book_id";
}
