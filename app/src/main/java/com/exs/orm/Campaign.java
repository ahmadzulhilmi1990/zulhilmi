package com.exs.orm;

import com.orm.SugarRecord;

public class Campaign extends SugarRecord {
    String count,campaign_id,client_id,project_id,facility_id,campaign_name,description,start_date,end_date,client,project;

    public Campaign(){

    }

    public Campaign(String count,String campaign_id,String client_id,String project_id,String facility_id,String campaign_name,String description,String start_date,String end_date,String client,String project){
        this.count=count;
        this.campaign_id=campaign_id;
        this.client_id=client_id;
        this.project_id=project_id;
        this.facility_id=facility_id;
        this.campaign_name=campaign_name;
        this.description=description;
        this.start_date=start_date;
        this.end_date=end_date;
        this.client=client;
        this.project=project;
    }

    public String getCount() { return count; }
    public String getCampaign_id() { return campaign_id; }
    public String getClient_id() {   return client_id;   }
    public String getProject_id() {   return project_id;   }
    public String getFacility_id() {   return facility_id;   }
    public String getCampaign_name() {   return campaign_name;   }
    public String getDescription() {   return description;   }
    public String getStart_date() {   return start_date;   }
    public String getEnd_date() {   return end_date;   }
    public String getClient() {   return client;   }
    public String getProject() {   return project;   }
}
