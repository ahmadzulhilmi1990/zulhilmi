package com.exs.orm;

import com.orm.SugarRecord;

public class InspectionItemsDetails extends SugarRecord {
    String inspection_category_id;
    String grade_c;
    String item_name;
    String seq_no;
    String grade_v;
    String grade_d;
    String inspection_items_details_id;

    public InspectionItemsDetails(){}

    public InspectionItemsDetails(String inspection_category_id, String grade_c, String item_name, String seq_no, String grade_v, String grade_d, String inspection_items_details_id){
        this.inspection_category_id=inspection_category_id;
        this.grade_c=grade_c;
        this.item_name=item_name;
        this.seq_no=seq_no;
        this.grade_v=grade_v;
        this.grade_d=grade_d;
        this.inspection_items_details_id=inspection_items_details_id;
    }

    public String getInspection_category_id() { return inspection_category_id; }
    public String getGrade_c() { return grade_c; }
    public String getItem_name() { return item_name; }
    public String getSeq_no() { return seq_no; }
    public String getGrade_v() { return grade_v; }
    public String getGrade_d() { return grade_d; }
    public String getInspection_items_details_id() { return inspection_items_details_id; }
}
