package com.zhukov.android.myapplicationlist.di.listcontact;


import com.zhukov.android.myapplicationlist.presentation.listcontact.view.ContactListFragment;

import dagger.Subcomponent;

@Subcomponent(modules = ContactListModule.class)
@ContactListScope
public interface ContactListComponent {
    void inject(ContactListFragment contactListFragment);
}
