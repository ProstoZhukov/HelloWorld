package com.zhukov.android.myapplicationlist.business.listcontact;

import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;

import java.util.List;
import java.util.UUID;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface IContactListInteractor {
    Completable deleteContact(UUID contactId);

    Single<UUID> addNewContact();

    Single<List<ContactModel>> loadContactList();
}
