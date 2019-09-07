package com.exs.orm;

import com.orm.SugarRecord;

public class EquipmentProtectionType extends SugarRecord {
    String equipment_protection_type_id;
    String inspection_id;


    public EquipmentProtectionType(){

    }

    public EquipmentProtectionType(String equipment_protection_type_id, String inspection_id){
        this.equipment_protection_type_id=equipment_protection_type_id;
        this.inspection_id=inspection_id;

    }

    public String getEquipment_protection_type_id() { return equipment_protection_type_id; }
    public String getInspection_id() { return inspection_id; }

}
