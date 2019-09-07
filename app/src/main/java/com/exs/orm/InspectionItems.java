package com.exs.orm;

import com.orm.SugarRecord;

public class InspectionItems extends SugarRecord {
    public String inspection_items_id;
    public String equipment_id;
    public String seq;
    public String name;
    public String item_result;
    public String item_result_id;
    public String label;
    public Long rowid;
    public String cat_name;
    public String cat_code;

    public InspectionItems(){}

    public InspectionItems(String inspection_items_id, String equipment_id, String seq, String name, String item_result, String item_result_id){
        this.inspection_items_id=inspection_items_id;
        this.equipment_id=equipment_id;
        this.seq=seq;
        this.name=name;
        this.item_result=item_result;
        this.item_result_id=item_result_id;
    }

    public InspectionItems(Long rowid,String inspection_items_id, String equipment_id, String seq, String name, String item_result, String item_result_id,String label,String cat_name,String cat_code){
        this.rowid=rowid;
        this.inspection_items_id=inspection_items_id;
        this.equipment_id=equipment_id;
        this.seq=seq;
        this.name=name;
        this.item_result=item_result;
        this.item_result_id=item_result_id;
        this.label = label;
        this.cat_name = cat_name;
        this.cat_code = cat_code;
    }

    public String getInspection_items_id() { return inspection_items_id; }
    public String getEquipment_id() { return equipment_id; }
    public String getSeq() { return seq; }
    public String getName() { return name; }
    public String getItem_result() { return item_result; }
    public String getItem_result_id() { return item_result_id; }
    public String getLabel() { return label; }
    public Long getRowid() { return rowid; }
    public String getCat_name() { return cat_name; }
    public String getCat_code() { return cat_code; }

    public void setInspection_items_id(String inspection_items_id) { this.inspection_items_id = inspection_items_id; }
    public void setEquipment_id(String equipment_id) { this.equipment_id = equipment_id; }
    public void setSeq(String seq) { this.seq = seq; }
    public void setName(String name) { this.name = name; }
    public void setItem_result(String item_result) { this.item_result = item_result; }
    public void setItem_result_id(String item_result_id) { this.item_result_id = item_result_id; }
    public void setLabel(String label) { this.label = label; }
    public void setRowid(Long rowid) { this.rowid = rowid; }
    public void setCat_name(String cat_name) { this.cat_name = cat_name; }
    public void setCat_code(String cat_code) { this.cat_code = cat_code; }

    public Object get(int position) {
        return position;
    }
}
