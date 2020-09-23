package com.zhukov.android.myapplicationlist.di.editorcontact;

import com.zhukov.android.myapplicationlist.business.editcontact.EditContactInteractor;
import com.zhukov.android.myapplicationlist.business.editcontact.IEditContactInteractor;
import com.zhukov.android.myapplicationlist.data.database.IDataBaseProvider;
import com.zhukov.android.myapplicationlist.data.repositories.editcontact.EditContactRepository;
import com.zhukov.android.myapplicationlist.data.repositories.editcontact.IEditContactRepository;
import com.zhukov.android.myapplicationlist.presentation.editorcontact.presenter.EditContactPresenter;
import com.zhukov.android.myapplicationlist.presentation.editorcontact.presenter.IEditContactPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class EditContactModule {

    @Provides
    @EditContactScope
    IEditContactPresenter provideEditContactPresenter(IEditContactInteractor editContactInteractor) {
        return new EditContactPresenter(editContactInteractor);
    }

    @Provides
    @EditContactScope
    IEditContactInteractor provideEditContactInteractor(IEditContactRepository contactsListRepository) {
        return new EditContactInteractor(contactsListRepository);
    }

    @Provides
    @EditContactScope
    IEditContactRepository provideEditContactRepository(IDataBaseProvider dataBaseProvider) {
        return new EditContactRepository(dataBaseProvider);
    }
}
