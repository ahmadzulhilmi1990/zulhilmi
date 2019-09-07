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

import com.exs.conn.HttpRequestForgotPassword;
import com.exs.conn.HttpRequestSignIn;
import com.exs.util.Generate;
import com.exs.util.SysMessage;
import com.exs.util.SysValidate;
import com.exs.util.UserPreference;

import org.json.JSONArray;

public class ForgotPassword extends Activity {
    private static final String LOG_TAG = "ForgotPassword";
    public Context context = this;
    public Activity activity = this;

    // :initialize class
    public com.exs.util.UserPreference UserPreference = new UserPreference();
    public com.exs.util.SysMessage SysMessage = new SysMessage();
    public com.exs.util.Generate Generate = new Generate();

    // :widget
    EditText etEmail;
    Button btn_submit;
    static ProgressDialog progressDialog;

    // :variable
    String status = "", message = "", data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        //configure strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //configure class
        UserPreference.init(context);
        SysMessage.init(context);
        Generate.init(context);


        etEmail=findViewById(R.id.etEmail);
        btn_submit =findViewById(R.id.btSubmit);


        btn_submit.setOnClickListener(new View.OnClickListener() { //forgot password
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable() == true) {
                    String para1 = etEmail.getText().toString();
                    if(para1!=null){
                        ExecuteForgotPassword ex = new ExecuteForgotPassword();
                        ex.execute(new String[]{para1});
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this, R.style.AppCompatAlertDialogStyle);
                        //builder.setTitle("Authentication error");
                        builder.setMessage(R.string.record_not_found);
                        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });
                        //builder.setNegativeButton("Cancel", null);
                        builder.show();
                    }
                    /*if (SysValidate.isEmailValid(para1)) {

                        ExecuteForgotPassword ex = new ExecuteForgotPassword();
                        ex.execute(new String[]{para1});

                    } else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this, R.style.AppCompatAlertDialogStyle);
                        //builder.setTitle("Authentication error");
                        builder.setMessage(R.string.err_invalid_email);
                        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });
                        //builder.setNegativeButton("Cancel", null);
                        builder.show();


                    }*/
                }else{
                    SysMessage.onToast(getResources().getString(R.string.MSG_NO_INTERNET));
                }
            }
        });
    }

    private class ExecuteForgotPassword extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(ForgotPassword.this, "",
                    getResources().getString(R.string.please_wait));
        }

        @Override
        protected String doInBackground(String... arg0) {

            String result = null;

            try {

                String login_id = arg0[0];

                String responbody = String.valueOf(HttpRequestForgotPassword.toHttpPostAPI(context,login_id));
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
                    Log.d(LOG_TAG, "Forgot password data : " + data);

                    AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this, R.style.AppCompatAlertDialogStyle);
                    //builder.setTitle("Authentication error");
                    builder.setMessage(R.string.forgot_failed);
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(ForgotPassword.this, ActivitySignin.class);
                            startActivity(i);
                            finish();
                            dialog.dismiss();

                        }
                    });
                    //builder.setNegativeButton("Cancel", null);
                    builder.show();
                }
            }else{

                AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this, R.style.AppCompatAlertDialogStyle);
                //builder.setTitle("Authentication error");
                builder.setMessage(R.string.forgot_failed);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
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
