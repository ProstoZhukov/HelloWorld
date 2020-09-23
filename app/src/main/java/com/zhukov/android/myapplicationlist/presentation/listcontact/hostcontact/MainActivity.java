package com.zhukov.android.myapplicationlist.presentation.listcontact.hostcontact;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.zhukov.android.myapplicationlist.R;

public class MainActivity extends AppCompatActivity {

    NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        mNavController = Navigation.findNavController(this,R.id.nav_host_fragment);
    }
}