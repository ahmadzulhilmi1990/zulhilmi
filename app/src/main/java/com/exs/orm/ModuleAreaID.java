package com.exs.orm;

import com.orm.SugarRecord;

public class ModuleAreaID extends SugarRecord {
    //String module_area_id;
    String label;
    String value;
    String key;

    public ModuleAreaID(){}

    public ModuleAreaID(String label,String value, String key){
        //this.module_area_id=module_area_id;
        this.label=label;
        this.value=value;
        this.key=key;
    }

    //public String getModule_area_id() { return module_area_id; }
    public String getLabel() { return label; }
    public String getValue() { return value; }
    public String getKey() { return key; }
}
