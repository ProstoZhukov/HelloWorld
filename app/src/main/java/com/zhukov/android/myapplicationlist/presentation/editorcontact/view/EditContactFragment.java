package com.zhukov.android.myapplicationlist.presentation.editorcontact.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zhukov.android.myapplicationlist.App;
import com.zhukov.android.myapplicationlist.R;
import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;
import com.zhukov.android.myapplicationlist.di.editorcontact.EditContactModule;
import com.zhukov.android.myapplicationlist.presentation.editorcontact.presenter.IEditContactPresenter;

import java.util.UUID;

import javax.inject.Inject;

public class EditContactFragment extends Fragment implements IEditContactView {
    public static final String ARG_CONTACT_EDIT_ID = "contactId";


    @Inject
    public IEditContactPresenter mEditContactPresenter ;

    private ContactModel mContactModel;

    private UUID mContactId;
    private ImageView mPhotoContact;
    private EditText mFirstName;
    private EditText mSecondName;
    private EditText mPatronymic;
    private EditText mMainNumber;
    private EditText mSecondNumber;
    private EditText mSecondNumber2;
    private EditText mSocialMedia;
    private EditText mSocialMedia2;
    private EditText mSocialMedia3;
    private EditText mAboutContact;
    private TableRow mSecondTable;
    private TableRow mSecondTable2;
    private TableRow mSocialTable2;
    private TableRow mSocialTable3;
    private FloatingActionButton mFloatingActionButton;

    private ImageView mToolbarBack;
    private ImageView mToolbarSave;
    private TextView  mToolbarTittle;
    private View mToolbar;


    public EditContactFragment (){
        App.get().plusEditContactModule(new EditContactModule()).inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEditContactPresenter.attachView(this);
        if (getArguments() != null) {
            mContactId = UUID.fromString(getArguments().getString(ARG_CONTACT_EDIT_ID));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_contact_fragment, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = (View) view.findViewById(R.id.toolbar);
        mToolbarBack = (ImageView) view.findViewById(R.id.toolbar_back);
        mToolbarSave = (ImageView) view.findViewById(R.id.toolbar_filter);
        mToolbarSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateContact();
            }
        });
        mToolbarTittle = (TextView) view.findViewById(R.id.toolbar_name);
        mToolbarBack.setClickable(true);
        mToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getView() !=null) {
                    mEditContactPresenter.onBackClicked();
                    if(getActivity() !=null) {
                        getActivity().onBackPressed();
                    }
                }
            }
        });
        mPhotoContact = (ImageView) view.findViewById(R.id.photo_contact_edit);
        mPhotoContact.setOnClickListener(v -> mEditContactPresenter.onPhotoImageClicked());
        mPhotoContact.setVisibility(View.VISIBLE);


        mFirstName = (EditText) view.findViewById(R.id.first_name_edit_text);
        mSecondName = (EditText) view.findViewById(R.id.second_name_edit_text);
        mPatronymic = (EditText) view.findViewById(R.id.patronymic_edit_text);
        mMainNumber = (EditText) view.findViewById(R.id.main_number_edit_text);
        mSecondNumber = (EditText) view.findViewById(R.id.second_number_edit_text);
        mSecondNumber2 = (EditText) view.findViewById(R.id.second_number2_edit_text);
        mSocialMedia = (EditText) view.findViewById(R.id.social_contacts_edit_text);
        mSocialMedia2 = (EditText) view.findViewById(R.id.social_contacts2_edit_text);
        mSocialMedia3 = (EditText) view.findViewById(R.id.social_contacts3_edit_text);
        mAboutContact = (EditText) view.findViewById(R.id.about_contact_edit_text);

        mSecondTable = (TableRow) view.findViewById(R.id.number_table2_edit_text);
        mSecondTable2 = (TableRow) view.findViewById(R.id.number_table3_edit_text);
        mSocialTable2 = (TableRow) view.findViewById(R.id.social_table2_edit_text);
        mSocialTable3 = (TableRow) view.findViewById(R.id.social_table3_edit_text);
        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.add_row);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopupMenu(getContext(),view);
            }
        });

        if (savedInstanceState == null) {
            mEditContactPresenter.loadContact(mContactId);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mEditContactPresenter.detachView();
        App.get().clearEditContactComponent();
    }

    @Override
    public void updateUI(ContactModel contactsModel) {
        mContactModel = contactsModel;
        if (contactsModel.getContactPhoto() != null) {
            mPhotoContact.setImageURI(Uri.parse(contactsModel.getContactPhoto()));
        }
        editContact(mContactModel);

    }

    @Override
    public void updateContact() {
        if(mMainNumber.getText().toString().equals("")){
            mMainNumber.setHintTextColor(Color.RED);
        } else {
            ContactModel contactModel = new ContactModel(mContactId);
            contactModel.setContactFirstName(mFirstName.getText().toString());
            contactModel.setContactLastName(mSecondName.getText().toString());
            contactModel.setPatronymic(mPatronymic.getText().toString());
            contactModel.setContactMainNumber(mMainNumber.getText().toString());
            contactModel.setContactSecondNumber(mSecondNumber.getText().toString());
            contactModel.setContactSecondNumber2(mSecondNumber2.getText().toString());
            contactModel.setContactSocialMedia(mSocialMedia.getText().toString());
            contactModel.setContactSocialMedia2(mSocialMedia2.getText().toString());
            contactModel.setContactSocialMedia3(mSocialMedia3.getText().toString());
            contactModel.setContactInformation(mAboutContact.getText().toString());
            mEditContactPresenter.updateContact(contactModel);
        }
    }

    @Override
    public void updatePhoto() {
        startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null) {
            Uri uri = data.getData();
            mPhotoContact.setImageURI(uri);
            mEditContactPresenter.photoUriLoaded(uri.toString());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void editContact(ContactModel contactsModel) {
        mFirstName.setText(contactsModel.getContactFirstName());
        mSecondName.setText(contactsModel.getContactLastName());
        mPatronymic.setText(contactsModel.getPatronymic());

        String contactPhoto = contactsModel.getContactPhoto();
        if (contactPhoto == null){
            mPhotoContact.setImageResource(R.drawable.avatar);
        }else {
            mPhotoContact.setImageURI(Uri.parse(contactPhoto));
        }
        mMainNumber.setText(contactsModel.getContactMainNumber());


        String contactSecondNumber = contactsModel.getContactSecondNumber();
        if(contactSecondNumber.equals("")){
            mSecondTable.setVisibility(View.GONE);
        }else {
            mSecondNumber.setText(contactSecondNumber);
        }
        String contactSecondNumber2 = contactsModel.getContactSecondNumber2();
        if(contactSecondNumber2.equals("")){
            mSecondTable2.setVisibility(View.GONE);
        }else {
            mSecondNumber2.setText(contactSecondNumber2);
        }
        mSocialMedia.setText(contactsModel.getContactSocialMedia());

        String contactSocialMedia2= contactsModel.getContactSocialMedia2();
        if(contactSocialMedia2.equals("")){
            mSocialTable2.setVisibility(View.GONE);
        }else {
            mSocialMedia2.setText(contactSocialMedia2);
        }
        String contactSocialMedia3 = contactsModel.getContactSocialMedia3();
        if(contactSocialMedia3.equals("")){
            mSocialTable3.setVisibility(View.GONE);
        }else {
            mSocialMedia3.setText(contactSocialMedia3);
        }
        mAboutContact.setText(contactsModel.getContactInformation());

    }

    @Override
    public void itemAddNumberClicked() {
        if(mSecondTable.getVisibility() == View.GONE){
            mSecondTable.setVisibility(View.VISIBLE);
        }else if(mSecondTable.getVisibility() == View.VISIBLE){
            mSecondTable2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void itemAddSocialClicked() {
        if(mSocialTable2.getVisibility() == View.GONE){
            mSocialTable2.setVisibility(View.VISIBLE);
        }else if(mSocialTable2.getVisibility() == View.VISIBLE){
            mSocialTable3.setVisibility(View.VISIBLE);
        }
    }

    private void createPopupMenu(final Context context, final View view){
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.edit_fb_menu);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mnu_fb_social:
                        mEditContactPresenter.itemAddSocialClicked();
                       return true;

                    case R.id.mnu_fb_number:
                        mEditContactPresenter.itemAddNumberClicked();
                        return true;

                    default:
                        return false;
                }
            }
        });

    }
}
