package com.zhukov.android.myapplicationlist.business.listcontact;

import android.annotation.SuppressLint;

import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;
import com.zhukov.android.myapplicationlist.data.repositories.listcontact.ContactListRepository;
import com.zhukov.android.myapplicationlist.data.repositories.listcontact.IContactListRepository;

import java.util.List;
import java.util.UUID;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ContactListInteractor implements IContactListInteractor {
    private IContactListRepository mContactListRepository;

    public ContactListInteractor(IContactListRepository contactListRepository){
        mContactListRepository = contactListRepository;
    }

    @Override
    public Single<UUID> addNewContact() {
      return mContactListRepository.addNewContact();
    }

    @Override
    public Completable deleteContact(UUID contactId) {
       return mContactListRepository.deleteContact(contactId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<ContactModel>> loadContactList() {
        return mContactListRepository.loadContactList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
