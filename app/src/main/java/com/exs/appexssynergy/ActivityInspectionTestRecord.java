package com.exs.appexssynergy;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.exs.orm.AreaTempClassID;
import com.exs.orm.Campaign;
import com.exs.orm.Equipment;
import com.exs.orm.Inspection;
import com.exs.orm.InspectionEquipment;
import com.exs.orm.Inspectors;
import com.exs.util.Generate;
import com.exs.util.SugarRecordExt;
import com.exs.util.SysMessage;
import com.exs.util.UserPreference;
import com.orm.query.Select;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.exs.util.SysPara.user_id;

public class ActivityInspectionTestRecord extends AppCompatActivity {
    private static final String LOG_TAG = "InspectionTestRecord";
    public Context context = this;

    DatePickerDialog picker;

    // :initialize class
    public com.exs.util.UserPreference UserPreference = new UserPreference();
    public com.exs.util.SysMessage SysMessage = new SysMessage();
    public com.exs.util.Generate Generate = new Generate();

    // :widget
    TextView txt_back,txt_header;
    Button btn_save,btn_cancel,btn_check_equipment;
    ListView listview;

    // :widget column 1
    RadioGroup radio_group_inspection_grade;
    CheckBox rb_inspection_grade_d,rb_inspection_grade_c,rb_inspection_grade_v;
    EditText edt_item_tag_no,edt_sub_system,edt_location,edt_drawing_reference,edt_ex_certificate_no,edt_equipment_serial_no;
    Spinner spinner_temp_class,spinner_type_of_inspection;

    // :widget column 2
    EditText edt_equipment,edt_report_no,edt_inspection_date,edt_owl_reference_no,edt_overall_ex_compliance_status;
    Spinner spinner_module_area,spinner_gas_group,spinner_ip_rating_1,spinner_ip_rating_2,spinner_zone;

    // :widget column 3
    EditText edt_sub_system_description,edt_data_sheet,edt_equipment_manufacturer;
    Spinner spinner_area_classfication;
    //LinearLayout btn_add_protection;
    TextView edt_protection_type;

    // :variable
    int year,month,day;
    String campaign_id,equipment_id,inspection_id,currentdate;
    Long ID;

    // :widget
    TextView text_fullname;

    List<AreaTempClassID> temp;
    ArrayList<String> tempArrayList;

    InspectionEquipment inspectionEquipment;
    Equipment equipment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_test_record);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        //configure strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //configure class
        UserPreference.init(context);
        SysMessage.init(context);
        Generate.init(context);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        currentdate = formatter.format(date).toString();
        Log.d(LOG_TAG,"currentdate : "+ currentdate);

        //initialize widget
        txt_back = (TextView) findViewById(R.id.txt_back);
        txt_header = (TextView) findViewById(R.id.txt_header);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_check_equipment = (Button)findViewById(R.id.btn_check_equipment);
        btn_cancel = (Button) findViewById(R.id.btn_back_content);
        listview = (ListView) findViewById(R.id.listview);

        //initialize widget column 1
        //radio_group_inspection_grade = (RadioGroup) findViewById(R.id.radio_group_inspection_grade);
        rb_inspection_grade_d = (CheckBox) findViewById(R.id.rb_inspection_grade_d);
        rb_inspection_grade_c = (CheckBox) findViewById(R.id.rb_inspection_grade_c);
        rb_inspection_grade_v = (CheckBox) findViewById(R.id.rb_inspection_grade_v);
        edt_item_tag_no = (EditText) findViewById(R.id.edt_item_tag_no);
        edt_sub_system = (EditText) findViewById(R.id.edt_sub_system);
        edt_location = (EditText) findViewById(R.id.edt_location);
        edt_drawing_reference = (EditText) findViewById(R.id.edt_drawing_reference);
        edt_ex_certificate_no = (EditText) findViewById(R.id.edt_ex_certificate_no);
        edt_equipment_serial_no = (EditText) findViewById(R.id.edt_equipment_serial_no);
        spinner_temp_class = (Spinner) findViewById(R.id.spinner_temp_class);
        spinner_type_of_inspection = (Spinner) findViewById(R.id.spinner_type_of_inspection);

        //initialize widget column 2
        edt_equipment = (EditText) findViewById(R.id.edt_equipment);
        edt_report_no = (EditText) findViewById(R.id.edt_report_no);
        edt_inspection_date = (EditText) findViewById(R.id.edt_inspection_date);
        edt_owl_reference_no = (EditText) findViewById(R.id.edt_owl_reference_no);
        edt_overall_ex_compliance_status = (EditText) findViewById(R.id.edt_overall_ex_compliance_status);
        spinner_module_area = (Spinner) findViewById(R.id.spinner_module_area);
        spinner_gas_group = (Spinner) findViewById(R.id.spinner_gas_group);
        spinner_ip_rating_1 = (Spinner) findViewById(R.id.spinner_ip_rating_1);
        spinner_ip_rating_2 = (Spinner) findViewById(R.id.spinner_ip_rating_2);
        spinner_zone = (Spinner) findViewById(R.id.spinner_zone);

        //initialize widget column 3
        edt_protection_type = (TextView) findViewById(R.id.edt_protection_type);
        edt_sub_system_description = (EditText) findViewById(R.id.edt_sub_system_description);
        edt_data_sheet = (EditText) findViewById(R.id.edt_data_sheet);
        edt_equipment_manufacturer = (EditText) findViewById(R.id.edt_equipment_manufacturer);
        spinner_area_classfication = (Spinner) findViewById(R.id.spinner_area_classfication);
        //btn_add_protection = (LinearLayout) findViewById(R.id.btn_add_protection);

        // get Intent Extra
        Intent intent = getIntent();
        campaign_id=intent.getStringExtra("campaign_id");
        equipment_id=intent.getStringExtra("equipment_id");
        inspection_id=intent.getStringExtra("inspection_id");
        //inspection_id=intent.getStringExtra("inspection_id");
        Log.d(LOG_TAG,"campaign_id = " + campaign_id);
        Log.d(LOG_TAG,"equipment_id = " + equipment_id);
        Log.d(LOG_TAG,"inspection_id = " + inspection_id);
        //load Inspection from local DB


        if(inspection_id!=null && inspection_id!=""){
            //List<EquipmentProtectionType> protectionTypes= EquipmentProtectionType.findWithQuery(EquipmentProtectionType.class,"SELECT * FROM EquipmentProtectionType JOIN ProtectionTypeID ON EquipmentProtectionType.EQUIPMENTPROTECTIONTYPEID=ProtectionTypeID.VALUE WHERE EquipmentProtectionType.INSPECTIONID="+"'"+inspection_id+"'");
            //final EquipmentProtectionType protectionType=Select.from(EquipmentProtectionType.class).where("INSPECTIONID = "+"'"+inspection_id+"'").first();
            Cursor cursor = SugarRecordExt.getDb().rawQuery("SELECT T.'KEY' FROM EQUIPMENT_PROTECTION_TYPE AS p" +
                    " JOIN PROTECTION_TYPE_ID AS T ON T.VALUE=P.EQUIPMENTPROTECTIONTYPEID " +
                    " WHERE p.INSPECTIONID=?", new String[] {inspection_id});

            String keys="";
            try {
                while (cursor.moveToNext()) {

                    int idx = cursor.getColumnIndex("KEY");
                    String key = cursor.getString(idx);

                    //keys+=key+"\n";
                    keys+=key+",";

                }
                String result_key=method(keys);
                //edt_protection_type.setText(result_key);

            } finally {
                cursor.close();
            }

            inspectionEquipment=Select.from(InspectionEquipment.class).where("EQUIPMENTID = "+"'"+equipment_id+"' AND CAMPAIGNID="+"'"+campaign_id+"'").first();

            Log.d(LOG_TAG,"equipment_id == " + inspectionEquipment.getEquipment_id());
            if(!inspectionEquipment.getEquipment_id().isEmpty()){
                ID=inspectionEquipment.getId();

                if(inspectionEquipment.getGrade_d() != null) {
                    if (inspectionEquipment.getGrade_d().equals("true")) {
                        rb_inspection_grade_d.setChecked(true);
                    } else {
                        rb_inspection_grade_d.setChecked(false);
                    }
                }else{
                    rb_inspection_grade_d.setChecked(false);
                }

                if(inspectionEquipment.getGrade_c() != null) {
                    if (inspectionEquipment.getGrade_c().equals("true")) {
                        rb_inspection_grade_c.setChecked(true);
                    } else {
                        rb_inspection_grade_c.setChecked(false);
                    }
                }else{
                    rb_inspection_grade_c.setChecked(false);
                }

                if(inspectionEquipment.getGrade_v() != null) {
                    if(inspectionEquipment.getGrade_v().equals("true")){
                        rb_inspection_grade_v.setChecked(true);
                    }else{
                        rb_inspection_grade_v.setChecked(false);
                    }
                }else{
                    rb_inspection_grade_v.setChecked(false);
                }

                edt_item_tag_no.setText(inspectionEquipment.getItem_tag_no());
                edt_sub_system.setText(inspectionEquipment.getSub_system());
                edt_location.setText(inspectionEquipment.getLocation());
                edt_drawing_reference.setText(inspectionEquipment.getDrawing_ref());
                edt_ex_certificate_no.setText(inspectionEquipment.getEx_cert_no());
                edt_equipment_serial_no.setText(inspectionEquipment.getEquipment_serial_no());

                // spinner_temp_class.setSelection(inspectionEquipment.);
                // spinner_type_of_inspection.setSelection
                // Temp Area
                int position=0;
                temp = AreaTempClassID.listAll(AreaTempClassID.class);
                tempArrayList= new ArrayList<>();
                if(temp.size() > 0 ) {

                    for (int i = 0; i < temp.size(); i++) {

                        tempArrayList.add(temp.get(i).getKey());
                        if(temp.get(i).getValue().equals(inspectionEquipment.getArea_temp_class_id())){
                            position=i;
                        }

                    }

                }

               /* String[] spinnerArray = new String[temp.size()];
                HashMap<Integer,String> spinnerMap = new HashMap<Integer, String>();
                for (int i = 0; i < temp.size(); i++)
                {
                    spinnerMap.put(i,temp.get(i).getValue());
                    //spinnerArray[i] = Province_NAME.get(i);
                    tempArrayList.add(temp.get(i).getKey());
                }*/

                    final ArrayAdapter<String> adapter= new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,tempArrayList);
                    spinner_temp_class.setAdapter(adapter);


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            spinner_temp_class.setSelection(3);
                            //spinner_temp_class.setSelection(adapter.getPosition(inspectionEquipment.getArea_temp_class_id()));
                        }
                    },1000);

                    // spinner_type_of_inspection.setSelection

                    edt_equipment.setText(inspectionEquipment.getDescription());
                    edt_report_no.setText(inspectionEquipment.getReport_no());
                    edt_inspection_date.setText(inspectionEquipment.getInspection_date());
                    edt_owl_reference_no.setText(inspectionEquipment.getReference_no());
                    edt_overall_ex_compliance_status.setText(inspectionEquipment.getCompliance_status());
                /*spinner_module_area = (Spinner) findViewById(R.id.spinner_module_area);
                spinner_gas_group = (Spinner) findViewById(R.id.spinner_gas_group);
                spinner_ip_rating_1 = (Spinner) findViewById(R.id.spinner_ip_rating_1);
                spinner_ip_rating_2 = (Spinner) findViewById(R.id.spinner_ip_rating_2);
                spinner_zone = (Spinner) findViewById(R.id.spinner_zone);*/

                    //edt_protection_type.setText(inspectionEquipment.getEquipment_protection_type_id());
                    edt_sub_system_description.setText(inspectionEquipment.getSub_system_desc());
                    edt_data_sheet.setText(inspectionEquipment.getData_sheet());
                    edt_equipment_manufacturer.setText(inspectionEquipment.getEquipment_manufacturer());
                    //spinner_area_classfication = (Spinner) findViewById(R.id.spinner_area_classfication);
            }

            //onclick
            btn_save.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("LongLogTag")
                @Override
                public void onClick(View v) {

                    InspectionEquipment inspectionEquipment1 = InspectionEquipment.findById(InspectionEquipment.class,ID);
                    inspectionEquipment1.grade_c = ""+rb_inspection_grade_c.isChecked();
                    inspectionEquipment1.grade_d = ""+rb_inspection_grade_d.isChecked();
                    inspectionEquipment1.grade_v = ""+rb_inspection_grade_v.isChecked();
                    inspectionEquipment1.report_no = edt_report_no.getText().toString();
                    inspectionEquipment1.inspection_date = currentdate;//edt_inspection_date.getText().toString();
                    inspectionEquipment1.location = edt_location.getText().toString();
                    inspectionEquipment1.inspection_type_id = spinner_type_of_inspection.getSelectedItem().toString();
                    inspectionEquipment1.item_tag_no =edt_item_tag_no.getText().toString();
                    inspectionEquipment1.description =edt_equipment.getText().toString();
                    inspectionEquipment1.equipment_type_id =spinner_type_of_inspection.getSelectedItem().toString();
                    inspectionEquipment1.sub_system =edt_sub_system.getText().toString();
                    inspectionEquipment1.sub_system_desc =edt_sub_system_description.getText().toString();
                    inspectionEquipment1.module_area_id =spinner_module_area.getSelectedItem().toString();
                    inspectionEquipment1.data_sheet =edt_data_sheet.getText().toString();
                    inspectionEquipment1.drawing_ref =edt_drawing_reference.getText().toString();
                    inspectionEquipment1.area_temp_class_id =spinner_temp_class.getSelectedItem().toString();
                    inspectionEquipment1.area_gas_group_id =spinner_gas_group.getSelectedItem().toString();
                    //inspectionEquipment1.equipment_protection_type_id =edt_protection_type.getText().toString();
                    inspectionEquipment1.equipment_manufacturer =edt_equipment_manufacturer.getText().toString();
                    inspectionEquipment1.equipment_serial_no =edt_equipment_serial_no.getText().toString();
                    inspectionEquipment1.ex_cert_no =edt_ex_certificate_no.getText().toString();
                    inspectionEquipment1.equipment_zone_id =edt_ex_certificate_no.getText().toString();
                    inspectionEquipment1.equipment_zone_id =spinner_zone.getSelectedItem().toString();


                    inspectionEquipment1.save();
                    SysMessage.onToast("Successfully save to local.");
                    //String radio_group_inspection_grade = getradio_group_inspection_grade();
                    //Toast.makeText(ActivityInspectionTestRecord.this,""+rb_inspection_grade_d.isChecked(),Toast.LENGTH_LONG).show();
                    //Log.d(LOG_TAG,"radio_group_inspection_grade = " + rb_inspection_grade_d.isChecked());
                }
            });



        }else{ // add new inspection equipment

            edt_inspection_date.setText(currentdate);
            equipment=Select.from(Equipment.class).where("EQUIPMENTID = "+"'"+equipment_id+"'").limit("1").first();
            //Log.d(LOG_TAG,"equipment.getItem_tag_no() = " + equipment.getItem_tag_no());

            if(equipment.getItem_tag_no() == null){
                edt_item_tag_no.setText("");
            }else{
                edt_item_tag_no.setText(equipment.getItem_tag_no());
            }
            /*if(equipment.getItem_tag_no().length() > 0 && equipment.getItem_tag_no() != null){
                edt_item_tag_no.setText(equipment.getItem_tag_no());
            }else{
                edt_item_tag_no.setText("");
            }*/

            edt_sub_system.setText(equipment.getSub_system());
            edt_drawing_reference.setText(equipment.getDrawing_ref());
            edt_ex_certificate_no.setText(equipment.getEx_cert_no());
            edt_equipment_serial_no.setText(equipment.getEquipment_serial_no());

            // spinner_temp_class.setSelection(inspectionEquipment.);
            // spinner_type_of_inspection.setSelection
            // Temp Area
            int position=0;
            temp = AreaTempClassID.listAll(AreaTempClassID.class);
            tempArrayList= new ArrayList<>();
            if(temp.size() > 0 ) {

                for (int i = 0; i < temp.size(); i++) {

                    tempArrayList.add(temp.get(i).getKey());
                    if(temp.get(i).getValue().equals(equipment.getArea_temp_class_id())){
                        position=i;
                    }

                }

            }

               /* String[] spinnerArray = new String[temp.size()];
                HashMap<Integer,String> spinnerMap = new HashMap<Integer, String>();
                for (int i = 0; i < temp.size(); i++)
                {
                    spinnerMap.put(i,temp.get(i).getValue());
                    //spinnerArray[i] = Province_NAME.get(i);
                    tempArrayList.add(temp.get(i).getKey());
                }*/

            final ArrayAdapter<String> adapter= new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,tempArrayList);
            spinner_temp_class.setAdapter(adapter);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    spinner_temp_class.setSelection(3);
                    //spinner_temp_class.setSelection(adapter.getPosition(inspectionEquipment.getArea_temp_class_id()));
                }
            },1000);

            // spinner_type_of_inspection.setSelection

            edt_equipment.setText(equipment.getDescription());
            //edt_report_no.setText(equipment.getReport_no());
            //edt_inspection_date.setText(equipment.getInspection_date());
            edt_owl_reference_no.setText(equipment.getReference_no());
            //edt_overall_ex_compliance_status.setText(equipment.getCompliance_status());
                spinner_module_area = (Spinner) findViewById(R.id.spinner_module_area);
                spinner_gas_group = (Spinner) findViewById(R.id.spinner_gas_group);
                spinner_ip_rating_1 = (Spinner) findViewById(R.id.spinner_ip_rating_1);
                spinner_ip_rating_2 = (Spinner) findViewById(R.id.spinner_ip_rating_2);
                spinner_zone = (Spinner) findViewById(R.id.spinner_zone);

            //edt_protection_type.setText(inspectionEquipment.getEquipment_protection_type_id());
            edt_sub_system_description.setText(equipment.getSub_system_desc());
            edt_data_sheet.setText(equipment.getData_sheet());
            edt_equipment_manufacturer.setText(equipment.getEquipment_manufacturer());
            //spinner_area_classfication = (Spinner) findViewById(R.id.spinner_area_classfication);

            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //save inspection
                    String rand_id=getRandomString(32);
                    Inspection inspection = new Inspection(rand_id,"","","", "","", "","","","","", "","","","","","",
                            "","","","","","");

                    //save inspection_equipment
                    Campaign campaign=Select.from(Campaign.class).where("CAMPAIGNID = "+"'"+campaign_id+"'").first();
                    Inspectors inspectors=Select.from(Inspectors.class).where("CAMPAIGNID = "+"'"+campaign_id+"'").first();

                    String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                   InspectionEquipment inspectionEquipment = new InspectionEquipment(rand_id,equipment_id,String.valueOf(rb_inspection_grade_d.isChecked()),String.valueOf(rb_inspection_grade_c.isChecked()),String.valueOf(rb_inspection_grade_v.isChecked()),edt_report_no.getText().toString(),edt_inspection_date.getText().toString(),edt_location.getText().toString(),spinner_type_of_inspection.getSelectedItem().toString(),inspectors.getInspector_id(),
                           inspectors.getIs_supervisor(),user_id,currentdate,user_id,currentdate,campaign_id,"NEW","",edt_equipment.getText().toString(),edt_item_tag_no.getText().toString(),
                           spinner_type_of_inspection.getSelectedItem().toString(),campaign.getFacility_id(),"",edt_sub_system.getText().toString(),edt_sub_system_description.getText().toString(),spinner_module_area.getSelectedItem().toString(),edt_data_sheet.getText().toString(),edt_drawing_reference.getText().toString(),"",spinner_temp_class.getSelectedItem().toString(),
                           spinner_gas_group.getSelectedItem().toString(),edt_equipment_manufacturer.getText().toString(),"",edt_equipment_serial_no.getText().toString(),spinner_temp_class.getSelectedItem().toString(),spinner_gas_group.getSelectedItem().toString(),edt_ex_certificate_no.getText().toString(),spinner_zone.getSelectedItem().toString(),
                           spinner_ip_rating_1.getSelectedItem().toString(),spinner_ip_rating_2.getSelectedItem().toString(),"");

                   inspectionEquipment.save();
                    SysMessage.onToast("Successfully save to local.");
                }
            });
        }


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_check_equipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to = new Intent(context,ActivityCheckInspectionRecord.class);
                to.putExtra("campaign_id",campaign_id);
                to.putExtra("equipment_id",equipment_id);
                to.putExtra("inspection_id",inspection_id);
                startActivity(to);
            }
        });

        edt_inspection_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialog(0);
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                edt_inspection_date.setText(year + "-" + (monthOfYear + 1) + "-" +dayOfMonth );
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        edt_protection_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to = new Intent(context,ActivityProtectionType.class);
                to.putExtra("inspection_id",inspection_id);
                startActivity(to);
            }
        });

        // Set Current Value
        spinner_zone.setSelection(getIndex(spinner_zone, "21"));
    }

    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";

    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
    }

    public String getradio_group_inspection_grade(){
        int selectedId=radio_group_inspection_grade.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton)findViewById(selectedId);
        return String.valueOf(rb.getText());
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            day = selectedDay;
            month = selectedMonth;
            year = selectedYear;
            edt_inspection_date.setText(selectedYear + "-" + (selectedMonth + 1) + "-"+ selectedDay);
        }
    };

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public String method(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}
