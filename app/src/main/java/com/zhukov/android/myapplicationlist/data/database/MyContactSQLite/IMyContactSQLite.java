package com.zhukov.android.myapplicationlist.data.database.MyContactSQLite;

import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;

import java.util.List;
import java.util.UUID;

public interface IMyContactSQLite {
    void deleteContact(UUID contactId);

    UUID addContact();

    List<ContactModel> getContacts();

    ContactModel getContact(UUID contactId);

    void updateContact(ContactModel contactModel);
}
