package com.exs.orm;

import com.orm.SugarRecord;

public class AreaClassID extends SugarRecord {
    //String area_class_id;
    String label;
    String value;
    String key;

    public AreaClassID(){

    }

    public AreaClassID(String label,String value, String key){
        //this.area_class_id=area_class_id;
        this.label=label;
        this.value=value;
        this.key=key;

    }

    //public String getArea_class_id() { return area_class_id; }
    public String getLabel() { return label; }
    public String getValue() { return value; }
    public String getKey() { return key; }
}
