package com.exs.appexssynergy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.exs.util.SysPara;
import com.exs.util.UserPreference;


public class SplashScreen extends Activity{
    private int SPLASH_TIME = 5000;

    // :initialize class
    public UserPreference UserPreference = new UserPreference();
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        UserPreference.init(context);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(SPLASH_TIME);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    String access_token = UserPreference.getStringShared(SysPara.access_token);
                    if(access_token.length() > 0){

                        Intent i = new Intent(SplashScreen.this, ActivityChecklist.class);
                        startActivity(i);
                        finish();

                    }else {

                        Intent i = new Intent(SplashScreen.this, ActivitySignin.class);
                        startActivity(i);
                        finish();
                    }

                }
            }
        };
        timer.start();

    }
}
