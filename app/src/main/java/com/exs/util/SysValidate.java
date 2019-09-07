package com.exs.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SysValidate {

	public static boolean isEmailValid(String email) {
		boolean isValid = false;

		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = email;

		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}

	public static boolean isSameRetypePassword(String password,
			String confirm_password) {
		boolean isValid = false;
		if (password.equals(confirm_password)) {
			isValid = true;
		}
		return isValid;
	}

	public static boolean isHasLength(String text) {
		boolean isValid = false;
		if (text.length() > 0) {
			isValid = true;
		}
		return isValid;
	}

	public static String isNotNull(String text){
		String isValid = "999";
		if(text.length()>0 && !text.equals(null) && !text.equals("null")){
			isValid = text;
		}
		return isValid;
	}

}
