package com.zhukov.android.myapplicationlist.business.infocontact;

import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;

import java.util.UUID;

import io.reactivex.Single;

public interface IContactInteractor {

    Single<ContactModel> loadContact(UUID contactId);
}
