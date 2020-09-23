package com.zhukov.android.myapplicationlist.data.repositories.editcontact;

import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;

import java.util.UUID;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface IEditContactRepository {
    Single<ContactModel> loadContact(UUID contactId);

    Completable updateContact(ContactModel contactModel);

    /* Single<UUID> addNewContact();*/
}
