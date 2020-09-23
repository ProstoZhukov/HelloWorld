package com.zhukov.android.myapplicationlist.di.application;

import com.zhukov.android.myapplicationlist.di.editorcontact.EditContactComponent;
import com.zhukov.android.myapplicationlist.di.editorcontact.EditContactModule;
import com.zhukov.android.myapplicationlist.di.infocontact.ContactComponent;
import com.zhukov.android.myapplicationlist.di.infocontact.ContactModule;
import com.zhukov.android.myapplicationlist.di.listcontact.ContactListComponent;
import com.zhukov.android.myapplicationlist.di.listcontact.ContactListModule;
import com.zhukov.android.myapplicationlist.presentation.listcontact.hostcontact.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    void inject(MainActivity mainActivity);

    ContactListComponent plus(ContactListModule contactsListModule);

    EditContactComponent plus(EditContactModule editContactModule);

    ContactComponent plus(ContactModule contactModule);

}
