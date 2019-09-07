package com.exs.fragment;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.exs.adapter.CampaignAdapter;
import com.exs.appexssynergy.R;
import com.exs.orm.Campaign;
import com.exs.util.Generate;
import com.exs.util.SysMessage;
import com.exs.util.UserPreference;

import java.util.ArrayList;
import java.util.List;

public class FragmentCampaign extends Fragment {
    private static final String LOG_TAG = "Campaign";

    // :initialize class
    public UserPreference UserPreference = new UserPreference();
    public SysMessage SysMessage = new SysMessage();
    public com.exs.util.Generate Generate = new Generate();

    View view;
    ListView listView;
    TextView no_record;

    List<Campaign> camp;
    ArrayList<Campaign> campArrayList;

    // :variable

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_campaign, container, false);

        // set title
        String strtext = getArguments().getString("title");
        getActivity().setTitle(strtext);

        no_record = (TextView) view.findViewById(R.id.tvNoRecord);
        listView = (ListView) view.findViewById(R.id.list);

        //configure strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //configure class
        UserPreference.init(getContext());
        SysMessage.init(getContext());
        Generate.init(getContext());

        camp = Campaign.listAll(Campaign.class);
        campArrayList= new ArrayList<>();

        if(camp.size() > 0 ) {
            no_record.setVisibility(View.GONE);

            for (int i = 0; i < camp.size(); i++) {
                Log.d(LOG_TAG, "ACTION TYPE " + camp.get(i).getCampaign_name());
                int count = i+1;
                campArrayList.add(new Campaign(String.valueOf(count),camp.get(i).getCampaign_id(), camp.get(i).getClient_id(),camp.get(i).getProject_id(),camp.get(i).getFacility_id(),camp.get(i).getCampaign_name()
                        ,camp.get(i).getDescription(),camp.get(i).getStart_date(),camp.get(i).getEnd_date(),camp.get(i).getClient(),camp.get(i).getProject()));

            }

            //set campaign adapter
            CampaignAdapter adapter= new CampaignAdapter(getActivity(), campArrayList);
            listView.setAdapter(adapter);
        }else{
            no_record.setVisibility(View.VISIBLE);
        }

        return view;
    }

}
