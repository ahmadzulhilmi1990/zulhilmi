package com.exs.orm;

import com.orm.SugarRecord;

public class Client extends SugarRecord {
    String client_id;
    //String campaing_id;
    String client_name;
    String code;
    String pic_name;
    String file_name;
    String file_type;
    String file_location;
    String pic_email;
    String pic_contact;
    String address_id;
    //String address1;
    //String address2;
    //String address3;
    //String postcode;
    //String city;
    //String state_id;
    //String country_id;
    //String project_code;
    //String mandays;
    //String days_kick_off;


    public Client(){

    }

    public Client(String client_id, String client_name, String code, String pic_name, String file_name, String file_type,
                  String file_location, String pic_email, String pic_contact, String address_id){

        this.client_id=client_id;
        //this.campaing_id=campaing_id;
        this.client_name=client_name;
        this.code=code;
        this.pic_name=pic_name;
        this.file_name=file_name;
        this.file_type=file_type;
        this.file_location=file_location;
        this.pic_email=pic_email;
        this.pic_contact=pic_contact;
        this.address_id=address_id;
        /*this.address1=address1;
        this.address2=address2;
        this.address3=address3;
        this.postcode=postcode;
        this.city=city;
        this.state_id=state_id;
        this.country_id=country_id;
        this.project_code=project_code;
        this.mandays=mandays;
        this.days_kick_off=days_kick_off;*/

    }

    public String getClient_id() { return client_id; }
    //public String getCampaing_id() { return campaing_id; }
    public String getClient_name() { return client_name; }
    public String getCode() { return code; }
    public String getPic_name() { return pic_name; }
    public String getFile_name() { return file_name; }
    public String getFile_type() { return file_type; }
    public String getFile_location() { return file_location; }
    public String getPic_email() { return pic_email; }
    public String getPic_contact() { return pic_contact; }
    public String getAddress_id() { return address_id; }
   /* public String getAddress1() { return address1; }
    public String getAddress2() { return address2; }
    public String getAddress3() { return address3; }
    public String getPostcode() { return postcode; }
    public String getCity() { return city; }
    public String getState_id() { return state_id; }
    public String getCountry_id() { return country_id; }
    public String getProject_code() { return project_code; }
    public String getMandays() { return mandays; }
    public String getDays_kick_off() { return days_kick_off; }*/
}
