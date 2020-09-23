package com.zhukov.android.myapplicationlist.data.repositories.listcontact;

import com.zhukov.android.myapplicationlist.data.database.IDataBaseProvider;
import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;

import java.util.List;
import java.util.UUID;

import io.reactivex.Completable;
import io.reactivex.Single;

public class ContactListRepository implements IContactListRepository {

    private IDataBaseProvider mDataBaseProvider;

    public ContactListRepository(IDataBaseProvider dataBaseProvider) {
        mDataBaseProvider = dataBaseProvider;
    }

    @Override
    public Single<UUID> addNewContact() {
        return mDataBaseProvider.addNewContact();
    }

    @Override
    public Completable deleteContact(UUID contactId) {
        return mDataBaseProvider.deleteContact(contactId);
    }

    @Override
    public Single<List<ContactModel>> loadContactList() {
        return mDataBaseProvider.loadContactList();
    }
}
