package com.exs.appexssynergy;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.exs.util.Generate;
import com.exs.util.SysMessage;
import com.exs.util.UserPreference;

public class ActivityChecklist extends ListActivity {
    private static final String LOG_TAG = "ActivityChecklist";
    public Context context = this;
    public Activity activity = this;

    // :initialize class
    public com.exs.util.UserPreference UserPreference = new UserPreference();
    public com.exs.util.SysMessage SysMessage = new SysMessage();
    public com.exs.util.Generate Generate = new Generate();

    // :widget
    EditText username, password;
    TextView txt_forgot;
    Button btnProceed;
    static ProgressDialog progressDialog;

    // :variable
    String status = "", message = "", data = "";

    /** String array used as the datasource for the ArrayAdapter of the listview **/
    String[] countries = new String[] {
            "Personal Protective Equipment (PPE) and Safety Equipment shall be used at all time",
            "Work scope shall be clearly confirmed in writing by client.",
            "JSA has been prepared with approved work permit",
            "Client Site Representative shall be duly informed and permission granted prior to the commencement of work.",
            "Lockout-tagout (LOTO) procedure shall be carried out if required, ascertaining that no workers are working on the electrical items prior to carrying out the work.",
            "All electrical circuits shall be de-energized before commencement of work.",
            "All tools used to carry out the work activities shall be appropriate for the work, adequately insulated, in good working condition and of the correct size.",
            "Suitable measuring equipment shall be used to ensure that the electrical circuit is safe and not connected to any electrical supply.",
            "The work shall not be carried out in wet areas or slippery conditions."
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_checklist);

        //configure strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //configure class
        UserPreference.init(context);
        SysMessage.init(context);
        Generate.init(context);

        btnProceed=findViewById(R.id.btnProceed);

        /** Defining array adapter to store items for the listview **/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, countries);

        /** Setting the arrayadapter for this listview  **/
        getListView().setAdapter(adapter);

        /** Defining checkbox click event listener **/
        View.OnClickListener clickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CheckBox chk = (CheckBox) v;
                int itemCount = getListView().getCount();
                int cnt=0;
                for(int i=0 ; i < itemCount ; i++){
                    getListView().setItemChecked(i, chk.isChecked());
                    if(chk.isChecked()){
                        cnt++;
                    }
                }

                if(cnt>=9){
                    btnProceed.setBackgroundColor(Color.parseColor("#67c23a"));
                    btnProceed.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent i = new Intent(ActivityChecklist.this, Dashboard.class);
                            startActivity(i);
                            finish();
                        }
                    });
                }else{
                    btnProceed.setBackgroundColor(getResources().getColor(R.color.gray_slow));
                    btnProceed.setClickable(false);
                }
            }
        };

        /** Defining click event listener for the listitem checkbox */
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                CheckBox chk = (CheckBox) findViewById(R.id.chkAll);
                int checkedItemCount = getCheckedItemCount();

                if(getListView().getCount()==checkedItemCount)
                    chk.setChecked(true);
                else
                    chk.setChecked(false);

                if(checkedItemCount>=9){
                    btnProceed.setBackgroundColor(Color.parseColor("#67c23a"));
                    btnProceed.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent i = new Intent(ActivityChecklist.this, Dashboard.class);
                            startActivity(i);
                            finish();
                        }
                    });
                }else{
                    btnProceed.setBackgroundColor(getResources().getColor(R.color.gray_slow));
                    btnProceed.setClickable(false);
                }
            }
        };

        /** Getting reference to checkbox available in the main.xml layout */
        CheckBox chkAll =  ( CheckBox ) findViewById(R.id.chkAll);

        /** Setting a click listener for the checkbox **/
        chkAll.setOnClickListener(clickListener);

        /** Setting a click listener for the listitem checkbox **/
        getListView().setOnItemClickListener(itemClickListener);

    }

    /**
     *
     * Returns the number of checked items
     */
    private int getCheckedItemCount(){
        int cnt = 0;
        SparseBooleanArray positions = getListView().getCheckedItemPositions();
        int itemCount = getListView().getCount();

        for(int i=0;i<itemCount;i++){
            if(positions.get(i))
                cnt++;
        }
        return cnt;
    }
}
