package com.exs.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exs.appexssynergy.ActivitySignin;
import com.exs.appexssynergy.R;
import com.exs.conn.HttpRequestFullApi;
import com.exs.util.DataProcessing;
import com.exs.util.Generate;
import com.exs.util.SysMessage;
import com.exs.util.SysPara;
import com.exs.util.UserPreference;

import org.json.JSONArray;

public class FragmentDataManagement extends Fragment {

    private static final String LOG_TAG = "DataManagement";

    // :initialize class
    public com.exs.util.UserPreference UserPreference = new UserPreference();
    public com.exs.util.SysMessage SysMessage = new SysMessage();
    public com.exs.util.Generate Generate = new Generate();
    private Handler handler = new Handler();
    public DataProcessing dataProcessing = new DataProcessing();

    View view,view_data_download;
    LinearLayout layout_download_data;
    TextView txt_click_download_data;
    TextView txt_total_campaign;
    ProgressBar progressBarCampaign;

    // :variable
    String status = "", message = "", data = "";
    int pStatus,pCampaign = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_data_management, container, false);
        String strtext = getArguments().getString("title");
        getActivity().setTitle(strtext);

        //configure strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //configure class
        UserPreference.init(getContext());
        SysMessage.init(getContext());
        Generate.init(getContext());
        dataProcessing.init(getContext());

        //configure view
        view_data_download = (View)view.findViewById(R.id.view_data_download);
        txt_click_download_data = (TextView)view.findViewById(R.id.txt_click_download_data);
        layout_download_data = (LinearLayout)view.findViewById(R.id.layout_download_data);

        //configure download view
        txt_total_campaign = (TextView)view.findViewById(R.id.txt_total_campaign);
        progressBarCampaign = (ProgressBar) view.findViewById(R.id.progressBarCampaign);

        //click text bottom
        layout_download_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_data_download.setVisibility(View.VISIBLE);
                DialogResetData();
            }
        });
        return view;
    }

    private void onProgress(final TextView txtProgress,final ProgressBar progressBar,String code){

        if(code.equals("Campaign")){
            pStatus = pCampaign;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus <= 100) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(pStatus);
                            if(pStatus < 101) {
                                txtProgress.setText(pStatus + " %");
                            }

                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
            }
        }).start();
    }

    private class ExecuteMyCampaign extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... arg0) {
            String result = null;
            try {
                //String responbody = String.valueOf(HttpRequestMyCampaign.toHttpPostAPI(getContext(),UserPreference.getStringShared(SysPara.token_type),UserPreference.getStringShared(SysPara.access_token)));
                String responbody = String.valueOf(HttpRequestFullApi.toHttpPostAPI(getContext(),UserPreference.getStringShared(SysPara.token_type),UserPreference.getStringShared(SysPara.access_token)));
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
            if(result.equals("200")){
                if(data.length()>5) {
                    if(data.contains("mycampaign")){
                        Log.d(LOG_TAG, "Data mycampaign : " + data);
                        dataProcessing.insertMyCampaign(data);
                    }else{
                        sessionExpired();
                    }
                }
            }else{
                sessionExpired();
            }
        }
    }

    public void DialogResetData(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Download");
        builder.setMessage("Data updated will be lost, are you sure to download new data?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                if(isNetworkAvailable() == true) {

                    onProgress(txt_total_campaign,progressBarCampaign,"Campaign");
                    ExecuteMyCampaign ex = new ExecuteMyCampaign();
                    ex.execute();

                }else {
                    SysMessage.onToast(getResources().getString(R.string.MSG_NO_INTERNET));
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.show();
    }

    public void DialogDownloadEnd(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Download");
        builder.setMessage("Successfully download data.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void sessionExpired(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Authentication error");
        builder.setMessage("Invalid Token.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                UserPreference.removeAllString();
                Intent to = new Intent(getContext(), ActivitySignin.class);
                startActivity(to);

            }
        });
        builder.show();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
