package com.zhukov.android.myapplicationlist.data.database.MyContactSQLite;

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

public class MyContactSQLite implements IMyContactSQLite{

    private SQLiteDatabase mDatabase;
    private UUID mUUID;



    public MyContactSQLite(ContactBaseHelper contactBaseHelper){
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
        values.put(ContactTable.Cols.SECOND_NUMBER2, contactModel.getContactSecondNumber2());
        values.put(ContactTable.Cols.SOCIAL_MEDIA, contactModel.getContactSocialMedia());
        values.put(ContactTable.Cols.SOCIAL_MEDIA2, contactModel.getContactSocialMedia2());
        values.put(ContactTable.Cols.SOCIAL_MEDIA3, contactModel.getContactSocialMedia3());
        values.put(ContactTable.Cols.ADDITIONAL_INFORMATION, contactModel.getContactInformation());

        return values;
    }

    @Override
    public UUID addContact() {
        ContactModel contactModel = new ContactModel(UUID.randomUUID());
        ContentValues values = getContentValues(contactModel);
        mDatabase.insert(ContactTable.NAME,null,values);
        return contactModel.getContactId();
    }

    @Override
    public void updateContact(ContactModel contactModel) {
        String uuidString = contactModel.getContactId().toString();
        ContentValues values = getContentValues(contactModel);

        mDatabase.update(ContactTable.NAME, values,
                ContactTable.Cols.UUID + " = ?",
                new String[]{ uuidString });
    }

    private ContactCursorWrapper queryContacts(SQLiteDatabase db, String whereClause, String[] whereArgs) {
        Cursor cursor = db.query(
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

    @Override
    public List<ContactModel> getContacts(){
        ContactCursorWrapper cursorWrapper = queryContacts(mDatabase,
                null,
                null);
        List<ContactModel> contactModelList = new ArrayList<>();
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

    @Override
    public ContactModel getContact(UUID id){
        String uuidString = id.toString();
        ContactCursorWrapper cursorWrapper = queryContacts(mDatabase,
                ContactTable.Cols.UUID + " = ?",
                new String[]{uuidString}
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

    @Override
    public void deleteContact(UUID contactId) {
        String uuidString = contactId.toString();
        mDatabase.delete(ContactTable.NAME,
                ContactTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }
}
