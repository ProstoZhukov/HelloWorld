package com.zhukov.android.myapplicationlist.presentation.editorcontact.presenter;

import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;
import com.zhukov.android.myapplicationlist.presentation.editorcontact.view.IEditContactView;

import java.util.UUID;

public interface IEditContactPresenter {
    void attachView(IEditContactView editContactView);

    void detachView();

    void loadContact(UUID contactId);

    void updateContact(ContactModel contactsModel);

    void onBackClicked();

    void onPhotoImageClicked();

    void photoUriLoaded(String photoUri);

    void itemAddNumberClicked();

    void itemAddSocialClicked();

    void onDestroy();


}
