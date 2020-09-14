package com.zhukov.android.myapplicationlist.presentation.listcontact.presenter;

import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;

import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;
import com.zhukov.android.myapplicationlist.presentation.listcontact.view.IContactListView;

import java.util.List;
import java.util.UUID;

public interface IContactListPresenter extends LifecycleObserver {

    void attachView(IContactListView iContactListView);

    void detachView();

    void onStartView();

    void onItemClicked(UUID contactId);

    void loadContactList();

    void onItemMenuEdit(UUID contactId);

    void deleteContact(UUID contactId);

    void addNewContact();

    void updateContactListSecondName();

    void updateContactListFirstName();

    void onDestroy();


}
