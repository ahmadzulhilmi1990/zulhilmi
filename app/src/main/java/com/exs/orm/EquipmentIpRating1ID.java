package com.exs.orm;

import com.orm.SugarRecord;

public class EquipmentIpRating1ID extends SugarRecord {
    //String equipment_ip_rating1_id;
    String label;
    String value;
    String key;

    public EquipmentIpRating1ID(){

    }

    public EquipmentIpRating1ID(String label,String value, String key){
        //this.equipment_ip_rating1_id=equipment_ip_rating1_id;
        this.label=label;
        this.value=value;
        this.key=key;

    }

    //public String getEquipment_ip_rating1_id() { return equipment_ip_rating1_id; }
    public String getLabel() { return label; }
    public String getValue() { return value; }
    public String getKey() { return key; }
}
