package com.exs.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.exs.appexssynergy.ActivityInspectionTestRecord;
import com.exs.appexssynergy.R;
import com.exs.orm.InspectionEquipment;

import java.util.ArrayList;

public class InspectionAdapter extends ArrayAdapter<InspectionEquipment> {

    ProgressDialog progressDialog;
    private ArrayList<InspectionEquipment> dataSet;
    Context mContext;
    Fragment fragment = null;
    Class fragmentClass = null;
    Bundle bundle = new Bundle();
    String keycode;

    String equipment_tag_no,equipment_description,compliance_status;
    // View lookup cache
    private static class ViewHolder {
        TextView no,date,equipment_tag_no,equipment,inspector, status, ex_status, findings;
        ImageView btn_edit,btn_delete;

    }
    public InspectionAdapter(Context context, ArrayList<InspectionEquipment> data) {
        super(context, R.layout.activity_inspection_adapter, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final InspectionEquipment dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_inspection_adapter, parent, false);
            viewHolder.no = (TextView) convertView.findViewById(R.id.no);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.equipment_tag_no = (TextView) convertView.findViewById(R.id.equipment_tag_no);
            viewHolder.equipment = (TextView) convertView.findViewById(R.id.equipment);
            viewHolder.inspector = (TextView) convertView.findViewById(R.id.inspector);
            viewHolder.status = (TextView) convertView.findViewById(R.id.status);
            viewHolder.ex_status = (TextView) convertView.findViewById(R.id.ex_status);
            viewHolder.findings = (TextView) convertView.findViewById(R.id.finding);
            viewHolder.btn_edit = (ImageView) convertView.findViewById(R.id.btn_edit);
            viewHolder.btn_delete = (ImageView) convertView.findViewById(R.id.btn_delete);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String inspection_date = null;
        try {
            String currentString = dataModel.getInspection_date();
            String[] separated = currentString.split("T");
            inspection_date=separated[0];

        }catch (Exception e){
            Log.e("InspectionAdapter",e.getMessage());
        }

        viewHolder.no.setText(dataModel.getCount());
        viewHolder.date.setText(dataModel.getInspection_date());
        viewHolder.equipment_tag_no.setText(dataModel.getItem_tag_no());
        viewHolder.equipment.setText(dataModel.getDescription());
        viewHolder.status.setText(dataModel.getStatus());
        viewHolder.inspector.setText(dataModel.getInspector_name());

        try {
            String exstatus = null;
            exstatus = dataModel.getCompliance_status();
            Log.d("InspectionAdapter",exstatus);
            viewHolder.ex_status.setText(dataModel.getCompliance_status());
            viewHolder.ex_status.setTextColor(Color.TRANSPARENT);
            if(exstatus != null){
                if(exstatus.equals("YELLOW")){
                    viewHolder.ex_status.setBackgroundColor(Color.YELLOW);
                }else if(exstatus.equals("RED")){
                    viewHolder.ex_status.setBackgroundColor(Color.RED);
                }else if(exstatus.equals("WHITE")){
                    viewHolder.ex_status.setBackgroundColor(Color.LTGRAY);
                }else{
                    viewHolder.ex_status.setBackgroundColor(Color.LTGRAY);
                }
            }else{
                viewHolder.ex_status.setBackgroundColor(Color.LTGRAY);
            }
        }catch (Exception e){
            Log.e("InspectionAdapter",e.getMessage());
        }

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
                //press onlong click
            }
        });

        viewHolder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String campaign_id = null;
                    campaign_id = dataModel.getCampaign_id();
                    if(campaign_id != null) {
                        Log.d("campaign_id = ", dataModel.getCampaign_id());
                        Log.d("equipment_id = ", dataModel.getEquipment_id());

                        Intent to = new Intent(getContext(), ActivityInspectionTestRecord.class);
                        to.putExtra("campaign_id",dataModel.getCampaign_id());
                        to.putExtra("equipment_id",dataModel.getEquipment_id());
                        to.putExtra("inspection_id",dataModel.getId());
                        getContext().startActivity(to);
                    }
                }catch (Exception e){
                    Log.e("btn_edit",e.getMessage());
                }

            }
        });

        viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDelete(dataModel.getDescription());
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to = new Intent(getContext(), ActivityInspectionTestRecord.class);
                to.putExtra("campaign_id",dataModel.getCampaign_id());
                to.putExtra("equipment_id",dataModel.getEquipment_id());
                to.putExtra("inspection_id",dataModel.getInspection_detail_id());
                getContext().startActivity(to);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

    public void DialogDelete(String name){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Message");
        builder.setMessage("Are you sure to delete "+name+ "?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


}
