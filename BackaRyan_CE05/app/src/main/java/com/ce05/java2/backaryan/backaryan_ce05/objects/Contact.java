package com.ce05.java2.backaryan.backaryan_ce05.objects;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Contact {
    String id;
    String name;
    String phoneNum;
    Bitmap picture;

    public Contact(String _id, String _name, String _phoneNum, Bitmap _picture) {
        id=_id;
        name = _name;
        phoneNum = _phoneNum;
        picture = _picture;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public String getId(){
        return id;
    }
}
