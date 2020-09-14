package com.zhukov.android.myapplicationlist.data.database.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class ContactModel implements Parcelable {
    private UUID mContactId;
    private String mContactFirstName="";
    private String mContactLastName="";
    private String mPatronymic="";
    private String mContactPhoto;
    private String mContactMainNumber="";
    private String mContactSecondNumber="";
    private String mContactSecondNumber2="";
    private String mContactSocialMedia="";
    private String mContactSocialMedia2="";
    private String mContactSocialMedia3="";
    private String mContactInformation="";


   public ContactModel(){
       mContactId = UUID.randomUUID();
   }

   public ContactModel(UUID id){
       mContactId = id;
   }

    protected ContactModel(Parcel in) {
        mContactFirstName = in.readString();
        mContactLastName = in.readString();
        mPatronymic = in.readString();
        mContactPhoto = in.readString();
        mContactMainNumber = in.readString();
        mContactSecondNumber = in.readString();
        mContactSecondNumber2 = in.readString();
        mContactSocialMedia = in.readString();
        mContactSocialMedia2 = in.readString();
        mContactSocialMedia3 = in.readString();
        mContactInformation = in.readString();
    }

    public static final Creator<ContactModel> CREATOR = new Creator<ContactModel>() {
        @Override
        public ContactModel createFromParcel(Parcel in) {
            return new ContactModel(in);
        }

        @Override
        public ContactModel[] newArray(int size) {
            return new ContactModel[size];
        }
    };

    public UUID getContactId() {
        return mContactId;
    }

    public String getContactFirstName() {
        return mContactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        mContactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return mContactLastName;
    }

    public void setContactLastName(String contactLastName) {
        mContactLastName = contactLastName;
    }

    public String getPatronymic() {
        return mPatronymic;
    }

    public void setPatronymic(String patronymic) {
        mPatronymic = patronymic;
    }

    public String getContactPhoto() {
        return mContactPhoto;
    }

    public void setContactPhoto(String contactPhoto) {
        mContactPhoto = contactPhoto;
    }

    public String getContactMainNumber() {
        return mContactMainNumber;
    }

    public void setContactMainNumber(String contactMainNumber) {
        mContactMainNumber = contactMainNumber;
    }

    public String getContactSecondNumber() {
        return mContactSecondNumber;
    }

    public void setContactSecondNumber(String contactSecondNumber) {
        mContactSecondNumber = contactSecondNumber;
    }

    public String getContactSecondNumber2() {
        return mContactSecondNumber2;
    }

    public void setContactSecondNumber2(String contactSecondNumber2) {
        mContactSecondNumber2 = contactSecondNumber2;
    }

    public String getContactSocialMedia() {
        return mContactSocialMedia;
    }

    public void setContactSocialMedia(String contactSocialMedia) {
        mContactSocialMedia = contactSocialMedia;
    }

    public String getContactSocialMedia2() {
        return mContactSocialMedia2;
    }

    public void setContactSocialMedia2(String contactSocialMedia2) {
        mContactSocialMedia2 = contactSocialMedia2;
    }

    public String getContactSocialMedia3() {
        return mContactSocialMedia3;
    }

    public void setContactSocialMedia3(String contactSocialMedia3) {
        mContactSocialMedia3 = contactSocialMedia3;
    }

    public String getContactInformation() {
        return mContactInformation;
    }

    public void setContactInformation(String contactInformation) {
        mContactInformation = contactInformation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mContactFirstName);
        parcel.writeString(mContactLastName);
        parcel.writeString(mPatronymic);
        parcel.writeString(mContactPhoto);
        parcel.writeString(mContactMainNumber);
        parcel.writeString(mContactSecondNumber);
        parcel.writeString(mContactSecondNumber2);
        parcel.writeString(mContactSocialMedia);
        parcel.writeString(mContactSocialMedia2);
        parcel.writeString(mContactSocialMedia3);
        parcel.writeString(mContactInformation);
    }
}
