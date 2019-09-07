package com.exs.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exs.appexssynergy.ActivityInspectionTestRecord;
import com.exs.appexssynergy.Dashboard;
import com.exs.appexssynergy.R;
import com.exs.fragment.FragmentInspection;
import com.exs.orm.Campaign;
import com.exs.orm.Equipment;

import java.util.ArrayList;

public class EquipmentAdapter extends ArrayAdapter<Equipment> {

    ProgressDialog progressDialog;
    private ArrayList<Equipment> dataSet;

    Context mContext;
    String campaign_id;

    // View lookup cache
    private static class ViewHolder {
        TextView no,tag_no, equipment;
        Button btn_action;
        ProgressBar pb;


    }
    public EquipmentAdapter(Context context, ArrayList<Equipment> data, String campaign_id) {
        super(context, R.layout.activity_equipment_adapter, data);
        this.dataSet = data;
        this.mContext=context;
        this.campaign_id=campaign_id;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Equipment dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_equipment_adapter, parent, false);
            viewHolder.no = (TextView) convertView.findViewById(R.id.no);
            viewHolder.tag_no = (TextView) convertView.findViewById(R.id.tag_no);
            viewHolder.equipment = (TextView) convertView.findViewById(R.id.equipment);
            viewHolder.btn_action = (Button) convertView.findViewById(R.id.btn_action);



            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.no.setText(dataModel.getCount());
        viewHolder.tag_no.setText(dataModel.getItem_tag_no());
        viewHolder.equipment.setText(dataModel.getDescription());

        viewHolder.btn_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to = new Intent(mContext, ActivityInspectionTestRecord.class);
                to.putExtra("campaign_id",campaign_id);
                to.putExtra("equipment_id",dataModel.getEquipment_id());

                mContext.startActivity(to);


            }
        });
        // Return the completed view to render on screen
        return convertView;
    }


}
