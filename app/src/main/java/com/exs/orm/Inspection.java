package com.exs.orm;

import com.orm.SugarRecord;

public class Inspection extends SugarRecord {

    String count;
    String inspection_id;
    String equipment_id;
    String grade_d;
    String grade_c;
    String grade_v;
    String report_no;
    String inspection_date;
    String location;
    String inspection_type_id;
    String inspector_id;
    String supervisor_id;
    String created_by;
    String created_date,updated_by,updated_date,campaign_id,grades,status,compliance_status,inspector,item_tag_no,description,finding_count;

    public Inspection(){

    }

    public Inspection(String count, String inspection_id,String equipment_id,String grade_d,String grade_c, String grade_v,String report_no,
                      String inspection_date,String location,String inspection_type_id,String inspector_id,String supervisor_id,
                      String created_by,String created_date,String updated_by,String updated_date,String campaign_id,String grades,
                      String status,String compliance_status,String inspector,String item_tag_no,String description,String finding_count){

        this.count=count;
        this.inspection_id=inspection_id;
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
        this.grades=grades;
        this.status=status;
        this.compliance_status=compliance_status;
        this.inspector=inspector;
        this.item_tag_no=item_tag_no;
        this.description=description;
        this.finding_count=finding_count;

    }

    public Inspection(String inspection_id,String equipment_id,String grade_d,String grade_c, String grade_v,String report_no,
                      String inspection_date,String location,String inspection_type_id,String inspector_id,String supervisor_id,
                      String created_by,String created_date,String updated_by,String updated_date,String campaign_id,String grades,
                      String status,String compliance_status,String inspector,String item_tag_no,String description,String finding_count){

        this.inspection_id=inspection_id;
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
        this.grades=grades;
        this.status=status;
        this.compliance_status=compliance_status;
        this.inspector=inspector;
        this.item_tag_no=item_tag_no;
        this.description=description;
        this.finding_count=finding_count;

    }

    public String getCount() { return count; }
    public String getInspection_id() { return inspection_id; }
    public String getEquipment_id(){return equipment_id;}
    public String getGrade_d(){return grade_d;}
    public String getGrade_c(){return grade_c;}
    public String getGrade_v(){return grade_v;}
    public String getReport_no(){return report_no;}
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
    public String getGrades() { return grades; }
    public String getStatus() { return status; }
    public String getCompliance_status() { return compliance_status; }
    public String getInspector() { return inspector; }
    public String getItem_tag_no() { return item_tag_no; }
    public String getDescription() { return description; }
    public String getFinding_count() { return finding_count; }

}
