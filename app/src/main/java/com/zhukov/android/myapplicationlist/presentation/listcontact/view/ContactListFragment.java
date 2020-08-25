package com.zhukov.android.myapplicationlist.presentation.listcontact.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zhukov.android.myapplicationlist.R;
import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;
import com.zhukov.android.myapplicationlist.presentation.infocontact.view.ContactFragment;
import com.zhukov.android.myapplicationlist.presentation.listcontact.adapters.ContactAdapter;
import com.zhukov.android.myapplicationlist.presentation.listcontact.adapters.ContactAdapter.OnItemClickListener;
import com.zhukov.android.myapplicationlist.presentation.listcontact.presenter.IContactListPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ContactListFragment extends Fragment implements IContactListView, OnItemClickListener {



    private RecyclerView mContactRecyclerView;
    private ContactAdapter mContactAdapter;
    private FloatingActionButton mFloatingActionButton;

    private IContactListPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.contact_list_fragment,container,false);

        mContactRecyclerView = (RecyclerView) view.findViewById(R.id.contact_recycler_view);
        mContactRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.floating_button_list);
        mFloatingActionButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.editContactFragment));
        updateUI();

        return view;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        createPopupMenu(getContext(),getView());
        return super.onOptionsItemSelected(item);
    }


    private void updateUI(){
        mContactAdapter = new ContactAdapter();
        mContactRecyclerView.setAdapter(mContactAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_contact_list, menu);
    }

    private void createPopupMenu(final Context context, View view) {
        PopupMenu popupMenu = new PopupMenu(getContext(), getView());
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.list_menu_filter_popupmenu, popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.mnu_filter_secondName:
                        ContactAdapter.setFilter(false);
                        mContactAdapter.notifyDataSetChanged();
                        return true;
                    case R.id.mnu_filter_name:
                        ContactAdapter.setFilter(true);
                        mContactAdapter.notifyDataSetChanged();
                        return true;
                    default:
                        return false;
                }

            }
        });
    }

    @Override
    public void onItemClick() {
        mPresenter.onItemClicked();
    }

    @Override
    public void openContactScreen() {
        if(getView()!= null) {
            Navigation.findNavController(getView()).navigate(R.id.contactFragment);
        }
    }

    //сортировка контактов по имени и фамилии
    public static List<ContactModel> getFilterList(List<ContactModel> modelList, boolean secondName) {
        List<ContactModel> modelListUpdate = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        if (!secondName) {
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
        }else {
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

        }

        return modelListUpdate;
    }

    public void openContact(UUID contactId) {
        Bundle args = new Bundle();
        args.putString(ContactFragment.ARG_CONTACT_ID, contactId.toString());
        NavHostFragment.findNavController(this).navigate(R.id.contactFragment, args);
    }

}
