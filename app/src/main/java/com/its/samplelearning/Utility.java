package com.its.samplelearning;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Utility {
    public final static String SHARED_PREF = "com.its.samplelearning";
    public final static String SHARED_PREF_USERNAME = "username";
    public final static String SHARED_PREF_PASSWORD = "password";
    public final static String SHARED_PREF_FIRSTNAME = "firstname";
    public final static String SHARED_PREF_LASTNAME = "lastname";
    public final static String SHARED_PREF_DESIGNATION = "designation";
    public final static String SHARED_PREF_LOCATION = "location";
    public final static String SHARED_PREF_ABOUT_ME = "aboutMe";
    public final static String SHARED_PREF_ALREADY_LOGGED_IN = "isLoggedIn";

    public static void setToolbar(AppCompatActivity context, String title) {
        Toolbar toolbar = context.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        context.setSupportActionBar(toolbar);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context.getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onBackPressed();
            }
        });
    }
}
