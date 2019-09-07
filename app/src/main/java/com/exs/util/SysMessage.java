package com.exs.util;

import android.content.Context;
import android.widget.Toast;

public class SysMessage {

	Context context;

	public void init(Context c){
		context = c;
	}

	public void onToast(String msg){
		Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
	}
}
