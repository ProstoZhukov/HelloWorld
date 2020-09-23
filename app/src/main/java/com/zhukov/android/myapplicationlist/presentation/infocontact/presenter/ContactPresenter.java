package com.zhukov.android.myapplicationlist.presentation.infocontact.presenter;

import android.util.Log;

import com.zhukov.android.myapplicationlist.business.infocontact.IContactInteractor;
import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;
import com.zhukov.android.myapplicationlist.presentation.infocontact.view.IContactView;

import java.util.UUID;

import io.reactivex.disposables.CompositeDisposable;

public class ContactPresenter implements IContactPresenter {
    private static final String TAG = "MY_TAG";

    private IContactView mView;
    private IContactInteractor mInteractor;

    private ContactModel mContactModel;

    private CompositeDisposable mDisposer = new CompositeDisposable();

    public ContactPresenter(IContactInteractor iContactInteractor) {
        mInteractor = iContactInteractor;
    }

    @Override
    public void attachView(IContactView iContactView) {
        mView = iContactView;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void loadContact(UUID contactId) {
        if (mContactModel == null) {
            mDisposer.add(
                    mInteractor.loadContact(contactId)
                            .subscribe(this::onSuccess, this::onError)
            );
        } else {
            mView.updateUI(mContactModel);
        }
    }

    @Override
    public void itemEditClicked() {
        mView.openEditContactScreen();
    }

    @Override
    public void onBackClicked() {
        mView.goToListContacts();
    }

    private void onSuccess(ContactModel contactModel) {
        mContactModel = contactModel;
        mView.updateUI(mContactModel);

    }

    private void onError(Throwable throwable) {
        Log.e(TAG, getClass().getSimpleName() + " onError" + throwable);
    }

    @Override
    public void onDestroy() {
        mDisposer.dispose();
    }
}
