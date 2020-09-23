package com.zhukov.android.myapplicationlist;

import android.app.Application;

import com.zhukov.android.myapplicationlist.di.application.AppComponent;
import com.zhukov.android.myapplicationlist.di.application.AppModule;
import com.zhukov.android.myapplicationlist.di.application.DaggerAppComponent;
import com.zhukov.android.myapplicationlist.di.editorcontact.EditContactComponent;
import com.zhukov.android.myapplicationlist.di.editorcontact.EditContactModule;
import com.zhukov.android.myapplicationlist.di.infocontact.ContactComponent;
import com.zhukov.android.myapplicationlist.di.infocontact.ContactModule;
import com.zhukov.android.myapplicationlist.di.listcontact.ContactListComponent;
import com.zhukov.android.myapplicationlist.di.listcontact.ContactListModule;

public class App extends Application {

    private static App mApp;
    private AppComponent mAppComponent;

    private ContactListComponent mContactListComponent;
    private EditContactComponent mEditContactComponent;
    private ContactComponent mContactComponent;

    public App() {
        mApp = this;
    }

    public static App get() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public ContactListComponent plusContactListModule(ContactListModule contactsListModule) {
        if (mContactListComponent == null) {
            mContactListComponent = getAppComponent().plus(contactsListModule);
        }
        return mContactListComponent;
    }

    public void clearContactsListComponent() {
        mContactListComponent = null;
    }

    public EditContactComponent plusEditContactModule(EditContactModule editContactModule) {
        if (mEditContactComponent == null) {
            mEditContactComponent = getAppComponent().plus(editContactModule);
        }
        return mEditContactComponent;
    }

    public void clearEditContactComponent() {
        mEditContactComponent = null;
    }

    public ContactComponent plusContactModule(ContactModule contactModule) {
        if (mContactComponent == null) {
            mContactComponent = getAppComponent().plus(contactModule);
        }
        return mContactComponent;
    }

    public void clearContactComponent() {
        mContactComponent = null;
    }

}
