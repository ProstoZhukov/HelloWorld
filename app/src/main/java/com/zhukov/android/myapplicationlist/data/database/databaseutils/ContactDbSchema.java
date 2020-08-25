package com.zhukov.android.myapplicationlist.data.database.databaseutils;

public class ContactDbSchema {
    public static final class ContactTable{
        public static final String NAME = "contacts";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String FIRST_NAME = "firstName";
            public static final String LAST_NAME = "lastName";
            public static final String PATRONYMIC = "patronymic";
            public static final String PHOTO_URI = "photoUri";
            public static final String MAIN_NUMBER = "mainNumber";
            public static final String SECOND_NUMBER = "secondNumber";
            public static final String SOCIAL_MEDIA = "socialMedia";
            public static final String ADDITIONAL_INFORMATION = "additionalInformation";
        }
    }
}
