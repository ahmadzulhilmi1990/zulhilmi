package com.exs.orm;

import com.orm.SugarRecord;

public class Facility extends SugarRecord {
    String facility_id;
    String facility_name;
    String code;
    String location;
    String lang;
    String lat;
    String pic_name;
    String no_of_equipment;
    String pic_email;
    String pic_contact;
    String client_id;
    //String campaign_id;

    public Facility(){

    }

    public Facility(String facility_id, String facility_name, String code, String location, String lang, String lat, String pic_name, String no_of_equipment,
            String pic_email, String pic_contact, String client_id){

        this.facility_id=facility_id;
        this.facility_name=facility_name;
        this.code=code;
        this.location=location;
        this.lang=lang;
        this.lat=lat;
        this.pic_name=pic_name;
        this.no_of_equipment=no_of_equipment;
        this.pic_email=pic_email;
        this.pic_contact=pic_contact;
        this.client_id=client_id;
        //this.campaign_id=campaign_id;
    }

    public String getFacility_id() {return facility_id; }
    public String getFacility_name() { return facility_name; }
    public String getCode() { return code; }
    public String getLocation() { return location; }
    public String getLang() { return lang; }
    public String getLat() { return lat; }
    public String getPic_name() { return pic_name; }
    public String getNo_of_equipment() { return no_of_equipment; }
    public String getPic_email() { return pic_email; }
    public String getPic_contact() { return pic_contact; }
    public String getClient_id() { return client_id; }
    //public String getCampaign_id() { return campaign_id; }
}

