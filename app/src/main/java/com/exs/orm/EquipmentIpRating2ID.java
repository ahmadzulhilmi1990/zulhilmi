package com.exs.orm;

import com.orm.SugarRecord;

public class EquipmentIpRating2ID extends SugarRecord {
    //String equipment_ip_rating2_id;
    String label;
    String value;
    String key;

    public EquipmentIpRating2ID(){

    }

    public EquipmentIpRating2ID(String label,String value, String key){
        //this.equipment_ip_rating2_id=equipment_ip_rating2_id;
        this.label=label;
        this.value=value;
        this.key=key;

    }

    //public String getEquipment_ip_rating2_id() { return equipment_ip_rating2_id; }
    public String getLabel() { return label; }
    public String getValue() { return value; }
    public String getKey() { return key; }
}
