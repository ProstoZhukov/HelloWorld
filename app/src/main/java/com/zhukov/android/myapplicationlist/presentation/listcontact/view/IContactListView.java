package com.zhukov.android.myapplicationlist.presentation.listcontact.view;

import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;

import java.util.List;
import java.util.UUID;

public interface IContactListView {

    void openContactScreen(UUID contactId);

    void openEditContactScreen(UUID contactId);

    void updateContactList(List<ContactModel> contactModelList);

    List<ContactModel> updateContactListSecondName(List<ContactModel> modelList);

    List<ContactModel> updateContactListFirstName(List<ContactModel> modelList);

    /*void goToEditScreen();*/

}
