package com.exs.appexssynergy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.exs.conn.HttpRequestSignIn;
import com.exs.util.Generate;
import com.exs.util.SysMessage;
import com.exs.util.UserPreference;

import org.json.JSONArray;

public class ActivitySignin extends Activity {

    private static final String LOG_TAG = "ActivitySignin";
    public Context context = this;
    public Activity activity = this;

    // :initialize class
    public UserPreference UserPreference = new UserPreference();
    public SysMessage SysMessage = new SysMessage();
    public Generate Generate = new Generate();

    // :widget
    EditText username, password;
    TextView txt_forgot;
    Button btn_login;
    static ProgressDialog progressDialog;

    // :variable
    String status = "", message = "", data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //configure strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //configure class
        UserPreference.init(context);
        SysMessage.init(context);
        Generate.init(context);

        username=findViewById(R.id.etUsername);
        password=findViewById(R.id.etPassword);
        btn_login =findViewById(R.id.btLogin);
        txt_forgot= findViewById(R.id.txForgot);

        txt_forgot.setOnClickListener(new View.OnClickListener() { //forgot password
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivitySignin.this, ForgotPassword.class);
                startActivity(i);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() { //login
            public void onClick(View v) {


                if(isNetworkAvailable() == true) {
                    String para1 = username.getText().toString();
                    String para2 = password.getText().toString();

                    if (!username.getText().toString().equals("") && !password.getText().toString().equals("")) {

                        ExecuteSignIn ex = new ExecuteSignIn();
                        ex.execute(new String[]{para1, para2});

                    } else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(ActivitySignin.this, R.style.AppCompatAlertDialogStyle);
                        builder.setTitle("Authentication error");
                        builder.setMessage("Please fill in username and password.");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });
                        //builder.setNegativeButton("Cancel", null);
                        builder.show();


                    }
                }else{
                    SysMessage.onToast(getResources().getString(R.string.MSG_NO_INTERNET));
                }
            }
        });
    }

    private class ExecuteSignIn extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(ActivitySignin.this, "",
                    getResources().getString(R.string.please_wait));
        }

        @Override
        protected String doInBackground(String... arg0) {

            String result = null;

            try {

                String username = arg0[0];
                String password = arg0[1];

                String responbody = String.valueOf(HttpRequestSignIn.toHttpPostAPI(context,"",username,password,"","",""));
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
                    Generate.generateToken(data);

                    Intent i = new Intent(ActivitySignin.this, ActivityChecklist.class);
                    startActivity(i);
                    finish();

                }
            }else{

                AlertDialog.Builder builder = new AlertDialog.Builder(ActivitySignin.this, R.style.AppCompatAlertDialogStyle);
                builder.setTitle("Authentication error");
                builder.setMessage("Incorrect username and password.");
                //builder.setPositiveButton("OK", null);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                //builder.setNegativeButton("Cancel", null);
                builder.show();
            }

            if(data.length()>0) {
                //SysMessage.onToast(data);
            }
         }

    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }



}
