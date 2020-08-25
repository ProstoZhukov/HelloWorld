package com.zhukov.android.myapplicationlist.data.database.model;


import java.util.UUID;

public class ContactModel{
    private UUID mContactId;
   private String mContactFirstName;
   private String mContactLastName;
   private String mPatronymic;
   private String mContactPhoto;
   private String mContactMainNumber;
   private String mContactSecondNumber;
   private String mContactSocialMedia;
   private String mContactInformation;

   public ContactModel(){
       this(UUID.randomUUID());
   }

   public ContactModel(UUID id){
       mContactId = id;
   }

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

    public String getContactSocialMedia() {
        return mContactSocialMedia;
    }

    public void setContactSocialMedia(String contactSocialMedia) {
        mContactSocialMedia = contactSocialMedia;
    }

    public String getContactInformation() {
        return mContactInformation;
    }

    public void setContactInformation(String contactInformation) {
        mContactInformation = contactInformation;
    }
}
