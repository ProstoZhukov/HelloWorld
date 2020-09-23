package com.zhukov.android.myapplicationlist.presentation.infocontact.presenter;

import com.zhukov.android.myapplicationlist.presentation.infocontact.view.IContactView;

import java.util.UUID;

public interface IContactPresenter {
    void attachView(IContactView iContactView);

    void detachView();

    void itemEditClicked();

    void onBackClicked();

    void onDestroy();

    void loadContact(UUID contactId);


}
