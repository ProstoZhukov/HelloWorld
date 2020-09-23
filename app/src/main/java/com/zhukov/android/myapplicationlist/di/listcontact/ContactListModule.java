package com.zhukov.android.myapplicationlist.di.listcontact;

import com.zhukov.android.myapplicationlist.business.listcontact.ContactListInteractor;
import com.zhukov.android.myapplicationlist.business.listcontact.IContactListInteractor;
import com.zhukov.android.myapplicationlist.data.database.IDataBaseProvider;
import com.zhukov.android.myapplicationlist.data.repositories.listcontact.ContactListRepository;
import com.zhukov.android.myapplicationlist.data.repositories.listcontact.IContactListRepository;
import com.zhukov.android.myapplicationlist.presentation.listcontact.presenter.ContactListPresenter;
import com.zhukov.android.myapplicationlist.presentation.listcontact.presenter.IContactListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactListModule {
    @Provides
    @ContactListScope
    IContactListPresenter provideContactListPresenter(IContactListInteractor contactListInteractor) {
        return new ContactListPresenter(contactListInteractor);
    }

    @Provides
    @ContactListScope
    IContactListInteractor provideContactsListInteractor(IContactListRepository contactsListRepository) {
        return new ContactListInteractor(contactsListRepository);
    }

    @Provides
    @ContactListScope
    IContactListRepository provideContactsListRepository(IDataBaseProvider dataBaseProvider) {
        return new ContactListRepository(dataBaseProvider);
    }


}
