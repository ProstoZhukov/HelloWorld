package com.zhukov.android.myapplicationlist.presentation.infocontact.view;

import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;

public interface IContactView {

    void openEditContactScreen();

    void goToListContacts();

    void updateUI(ContactModel contactsModel);

}
