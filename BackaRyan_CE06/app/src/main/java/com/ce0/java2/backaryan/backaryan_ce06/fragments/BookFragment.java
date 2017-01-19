package com.ce0.java2.backaryan.backaryan_ce06.fragments;


import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ce0.java2.backaryan.backaryan_ce06.BookContract;
import com.ce0.java2.backaryan.backaryan_ce06.R;


public class BookFragment extends ListFragment {


    private static final String TAG = "BookFragment";

    public BookFragment() {
        // Required empty public constructor
    }

    public static BookFragment newInstance(){
        BookFragment frag = new BookFragment();
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String bookName = "null";
        ContentResolver cr = getActivity().getContentResolver();
        String[] columns = new String[]{
                BookContract.ID,
                BookContract.BOOK_ID,
                BookContract.TITLE,
                BookContract.DESCRIPTION,
                BookContract.THUMBNAIL
        };
        Cursor cursor = cr.query(BookContract.CONTENT_URI,columns,null,null,null);


        if(cursor!=null && cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                bookName = cursor.getString(cursor.getColumnIndex(BookContract.TITLE));

                cursor.close();
            }
        }
        Log.d(TAG, bookName);

    }
}
