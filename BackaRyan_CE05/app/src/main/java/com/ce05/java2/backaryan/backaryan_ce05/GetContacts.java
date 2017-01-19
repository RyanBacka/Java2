package com.ce05.java2.backaryan.backaryan_ce05;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import com.ce05.java2.backaryan.backaryan_ce05.objects.Contact;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by ryankbacka on 12/6/16.
 */

public class GetContacts {
    private ContentResolver cr;


    public GetContacts(ContentResolver _cr) {
        cr = _cr;
    }

    public String getName(String contactId) {

        String whereName = ContactsContract.Data.MIMETYPE + " = ? AND " + ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID + " = ?";
        String[] whereNameParams = new String[] { ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE, contactId };

        Cursor cur = cr.query(ContactsContract.Data.CONTENT_URI, null, whereName, whereNameParams, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
        cur.moveToFirst();

        String firstName = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));
        String middleName = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.MIDDLE_NAME));
        String lastName = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));
        cur.close();
        String fullName = firstName;
        if(middleName!=null){
            fullName += " "+middleName;
        }
        if(lastName!=null){
            fullName += " "+lastName;
        }

        return fullName;
    }

    public String getNumber(String contactId) {
        ArrayList<String> phoneNumber = new ArrayList<>();
        Cursor cur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? ",
                new String[]{contactId}, null);
        if(cur!=null) {
            while (cur.moveToNext()) {
                int type = cur.getInt(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                String phoneType = "";
                switch (type) {
                    case 1:
                        phoneType = "home";
                        break;
                    case 2:
                        phoneType = "cell";
                        break;
                    case 3:
                        phoneType = "office";
                        break;
                }
                phoneType += ": " +
                        cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)) + ",";
                phoneNumber.add(phoneType);
            }
            cur.close();
        }
        String numbers = "";
        for (int i = 0; i < phoneNumber.size(); i++) {
            numbers += phoneNumber.get(i);
        }

        return numbers;
    }

    public String getEMailAddress(String contactId) {
        ArrayList<String> mailAddress = new ArrayList<String>();
        Cursor emailCur = cr.query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ? ",
                new String[]{contactId}, null);
        if(emailCur!=null) {
            while (emailCur.moveToNext()) {
                String email = emailCur
                        .getString(emailCur
                                .getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                        + ", ";
                mailAddress.add(email);
            }
            emailCur.close();
        }

        String emails = "";

        for (int i = 0; i < mailAddress.size(); i++) {
            emails += mailAddress.get(i);
        }

        return emails;
    }

    public Bitmap getPhoto(String contactId) {
        Bitmap photo = null;
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(contactId));
        Uri photoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
        Cursor photoCur = cr.query(photoUri, new String[]{ContactsContract.Contacts.Photo.PHOTO}, null, null, null);
        if (photoCur != null) {
            try {
                if (photoCur.moveToFirst()) {
                    byte[] data = photoCur.getBlob(0);
                    if (data != null) {
                        photo = BitmapFactory.decodeStream(new ByteArrayInputStream(data));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            photoCur.close();
        }
        return photo;
    }


    public ArrayList<Contact> readContacts() {

        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        ArrayList<Contact> contacts = new ArrayList<>();

        if (cur != null && cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String phone = "";
                Bitmap photo = null;

                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    System.out.println("name : " + name + ", ID : " + id);

                    // get the phone number
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    if (pCur != null) {
                        while (pCur.moveToNext()) {
                            phone = pCur.getString(
                                    pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            System.out.println("phone" + phone);
                        }
                        pCur.close();
                    }

                    Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(id));
                    Uri photoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
                    Cursor photoCur = cr.query(photoUri, new String[]{ContactsContract.Contacts.Photo.PHOTO}, null, null, null);
                    if (photoCur != null) {
                        try {
                            if (photoCur.moveToFirst()) {
                                byte[] data = photoCur.getBlob(0);
                                if (data != null) {
                                    photo = BitmapFactory.decodeStream(new ByteArrayInputStream(data));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        photoCur.close();
                    }
                    Contact contact = new Contact(id,name,phone,photo);
                    contacts.add(contact);
                }
            }
            cur.close();
        }
        return contacts;
    }
}
