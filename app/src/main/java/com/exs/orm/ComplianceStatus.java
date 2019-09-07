package com.exs.orm;

import com.orm.SugarRecord;

public class ComplianceStatus extends SugarRecord {
    //String compliance_status_id;
    String label;
    String value;

    public ComplianceStatus(){

    }

    public ComplianceStatus(String label,String value){
        //this.compliance_status_id=compliance_status_id;
        this.label=label;
        this.value=value;

    }

    //public String compliance_status_id() { return compliance_status_id; }
    public String getLabel() { return label; }
    public String getValue() { return value; }
}
