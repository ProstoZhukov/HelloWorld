package com.zhukov.android.myapplicationlist.data.database.databaseutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zhukov.android.myapplicationlist.data.database.databaseutils.ContactDbSchema.ContactTable;

public class ContactBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "contactBase.db";

    public ContactBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + ContactTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ContactTable.Cols.UUID + ", " +
                ContactTable.Cols.FIRST_NAME + ", " +
                ContactTable.Cols.LAST_NAME +", " +
                ContactTable.Cols.PATRONYMIC +", " +
                ContactTable.Cols.PHOTO_URI +", " +
                ContactTable.Cols.MAIN_NUMBER +", " +
                ContactTable.Cols.SECOND_NUMBER +", " +
                ContactTable.Cols.SECOND_NUMBER2 +", " +
                ContactTable.Cols.SOCIAL_MEDIA +", " +
                ContactTable.Cols.SOCIAL_MEDIA2 +", " +
                ContactTable.Cols.SOCIAL_MEDIA3 +", " +
                ContactTable.Cols.ADDITIONAL_INFORMATION +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
