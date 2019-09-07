package com.exs.appexssynergy;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.exs.conn.HttpRequestTestToken;
import com.exs.fragment.FragmentCampaign;
import com.exs.fragment.FragmentDPR;
import com.exs.fragment.FragmentDashboard;
import com.exs.fragment.FragmentDataManagement;
import com.exs.util.Generate;
import com.exs.util.SysMessage;
import com.exs.util.SysPara;
import com.exs.util.UserPreference;

import org.json.JSONArray;
import org.json.JSONException;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = "Dashboard";
    public Context context = this;
    static ProgressDialog progressDialog;

    // :initialize class
    public UserPreference UserPreference = new UserPreference();
    public SysMessage SysMessage = new SysMessage();
    public Generate Generate = new Generate();

    // :variable
    String status = "", message = "", data = "";

    // :widget
    TextView text_fullname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int orientation=this.getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // code for portrait mode
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            // code for landscape mode
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        //TextView navUsername = (TextView) headerView.findViewById(R.id.text_fullname);
        //navUsername.setText("Your Text Here");

        text_fullname = headerView.findViewById(R.id.text_fullname);

        //configure class
        UserPreference.init(context);
        SysMessage.init(context);
        Generate.init(context);

       // ExecuteTestToken ex = new ExecuteTestToken();
        //ex.execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        Class fragmentClass = null;
        Bundle bundle = new Bundle();

        if (id == R.id.nav_home) {
            // Handle the camera action
            bundle.putString("title", "Dashboard");
            fragmentClass = FragmentDashboard.class;

        }else if (id == R.id.nav_dpr) {

            bundle.putString("title", "DPR List");
            fragmentClass = FragmentDPR.class;

        }else if (id == R.id.nav_campaign) {

            bundle.putString("title", "Campaign");
            fragmentClass = FragmentCampaign.class;

        }else if (id == R.id.nav_data_management) {

            bundle.putString("title", "Data Management");
            fragmentClass = FragmentDataManagement.class;

        }else if (id == R.id.nav_logout) {
            UserPreference.removeAllString();
            Intent to = new Intent(context,ActivitySignin.class);
            startActivity(to);
            finish();
        }


        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class ExecuteTestToken extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(Dashboard.this, "",
                    getResources().getString(R.string.please_wait));
        }

        @Override
        protected String doInBackground(String... arg0) {

            String result = null;

            try {

                String responbody = String.valueOf(HttpRequestTestToken.toHttpPostAPI(context,UserPreference.getStringShared(SysPara.token_type),UserPreference.getStringShared(SysPara.access_token)));
                Log.d(LOG_TAG, "responbody : " + responbody);
                JSONArray dataJSONArray = new JSONArray(responbody);

                if (dataJSONArray.length() > 0) {

                    for (int i = 0; i < dataJSONArray.length(); i++) {

                        status = dataJSONArray.getJSONObject(i).getString("getResponseCode").toString();
                        data = dataJSONArray.getJSONObject(i).getString("data").toString();

                    }
                }

                result = status;

            } catch (Exception e) {
                Log.d(LOG_TAG, e.getMessage());
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(LOG_TAG, "result: " + result);
            progressDialog.dismiss();

            if(result.equals("200")){

                if(data.length()>5) {
                    Log.d(LOG_TAG, "Data User : " + data);
                    Generate.generateUser(data);
                    getView(data);
                }
            }else{

                AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this, R.style.AppCompatAlertDialogStyle);
                builder.setTitle("Authentication error");
                builder.setMessage("Invalid Token.");
                //builder.setPositiveButton("OK", null);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        UserPreference.removeAllString();
                        Intent to = new Intent(context,ActivitySignin.class);
                        startActivity(to);
                        finish();

                    }
                });
                //builder.setNegativeButton("Cancel", null);
                builder.show();
            }

        }

    }

    public void getView(String json){

        JSONArray dataJSONArray = null;
        try {
            dataJSONArray = new JSONArray("["+json+"]");

            if (dataJSONArray.length() > 0) {

                for (int i = 0; i < dataJSONArray.length(); i++) {

                    String full_name = dataJSONArray.getJSONObject(i).getString("full_name").toString();

                    // :setText
                    text_fullname.setText(full_name);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
