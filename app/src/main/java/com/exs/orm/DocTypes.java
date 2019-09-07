package com.exs.orm;

import com.orm.SugarRecord;

public class DocTypes extends SugarRecord {
    //String doc_types_id;
    String label;
    String value;

    public DocTypes(){

    }

    public DocTypes(String label,String value){
        //this.doc_types_id=doc_types_id;
        this.label=label;
        this.value=value;

    }

    //public String doc_types_id() { return doc_types_id; }
    public String getLabel() { return label; }
    public String getValue() { return value; }
}
