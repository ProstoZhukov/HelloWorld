package com.zhukov.android.myapplicationlist.presentation.listcontact.presenter;

import com.zhukov.android.myapplicationlist.presentation.listcontact.view.IContactListView;

public interface IContactListPresenter {
    void attachView(IContactListView iContactListView);

    void detachView();

    void onItemClicked();


}
