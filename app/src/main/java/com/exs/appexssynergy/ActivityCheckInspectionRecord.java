package com.exs.appexssynergy;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.exs.adapter.InspectionItemsAdapter;
import com.exs.orm.InspectionCategories;
import com.exs.orm.InspectionItems;
import com.exs.orm.InspectionItemsDetails;
import com.exs.util.Generate;
import com.exs.util.SysMessage;
import com.exs.util.UserPreference;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

public class ActivityCheckInspectionRecord extends AppCompatActivity {
    private static final String LOG_TAG = "ActivityCheckInspection";
    public Context context = this;

    DatePickerDialog picker;

    // :initialize class
    public com.exs.util.UserPreference UserPreference = new UserPreference();
    public com.exs.util.SysMessage SysMessage = new SysMessage();
    public com.exs.util.Generate Generate = new Generate();

    // :widget
    TextView txt_back,txt_header;
    Button btn_save,btn_cancel;
    ListView listview;

    // :variable
    String equipment_id,campaign_id,inspection_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_inspection_check_record);
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
        listview = (ListView) findViewById(R.id.listview);

        // get Intent Extra
        Intent intent = getIntent();
        campaign_id=intent.getStringExtra("campaign_id");
        equipment_id=intent.getStringExtra("equipment_id");
        inspection_id=intent.getStringExtra("inspection_id");
        Log.d(LOG_TAG,"campaign_id = " + campaign_id);
        Log.d(LOG_TAG,"equipment_id = " + equipment_id);

        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getInspectionItems();
    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
    }

    List<InspectionItems> item;
    ArrayList<InspectionItems> itemArrayList;
    InspectionItemsAdapter adapter_content = null;

    public void getInspectionItems(){
        item=InspectionItems.findWithQuery(InspectionItems.class,"SELECT * FROM Inspection_Items WHERE EQUIPMENTID="+"'"+equipment_id+"'");
        itemArrayList= new ArrayList<>();
        itemArrayList.clear();
        Log.d(LOG_TAG,"item.size() = " + item.size());
        if(item.size() > 0 ) {

            //adapter_content=new InspectionItemsAdapter();
            //adapter_content.clear();
            for (int i = 0; i < item.size(); i++) {
                long rowid = item.get(i).getId();
                Log.d(LOG_TAG,"->" + item.get(i).getInspection_items_id());
                Log.d(LOG_TAG,"->" + item.get(i).getItem_result_id());
                InspectionItemsDetails inspectionitemsdetails=Select.from(InspectionItemsDetails.class).where("INSPECTIONITEMSDETAILSID = "+"'"+item.get(i).getInspection_items_id()+"'").first();
                if(!inspectionitemsdetails.getItem_name().isEmpty()) {
                    try {
                        if (inspectionitemsdetails.getItem_name() != null) {
                            String item_name = inspectionitemsdetails.getItem_name();
                            String inspection_category_id = inspectionitemsdetails.getInspection_category_id();

                            InspectionCategories catitem = Select.from(InspectionCategories.class).where("INSPECTIONCATEGORIESID = "+"'"+inspection_category_id+"' ORDER BY CODE ASC").first();
                            if(!catitem.getCategory_name().isEmpty()) {
                                try {
                                    //InspectionItems c =new InspectionItems();
                                    String cat_name = catitem.getCategory_name();
                                    String cat_code = catitem.getCode();
                                    if (cat_name != null) {
                                        Log.d(LOG_TAG, "cat_name = " + cat_name);

                                        /*c.setId(rowid);
                                        c.setInspection_items_id(item.get(i).getInspection_items_id());
                                        c.setEquipment_id(item.get(i).getEquipment_id());
                                        c.setSeq(item.get(i).getSeq());
                                        c.setName(item.get(i).getName());
                                        c.setItem_result(item.get(i).getItem_result());
                                        c.setItem_result_id(item.get(i).getItem_result_id());
                                        c.setLabel(item.get(i).getLabel());
                                        c.setCat_name(cat_name);
                                        c.setCat_code(cat_code);*/

                                        itemArrayList.add(new InspectionItems(rowid,item.get(i).getInspection_items_id(), item.get(i).getEquipment_id(), item.get(i).getSeq(), item.get(i).getName(), item.get(i).getItem_result(),item.get(i).getItem_result_id(),item_name,cat_name,cat_code));
                                    }
                                    //adapter_content.add(c);
                                } catch (Exception e) {
                                    Log.e(LOG_TAG, e.getMessage());
                                }
                            }
                        }
                    } catch (Exception e) {
                        Log.e(LOG_TAG, e.getMessage());
                    }
                }

            }

            //set campaign adapter
            InspectionItemsAdapter adapter= new InspectionItemsAdapter(context, itemArrayList);
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


