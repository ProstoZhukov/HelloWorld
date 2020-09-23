package com.zhukov.android.myapplicationlist.data.repositories.infocontact;

import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;

import java.util.UUID;

import io.reactivex.Single;

public interface IContactRepository {

    Single<ContactModel> loadContact(UUID contactId);
}
