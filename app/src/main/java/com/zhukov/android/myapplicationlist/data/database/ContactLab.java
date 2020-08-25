package com.zhukov.android.myapplicationlist.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhukov.android.myapplicationlist.data.database.databaseutils.ContactBaseHelper;
import com.zhukov.android.myapplicationlist.data.database.databaseutils.ContactCursorWrapper;
import com.zhukov.android.myapplicationlist.data.database.databaseutils.ContactDbSchema.ContactTable;
import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContactLab {

    private SQLiteDatabase mDatabase;

    public ContactLab(ContactBaseHelper contactBaseHelper){
        mDatabase =  contactBaseHelper.getWritableDatabase();
    }

    private static ContentValues getContentValues(ContactModel contactModel){
        ContentValues values = new ContentValues();
        values.put(ContactTable.Cols.UUID, contactModel.getContactId().toString());
        values.put(ContactTable.Cols.FIRST_NAME, contactModel.getContactFirstName());
        values.put(ContactTable.Cols.LAST_NAME, contactModel.getContactLastName());
        values.put(ContactTable.Cols.PATRONYMIC, contactModel.getPatronymic());
        values.put(ContactTable.Cols.PHOTO_URI, contactModel.getContactPhoto());
        values.put(ContactTable.Cols.MAIN_NUMBER, contactModel.getContactMainNumber());
        values.put(ContactTable.Cols.SECOND_NUMBER, contactModel.getContactSecondNumber());
        values.put(ContactTable.Cols.SOCIAL_MEDIA, contactModel.getContactSocialMedia());
        values.put(ContactTable.Cols.ADDITIONAL_INFORMATION, contactModel.getContactInformation());

        return values;
    }

    public void addContact(ContactModel contactModel){
        ContentValues values = getContentValues(contactModel);
        mDatabase.insert(ContactTable.NAME,null,values);
    }

    public void updateContact(ContactModel contactModel){
        String uuidString = contactModel.getContactId().toString();
        ContentValues values = getContentValues(contactModel);

        mDatabase.update(ContactTable.NAME, values,
                ContactTable.Cols.UUID + " = ?",
                new String[]{ uuidString });
    }

    private ContactCursorWrapper queryContacts(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ContactTable.NAME,
                null, // columns - с null выбираются все столбцы
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new ContactCursorWrapper(cursor);
    }

    public List<ContactModel> getContacts(){
        List<ContactModel> contactModelList = new ArrayList<>();

        ContactCursorWrapper cursorWrapper = queryContacts(null,null);
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()){
                contactModelList.add(cursorWrapper.getContactModel());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }
        return contactModelList;
    }

    public ContactModel getContact(UUID id){

        ContactCursorWrapper cursorWrapper = queryContacts(
                ContactTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
                );
        try {
            if(cursorWrapper.getCount() == 0){
                return null;
            }

            cursorWrapper.moveToFirst();
            return cursorWrapper.getContactModel();
        } finally {
            cursorWrapper.close();
        }
    }

    public void deleteContact(UUID contactId) {
        String uuidString = contactId.toString();
        mDatabase.delete(ContactTable.NAME,
                ContactTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }




}
