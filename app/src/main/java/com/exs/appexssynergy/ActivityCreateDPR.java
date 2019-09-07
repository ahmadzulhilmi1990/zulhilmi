package com.exs.appexssynergy;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import com.exs.util.Generate;
import com.exs.util.SysMessage;
import com.exs.util.UserPreference;

public class ActivityCreateDPR extends AppCompatActivity {
    private static final String LOG_TAG = "ActivityCreateDPR";
    public Context context = this;

    DatePickerDialog picker;

    // :initialize class
    public com.exs.util.UserPreference UserPreference = new UserPreference();
    public com.exs.util.SysMessage SysMessage = new SysMessage();
    public com.exs.util.Generate Generate = new Generate();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_dpr);

        //configure strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //configure class
        UserPreference.init(context);
        SysMessage.init(context);
        Generate.init(context);

    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

}


