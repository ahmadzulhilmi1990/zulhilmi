package com.exs.orm;

import com.orm.SugarRecord;

public class InspectionCategories extends SugarRecord {
    String inspection_categories_id;
    String code;
    String category_name;

    public InspectionCategories(){}

    public InspectionCategories(String inspection_categories_id,String code,String category_name){
        this.inspection_categories_id=inspection_categories_id;
        this.code=code;
        this.category_name=category_name;
    }

    public String getInspection_categories_id() { return inspection_categories_id; }
    public String getCode() { return code; }
    public String getCategory_name() { return category_name; }
}
