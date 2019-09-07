package com.exs.orm;

import com.orm.SugarRecord;

public class ProtectionTypeID extends SugarRecord {
    //String protection_type_id;
    String label;
    String value;
    String key;
    String inspection_id;
    public Boolean checked;

    public ProtectionTypeID(){}

    public ProtectionTypeID(String label,String value, String key){
        //this.protection_type_id=protection_type_id;
        this.label=label;
        this.value=value;
        this.key=key;
    }

    public ProtectionTypeID(String label,String value, String key, String inspection_id, Boolean checked){
        //this.protection_type_id=protection_type_id;
        this.label=label;
        this.value=value;
        this.key=key;
        this.inspection_id = inspection_id;
        this.checked = checked;
    }

   // public String getProtection_type_id() { return protection_type_id; }
    public String getLabel() { return label; }
    public String getValue() { return value; }
    public String getKey() { return key; }
    public String getInspection_id() { return inspection_id; }
    public Boolean getChecked() { return checked; }
}
