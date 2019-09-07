package com.exs.orm;

import com.orm.SugarRecord;

public class AreaGasGroupID extends SugarRecord {
    //String area_gas_group_id;
    String label;
    String value;
    String key;

    public AreaGasGroupID(){

    }

    public AreaGasGroupID(String label,String value, String key){
        //this.area_gas_group_id=area_gas_group_id;
        this.label=label;
        this.value=value;
        this.key=key;

    }

    //public String getArea_gas_group_id() { return area_gas_group_id; }
    public String getLabel() { return label; }
    public String getValue() { return value; }
    public String getKey() { return key; }
}
