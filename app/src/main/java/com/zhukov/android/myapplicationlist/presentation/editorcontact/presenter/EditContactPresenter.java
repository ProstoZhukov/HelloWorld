package com.zhukov.android.myapplicationlist.presentation.editorcontact.presenter;

import android.util.Log;

import com.zhukov.android.myapplicationlist.business.editcontact.IEditContactInteractor;
import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;
import com.zhukov.android.myapplicationlist.presentation.editorcontact.view.IEditContactView;

import java.util.UUID;

import io.reactivex.disposables.CompositeDisposable;

public class EditContactPresenter implements IEditContactPresenter {
    private static final String TAG = "MY_TAG";

    private IEditContactView mView;
    private ContactModel mContactModel;

    private CompositeDisposable mDisposer = new CompositeDisposable();
    private IEditContactInteractor mEditContactInteractor;

    public EditContactPresenter(IEditContactInteractor iEditContactInteractor) {
        mEditContactInteractor = iEditContactInteractor;
    }

    @Override
    public void attachView(IEditContactView editContactView) {
        mView = editContactView;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onBackClicked() {
        mView.updateContact();
    }

    @Override
    public void loadContact(UUID contactId) {
        if (mContactModel == null) {
            mDisposer.add(
                    mEditContactInteractor.loadContact(contactId)
                            .subscribe(this::onSuccess, this::onError)
            );
        } else {
            mView.updateUI(mContactModel);
        }
    }

    @Override
    public void updateContact(ContactModel contactModel) {
        if (contactModel.getContactPhoto() == null && mContactModel.getContactPhoto() != null) {
            contactModel.setContactPhoto(mContactModel.getContactPhoto());
        }

        mContactModel = contactModel;
        mView.updateUI(contactModel);
        mDisposer.add(
                mEditContactInteractor.updateContact(contactModel)
                        .subscribe(this::onUpdate, this::onError)
        );
    }

    @Override
    public void onPhotoImageClicked() {
        mView.updatePhoto();
    }

    @Override
    public void photoUriLoaded(String photoUri) {
        mContactModel.setContactPhoto(photoUri);
    }

    private void onUpdate() {
        Log.i(TAG, " - контакт был успешно обновлен");
    }

    private void onAdd(UUID id) {
        Log.i(TAG, " - контакт был успешно добавлен" + id);
    }


    private void onSuccess(ContactModel contactModel) {
        mView.updateUI(contactModel);
        mContactModel = contactModel;
    }

    private void onError(Throwable throwable) {
        Log.e(TAG, getClass().getSimpleName() + " onError" + throwable);
    }

    @Override
    public void itemAddNumberClicked() {
        mView.itemAddNumberClicked();
    }

    @Override
    public void itemAddSocialClicked() {
        mView.itemAddSocialClicked();
    }

    @Override
    public void onDestroy() {
        mDisposer.dispose();
    }
}
