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

public class HttpRequestUserDetails {

	private final static String LOG_TAG = "HTTP-REQUEST";

	public static String toHttpPostAPI(Context c, String grant_type,String username, String password, String client_id, String client_secret, String scope) {

		String result = "";
		int getResponseCode=400;
		HttpURLConnection conn = null;
		try {
			URL url = new URL(SysPara.API_SIGN_IN);
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod(SysPara.POST);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			String body = "grant_type="+grant_type;
					body +="&username="+username;
					body += "&password="+password;
					body += "&scope="+scope;
					body += "&client_id="+client_id;
					body += "&client_secret="+client_secret;

			OutputStream output = new BufferedOutputStream(conn.getOutputStream());
			output.write(body.getBytes());
			output.flush();
			getResponseCode = conn.getResponseCode();

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				String line = null;
				while ((line = reader.readLine()) != null) {
					result += line + "\n";
				}
			}


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
