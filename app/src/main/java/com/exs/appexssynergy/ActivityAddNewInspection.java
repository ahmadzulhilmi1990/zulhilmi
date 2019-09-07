package com.exs.appexssynergy;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.exs.adapter.EquipmentAdapter;
import com.exs.adapter.InspectionItemsAdapter;
import com.exs.orm.Equipment;
import com.exs.util.Generate;
import com.exs.util.SysMessage;
import com.exs.util.UserPreference;

import java.util.ArrayList;
import java.util.List;

public class ActivityAddNewInspection extends AppCompatActivity {
    private static final String LOG_TAG = "ActivityAddNewInspection";
    public Context context = this;

    DatePickerDialog picker;

    // :initialize class
    public com.exs.util.UserPreference UserPreference = new UserPreference();
    public com.exs.util.SysMessage SysMessage = new SysMessage();
    public com.exs.util.Generate Generate = new Generate();

    // :widget
    TextView txt_back;
    ListView listview;
    EditText et_search;

    // :variable
    String campaign_id;

    List<Equipment> equipment;
    ArrayList<Equipment> equipmentArrayList;
    InspectionItemsAdapter adapter_content = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_new_inspection);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        //configure strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //configure class
        UserPreference.init(context);
        SysMessage.init(context);
        Generate.init(context);

        //initialize widget
        txt_back = (TextView) findViewById(R.id.txt_back);
        listview = (ListView) findViewById(R.id.list);
        et_search = (EditText) findViewById(R.id.et_search);

        // get Intent Extra
        Intent intent = getIntent();
        campaign_id=intent.getStringExtra("campaign_id");
        Log.d(LOG_TAG,"campaign_id = " + campaign_id);

        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getAllEquipments();

        et_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                Log.i(LOG_TAG, "Typed=" + s.toString());
                if(s.toString().length() > 1) {
                    getEquipmentTag(s.toString());
                }else{
                    getAllEquipments();
                }
            }

        });
    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
    }

    public void getEquipmentTag(String tag){
        equipment= Equipment.findWithQuery(Equipment.class,"SELECT * FROM Equipment WHERE DESCRIPTION LIKE "+"'%"+tag.trim()+"%' OR ITEMTAGNO LIKE "+"'%"+tag.trim()+"%'");

        equipmentArrayList= new ArrayList<>();

        if(equipment.size() > 0 ) {

            //listView.setVisibility(View.VISIBLE);

            for (int i = 0; i < equipment.size(); i++) {

                int count = i+1;

                equipmentArrayList.add(new Equipment(String.valueOf(count),equipment.get(i).getEquipment_id(),equipment.get(i).getDescription(),equipment.get(i).getItem_tag_no(),
                        equipment.get(i).getEquipment_type_id(),equipment.get(i).getFacility_id(),equipment.get(i).getReference_no(),equipment.get(i).getSub_system(),
                        equipment.get(i).getSub_system_desc(),equipment.get(i).getModule_area_id(),equipment.get(i).getData_sheet(),equipment.get(i).getDrawing_ref(),
                        equipment.get(i).getArea_class_id(),equipment.get(i).getArea_temp_class_id(),equipment.get(i).getArea_gas_group_id(),equipment.get(i).getEquipment_manufacturer(),
                        equipment.get(i).getModel_type(),equipment.get(i).getEquipment_serial_no(),equipment.get(i).getEquipment_temp_class_id(),equipment.get(i).getEquipment_gas_group_id(),
                        equipment.get(i).getEx_cert_no(),equipment.get(i).getEquipment_zone_id(),equipment.get(i).getEquipment_ip_rating1_id(),equipment.get(i).getEquipment_ip_rating2_id(),equipment.get(i).getUrl()));

            }

            //set equipment adapter
            EquipmentAdapter adapter= new EquipmentAdapter(context, equipmentArrayList, campaign_id);
            adapter.notifyDataSetChanged();
            listview.setAdapter(adapter);
        }else{
            //no_record.setVisibility(View.VISIBLE);
            listview.setVisibility(View.GONE);
        }
    }

    public void getAllEquipments(){
        equipment=Equipment.listAll(Equipment.class);

        equipmentArrayList= new ArrayList<>();
        equipmentArrayList.clear();

        if(equipment.size()>0) {

            //adapter_content=new InspectionItemsAdapter();
            //adapter_content.clear();
            for (int i = 0; i < equipment.size(); i++) {

                int count = i+1;

                equipmentArrayList.add(new Equipment(String.valueOf(count),equipment.get(i).getEquipment_id(),equipment.get(i).getDescription(),equipment.get(i).getItem_tag_no(),
                        equipment.get(i).getEquipment_type_id(),equipment.get(i).getFacility_id(),equipment.get(i).getReference_no(),equipment.get(i).getSub_system(),
                        equipment.get(i).getSub_system_desc(),equipment.get(i).getModule_area_id(),equipment.get(i).getData_sheet(),equipment.get(i).getDrawing_ref(),
                        equipment.get(i).getArea_class_id(),equipment.get(i).getArea_temp_class_id(),equipment.get(i).getArea_gas_group_id(),equipment.get(i).getEquipment_manufacturer(),
                        equipment.get(i).getModel_type(),equipment.get(i).getEquipment_serial_no(),equipment.get(i).getEquipment_temp_class_id(),equipment.get(i).getEquipment_gas_group_id(),
                        equipment.get(i).getEx_cert_no(),equipment.get(i).getEquipment_zone_id(),equipment.get(i).getEquipment_ip_rating1_id(),equipment.get(i).getEquipment_ip_rating2_id(),equipment.get(i).getUrl()));

            }
            //set equipment adapter
            EquipmentAdapter adapter= new EquipmentAdapter(context, equipmentArrayList,campaign_id);
            adapter.notifyDataSetChanged();
            listview.setAdapter(adapter);

        }else{
            //no_record.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

}


