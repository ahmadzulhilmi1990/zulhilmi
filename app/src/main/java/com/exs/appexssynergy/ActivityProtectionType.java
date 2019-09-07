package com.exs.appexssynergy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.exs.adapter.ProtectionTypeAdapter;
import com.exs.orm.ProtectionTypeID;
import com.exs.util.Generate;
import com.exs.util.SugarRecordExt;
import com.exs.util.SysMessage;
import com.exs.util.UserPreference;

import java.util.ArrayList;
import java.util.List;

public class ActivityProtectionType extends AppCompatActivity {

    private static final String LOG_TAG = "ProtectionType";
    public Context context = this;
    public Activity activity = this;

    // :initialize class
    public UserPreference UserPreference = new UserPreference();
    public SysMessage SysMessage = new SysMessage();
    public Generate Generate = new Generate();

    List<ProtectionTypeID> protectiontypeid;
    ArrayList<ProtectionTypeID> arr;

    // :widget
    ListView listview;
    TextView txt_back;

    // :variable
    String status = "", message = "", data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_protection_type);

        //configure strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //configure class
        UserPreference.init(context);
        SysMessage.init(context);
        Generate.init(context);

        Intent in = getIntent();
        String inspection_id = in.getStringExtra("inspection_id");

        listview = (ListView)findViewById(R.id.listview);
        txt_back = (TextView)findViewById(R.id.txt_back);

        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        protectiontypeid = ProtectionTypeID.listAll(ProtectionTypeID.class);
        arr= new ArrayList<>();
        arr.clear();
        if(protectiontypeid.size() > 0 ) {

            for (int i = 0; i < protectiontypeid.size(); i++) {
                int count = i+1;
                boolean ischeck=false;
                //EquipmentProtectionType protectionType= Select.from(EquipmentProtectionType.class).where("INSPECTIONID = "+"'"+inspection_id+"'").first();
                //EquipmentProtectionType protectionType= EquipmentProtectionType.find(EquipmentProtectionType.class,"WHERE INSPECTIONID",inspection_id);

                Cursor cursor = SugarRecordExt.getDb().rawQuery("SELECT * FROM EQUIPMENT_PROTECTION_TYPE AS p" +
                        " WHERE p.INSPECTIONID=?", new String[] {inspection_id});

                String keys="";
                try {
                    while (cursor.moveToNext()) {

                        int idx = cursor.getColumnIndex("EQUIPMENTPROTECTIONTYPEID");
                        String equipment_protection_type_id = cursor.getString(idx);

                        if(equipment_protection_type_id.equals(protectiontypeid.get(i).getValue())){
                            arr.add(new ProtectionTypeID(protectiontypeid.get(i).getLabel(), protectiontypeid.get(i).getValue(),protectiontypeid.get(i).getKey(),inspection_id,true));
                        }
                    }
                } finally {
                    cursor.close();
                }
                arr.add(new ProtectionTypeID(protectiontypeid.get(i).getLabel(), protectiontypeid.get(i).getValue(),protectiontypeid.get(i).getKey(),inspection_id,ischeck));
            }

            //set campaign adapter
            ProtectionTypeAdapter adapter= new ProtectionTypeAdapter(context, arr);
            listview.setAdapter(adapter);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
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