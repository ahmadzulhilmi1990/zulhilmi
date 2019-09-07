package com.exs.conn;

import android.content.Context;

import com.exs.util.SysPara;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class HttpRequestInspectionList {
    private final static String LOG_TAG = "HTTP-REQUEST";

    public static String toHttpGetAPI(Context c, String campaign_id, String token_type, String access_token) {

        String result = "";
        int getResponseCode=400;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(SysPara.API_LIST_MY_INSPECTION+campaign_id+"/inspection");
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod(SysPara.GET);
            conn.addRequestProperty("Authorization", token_type + " " + access_token);
            conn.setRequestProperty("accept", "application/json");
            conn.setDoInput(true);
            conn.setDoOutput(false);
            String body = "id="+campaign_id;

            /*OutputStream output = new BufferedOutputStream(conn.getOutputStream());
            output.write(body.getBytes());
            output.flush();*/
            getResponseCode = conn.getResponseCode();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result += line + "\n";
                }
            }

            //getData();

        } catch (ProtocolException e) {
            e.printStackTrace();
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            conn.disconnect();
        } finally {
            conn.disconnect();
        }
        if(result.length() < 1){
            result = "[]";
        }
        String response = "[{\"getResponseCode\":\"" +String.valueOf(getResponseCode)+ "\",\"data\":" +result+"}]";
        return response;
    }
}
