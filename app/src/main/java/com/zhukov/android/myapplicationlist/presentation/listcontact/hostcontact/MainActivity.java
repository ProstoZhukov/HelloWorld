package com.zhukov.android.myapplicationlist.presentation.listcontact.hostcontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.zhukov.android.myapplicationlist.R;
import com.zhukov.android.myapplicationlist.presentation.infocontact.view.ContactFragment;
import com.zhukov.android.myapplicationlist.presentation.listcontact.view.ContactListFragment;

public class MainActivity extends AppCompatActivity {

    NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        mNavController = Navigation.findNavController(this,R.id.nav_host_fragment);
    }
}