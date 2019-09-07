package com.exs.orm;

import com.orm.SugarRecord;

public class Project extends SugarRecord {
    String project_id;
    //String campaign_id;
    String project_name;
    String code;
    String description;
    String start_date;
    String end_date;
    String client_id;



    public Project(){

    }

    public Project(String project_id, String project_name, String code, String description, String start_date,
                   String end_date, String client_id){

        this.project_id=project_id;
        //this.project_id=campaign_id;
        this.project_name=project_name;
        this.code=code;
        this.description=description;
        this.start_date=start_date;
        this.end_date=end_date;
        this.client_id=client_id;
    }

    public String getProject_id(){return project_id;}
    //public String getCampaign_id(){return campaign_id;}
    public String getProject_name(){return project_name;}
    public String getCode(){return code;}
    public String getDescription(){return description;}
    public String getStart_date(){return start_date;}
    public String getEnd_date(){return end_date;}
    public String getClient_id(){return client_id;}

}
