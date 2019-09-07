package com.exs.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.exs.adapter.InspectionAdapter;
import com.exs.appexssynergy.ActivityAddNewInspection;
import com.exs.appexssynergy.R;
import com.exs.orm.InspectionEquipment;
import com.exs.orm.Inspectors;
import com.exs.util.Generate;
import com.exs.util.SysMessage;
import com.exs.util.UserPreference;
import com.orm.query.Select;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FragmentInspection extends Fragment{
    private static final String LOG_TAG = "Inspection";

    // :initialize class
    public com.exs.util.UserPreference UserPreference = new UserPreference();
    public com.exs.util.SysMessage SysMessage = new SysMessage();
    public com.exs.util.Generate Generate = new Generate();
    DatePickerDialog picker;

    View view;
    ListView listView;
    TextView no_record;
    EditText edt_equipment_tag,edt_date;
    static ProgressDialog progressDialog;
    LinearLayout btn_search,btn_reset,btn_add_new_inspection;
    Spinner spinner_inspector;

    List<InspectionEquipment> insp;
    List<Inspectors> inspector;
    ArrayList<InspectionEquipment> inspArrayList;

    // :variable
    String status = "", message = "", data = "";
    String campaign_id,inspector_id;
    int year,month,day;
    String start_campaign,end_campaign;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_inspection, container, false);

        // set title
        String strtext = getArguments().getString("title");
        campaign_id = getArguments().getString("campaign_id");
        start_campaign = getArguments().getString("start_campaign");
        end_campaign = getArguments().getString("end_campaign");
        getActivity().setTitle(strtext);

        no_record = (TextView) view.findViewById(R.id.tvNoRecord);
        listView = (ListView) view.findViewById(R.id.list);
        edt_equipment_tag = (EditText) view.findViewById(R.id.edt_equipment_tag);
        edt_date = (EditText) view.findViewById(R.id.edt_date);
        btn_search = (LinearLayout) view.findViewById(R.id.btn_search);
        btn_reset = (LinearLayout) view.findViewById(R.id.btn_reset);
        btn_add_new_inspection = (LinearLayout) view.findViewById(R.id.btn_add_new_inspection);
        spinner_inspector = (Spinner) view.findViewById(R.id.spinner_inspector);

        spinner_inspector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {

                String item = parent.getItemAtPosition(position).toString();
                if(item != "- Select Inspector -") {
                    Inspectors ins = Select.from(Inspectors.class).where("FULLNAME = " + "'" + item + "'").first();
                    if (!ins.getInspector_id().isEmpty()) {
                        inspector_id = ins.getInspector_id();
                    } else {
                        inspector_id = "";
                    }
                }else{
                    inspector_id = "";
                }
                //Toast.makeText(parent.getContext(), "Selected: " + inspector_id, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //configure strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //configure class
        UserPreference.init(getContext());
        SysMessage.init(getContext());
        Generate.init(getContext());

        getAll();
        getInspector();

        if(start_campaign.length()>4 && end_campaign.length()>4){
            getListDate(start_campaign,end_campaign);
        }

        edt_equipment_tag.addTextChangedListener(new TextWatcher() {

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
                    getAll();
                }
            }

        });

        edt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialog(0);
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                edt_date.setText(year + "-" + (monthOfYear + 1) + "-" +dayOfMonth );
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickID = "";
                getBySearch();
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_date.setText("");
                edt_equipment_tag.setText("");
                clickID = "";
                if(start_campaign.length()>4 && end_campaign.length()>4){
                    getListDate(start_campaign,end_campaign);
                }
                getInspector();
                getAll();
            }
        });

        btn_add_new_inspection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to = new Intent(getContext(), ActivityAddNewInspection.class);
                to.putExtra("campaign_id",campaign_id);
                getContext().startActivity(to);
            }
        });

        return view;
    }

    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(getContext(), datePickerListener, year, month, day);
    }
    String clickID = "";
    public void getListDate(String start_date,String end_date){
        Log.d(LOG_TAG,"UP clickID = " + clickID);
        final List<Date> dates = new ArrayList<Date>();
        dates.clear();
        DateFormat formatter ;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = formatter.parse(start_date + " 00:00:00");
            endDate = formatter.parse(end_date + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long interval = 24*1000 * 60 * 60; // 1 hour in millis
        long endTime =endDate.getTime(); // create your endtime here, possibly using Calendar or Date
        long curTime = startDate.getTime();
        while (curTime <= endTime) {
            dates.add(new Date(curTime));
            curTime += interval;
        }

        final LinearLayout layout = (LinearLayout) view.findViewById(R.id.date_container);
        layout.removeAllViews();
        int i = 0;
        for(i=0;i<dates.size();i++){
            Date lDate =(Date)dates.get(i);
            String ds = formatter.format(lDate);
            LinearLayout ly = (LinearLayout) view.inflate(getContext(), R.layout.row_inspection_date, null);
            final TextView text = (TextView) ly.findViewById(R.id.text);
            text.setId(i);
            text.setTag(i);
            text.setText(String.valueOf(ds));
            Log.d(LOG_TAG,"clickID = " + clickID);
            if(String.valueOf(i) != clickID){
                text.setTextColor(Color.BLACK);
            }else{
                text.setTextColor(Color.BLUE);
            }
            layout.addView(ly);

            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(LOG_TAG,"text = " + text.getText().toString());
                    getByDateListed(text.getText().toString());
                    text.setTextColor(Color.BLUE);
                    clickID = text.getTag().toString();
                    Log.d(LOG_TAG,"Data clickID = " + clickID);
                    if(start_campaign.length()>4 && end_campaign.length()>4){
                        getListDate(start_campaign,end_campaign);
                    }
                }
            });
        }
    }


    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            day = selectedDay;
            month = selectedMonth;
            String MM = "";
            if(String.valueOf(selectedMonth).length()< 2){
                MM = "0"+(selectedMonth + 1);
            }else{
                MM = String.valueOf((selectedMonth + 1));
            }
            year = selectedYear;
            edt_date.setText(selectedYear + "-" + MM + "-"+ selectedDay);
        }
    };

    public void getInspector(){
        inspector= Inspectors.findWithQuery(Inspectors.class,"SELECT * FROM Inspectors WHERE CAMPAIGNID="+"'"+campaign_id+"'");
        List<String> categories = new ArrayList<String>();
        categories.add("- Select Inspector -");
        if(inspector.size() > 0 ) {
            for (int i = 0; i < inspector.size(); i++) {
                String name = inspector.get(i).getFull_name();
                Log.d(LOG_TAG,"inspector = " + name);
                categories.add(name);

            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_inspector.setAdapter(dataAdapter);

        }
    }

    public void getEquipmentTag(String tag){
        insp=InspectionEquipment.findWithQuery(InspectionEquipment.class,"SELECT * FROM Inspection_Equipment WHERE CAMPAIGNID="+"'"+campaign_id+"' AND ITEMTAGNO LIKE "+"'%"+tag.trim()+"%'");
        //insp=InspectionEquipment.findWithQuery(InspectionEquipment.class,"SELECT * FROM Inspection_Equipment WHERE ITEMTAGNO LIKE "+"'%"+tag+"%'");
        inspArrayList= new ArrayList<>();

        if(insp.size() > 0 ) {
            no_record.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);

            for (int i = 0; i < insp.size(); i++) {
                String inspector_name= Select.from(Inspectors.class).where("INSPECTORID = "+"'"+ insp.get(i).getInspector_id()+"'").first().getFull_name();

                inspArrayList.add(new InspectionEquipment(String.valueOf(i+1),insp.get(i).getInspection_date(),insp.get(i).getItem_tag_no(),insp.get(i).getDescription(),
                        inspector_name,insp.get(i).getStatus(),insp.get(i).getCompliance_status(),insp.get(i).getCampaign_id(),insp.get(i).getEquipment_id(),insp.get(i).getInspection_detail_id()));

            }

            //set campaign adapter
            InspectionAdapter adapter= new InspectionAdapter(getContext(), inspArrayList);
            listView.setAdapter(adapter);
        }else{
            no_record.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }
    }

    public void getAll(){

        //insp=InspectionEquipment.findWithQuery(InspectionEquipment.class,"SELECT * FROM Inspection_Equipment WHERE CAMPAIGNID="+"'"+campaign_id+"' AND INSPECTIONDATE BETWEEN '"+start_campaign+"' AND '"+end_campaign+"'");
        //insp=InspectionEquipment.findWithQuery(InspectionEquipment.class,"SELECT * FROM Inspection_Equipment WHERE CAMPAIGNID="+"'"+campaign_id+"' AND INSPECTIONDATE >= '"+start_campaign+"' AND INSPECTIONDATE <= '"+end_campaign+"'");
        insp=InspectionEquipment.findWithQuery(InspectionEquipment.class,"SELECT * FROM Inspection_Equipment WHERE CAMPAIGNID="+"'"+campaign_id+"'");

        inspArrayList= new ArrayList<>();

        if(insp.size() > 0 ) {
            no_record.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);

            for (int i = 0; i < insp.size(); i++) {
                String inspector_name= Select.from(Inspectors.class).where("INSPECTORID = "+"'"+ insp.get(i).getInspector_id()+"'").first().getFull_name();

                inspArrayList.add(new InspectionEquipment(String.valueOf(i+1),insp.get(i).getInspection_date(),insp.get(i).getItem_tag_no(),insp.get(i).getDescription(),
                        inspector_name,insp.get(i).getStatus(),insp.get(i).getCompliance_status(),insp.get(i).getCampaign_id(),insp.get(i).getEquipment_id(),insp.get(i).getInspection_detail_id()));

            }

            //set campaign adapter
            InspectionAdapter adapter= new InspectionAdapter(getContext(), inspArrayList);
            listView.setAdapter(adapter);
        }else{
            no_record.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }
    }

    public void getBySearch(){

        String cmd = "";
        String equipment_tag = edt_equipment_tag.getText().toString();
        if(equipment_tag.length()>0){
            cmd += " ITEMTAGNO LIKE '%" + equipment_tag + "%' AND";
        }

        if(inspector_id.length()>0){
            cmd += " INSPECTORID = '" + inspector_id + "' AND";
        }

        String date = edt_date.getText().toString();
        if(date.length()>0){
            cmd += " INSPECTIONDATE = '" + date + "' AND";
        }

        insp=InspectionEquipment.findWithQuery(InspectionEquipment.class,"SELECT * FROM Inspection_Equipment WHERE"+cmd+" CAMPAIGNID="+"'"+campaign_id+"'");
        //insp=InspectionEquipment.findWithQuery(InspectionEquipment.class,"SELECT * FROM Inspection_Equipment WHERE"+cmd+" CAMPAIGNID="+"'"+campaign_id+"' AND INSPECTIONDATE BETWEEN '"+start_campaign+"' AND '"+end_campaign+"'");
        inspArrayList= new ArrayList<>();

        if(insp.size() > 0 ) {
            no_record.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);

            for (int i = 0; i < insp.size(); i++) {
                String inspector_name= Select.from(Inspectors.class).where("INSPECTORID = "+"'"+ insp.get(i).getInspector_id()+"'").first().getFull_name();

                inspArrayList.add(new InspectionEquipment(String.valueOf(i+1),insp.get(i).getInspection_date(),insp.get(i).getItem_tag_no(),insp.get(i).getDescription(),
                        inspector_name,insp.get(i).getStatus(),insp.get(i).getCompliance_status(),insp.get(i).getCampaign_id(),insp.get(i).getEquipment_id(),insp.get(i).getInspection_detail_id()));

            }

            //set campaign adapter
            InspectionAdapter adapter= new InspectionAdapter(getContext(), inspArrayList);
            listView.setAdapter(adapter);
        }else{
            no_record.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }
    }

    public void getByDateListed(String date){
        String cmd ="";
        if(date.length()>0){
            cmd += " INSPECTIONDATE = '" + date + "' AND";
        }

        insp=InspectionEquipment.findWithQuery(InspectionEquipment.class,"SELECT * FROM Inspection_Equipment WHERE"+cmd+" CAMPAIGNID="+"'"+campaign_id+"' AND INSPECTIONDATE BETWEEN '"+start_campaign+"' AND '"+end_campaign+"'");
        inspArrayList= new ArrayList<>();

        if(insp.size() > 0 ) {
            no_record.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);

            for (int i = 0; i < insp.size(); i++) {
                String inspector_name= Select.from(Inspectors.class).where("INSPECTORID = "+"'"+ insp.get(i).getInspector_id()+"'").first().getFull_name();

                inspArrayList.add(new InspectionEquipment(String.valueOf(i+1),insp.get(i).getInspection_date(),insp.get(i).getItem_tag_no(),insp.get(i).getDescription(),
                        inspector_name,insp.get(i).getStatus(),insp.get(i).getCompliance_status(),insp.get(i).getCampaign_id(),insp.get(i).getEquipment_id(),insp.get(i).getInspection_detail_id()));

            }

            //set campaign adapter
            InspectionAdapter adapter= new InspectionAdapter(getContext(), inspArrayList);
            listView.setAdapter(adapter);
        }else{
            no_record.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }
    }
}
