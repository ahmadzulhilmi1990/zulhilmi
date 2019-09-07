package com.exs.orm;

import com.orm.SugarRecord;

public class AreaTempClassID extends SugarRecord {
    //String area_temp_class_id;
    String row_id;
    String label;
    String value;
    String key;

    public AreaTempClassID(){

    }

    public AreaTempClassID(String row_id, String label,String value, String key){
        //this.area_temp_class_id=area_temp_class_id;
        this.row_id=row_id;
        this.label=label;
        this.value=value;
        this.key=key;

    }

    //public String getArea_temp_class_id() { return area_temp_class_id; }
    public String getRow_id() { return row_id; }
    public String getLabel() { return label; }
    public String getValue() { return value; }
    public String getKey() { return key; }
}
