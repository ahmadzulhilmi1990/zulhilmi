package com.exs.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.exs.appexssynergy.R;
import com.exs.orm.InspectionItems;

import java.util.ArrayList;

public class InspectionItemsAdapter extends ArrayAdapter<InspectionItems> {

    ProgressDialog progressDialog;
    private ArrayList<InspectionItems> dataSet;
    Context mContext;

    // View lookup cache
    public static class ViewHolder {
        TextView txt_code,txt_categories,txt_check_items_name;
        RadioGroup rg_result;
        RadioButton rb_result_comply,rb_result_notcomply,rb_result_na;
        Button btn_inspections;

    }
    public InspectionItemsAdapter(Context context, ArrayList<InspectionItems> data) {
        super(context, R.layout.activity_inspectionitems_adapter, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;
        viewHolder = new ViewHolder();
        if (convertView == null) {

             LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_inspectionitems_adapter, parent, false);
            viewHolder.txt_code = (TextView) convertView.findViewById(R.id.txt_code);
            viewHolder.txt_categories = (TextView) convertView.findViewById(R.id.txt_categories);
            viewHolder.txt_check_items_name = (TextView) convertView.findViewById(R.id.txt_check_items_name);
            viewHolder.rg_result = (RadioGroup) convertView.findViewById(R.id.rg_result);
            viewHolder.rb_result_comply = (RadioButton) convertView.findViewById(R.id.rb_result_comply);
            viewHolder.rb_result_notcomply = (RadioButton) convertView.findViewById(R.id.rb_result_notcomply);
            viewHolder.rb_result_na = (RadioButton) convertView.findViewById(R.id.rb_result_na);
            viewHolder.btn_inspections = (Button) convertView.findViewById(R.id.btn_inspections);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Get the data item for this position
        final InspectionItems dataModel = getItem(position);

        viewHolder.txt_code.setText(dataModel.getCat_code());
        viewHolder.txt_categories.setText(dataModel.getCat_name());
        viewHolder.txt_check_items_name.setText(dataModel.getLabel());
        Log.d("InspectionItemsAdapter", "getItem_result() => " + dataModel.getItem_result());
        if (dataModel.getItem_result() != null) {

            if (dataModel.getItem_result().equals("1")) {
               // if (dataModel.getItem_result().equals("1")) {
                viewHolder.rb_result_comply.setChecked(true);
                viewHolder.rb_result_comply.setTag(position);
            } else if (dataModel.getItem_result().equals("2")) {
                viewHolder.rb_result_notcomply.setChecked(true);
                viewHolder.rb_result_notcomply.setTag(position);
            } else if (dataModel.getItem_result().equals("3")) {
                viewHolder.rb_result_na.setChecked(true);
                viewHolder.rb_result_na.setTag(position);
            }
        }

        viewHolder.rb_result_comply.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    InspectionItems inspectionitems = InspectionItems.findById(InspectionItems.class, dataModel.getRowid());
                    inspectionitems.item_result = "1";//+dataModel.getItem_result();
                    inspectionitems.save();
                }
            }
        });

        viewHolder.rb_result_notcomply.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    InspectionItems inspectionitems = InspectionItems.findById(InspectionItems.class, dataModel.getRowid());
                    inspectionitems.item_result = "2";//+dataModel.getItem_result();
                    inspectionitems.save();
                }
            }
        });

        viewHolder.rb_result_na.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    InspectionItems inspectionitems = InspectionItems.findById(InspectionItems.class, dataModel.getRowid());
                    inspectionitems.item_result = "3";//+dataModel.getItem_result();
                    inspectionitems.save();
                }
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public int getCount() {
        return dataSet.size(); // size, lenght, count ...?
    }

    /*@Override
    public Object getItem(int position) {
        return position;
    }*/

}


