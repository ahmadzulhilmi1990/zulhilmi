package com.exs.orm;

import com.orm.SugarRecord;

public class Equipment extends SugarRecord {

    String count;
    String equipment_id;
    String description;
    String item_tag_no;
    String equipment_type_id;
    String facility_id;
    String reference_no;
    String sub_system;
    String sub_system_desc;
    String module_area_id;
    String data_sheet;
    String drawing_ref;
    String area_class_id;
    String area_temp_class_id;
    String area_gas_group_id;
    //String equipment_protection_type_id;
    String equipment_manufacturer;
    String model_type;
    String equipment_serial_no;
    String equipment_temp_class_id;
    String equipment_gas_group_id;
    String ex_cert_no;
    String equipment_zone_id;
    String equipment_ip_rating1_id;
    String equipment_ip_rating2_id;
    String url;


    public Equipment(){

    }

    public Equipment (String count,String equipment_id, String description, String item_tag_no, String equipment_type_id, String facility_id, String reference_no,
                      String sub_system, String sub_system_desc, String module_area_id, String data_sheet, String drawing_ref, String area_class_id,
                      String area_temp_class_id, String area_gas_group_id, String equipment_manufacturer,
                      String model_type, String equipment_serial_no, String equipment_temp_class_id, String equipment_gas_group_id, String ex_cert_no,
                      String equipment_zone_id, String equipment_ip_rating1_id, String equipment_ip_rating2_id, String url){

        this.count=count;
        this.equipment_id=equipment_id;
        this.description=description;
        this.item_tag_no=item_tag_no;
        this.equipment_type_id=equipment_type_id;
        this.facility_id=facility_id;
        this.reference_no=reference_no;
        this.sub_system=sub_system;
        this.sub_system_desc=sub_system_desc;
        this.module_area_id=module_area_id;
        this.data_sheet=data_sheet;
        this.drawing_ref=drawing_ref;
        this.area_class_id=area_class_id;
        this.area_temp_class_id=area_temp_class_id;
        this.area_gas_group_id=area_gas_group_id;
        //this.equipment_protection_type_id=equipment_protection_type_id;
        this.equipment_manufacturer=equipment_manufacturer;
        this.model_type=model_type;
        this.equipment_serial_no=equipment_serial_no;
        this.equipment_temp_class_id=equipment_temp_class_id;
        this.equipment_gas_group_id=equipment_gas_group_id;
        this.ex_cert_no=ex_cert_no;
        this.equipment_zone_id=equipment_zone_id;
        this.equipment_ip_rating1_id=equipment_ip_rating1_id;
        this.equipment_ip_rating2_id=equipment_ip_rating2_id;
        this.url=url;

    }

    public Equipment (String equipment_id, String description, String item_tag_no, String equipment_type_id, String facility_id, String reference_no,
            String sub_system, String sub_system_desc, String module_area_id, String data_sheet, String drawing_ref, String area_class_id,
            String area_temp_class_id, String area_gas_group_id, String equipment_manufacturer,
            String model_type, String equipment_serial_no, String equipment_temp_class_id, String equipment_gas_group_id, String ex_cert_no,
            String equipment_zone_id, String equipment_ip_rating1_id, String equipment_ip_rating2_id, String url){

        this.equipment_id=equipment_id;
        this.description=description;
        this.item_tag_no=item_tag_no;
        this.equipment_type_id=equipment_type_id;
        this.facility_id=facility_id;
        this.reference_no=reference_no;
        this.sub_system=sub_system;
        this.sub_system_desc=sub_system_desc;
        this.module_area_id=module_area_id;
        this.data_sheet=data_sheet;
        this.drawing_ref=drawing_ref;
        this.area_class_id=area_class_id;
        this.area_temp_class_id=area_temp_class_id;
        this.area_gas_group_id=area_gas_group_id;
        //this.equipment_protection_type_id=equipment_protection_type_id;
        this.equipment_manufacturer=equipment_manufacturer;
        this.model_type=model_type;
        this.equipment_serial_no=equipment_serial_no;
        this.equipment_temp_class_id=equipment_temp_class_id;
        this.equipment_gas_group_id=equipment_gas_group_id;
        this.ex_cert_no=ex_cert_no;
        this.equipment_zone_id=equipment_zone_id;
        this.equipment_ip_rating1_id=equipment_ip_rating1_id;
        this.equipment_ip_rating2_id=equipment_ip_rating2_id;
        this.url=url;

    }


    public String getCount() {
        return count;
    }

    public String getEquipment_id() {
        return equipment_id;
    }
    public String getDescription(){
        return description;
    }

    public String getItem_tag_no() {
        return item_tag_no;
    }

    public String getEquipment_type_id() {
        return equipment_type_id;
    }

    public String getFacility_id() {
        return facility_id;
    }

    public String getReference_no() {
        return reference_no;
    }

    public String getSub_system() {
        return sub_system;
    }

    public String getSub_system_desc() {
        return sub_system_desc;
    }

    public String getModule_area_id() {
        return module_area_id;
    }

    public String getData_sheet() {
        return data_sheet;
    }

    public String getDrawing_ref() {
        return drawing_ref;
    }

    public String getArea_class_id() {
        return area_class_id;
    }

    public String getArea_temp_class_id() {
        return area_temp_class_id;
    }

    public String getArea_gas_group_id() {
        return area_gas_group_id;
    }

    //public String getEquipment_protection_type_id() { return equipment_protection_type_id; }

    public String getEquipment_manufacturer() {
        return equipment_manufacturer;
    }

    public String getModel_type() {
        return model_type;
    }

    public String getEquipment_serial_no() {
        return equipment_serial_no;
    }

    public String getEquipment_temp_class_id() {
        return equipment_temp_class_id;
    }

    public String getEquipment_gas_group_id() {
        return equipment_gas_group_id;
    }

    public String getEx_cert_no() {
        return ex_cert_no;
    }

    public String getEquipment_zone_id() {
        return equipment_zone_id;
    }

    public String getEquipment_ip_rating1_id() {
        return equipment_ip_rating1_id;
    }

    public String getEquipment_ip_rating2_id() {
        return equipment_ip_rating2_id;
    }

    public String getUrl() {
        return url;
    }
}


