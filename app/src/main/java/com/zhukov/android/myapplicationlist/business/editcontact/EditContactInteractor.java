package com.zhukov.android.myapplicationlist.business.editcontact;

import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;
import com.zhukov.android.myapplicationlist.data.repositories.editcontact.IEditContactRepository;

import java.util.UUID;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EditContactInteractor implements IEditContactInteractor {
    IEditContactRepository mEditContactRepository;

    public EditContactInteractor(IEditContactRepository editContactRepository) {
        mEditContactRepository = editContactRepository;
    }

    @Override
    public Single<ContactModel> loadContact(UUID contactId) {
        return mEditContactRepository.loadContact(contactId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable updateContact(ContactModel contactModel) {
        return mEditContactRepository.updateContact(contactModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
