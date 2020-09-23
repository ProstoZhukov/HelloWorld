package com.zhukov.android.myapplicationlist.di.editorcontact;


import com.zhukov.android.myapplicationlist.presentation.editorcontact.view.EditContactFragment;

import dagger.Subcomponent;

@Subcomponent(modules = EditContactModule.class)
@EditContactScope
public interface EditContactComponent {
    void inject(EditContactFragment editContactFragment);
}
