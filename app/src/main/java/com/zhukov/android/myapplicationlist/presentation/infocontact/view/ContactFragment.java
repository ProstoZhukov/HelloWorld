package com.zhukov.android.myapplicationlist.presentation.infocontact.view;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.zhukov.android.myapplicationlist.App;
import com.zhukov.android.myapplicationlist.R;
import com.zhukov.android.myapplicationlist.data.database.model.ContactModel;
import com.zhukov.android.myapplicationlist.di.infocontact.ContactModule;
import com.zhukov.android.myapplicationlist.presentation.editorcontact.view.EditContactFragment;
import com.zhukov.android.myapplicationlist.presentation.infocontact.presenter.IContactPresenter;

import java.util.UUID;

import javax.inject.Inject;


public class ContactFragment extends Fragment implements IContactView{
    public static final String ARG_CONTACT_ID = "contactId";

    private ContactModel mContactModel;

    @Inject
    public IContactPresenter mPresenter;

    private UUID mContactId;
    private ImageView mPhotoContact;
    private TextView mFirstName;
    private TextView mSecondName;
    private TextView mPatronymic;
    private TextView mMainNumber;
    private TextView mSecondNumber;
    private TextView mSecondNumber2;
    private TextView mSocialMedia;
    private TextView mSocialMedia2;
    private TextView mSocialMedia3;
    private TextView mAboutContact;
    private TableRow mSecondTable;
    private TableRow mSecondTable2;
    private TableRow mSocialTable2;
    private TableRow mSocialTable3;

    private ImageView mToolbarBack;
    private ImageView mToolbarEdit;
    private TextView  mToolbarTittle;
    private View mToolbar;




    public ContactFragment(){
        App.get().plusContactModule(new ContactModule()).inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContactId = UUID.fromString(getArguments().getString(ARG_CONTACT_ID));
        }
        mPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.info_contact_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mToolbar = (View) view.findViewById(R.id.toolbar);
        mToolbarBack = (ImageView) view.findViewById(R.id.toolbar_back);
        mToolbarEdit = (ImageView) view.findViewById(R.id.toolbar_edit);
        mToolbarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.itemEditClicked();
            }
        });
        mToolbarTittle = (TextView) view.findViewById(R.id.toolbar_name);
        mToolbarBack.setClickable(true);
        mToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onBackClicked();
            }
        });

        mPhotoContact = (ImageView) view.findViewById(R.id.photo_contact);
        mFirstName = (TextView) view.findViewById(R.id.first_name);
        mSecondName = (TextView) view.findViewById(R.id.second_name);
        mPatronymic = (TextView) view.findViewById(R.id.patronymic);
        mMainNumber = (TextView) view.findViewById(R.id.main_number);
        mSecondNumber = (TextView) view.findViewById(R.id.second_number);
        mSecondNumber2 = (TextView) view.findViewById(R.id.second_number2);
        mSocialMedia = (TextView) view.findViewById(R.id.social_contacts);
        mSocialMedia2 = (TextView) view.findViewById(R.id.social_contacts2);
        mSocialMedia3 = (TextView) view.findViewById(R.id.social_contacts3);
        mAboutContact = (TextView) view.findViewById(R.id.about_contact);

        mSecondTable = (TableRow) view.findViewById(R.id.number_table2);
        mSecondTable2 = (TableRow) view.findViewById(R.id.number_table3);
        mSocialTable2 = (TableRow) view.findViewById(R.id.social_table2);
        mSocialTable3 = (TableRow) view.findViewById(R.id.social_table3);

        mPresenter.loadContact(mContactId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        App.get().clearContactComponent();
    }

    @Override
    public void openEditContactScreen() {
        if (getView() != null) {
            Bundle args = new Bundle();
            args.putString(EditContactFragment.ARG_CONTACT_EDIT_ID, mContactId.toString());
            Navigation.findNavController(getView()).navigate(R.id.editContactFragment,args);
        }
    }

    @Override
    public void goToListContacts() {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void updateUI(ContactModel contactsModel) {
        showContact(contactsModel);
    }

    private void showContact(ContactModel contactModel){
        String contactFirstName = contactModel.getContactFirstName();
        if (contactFirstName.equals("")) {
            mFirstName.setVisibility(View.GONE);
        } else {
            mFirstName.setText(contactFirstName);
        }
        String contactLastName = contactModel.getContactLastName();
        if (contactLastName.equals("")) {
            mSecondName.setVisibility(View.GONE);
        } else {
            mSecondName.setText(contactLastName);
        }
        String patronymic = contactModel.getPatronymic();
        if (patronymic.equals("")) {
            mPatronymic.setVisibility(View.GONE);
        } else {
            mPatronymic.setText(contactLastName);
        }
        String contactPhoto = contactModel.getContactPhoto();
        if (contactPhoto == null) {
            mPhotoContact.setImageResource(R.drawable.avatar);
        } else {
            mPhotoContact.setImageURI(Uri.parse(contactPhoto));
        }
        String contactMainNumber = contactModel.getContactMainNumber();
        if (contactMainNumber.equals("")) {
            mMainNumber.setText(R.string.unknown_number_now);
            mMainNumber.setTextColor(Color.RED);
        } else {
            mMainNumber.setText(contactMainNumber);
            mMainNumber.setTextColor(Color.BLUE);
        }
        String contactSecondNumber= contactModel.getContactSecondNumber();
        if (contactSecondNumber.equals("")) {
            mSecondTable.setVisibility(View.GONE);
        } else {
            mSecondNumber.setText(contactSecondNumber);
            mSecondNumber.setTextColor(Color.BLUE);
        }
        String contactSecondNumber2 = contactModel.getContactSecondNumber2();
        if (contactSecondNumber2.equals("")) {
            mSecondTable2.setVisibility(View.GONE);
        } else {
            mSecondNumber2.setText(contactSecondNumber2);
            mSecondNumber2.setTextColor(Color.BLUE);
        }
        String contactSocialMedia = contactModel.getContactSocialMedia();
        if (contactSocialMedia.equals("")) {
            mSocialMedia.setText(R.string.unknown_info);
        } else {
            mSocialMedia.setText(contactSocialMedia);
            mSocialMedia.setTextColor(Color.BLUE);
        }
        String contactSocialMedia2= contactModel.getContactSocialMedia2();
        if (contactSocialMedia2.equals("")) {
            mSocialTable2.setVisibility(View.GONE);
        } else {
            mSocialMedia2.setText(contactSocialMedia2);
            mSocialMedia2.setTextColor(Color.BLUE);
        }
        String contactSocialMedia3 = contactModel.getContactSocialMedia3();
        if (contactSocialMedia3.equals("")) {
            mSocialTable3.setVisibility(View.GONE);
        } else {
            mSocialMedia3.setText(contactSocialMedia3);
            mSocialMedia3.setTextColor(Color.BLUE);
        }
        String contactInformation = contactModel.getContactInformation();
        if (contactInformation.equals("")) {
            mAboutContact.setText(R.string.unknown_info);
        } else {
            mAboutContact.setText(contactInformation);
        }
    }




}
