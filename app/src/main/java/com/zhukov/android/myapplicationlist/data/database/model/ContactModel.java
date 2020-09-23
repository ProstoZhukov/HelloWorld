package com.zhukov.android.myapplicationlist.data.database.model;


import java.util.UUID;

public class ContactModel {
    private UUID mContactId;
    private String mContactFirstName = "";
    private String mContactLastName = "";
    private String mPatronymic = "";
    private String mContactPhoto;
    private String mContactMainNumber = "";
    private String mContactSecondNumber = "";
    private String mContactSecondNumber2 = "";
    private String mContactSocialMedia = "";
    private String mContactSocialMedia2 = "";
    private String mContactSocialMedia3 = "";
    private String mContactInformation = "";


    public ContactModel() {
        mContactId = UUID.randomUUID();
    }

    public ContactModel(UUID id) {
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
}
