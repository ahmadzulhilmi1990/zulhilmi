package com.exs.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.exs.appexssynergy.R;
import com.exs.orm.EquipmentProtectionType;
import com.exs.orm.ProtectionTypeID;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class ProtectionTypeAdapter extends ArrayAdapter<ProtectionTypeID> {

    ProgressDialog progressDialog;
    private ArrayList<ProtectionTypeID> dataSet;

    Context mContext;
    String keycode;
    private List<ProtectionTypeID> list;

    // View lookup cache
    private static class ViewHolder {
        TextView title;
        CheckBox chk_box;


    }
    public ProtectionTypeAdapter(Context context, ArrayList<ProtectionTypeID> data) {
        super(context, R.layout.activity_protection_type_row, data);
        this.dataSet = data;
        this.mContext=context;
        this.list =data;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ProtectionTypeID getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Boolean isChecked(int position) {
        return list.get(position).getChecked();
    }

    public View getView(final int position, View rowView, ViewGroup parent) {
        View convertView = rowView;
        // Get the data item for this position
        final ProtectionTypeID dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_protection_type_row, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.chk_box = (CheckBox) convertView.findViewById(R.id.chk_box);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
           // viewHolder.chk_box.setOnCheckedChangeListener(null);
        }

        //String value = Select.from(EquipmentProtectionType.class).where("EQUIPMENTPROTECTIONTYPE = "'++'").first().getFull_name();


        viewHolder.title.setText(dataModel.getKey());

       /* viewHolder.chk_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    EquipmentProtectionType data = new EquipmentProtectionType(dataModel.getValue(),dataModel.getInspection_id());
                    data.save();
                }else{
                    //delete
                    //EquipmentProtectionType data = new EquipmentProtectionType.
                }
            }
        });*/

        viewHolder.chk_box.setChecked(list.get(position).checked);
        viewHolder.chk_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean newState = !list.get(position).getChecked();
                list.get(position).checked= newState;
                if(newState){
                    EquipmentProtectionType data = new EquipmentProtectionType(dataModel.getValue(),dataModel.getInspection_id());
                    data.save();
                }else{

                    SugarRecord.deleteAll(EquipmentProtectionType.class,"EQUIPMENTPROTECTIONTYPEID="+"'"+dataModel.getValue()+"'");

                }

            }
        });

        viewHolder.chk_box.setChecked(isChecked(position));

        // Return the completed view to render on screen
        return convertView;
    }


}
