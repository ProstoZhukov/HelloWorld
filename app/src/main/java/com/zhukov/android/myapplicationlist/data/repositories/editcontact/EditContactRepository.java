package com.zhukov.android.myapplicationlist.data.repositories.editcontact;

import com.zhukov.android.myapplicationlist.data.database.IDataBaseProvider;
import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;

import java.util.UUID;

import io.reactivex.Completable;
import io.reactivex.Single;

public class EditContactRepository implements IEditContactRepository {
    private IDataBaseProvider mDataBaseProvider;

    public EditContactRepository(IDataBaseProvider iDataBaseProvider) {
        mDataBaseProvider = iDataBaseProvider;
    }

    @Override
    public Single<ContactModel> loadContact(UUID contactId) {
        return mDataBaseProvider.loadContact(contactId);
    }

    @Override
    public Completable updateContact(ContactModel contactModel) {
        return mDataBaseProvider.updateContact(contactModel);
    }
}
