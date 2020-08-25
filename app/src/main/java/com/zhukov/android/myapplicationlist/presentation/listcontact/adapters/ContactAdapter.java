package com.zhukov.android.myapplicationlist.presentation.listcontact.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.zhukov.android.myapplicationlist.R;
import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;
import com.zhukov.android.myapplicationlist.presentation.infocontact.view.ContactFragment;
import com.zhukov.android.myapplicationlist.presentation.listcontact.view.ContactListFragment;

import java.util.ArrayList;
import java.util.List;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {

    private List<ContactModel> mContactModels;
    private OnItemClickListener mOnItemClickListener;


    public static boolean mFilter = true;

    public static boolean isFilter() {
        return mFilter;
    }

    public static void setFilter(boolean filter) {
        mFilter = filter;
    }

    public ContactAdapter(){
        mContactModels = createContactModel();
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contact,parent,false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactHolder holder, int position) {
        final ContactModel contactModel = ContactListFragment.getFilterList(mContactModels,mFilter).get(position);
        final Context context = holder.itemView.getContext();
        holder.bind(contactModel);
        holder.mMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopupMenu(context,holder.mMenuItem,holder);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mContactModels.size();
    }


    class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //private ContactModel mContactModel;

        private ImageView mPhotoItem;
        private TextView mNameItem;
        private TextView mSecondNameItem;
        private TextView mNumberItem;
        private ImageButton mMenuItem;



        public ContactHolder(View view) {
            super(view);
            mMenuItem = (ImageButton) view.findViewById(R.id.item_menu);
            mPhotoItem = (ImageView) view.findViewById(R.id.photo_item);
            mNameItem = (TextView) view.findViewById(R.id.name_item);
            mSecondNameItem = (TextView) view.findViewById(R.id.second_name_item);
            mNumberItem = (TextView) view.findViewById(R.id.number_item);
            itemView.setOnClickListener(this);
        }

        public void bind(ContactModel contactModel){
            String itemName = contactModel.getContactFirstName();
            String itemSecondName = contactModel.getContactLastName();
            String itemNumber = contactModel.getContactMainNumber();
            String itemPhotoUri = contactModel.getContactPhoto();
           if(itemName.equals("")){
               mNameItem.setText(R.string.unknown_name);
           } else {
               mNameItem.setText(itemName);
           }
           if(itemSecondName.equals("")){
               mSecondNameItem.setText(R.string.unknown_surname);
           }else {
               mSecondNameItem.setText(itemSecondName);
           }
           if (itemNumber.equals("")){
               mNumberItem.setText(R.string.unknown_number);
           }else {
               mNumberItem.setText(itemNumber);
           }
           if(itemPhotoUri == null){
               mPhotoItem.setImageResource(R.drawable.avatar);
           }else {
               mPhotoItem.setImageURI(Uri.parse(itemPhotoUri));
           }

        }



        @Override
        public void onClick(View view) {
         mOnItemClickListener.onItemClick();

        }
    }
    private void createPopupMenu(final Context context, final View view, final ContactHolder contactHolder){
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.item_option_menu);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mnu_item_edit:
                        Navigation.findNavController(view).navigate(R.id.editContactFragment);
                        Toast.makeText(context,
                                "Вы перешли на экран редактирования!",
                                Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.mnu_item_delete:
                       int newPosition = contactHolder.getBindingAdapterPosition();
                       mContactModels.remove(newPosition);
                       notifyItemRemoved(newPosition);
                        Toast.makeText(context,
                                "Контакт удалён!",
                                Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });

    }



    private List<ContactModel> createContactModel(){
        List<ContactModel> contactModelList = new ArrayList<>();
        for (int i = 0; i<20;i++) {

                ContactModel contactModel = new ContactModel();
                contactModel.setContactFirstName("Dima" + i);
                contactModel.setContactLastName("Zhukov" + i+3);
                contactModel.setContactMainNumber("+79111619177");
            ContactModel contactModel2 = new ContactModel();
            contactModel2.setContactFirstName("Nika" + i);
            contactModel2.setContactLastName("Tolstykh" + i+3);
            contactModel2.setContactMainNumber("+79111619177");
            contactModelList.add(contactModel2);
                contactModelList.add(contactModel);

        }
        return contactModelList;
    }

    public interface OnItemClickListener{
       void   onItemClick();
    }



}
