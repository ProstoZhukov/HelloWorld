package com.zhukov.android.myapplicationlist.presentation.listcontact.presenter;


import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.zhukov.android.myapplicationlist.business.listcontact.IContactListInteractor;
import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;
import com.zhukov.android.myapplicationlist.presentation.listcontact.view.IContactListView;

import java.util.List;
import java.util.UUID;

import io.reactivex.disposables.CompositeDisposable;

public class ContactListPresenter implements IContactListPresenter {
    private static final String TAG = "MY_TAG";

    private CompositeDisposable mDisposer;

    private List<ContactModel> mModelList;


    IContactListView mView;

    private IContactListInteractor mInteractor ;

    public ContactListPresenter(IContactListInteractor listInteractor) {
        mInteractor = listInteractor;
    }

    @Override
    public void attachView(IContactListView iContactListView) {
        mView = iContactListView;
        mDisposer = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        mView = null;
        mDisposer.dispose();
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStartView() {
        if (mModelList == null) {
            mDisposer.add(mInteractor.loadContactList()
                    .subscribe(this::onLoadSuccess, this::onError));
        } else {
            mView.updateContactList(mModelList);
        }
    }

    @Override
    public void onItemClicked(UUID contactId) {
        mView.openContactScreen(contactId);
    }

    @Override
    public void onItemMenuEdit(UUID contactId) {
        mView.openEditContactScreen(contactId);
    }


    @Override
    public void addNewContact() {
        mDisposer.add(mInteractor.addNewContact()
                .subscribe(this::onAddSuccess, this::onError));
    }

    @Override
    public void deleteContact(UUID contactId) {
       mDisposer.add(mInteractor.deleteContact(contactId)
               .subscribe(this::onDeleteSuccess, this::onError));
    }

    @Override
    public void loadContactList() {
        mDisposer.add(mInteractor.loadContactList()
                    .subscribe(this::onLoadSuccess, this::onError));

    }

    @Override
    public void updateContactListSecondName() {
        mDisposer.add(mInteractor.loadContactList()
                .subscribe(this::onLoadUpdateSecondSuccess, this::onError));
    }

    @Override
    public void updateContactListFirstName() {
        mDisposer.add(mInteractor.loadContactList()
                .subscribe(this::onLoadUpdateFirstSuccess, this::onError));
    }

    private void onLoadSuccess(List<ContactModel> contactsList) {
        mView.updateContactList(contactsList);
    }
    private void onLoadUpdateSecondSuccess(List<ContactModel> contactsList) {
        mView.updateContactListSecondName(contactsList);
    }
    private void onLoadUpdateFirstSuccess(List<ContactModel> contactsList) {
        mView.updateContactListFirstName(contactsList);
    }
    private void onAddSuccess(UUID contactId) {
        mView.openEditContactScreen(contactId);
    }
    private void onDeleteSuccess() {
        Log.i(TAG, getClass().getSimpleName() + " contact deleted");
    }
    private void onError(Throwable throwable) {
     throwable.printStackTrace();
    }

    @Override
    public void onDestroy() {
        mDisposer.dispose();
    }

}
