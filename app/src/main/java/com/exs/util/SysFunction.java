package com.exs.util;

import android.app.AlertDialog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by user on 22/02/2017.
 */

public class SysFunction {

    static AlertDialog.Builder alert;


    public static String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }

    public static boolean isValidImei(String s){
        long n = Long.parseLong(s);
        int l = s.length();

        if(l!=15) // If length is not 15 then IMEI is Invalid
            return false;
        else
        {
            int d = 0, sum = 0;
            for(int i=15; i>=1; i--)
            {
                d = (int)(n%10);

                if(i%2 == 0)
                {
                    d = 2*d; // Doubling every alternate digit
                }

                sum = sum + sumDig(d); // Finding sum of the digits

                n = n/10;
            }

            System.out.println("Output : Sum = "+sum);

            if(sum%10==0 && sum!=0)
                return true;
            else
                return false;
        }
    }

    static int sumDig(int n) // SysFunction for finding and returning sum of digits of a number
    {
        int a = 0;
        while(n>0)
        {
            a = a + n%10;
            n = n/10;
        }
        return a;
    }

    public static String setCompleteString(String replace, int max, String data){
        String var = "";
        int total_added = max-data.length();
        for(int i=0;i<total_added;i++){
            var +=replace;
        }
        return var+data;
    }

    public static String getCurrentDateTime(){
        java.text.SimpleDateFormat s = new java.text.SimpleDateFormat("ddMMyyyyhhmmss");
        String var = s.format(new Date());
        return var;
    }



}
