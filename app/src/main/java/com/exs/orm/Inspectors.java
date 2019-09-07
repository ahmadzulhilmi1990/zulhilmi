package com.exs.orm;

import com.orm.SugarRecord;

public class Inspectors extends SugarRecord {
    String inspector_id;
    String campaign_id;
    String full_name;
    String is_supervisor;

    public Inspectors(){}

    public Inspectors(String inspector_id, String campaign_id, String full_name, String is_supervisor){
        this.inspector_id=inspector_id;
        this.campaign_id=campaign_id;
        this.full_name=full_name;
        this.is_supervisor=is_supervisor;
    }

    public String getInspector_id() { return inspector_id; }
    public String getCampaign_id() { return campaign_id; }
    public String getFull_name() { return full_name; }
    public String getIs_supervisor() { return is_supervisor; }
}
