package com.zhukov.android.myapplicationlist.data.repositories.infocontact;

import com.zhukov.android.myapplicationlist.data.database.IDataBaseProvider;
import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;

import java.util.UUID;

import io.reactivex.Single;

public class ContactRepository implements IContactRepository {

    private IDataBaseProvider mDataBaseProvider;

    public ContactRepository(IDataBaseProvider dataBaseProvider) {
        mDataBaseProvider = dataBaseProvider;
    }

    @Override
    public Single<ContactModel> loadContact(UUID contactId) {
        return mDataBaseProvider.loadContact(contactId);
    }

}
