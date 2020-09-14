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
import java.util.UUID;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {

    private List<ContactModel> mContactModels;
    private OnItemClickListener mOnItemClickListener;


    public ContactAdapter(OnItemClickListener onItemClickListener){
        mContactModels = new ArrayList<>();
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contact,parent,false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        holder.bind(mContactModels.get(position));
        holder.mMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               holder.createPopupMenu(context,holder.mMenuItem,holder);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mContactModels.size();
    }

    public void updateList(List<ContactModel> contactModelList){
        mContactModels.clear();
        mContactModels.addAll(contactModelList);
        notifyDataSetChanged();
    }


    class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private UUID mContactId;

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
            mContactId = contactModel.getContactId();
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
            mOnItemClickListener.onItemClick(mContactId);
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
                            mOnItemClickListener.onMenuItemEditClick(mContactId);
                            return true;
                        case R.id.mnu_item_delete:
                            int newPosition = contactHolder.getAdapterPosition();
                            notifyItemRemoved(newPosition);
                            mContactModels.remove(newPosition);
                            notifyDataSetChanged();
                            mOnItemClickListener.deleteItem(mContactId);
                            return true;
                        default:
                            return false;
                    }
                }
            });

        }
    }

    public interface OnItemClickListener{

       void  onItemClick(UUID contactId);

       void onMenuItemEditClick(UUID contactId);

       void deleteItem(UUID contactId);
    }



}
