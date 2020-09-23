package com.zhukov.android.myapplicationlist.data.database;

import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;

import java.util.List;
import java.util.UUID;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface IDataBaseProvider {
    Completable deleteContact(UUID contactId);

    Single<UUID> addNewContact();

    Single<List<ContactModel>> loadContactList();

    Single<ContactModel> loadContact(UUID contactId);

    Completable updateContact(ContactModel contactModel);
}
