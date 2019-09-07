package com.exs.orm;

import com.orm.SugarRecord;

public class Grades extends SugarRecord {
    String grade;
    String inspection_id;


    public Grades(){

    }

    public Grades(String grade, String inspection_id){
        this.grade=grade;
        this.inspection_id=inspection_id;

    }

    public String getGrade() { return grade; }
    public String getInspection_id() { return inspection_id; }

}
