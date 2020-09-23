package com.zhukov.android.myapplicationlist.data.database;

import com.zhukov.android.myapplicationlist.data.database.MyContactSQLite.IMyContactSQLite;
import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;

import java.util.List;
import java.util.UUID;

import io.reactivex.Completable;
import io.reactivex.Single;

public class SQLiteDbProvider implements IDataBaseProvider {

    IMyContactSQLite mDataBase;

    public SQLiteDbProvider(IMyContactSQLite iMyContactSQLite) {
        mDataBase = iMyContactSQLite;
    }


    @Override
    public Completable deleteContact(final UUID contactId) {
        return Completable.fromAction(() -> mDataBase.deleteContact(contactId));
    }

    @Override
    public Single<UUID> addNewContact() {
        return Single.fromCallable(() -> mDataBase.addContact());
    }

    @Override
    public Single<List<ContactModel>> loadContactList() {
        return Single.fromCallable(() -> mDataBase.getContacts());
    }

    @Override
    public Single<ContactModel> loadContact(UUID contactId) {
        return Single.fromCallable(() -> mDataBase.getContact(contactId));
    }

    @Override
    public Completable updateContact(ContactModel contactModel) {
        return Completable.fromAction(() -> mDataBase.updateContact(contactModel));
    }
}
