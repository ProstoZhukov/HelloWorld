package com.zhukov.android.myapplicationlist.data.database.databaseutils;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.zhukov.android.myapplicationlist.data.database.databaseutils.ContactDbSchema.ContactTable;
import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;

import java.util.UUID;

public class ContactCursorWrapper extends CursorWrapper {
    public  ContactCursorWrapper (Cursor cursor){
        super(cursor);
    }

    public ContactModel getContactModel(){
        String uuidString = getString(getColumnIndex(ContactTable.Cols.UUID));
        String contactFirstName = getString(getColumnIndex(ContactTable.Cols.FIRST_NAME));
        String contactLastName = getString(getColumnIndex(ContactTable.Cols.LAST_NAME));
        String patronymic = getString(getColumnIndex(ContactTable.Cols.PATRONYMIC));
        String contactPhoto = getString(getColumnIndex(ContactTable.Cols.PHOTO_URI));
        String contactMainNumber = getString(getColumnIndex(ContactTable.Cols.MAIN_NUMBER));
        String contactSecondNumber = getString(getColumnIndex(ContactTable.Cols.SECOND_NUMBER));
        String contactSocialMedia = getString(getColumnIndex(ContactTable.Cols.SOCIAL_MEDIA));
        String contactInformation = getString(getColumnIndex(ContactTable.Cols.ADDITIONAL_INFORMATION));

        ContactModel contactModel = new ContactModel(UUID.fromString(uuidString));
        contactModel.setContactFirstName(contactFirstName);
        contactModel.setContactLastName(contactLastName);
        contactModel.setPatronymic(patronymic);
        contactModel.setContactPhoto(contactPhoto);
        contactModel.setContactMainNumber(contactMainNumber);
        contactModel.setContactSecondNumber(contactSecondNumber);
        contactModel.setContactSocialMedia(contactSocialMedia);
        contactModel.setContactInformation(contactInformation);


        return contactModel;

    }
}
