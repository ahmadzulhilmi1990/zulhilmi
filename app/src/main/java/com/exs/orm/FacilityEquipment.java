package com.exs.orm;

import com.orm.SugarRecord;

public class FacilityEquipment extends SugarRecord {

    String facility_equipment_id;
    String description;
    String item_tag_no;
    String equipment_type_id;
    String facility_id;
    String equipment_type;


    public FacilityEquipment (){

    }

    public FacilityEquipment(String facility_equipment_id, String description, String item_tag_no, String equipment_type_id, String facility_id, String equipment_type){

        this.facility_equipment_id=facility_equipment_id;
        this.description=description;
        this.item_tag_no=item_tag_no;
        this.equipment_type_id=equipment_type_id;
        this.facility_id=facility_id;
        this.equipment_type=equipment_type;

    }


    public String getFacility_equipment_id(){ return facility_equipment_id;}
    public String getDescription(){ return description;}
    public String getItem_tag_no(){ return item_tag_no;}
    public String getEquipment_type_id(){ return equipment_type_id;}
    public String getFacility_id(){ return facility_id;}
    public String getEquipment_type(){ return equipment_type;}
}
