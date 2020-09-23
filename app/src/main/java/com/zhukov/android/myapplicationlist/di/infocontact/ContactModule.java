package com.zhukov.android.myapplicationlist.di.infocontact;

import com.zhukov.android.myapplicationlist.business.infocontact.ContactInteractor;
import com.zhukov.android.myapplicationlist.business.infocontact.IContactInteractor;
import com.zhukov.android.myapplicationlist.data.database.IDataBaseProvider;
import com.zhukov.android.myapplicationlist.data.repositories.infocontact.ContactRepository;
import com.zhukov.android.myapplicationlist.data.repositories.infocontact.IContactRepository;
import com.zhukov.android.myapplicationlist.presentation.infocontact.presenter.ContactPresenter;
import com.zhukov.android.myapplicationlist.presentation.infocontact.presenter.IContactPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactModule {
    @Provides
    @ContactScope
    IContactPresenter provideContactPresenter(IContactInteractor contactInteractor) {
        return new ContactPresenter(contactInteractor);
    }

    @Provides
    @ContactScope
    IContactInteractor provideContactInteractor(IContactRepository contactRepository) {
        return new ContactInteractor(contactRepository);
    }

    @Provides
    @ContactScope
    IContactRepository provideContactRepository(IDataBaseProvider dataBaseProvider) {
        return new ContactRepository(dataBaseProvider);
    }
}
