package com.exs.appexssynergy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import com.exs.adapter.ListViewItemCheckboxBaseAdapter;
import com.exs.adapter.ListViewItemDTO;
import com.exs.util.Generate;
import com.exs.util.SysMessage;
import com.exs.util.UserPreference;

import java.util.ArrayList;
import java.util.List;

public class ActivitySafetyPrecautions extends Activity {

    private static final String LOG_TAG = "ActivitySafetyPrecautions";
    public Context context = this;
    public Activity activity = this;
    ListViewItemCheckboxBaseAdapter listViewDataAdapter = null;

    // :initialize class
    public UserPreference UserPreference = new UserPreference();
    public SysMessage SysMessage = new SysMessage();
    public Generate Generate = new Generate();

    // :widget

    // :variable
    String status = "", message = "", data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_precautions);

        //configure strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //configure class
        UserPreference.init(context);
        SysMessage.init(context);
        Generate.init(context);

        // Get listview checkbox.
        final ListView listViewWithCheckbox = (ListView)findViewById(R.id.listview);

        // Initiate listview data.
        final List<ListViewItemDTO> initItemList = this.getInitViewItemDtoList();

        // Create a custom list view adapter with checkbox control.
        listViewDataAdapter = new ListViewItemCheckboxBaseAdapter(getApplicationContext(), initItemList);

        //listViewDataAdapter.notifyDataSetChanged();

        // Set data adapter to list view.
        listViewWithCheckbox.setAdapter(listViewDataAdapter);

        listViewWithCheckbox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long l) {
                // Get user selected item.
                Object itemObject = adapterView.getAdapter().getItem(itemIndex);

                // Translate the selected item to DTO object.
                ListViewItemDTO itemDto = (ListViewItemDTO)itemObject;

                // Get the checkbox.
                CheckBox itemCheckbox = (CheckBox) view.findViewById(R.id.list_view_item_checkbox);
                itemCheckbox.setSelected(itemCheckbox.isChecked());
                // Reverse the checkbox and clicked item check state.
                if(itemDto.isChecked())
                {
                    itemCheckbox.setChecked(false);
                    itemDto.setChecked(false);
                }else
                {
                    itemCheckbox.setChecked(true);
                    itemDto.setChecked(true);
                }

                //Toast.makeText(getApplicationContext(), "select item text : " + itemDto.getItemText(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onResume(){
        super.onResume();

        int count = listViewDataAdapter.getCurrentCount();
        Log.d("Count", "count = " + count);

    }

    public void toDashboard(){
        Intent i = new Intent(ActivitySafetyPrecautions.this, Dashboard.class);
        startActivity(i);
        finish();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    private List<ListViewItemDTO> getInitViewItemDtoList()
    {
        String itemTextArr[] = {
                "Personal Protective Equipment (PPE) and Safety Equipment shall be used at all time",
                "Work scope shall be clearly confirmed in writing by client.",
                "JSA has been prepared with approved work permit",
                "Client Site Representative shall be duly informed and permission granted prior to the commencement of work.",
                "Lockout-tagout (LOTO) procedure shall be carried out if required, ascertaining that no workers are working on the electrical items prior to carrying out the work.",
                "All electrical circuits shall be de-energized before commencement of work.",
                "All tools used to carry out the work activities shall be appropriate for the work, adequately insulated, in good working condition and of the correct size.",
                "Suitable measuring equipment shall be used to ensure that the electrical circuit is safe and not connected to any electrical supply.",
                "The work shall not be carried out in wet areas or slippery conditions."};

        List<ListViewItemDTO> ret = new ArrayList<ListViewItemDTO>();

        int length = itemTextArr.length;

        for(int i=0;i<length;i++)
        {
            String itemText = itemTextArr[i];

            ListViewItemDTO dto = new ListViewItemDTO();
            dto.setChecked(false);
            dto.setItemText(itemText);
            ret.add(dto);
        }

        return ret;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }


}
