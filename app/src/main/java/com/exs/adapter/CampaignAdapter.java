package com.exs.adapter;

import android.app.ProgressDialog;
import android.content.Context;
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

import com.exs.appexssynergy.Dashboard;
import com.exs.appexssynergy.R;
import com.exs.fragment.FragmentInspection;
import com.exs.orm.Campaign;

import java.util.ArrayList;

public class CampaignAdapter extends ArrayAdapter<Campaign> {

    ProgressDialog progressDialog;
    private ArrayList<Campaign> dataSet;

    Context mContext;
    String keycode;

    // View lookup cache
    private static class ViewHolder {
        TextView no,client, project, name,start_date,end_date;
        Button btnview_inspections;
        ProgressBar pb;


    }
    public CampaignAdapter(Context context, ArrayList<Campaign> data) {
        super(context, R.layout.activity_campaign_adapter, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Campaign dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_campaign_adapter, parent, false);
            viewHolder.no = (TextView) convertView.findViewById(R.id.no);
            viewHolder.client = (TextView) convertView.findViewById(R.id.client);
            viewHolder.project = (TextView) convertView.findViewById(R.id.project);
            viewHolder.name = (TextView) convertView.findViewById(R.id.campaign);
            viewHolder.start_date = (TextView) convertView.findViewById(R.id.start);
            viewHolder.end_date = (TextView) convertView.findViewById(R.id.end);
            viewHolder.btnview_inspections = (Button) convertView.findViewById(R.id.btnview_inspections);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.no.setText(dataModel.getCount());
        viewHolder.client.setText(dataModel.getClient());
        viewHolder.project.setText(dataModel.getProject());
        viewHolder.name.setText(dataModel.getCampaign_name());
        viewHolder.start_date.setText(dataModel.getStart_date());
        viewHolder.end_date.setText(dataModel.getEnd_date());

        viewHolder.btnview_inspections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                Class fragmentClass = null;
                Bundle bundle = new Bundle();
                bundle.putString("title", "Inspection");
                bundle.putString("campaign_id", dataModel.getCampaign_id());
                bundle.putString("start_campaign", dataModel.getStart_date());
                bundle.putString("end_campaign", dataModel.getEnd_date());
                // set Fragmentclass Arguments
                fragmentClass = FragmentInspection.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    fragment.setArguments(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Dashboard activity = (Dashboard) mContext;

                FragmentManager fragmentManager= activity.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

            }
        });
        // Return the completed view to render on screen
        return convertView;
    }


}
