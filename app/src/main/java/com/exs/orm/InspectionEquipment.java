package com.exs.orm;

import com.orm.SugarRecord;

public class InspectionEquipment extends SugarRecord {

    String count;
    public String inspection_detail_id;
    public String equipment_id;
    public String grade_d;
    public String grade_c;
    public String grade_v;
    public String report_no;
    public String inspection_date;
    public String location;
    public String inspection_type_id;
    public String inspector_id;
    public String supervisor_id;
    public String created_by;
    public String created_date;
    public String updated_by;
    public String updated_date;
    public String campaign_id;
    //public String grades;
    public String status;
    public String compliance_status;
    //public String inspector;
    public String item_tag_no;
    public String description;
    //public String finding_count;

    public String equipment_type_id;
    public String facility_id;
    public String reference_no;
    public String sub_system;
    public String sub_system_desc;
    public String module_area_id;
    public String data_sheet;
    public String drawing_ref;
    public String area_class_id;
    public String area_temp_class_id;
    public String area_gas_group_id;
    //public String equipment_protection_type_id;
    public String equipment_manufacturer;
    public String model_type;
    public String equipment_serial_no;
    public String equipment_temp_class_id;
    public String equipment_gas_group_id;
    public String ex_cert_no;
    public String equipment_zone_id;
    public String equipment_ip_rating1_id;
    public String equipment_ip_rating2_id;
    public String url;
    public String inspector_name;

    public InspectionEquipment(){

    }

    public InspectionEquipment (String count,String inspection_date,String item_tag_no, String description, String inspector_name, String status,String compliance_status,String campaign_id,String equipment_id,String inspection_detail_id){
        this.count=count;
        this.inspection_date=inspection_date;
        this.item_tag_no=item_tag_no;
        this.description=description;
        this.inspector_name=inspector_name;
        this.status=status;
        this.compliance_status=compliance_status;
        this.campaign_id=campaign_id;
        this.equipment_id=equipment_id;
        this.inspection_detail_id = inspection_detail_id;
    }

    public InspectionEquipment(String inspection_detail_id, String equipment_id, String grade_d, String grade_c, String grade_v, String report_no,
                               String inspection_date,String location,String inspection_type_id,String inspector_id,String supervisor_id,
                               String created_by,String created_date,String updated_by,String updated_date,String campaign_id,
                               String status,String compliance_status, String description, String item_tag_no, String equipment_type_id, String facility_id, String reference_no,
                               String sub_system, String sub_system_desc, String module_area_id, String data_sheet, String drawing_ref, String area_class_id,
                               String area_temp_class_id, String area_gas_group_id, String equipment_manufacturer,
                               String model_type, String equipment_serial_no, String equipment_temp_class_id, String equipment_gas_group_id, String ex_cert_no,
                               String equipment_zone_id, String equipment_ip_rating1_id, String equipment_ip_rating2_id, String url){

        this.inspection_detail_id=inspection_detail_id;
        this.equipment_id=equipment_id;
        this.grade_d=grade_d;
        this.grade_c=grade_c;
        this.grade_v=grade_v;
        this.report_no=report_no;
        this.inspection_date=inspection_date;
        this.location=location;
        this.inspection_type_id=inspection_type_id;
        this.inspector_id=inspector_id;
        this.supervisor_id=supervisor_id;
        this.created_by=created_by;
        this.created_date=created_date;
        this.updated_by=updated_by;
        this.updated_date=updated_date;
        this.campaign_id=campaign_id;
        //this.grades=grades;
        this.status=status;
        this.compliance_status=compliance_status;
        //this.inspector=inspector;
        this.item_tag_no=item_tag_no;
        this.description=description;
        //this.finding_count=finding_count;

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


    public String getCount() { return count; }
    public String getInspection_detail_id() { return inspection_detail_id; }
    public String getEquipment_id() { return equipment_id; }
    public String getGrade_d() { return grade_d; }
    public String getGrade_c() { return grade_c; }
    public String getGrade_v() { return grade_v; }
    public String getReport_no() { return report_no; }
    public String getInspection_date(){return inspection_date;}
    public String getLocation(){return location;}
    public String getInspection_type_id(){return inspection_type_id;}
    public String getInspector_id(){return inspector_id;}
    public String getSupervisor_id(){return supervisor_id;}
    public String getCreated_date(){return created_date;}
    public String getCreated_by() { return created_by; }
    public String getUpdated_by() { return updated_by; }
    public String getUpdated_date() { return updated_date; }
    public String getCampaign_id() { return campaign_id; }
    //public String getGrades() { return grades; }
    public String getStatus() { return status; }
    public String getCompliance_status() { return compliance_status; }
    //public String getInspector() { return inspector; }

    //public String getFinding_count() { return finding_count; }
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

    // public String getEquipment_protection_type_id() {return equipment_protection_type_id;}

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

    public String getInspector_name() { return inspector_name; }
}
