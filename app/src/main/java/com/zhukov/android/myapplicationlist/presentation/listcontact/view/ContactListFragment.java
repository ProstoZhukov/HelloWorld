package com.zhukov.android.myapplicationlist.presentation.listcontact.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zhukov.android.myapplicationlist.App;
import com.zhukov.android.myapplicationlist.R;
import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;
import com.zhukov.android.myapplicationlist.di.listcontact.ContactListModule;
import com.zhukov.android.myapplicationlist.presentation.editorcontact.view.EditContactFragment;
import com.zhukov.android.myapplicationlist.presentation.infocontact.view.ContactFragment;
import com.zhukov.android.myapplicationlist.presentation.listcontact.adapters.ContactAdapter;
import com.zhukov.android.myapplicationlist.presentation.listcontact.adapters.ContactAdapter.OnItemClickListener;
import com.zhukov.android.myapplicationlist.presentation.listcontact.presenter.IContactListPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

public class ContactListFragment extends Fragment implements IContactListView, OnItemClickListener {

    private RecyclerView mContactRecyclerView;
    private ContactAdapter mContactAdapter;
    private FloatingActionButton mFloatingActionButton;
    private ImageView mFilter;
    private ArrayList<ContactModel> mContactList;



    @Inject
    public IContactListPresenter mPresenter;

    public ContactListFragment(){
        App.get().plusContactListModule(new ContactListModule()).inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.attachView(this);
        getLifecycle().addObserver(mPresenter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contact_list_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContactRecyclerView = (RecyclerView) view.findViewById(R.id.contact_recycler_view);
        mContactRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mContactAdapter = new ContactAdapter(this);
        mContactRecyclerView.setAdapter(mContactAdapter);

        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.floating_button_list);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addNewContact();
            }
        });
        mFilter = (ImageView) view.findViewById(R.id.toolbar_filter);
        mFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopupMenu(getContext(),mFilter);
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        App.get().clearContactsListComponent();
        mPresenter.detachView();
        getLifecycle().removeObserver(mPresenter);
    }

    @Override
    public void onItemClick(UUID contactId) {
        mPresenter.onItemClicked(contactId);
    }

    @Override
    public void onMenuItemEditClick(UUID contactId) {
        mPresenter.onItemMenuEdit(contactId);
    }

    @Override
    public void deleteItem(UUID contactId) {
        mPresenter.deleteContact(contactId);
        mContactAdapter.notifyDataSetChanged();
    }



    @Override
    public void updateContactList(List<ContactModel> contactModelList) {
        mContactList =  new ArrayList<>();
        mContactList.addAll(contactModelList);
        mContactAdapter.updateList(mContactList);
    }

    @Override
    public void openEditContactScreen(UUID contactId) {
        if (getView() != null) {
            Bundle args = new Bundle();
            args.putString(EditContactFragment.ARG_CONTACT_EDIT_ID, contactId.toString());
            Navigation.findNavController(getView()).navigate(R.id.editContactFragment, args);
            mContactAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void openContactScreen(UUID contactId) {
        if(getView()!= null) {
            Bundle args = new Bundle();
            args.putString(ContactFragment.ARG_CONTACT_ID, contactId.toString());
            Navigation.findNavController(getView()).navigate(R.id.contactFragment,args);
        }
    }
    private void createPopupMenu(final Context context, View view) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.list_menu_filter_popupmenu, popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.mnu_filter_secondName:
                    mPresenter.updateContactListSecondName();
                    mContactAdapter.notifyDataSetChanged();
                    return true;
                case R.id.mnu_filter_name:
                   mPresenter.updateContactListFirstName();
                   mContactAdapter.notifyDataSetChanged();
                    return true;
                default:
                    return false;
            }

        });
    }

    //сортировка контактов по имени и фамилии
    @Override
    public List<ContactModel> updateContactListSecondName(List<ContactModel> modelList) {
        List<ContactModel> modelListUpdate = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i = 0; i < modelList.size(); i++) {
            stringArrayList.add(modelList.get(i).getContactLastName());
        }
        Collections.sort(stringArrayList);
        for (int i = 0; i < modelList.size(); i++) {
            for (int j =0; j< stringArrayList.size();j++){
                if(stringArrayList.get(i).equals(modelList.get(j).getContactLastName())){
                    modelListUpdate.add(modelList.get(j));
                }
            }
        }
        mContactAdapter.updateList(modelListUpdate);
        return modelListUpdate;
    }

    @Override
    public List<ContactModel> updateContactListFirstName(List<ContactModel> modelList) {
        List<ContactModel> modelListUpdate = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i = 0; i < modelList.size(); i++) {
            stringArrayList.add(modelList.get(i).getContactFirstName());
        }
        Collections.sort(stringArrayList);
        for (int i = 0; i < modelList.size(); i++) {
            for (int j =0; j< stringArrayList.size();j++){
                if(stringArrayList.get(i).equals(modelList.get(j).getContactFirstName())){
                    modelListUpdate.add(modelList.get(j));
                }
            }
        }
        mContactAdapter.updateList(modelListUpdate);
        return modelListUpdate;
    }
}
