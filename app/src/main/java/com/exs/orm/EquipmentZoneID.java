package com.exs.orm;

import com.orm.SugarRecord;

public class EquipmentZoneID extends SugarRecord {
    //String equipment_zone_id;
    String label;
    String value;
    String key;

    public EquipmentZoneID(){

    }

    public EquipmentZoneID(String label,String value, String key){
        //this.equipment_zone_id=equipment_zone_id;
        this.label=label;
        this.value=value;
        this.key=key;

    }

    //public String getEquipment_zone_id() { return equipment_zone_id; }
    public String getLabel() { return label; }
    public String getValue() { return value; }
    public String getKey() { return key; }
}
