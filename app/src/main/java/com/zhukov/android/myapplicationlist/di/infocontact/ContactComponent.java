package com.zhukov.android.myapplicationlist.di.infocontact;


import com.zhukov.android.myapplicationlist.presentation.infocontact.view.ContactFragment;

import dagger.Subcomponent;

@Subcomponent(modules = ContactModule.class)
@ContactScope
public interface ContactComponent {
    void inject(ContactFragment contactFragment);
}
