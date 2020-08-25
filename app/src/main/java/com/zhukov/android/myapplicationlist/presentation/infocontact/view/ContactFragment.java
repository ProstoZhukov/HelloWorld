package com.zhukov.android.myapplicationlist.presentation.infocontact.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zhukov.android.myapplicationlist.R;
import java.util.UUID;


public class ContactFragment extends Fragment {
    public static final String ARG_CONTACT_ID = "contactId";

    private UUID mContactId;
    private ImageView mPhotoContact;
    private TextView mFirstName;
    private TextView mSecondName;
    private TextView mPatronymic;
    private TextView mMainNumber;
    private TextView mSecondNumber;
    private TextView mSocialMedia;
    private TextView mAboutContact;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContactId = UUID.fromString(getArguments().getString(ARG_CONTACT_ID));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_contact_fragment,container,false);
        mPhotoContact = (ImageView) view.findViewById(R.id.photo_contact);
        mFirstName = (TextView) view.findViewById(R.id.first_name);
        mSecondName = (TextView) view.findViewById(R.id.second_name);
        mPatronymic = (TextView) view.findViewById(R.id.patronymic);
        mMainNumber = (TextView) view.findViewById(R.id.main_number);
        mSecondNumber = (TextView) view.findViewById(R.id.second_number);
        mSocialMedia = (TextView) view.findViewById(R.id.social_contacts);
        mAboutContact = (TextView) view.findViewById(R.id.about_contact);

        return view;
    }
}
