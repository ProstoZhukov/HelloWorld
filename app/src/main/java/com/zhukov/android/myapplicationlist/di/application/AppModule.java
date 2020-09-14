package com.zhukov.android.myapplicationlist.di.application;

import android.content.Context;

import com.zhukov.android.myapplicationlist.data.database.IDataBaseProvider;
import com.zhukov.android.myapplicationlist.data.database.MyContactSQLite.IMyContactSQLite;
import com.zhukov.android.myapplicationlist.data.database.MyContactSQLite.MyContactSQLite;
import com.zhukov.android.myapplicationlist.data.database.SQLiteDbProvider;
import com.zhukov.android.myapplicationlist.data.database.databaseutils.ContactBaseHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return mContext;
    }

    @Provides
    @Singleton
    IMyContactSQLite provideMyContactSQLite(Context context) {
        return new MyContactSQLite(new ContactBaseHelper(context));
    }

    @Provides
    @Singleton
    IDataBaseProvider provideDateBaseProvider(IMyContactSQLite myContactSQLite) {
        return new SQLiteDbProvider(myContactSQLite);
    }
}