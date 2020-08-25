package com.zhukov.android.myapplicationlist.presentation.listcontact.presenter;

import com.zhukov.android.myapplicationlist.business.listcontact.ContactListInteractor;
import com.zhukov.android.myapplicationlist.business.listcontact.IContactListInteractor;
import com.zhukov.android.myapplicationlist.presentation.listcontact.view.IContactListView;

public class ContactListPresenter implements IContactListPresenter {

    IContactListView mView;
    IContactListInteractor mInteractor = new ContactListInteractor();

    @Override
    public void attachView(IContactListView iContactListView) {
        mView = iContactListView;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onItemClicked() {
        mView.openContactScreen();
    }
}
