package com.zhukov.android.myapplicationlist.business.infocontact;

import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;
import com.zhukov.android.myapplicationlist.data.repositories.infocontact.IContactRepository;

import java.util.UUID;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ContactInteractor implements IContactInteractor {
    private IContactRepository mRepository;

    public ContactInteractor(IContactRepository iContactRepository) {
        mRepository = iContactRepository;
    }

    @Override
    public Single<ContactModel> loadContact(UUID contactId) {
        return mRepository.loadContact(contactId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
