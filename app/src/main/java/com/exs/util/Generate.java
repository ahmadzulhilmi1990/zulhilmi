package com.exs.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.orm.SugarContext;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by user on 20/12/2018.
 */

public class Generate {

    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    public UserPreference UserPreference = new UserPreference();

    public void init(Context c){
        settings = c.getSharedPreferences(PREFS_NAME, 0);
        UserPreference.init(c);

        //init
        SugarContext.init(c);
    }

    public void generateToken(String json){

        JSONArray dataJSONArray = null;
        try {
            dataJSONArray = new JSONArray("["+json+"]");

            if (dataJSONArray.length() > 0) {

                for (int i = 0; i < dataJSONArray.length(); i++) {

                    String access_token = dataJSONArray.getJSONObject(i).getString("access_token").toString();
                    String token_type = dataJSONArray.getJSONObject(i).getString("token_type").toString();

                    // :sharedpreferred
                    UserPreference.putString(SysPara.access_token,access_token);
                    UserPreference.putString(SysPara.token_type,token_type);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void generateUser(String json){

        JSONArray dataJSONArray = null;
        try {
            dataJSONArray = new JSONArray("["+json+"]");

            if (dataJSONArray.length() > 0) {

                for (int i = 0; i < dataJSONArray.length(); i++) {

                    String user_id = dataJSONArray.getJSONObject(i).getString("id").toString();
                    String login_id = dataJSONArray.getJSONObject(i).getString("login_id").toString();
                    String full_name = dataJSONArray.getJSONObject(i).getString("full_name").toString();
                    String code = dataJSONArray.getJSONObject(i).getString("code").toString();
                    String email = dataJSONArray.getJSONObject(i).getString("email").toString();
                    int user_type_id = dataJSONArray.getJSONObject(i).getInt("user_type_id");
                    String contact_no = dataJSONArray.getJSONObject(i).getString("contact_no").toString();
                    String designation = dataJSONArray.getJSONObject(i).getString("designation").toString();
                    String client_id = dataJSONArray.getJSONObject(i).getString("client_id").toString();
                    String iecex_cert_no = dataJSONArray.getJSONObject(i).getString("iecex_cert_no").toString();
                    String pcar_ref_no = dataJSONArray.getJSONObject(i).getString("pcar_ref_no").toString();
                    String password = dataJSONArray.getJSONObject(i).getString("password").toString();


                    // :sharedpreferred
                    UserPreference.putString(SysPara.user_id,user_id);
                    UserPreference.putString(SysPara.login_id,login_id);
                    UserPreference.putString(SysPara.full_name,full_name);
                    UserPreference.putString(SysPara.code,code);
                    UserPreference.putString(SysPara.email,email);
                    UserPreference.putString(SysPara.user_type_id,String.valueOf(user_type_id));
                    UserPreference.putString(SysPara.contact_no,contact_no);
                    UserPreference.putString(SysPara.designation,designation);
                    UserPreference.putString(SysPara.client_id,client_id);
                    UserPreference.putString(SysPara.iecex_cert_no,iecex_cert_no);
                    UserPreference.putString(SysPara.pcar_ref_no,pcar_ref_no);
                    UserPreference.putString(SysPara.password,password);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
