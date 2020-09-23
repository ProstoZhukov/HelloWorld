package com.zhukov.android.myapplicationlist.presentation.editorcontact.view;

import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;

public interface IEditContactView {
    void updateUI(ContactModel contactsModel);

    void updateContact();

    void updatePhoto();

    void itemAddNumberClicked();

    void itemAddSocialClicked();
}
