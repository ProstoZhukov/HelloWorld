package com.zhukov.android.myapplicationlist.business.editcontact;

import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;

import java.util.UUID;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface IEditContactInteractor {
    Single<ContactModel> loadContact(UUID contactId);

    Completable updateContact(ContactModel contactModel);

}
