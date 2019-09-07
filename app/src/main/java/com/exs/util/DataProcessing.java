package com.exs.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.exs.orm.AreaClassID;
import com.exs.orm.AreaGasGroupID;
import com.exs.orm.AreaTempClassID;
import com.exs.orm.Campaign;
import com.exs.orm.Client;
import com.exs.orm.ComplianceStatus;
import com.exs.orm.DocTypes;
import com.exs.orm.Equipment;
import com.exs.orm.EquipmentIpRating1ID;
import com.exs.orm.EquipmentIpRating2ID;
import com.exs.orm.EquipmentProtectionType;
import com.exs.orm.EquipmentZoneID;
import com.exs.orm.Facility;
import com.exs.orm.Grades;
import com.exs.orm.InspectionCategories;
import com.exs.orm.InspectionEquipment;
import com.exs.orm.InspectionItems;
import com.exs.orm.InspectionItemsDetails;
import com.exs.orm.Inspectors;
import com.exs.orm.ModuleAreaID;
import com.exs.orm.Project;
import com.exs.orm.ProtectionTypeID;
import com.orm.SugarContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DataProcessing {

    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    public com.exs.util.UserPreference UserPreference = new UserPreference();
    Campaign campaign;
    Inspectors inspectors;
    Client client;
    Project project;
    Facility facility;
    Equipment equipment;
    InspectionEquipment inspectionEquipment;
    Grades grades;
    EquipmentProtectionType equipmentProtectionType;
    InspectionItems inspectionItems;

    ProtectionTypeID protectionTypeID;
    ModuleAreaID moduleAreaID;
    AreaClassID areaClassID;
    AreaTempClassID areaTempClassID;
    AreaGasGroupID areaGasGroupID;
    EquipmentIpRating1ID equipmentIpRating1ID;
    EquipmentIpRating2ID equipmentIpRating2ID;
    EquipmentZoneID equipmentZoneID;
    DocTypes docTypes;
    ComplianceStatus complianceStatus;
    InspectionCategories inspectionCategories;
    InspectionItemsDetails inspectionItemsDetails;

    public void init(Context c){
        settings = c.getSharedPreferences(PREFS_NAME, 0);
        UserPreference.init(c);

        //init
        SugarContext.init(c);
        campaign = new Campaign();
        inspectors = new Inspectors();
        client = new Client();
        project = new Project();
        facility = new Facility();
        equipment = new Equipment();
        inspectionEquipment = new InspectionEquipment();
        grades = new Grades();
        equipmentProtectionType = new EquipmentProtectionType();
        inspectionItems = new InspectionItems();
        protectionTypeID = new ProtectionTypeID();
        moduleAreaID = new ModuleAreaID();
        areaClassID = new AreaClassID();
        areaTempClassID = new AreaTempClassID();
        areaGasGroupID = new AreaGasGroupID();
        equipmentIpRating1ID = new EquipmentIpRating1ID();
        equipmentIpRating2ID = new EquipmentIpRating2ID();
        equipmentZoneID = new EquipmentZoneID();
        docTypes = new DocTypes();
        complianceStatus = new ComplianceStatus();
        inspectionCategories = new InspectionCategories();
        inspectionItemsDetails = new InspectionItemsDetails();
    }

    public void insertMyCampaign(String json){

        JSONArray dataJSONArray = null;
        JSONArray dataJSONArrayClient = null;
        JSONArray dataJSONArrayProject = null;
        JSONArray dataJSONArrayFacility = null;
        JSONArray dataJSONArrayEquipment = null;
        JSONArray dataJSONArrayInspectionEquipment = null;

        JSONArray dataJSONArrayProtectionTypeID = null;
        JSONArray dataJSONArrayModuleAreaID = null;
        JSONArray dataJSONArrayAreaClassID = null;
        JSONArray dataJSONAreaTempClassID = null;
        JSONArray dataJSONAreaGasGroupID = null;
        JSONArray dataJSONEquipmentIpRating1ID = null;
        JSONArray dataJSONEquipmentIpRating2ID = null;
        JSONArray dataJSONEquipmentZoneID = null;
        JSONArray dataJSONDocTypes = null;
        JSONArray dataJSONComplianceStatus = null;
        JSONArray dataJSONInspectionCategories = null;
        JSONArray dataJSONInspectionItemsDetails = null;


        JSONObject jsonObjectb;
        try {
            jsonObjectb = new JSONObject(json);

            //dataJSONArray = new JSONArray(json);
            dataJSONArray = jsonObjectb.getJSONObject("mycampaign").getJSONArray("campaign");
            dataJSONArrayClient = jsonObjectb.getJSONObject("mycampaign").getJSONArray("client");
            dataJSONArrayProject = jsonObjectb.getJSONObject("mycampaign").getJSONArray("project");
            dataJSONArrayFacility = jsonObjectb.getJSONObject("mycampaign").getJSONArray("facility");
            dataJSONArrayEquipment = jsonObjectb.getJSONObject("mycampaign").getJSONArray("equipment");
            dataJSONArrayInspectionEquipment = jsonObjectb.getJSONObject("mycampaign").getJSONArray("inspection");


            dataJSONArrayProtectionTypeID = jsonObjectb.getJSONObject("library").getJSONArray("protection_type_id");
            dataJSONArrayModuleAreaID = jsonObjectb.getJSONObject("library").getJSONArray("module_area_id");
            dataJSONArrayAreaClassID = jsonObjectb.getJSONObject("library").getJSONArray("area_class_id");
            dataJSONAreaTempClassID = jsonObjectb.getJSONObject("library").getJSONArray("area_temp_class_id");
            dataJSONAreaGasGroupID = jsonObjectb.getJSONObject("library").getJSONArray("area_gas_group_id");
            dataJSONEquipmentIpRating1ID = jsonObjectb.getJSONObject("library").getJSONArray("equipment_ip_rating1_id");
            dataJSONEquipmentIpRating2ID = jsonObjectb.getJSONObject("library").getJSONArray("equipment_ip_rating2_id");
            dataJSONEquipmentZoneID = jsonObjectb.getJSONObject("library").getJSONArray("equipment_zone_id");
            dataJSONDocTypes = jsonObjectb.getJSONObject("library").getJSONArray("doc_types");
            dataJSONComplianceStatus = jsonObjectb.getJSONObject("library").getJSONArray("compliance_status");
            dataJSONInspectionCategories = jsonObjectb.getJSONObject("library").getJSONArray("inspection_category");
            dataJSONInspectionItemsDetails = jsonObjectb.getJSONObject("library").getJSONArray("inspection_item");

            if (dataJSONArray.length() > 0) { // campaign

                Campaign.deleteAll(Campaign.class);
                Inspectors.deleteAll(Inspectors.class);

                for (int i = 0; i < dataJSONArray.length(); i++) {

                    String id = dataJSONArray.getJSONObject(i).getString("id").toString();
                    String client_id = dataJSONArray.getJSONObject(i).getString("client_id").toString();
                    String project_id = dataJSONArray.getJSONObject(i).getString("project_id").toString();
                    String facility_id = dataJSONArray.getJSONObject(i).getString("facility_id").toString();
                    String campaign_name = dataJSONArray.getJSONObject(i).getString("campaign_name").toString();
                    String description = dataJSONArray.getJSONObject(i).getString("description").toString();
                    String start_date = dataJSONArray.getJSONObject(i).getString("start_date").toString();
                    String end_date = dataJSONArray.getJSONObject(i).getString("end_date").toString();
                    String client = dataJSONArray.getJSONObject(i).getString("client").toString();
                    String project = dataJSONArray.getJSONObject(i).getString("project").toString();

                    campaign = new Campaign(String.valueOf(dataJSONArray.length()),id, client_id,project_id,facility_id,campaign_name,description,start_date,end_date,client,project);
                    campaign.save();

                    JSONArray dataJSONArrayInspector = null;
                    dataJSONArrayInspector=dataJSONArray.getJSONObject(i).getJSONArray("inspectors");

                    for(int j = 0; j < dataJSONArrayInspector.length(); j++){
                        String campaign_id = dataJSONArray.getJSONObject(i).getString("id").toString();
                        String inspector_id = dataJSONArrayInspector.getJSONObject(j).getString("id").toString();
                        String full_name = dataJSONArrayInspector.getJSONObject(j).getString("full_name").toString();
                        String is_supervisor = dataJSONArrayInspector.getJSONObject(j).getString("is_supervisor").toString();

                        inspectors = new Inspectors(inspector_id,campaign_id,full_name,is_supervisor);
                        inspectors.save();
                    }
                }
            }

            if(dataJSONArrayClient.length() > 0){ //client

                Client.deleteAll(Client.class);

                for (int i = 0; i < dataJSONArrayClient.length(); i++) {

                    String id = dataJSONArrayClient.getJSONObject(i).getString("id").toString();
                    String file_location = dataJSONArrayClient.getJSONObject(i).getString("file_location").toString();
                    String file_name = dataJSONArrayClient.getJSONObject(i).getString("file_name").toString();
                    String pic_contact = dataJSONArrayClient.getJSONObject(i).getString("pic_contact").toString();
                    String pic_name = dataJSONArrayClient.getJSONObject(i).getString("pic_name").toString();
                    String client_name = dataJSONArrayClient.getJSONObject(i).getString("client_name").toString();
                    String address_id = dataJSONArrayClient.getJSONObject(i).getString("address_id").toString();
                    String file_type = dataJSONArrayClient.getJSONObject(i).getString("file_type").toString();
                    String pic_email = dataJSONArrayClient.getJSONObject(i).getString("pic_email").toString();
                    String code = dataJSONArrayClient.getJSONObject(i).getString("code").toString();


                    client = new Client(id,client_name,code,pic_name,file_name,file_type,file_location,pic_email,pic_contact,address_id);
                    client.save();
                }

            }

            if(dataJSONArrayProject.length() > 0){ //project

                Project.deleteAll(Project.class);

                for (int i = 0; i < dataJSONArrayProject.length(); i++) {

                    String id = dataJSONArrayProject.getJSONObject(i).getString("id").toString();
                    String description = dataJSONArrayProject.getJSONObject(i).getString("description").toString();
                    String project_name = dataJSONArrayProject.getJSONObject(i).getString("project_name").toString();
                    String client_id = dataJSONArrayProject.getJSONObject(i).getString("client_id").toString();
                    String start_date = dataJSONArrayProject.getJSONObject(i).getString("start_date").toString();
                    String code = dataJSONArrayProject.getJSONObject(i).getString("code").toString();
                    String end_date = dataJSONArrayProject.getJSONObject(i).getString("end_date").toString();

                    project = new Project(id,project_name,code,description,start_date,end_date,client_id);
                    project.save();
                }

            }


            if(dataJSONArrayFacility.length() > 0){ //facility

                Facility.deleteAll(Facility.class);

                for (int i = 0; i < dataJSONArrayFacility.length(); i++) {

                    String id = dataJSONArrayFacility.getJSONObject(i).getString("id").toString();
                    String client_id = dataJSONArrayFacility.getJSONObject(i).getString("client_id").toString();
                    String pic_contact = dataJSONArrayFacility.getJSONObject(i).getString("pic_contact").toString();
                    String pic_name = dataJSONArrayFacility.getJSONObject(i).getString("pic_name").toString();
                    String lat = dataJSONArrayFacility.getJSONObject(i).getString("lat").toString();
                    String location = dataJSONArrayFacility.getJSONObject(i).getString("location").toString();
                    String facility_name = dataJSONArrayFacility.getJSONObject(i).getString("facility_name").toString();
                    String no_of_equipment = dataJSONArrayFacility.getJSONObject(i).getString("no_of_equipment").toString();
                    String pic_email = dataJSONArrayFacility.getJSONObject(i).getString("pic_email").toString();
                    String lang = dataJSONArrayFacility.getJSONObject(i).getString("lang").toString();
                    String code = dataJSONArrayFacility.getJSONObject(i).getString("code").toString();

                    facility = new Facility(id,facility_name,code,location,lang,lat,pic_name,no_of_equipment,pic_email,pic_contact,client_id);
                    facility.save();
                }

            }


            if(dataJSONArrayEquipment.length() > 0){ //equipment

                Equipment.deleteAll(Equipment.class);

                for (int i = 0; i < dataJSONArrayEquipment.length(); i++) {

                    String id = dataJSONArrayEquipment.getJSONObject(i).getString("id").toString();
                    String area_temp_class_id = dataJSONArrayEquipment.getJSONObject(i).getString("area_temp_class_id").toString();
                    String equipment_type_id = dataJSONArrayEquipment.getJSONObject(i).getString("equipment_type_id").toString();
                    String equipment_zone_id = dataJSONArrayEquipment.getJSONObject(i).getString("equipment_zone_id").toString();
                    String area_gas_group_id = dataJSONArrayEquipment.getJSONObject(i).getString("area_gas_group_id").toString();
                    String reference_no = dataJSONArrayEquipment.getJSONObject(i).getString("reference_no").toString();
                    String equipment_ip_rating1_id = dataJSONArrayEquipment.getJSONObject(i).getString("equipment_ip_rating1_id").toString();
                    String equipment_manufacturer = dataJSONArrayEquipment.getJSONObject(i).getString("equipment_manufacturer").toString();
                    String sub_system = dataJSONArrayEquipment.getJSONObject(i).getString("sub_system").toString();
                    String equipment_ip_rating2_id = dataJSONArrayEquipment.getJSONObject(i).getString("equipment_ip_rating2_id").toString();
                    String model_type = dataJSONArrayEquipment.getJSONObject(i).getString("model_type").toString();
                    String sub_system_desc = dataJSONArrayEquipment.getJSONObject(i).getString("sub_system_desc").toString();
                    String url = dataJSONArrayEquipment.getJSONObject(i).getString("url").toString();
                    String equipment_serial_no = dataJSONArrayEquipment.getJSONObject(i).getString("equipment_serial_no").toString();
                    String module_area_id = dataJSONArrayEquipment.getJSONObject(i).getString("module_area_id").toString();
                    String facility_id = dataJSONArrayEquipment.getJSONObject(i).getString("facility_id").toString();
                    String equipment_temp_class_id = dataJSONArrayEquipment.getJSONObject(i).getString("equipment_temp_class_id").toString();
                    String data_sheet = dataJSONArrayEquipment.getJSONObject(i).getString("data_sheet").toString();
                    String equipment_gas_group_id = dataJSONArrayEquipment.getJSONObject(i).getString("equipment_gas_group_id").toString();
                    String description = dataJSONArrayEquipment.getJSONObject(i).getString("description").toString();
                    String drawing_ref = dataJSONArrayEquipment.getJSONObject(i).getString("drawing_ref").toString();
                    String item_tag_no = dataJSONArrayEquipment.getJSONObject(i).getString("item_tag_no").toString();
                    String ex_cert_no = dataJSONArrayEquipment.getJSONObject(i).getString("ex_cert_no").toString();
                    String area_class_id = dataJSONArrayEquipment.getJSONObject(i).getString("area_class_id").toString();


                    equipment = new Equipment(id,description,item_tag_no,equipment_type_id,facility_id,reference_no,sub_system,sub_system_desc,module_area_id,data_sheet,
                            drawing_ref,area_class_id,area_temp_class_id, area_gas_group_id, equipment_manufacturer,
                            model_type, equipment_serial_no, equipment_temp_class_id, equipment_gas_group_id, ex_cert_no,
                            equipment_zone_id, equipment_ip_rating1_id, equipment_ip_rating2_id, url );
                    equipment.save();
                }

            }


            if(dataJSONArrayInspectionEquipment.length() > 0){ //inspection equipment

                InspectionEquipment.deleteAll(InspectionEquipment.class);
                Grades.deleteAll(Grades.class);
                EquipmentProtectionType.deleteAll(EquipmentProtectionType.class);
                InspectionItems.deleteAll(InspectionItems.class);

                for (int i = 0; i < dataJSONArrayInspectionEquipment.length(); i++) {


                    String id = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("id").toString();
                    String equipment_id = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("equipment_id").toString();
                    String grade_d = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("grade_d").toString();
                    String grade_c = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("grade_c").toString();
                    String grade_v = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("grade_v").toString();
                    String report_no = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("report_no").toString();
                    String inspection_date = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("inspection_date").toString();
                    String location = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("location").toString();
                    String inspection_type_id = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("inspection_type_id").toString();
                    String inspector_id = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("inspector_id").toString();
                    String supervisor_id = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("supervisor_id").toString();
                    String created_by = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("created_by").toString();
                    String created_date = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("created_date").toString();
                    String updated_by = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("updated_by").toString();
                    String updated_date = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("updated_date").toString();
                    String campaign_id = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("campaign_id").toString();

                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try {
                        date = inputFormat.parse(inspection_date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String inspectiondate = outputFormat.format(date);
                    Log.d("inspectiondate","inspectiondate => " + inspectiondate);

                    JSONArray dataJSONArrayGrades = null;
                    dataJSONArrayGrades=dataJSONArrayInspectionEquipment.getJSONObject(i).getJSONArray("grades");

                    for(int j = 0; j < dataJSONArrayGrades.length(); j++){ // grades
                        String grade = dataJSONArrayGrades.get(j).toString();
                        grades = new Grades(grade,id);
                        grades.save();
                    }


                    String status = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("status").toString();
                    String compliance_status = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("compliance_status").toString();
                    String description = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("description").toString();
                    String item_tag_no = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("item_tag_no").toString();
                    String equipment_type_id = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("equipment_type_id").toString();
                    String facility_id = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("facility_id").toString();
                    String reference_no = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("reference_no").toString();
                    String sub_system = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("sub_system").toString();
                    String sub_system_desc = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("sub_system_desc").toString();
                    String module_area_id = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("module_area_id").toString();
                    String data_sheet = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("data_sheet").toString();
                    String drawing_ref = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("drawing_ref").toString();
                    String area_class_id = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("area_class_id").toString();
                    String area_temp_class_id = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("area_temp_class_id").toString();
                    String area_gas_group_id = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("area_gas_group_id").toString();


                    JSONArray dataJSONArrayEquipmentProtectionType = null;
                    dataJSONArrayEquipmentProtectionType=dataJSONArrayInspectionEquipment.getJSONObject(i).getJSONArray("equipment_protection_type_id");

                    for(int j = 0; j < dataJSONArrayEquipmentProtectionType.length(); j++){ // equipment protection type
                        String type = dataJSONArrayEquipmentProtectionType.get(j).toString();

                        equipmentProtectionType = new EquipmentProtectionType(type,id);
                        equipmentProtectionType.save();
                    }


                    String equipment_manufacturer = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("equipment_manufacturer").toString();
                    String model_type = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("model_type").toString();
                    String equipment_serial_no = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("equipment_serial_no").toString();
                    String equipment_temp_class_id = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("equipment_temp_class_id").toString();
                    String equipment_gas_group_id = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("equipment_gas_group_id").toString();
                    String ex_cert_no = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("ex_cert_no").toString();
                    String equipment_zone_id = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("equipment_zone_id").toString();
                    String equipment_ip_rating1_id = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("equipment_ip_rating1_id").toString();
                    String equipment_ip_rating2_id = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("equipment_ip_rating2_id").toString();
                    String url = dataJSONArrayInspectionEquipment.getJSONObject(i).getString("url").toString();


                    inspectionEquipment = new InspectionEquipment(id,equipment_id,grade_d,grade_c,grade_v,report_no,inspectiondate,location,inspection_type_id,inspector_id,
                            supervisor_id,created_by,created_date,updated_by,updated_date,campaign_id,status,compliance_status,description,item_tag_no,
                            equipment_type_id,facility_id,reference_no,sub_system,sub_system_desc,module_area_id,data_sheet,drawing_ref,area_class_id,area_temp_class_id,
                            area_gas_group_id,equipment_manufacturer,model_type,equipment_serial_no,equipment_temp_class_id,equipment_gas_group_id,ex_cert_no,equipment_zone_id,
                            equipment_ip_rating1_id,equipment_ip_rating2_id,url);
                    inspectionEquipment.save();



                    JSONArray dataJSONArrayInspectionItem = null;
                    dataJSONArrayInspectionItem=dataJSONArrayInspectionEquipment.getJSONObject(i).getJSONArray("inspection_items");

                    for(int j = 0; j < dataJSONArrayInspectionItem.length(); j++){ // Inspection Items

                        String inspection_item_id = dataJSONArrayInspectionItem.getJSONObject(j).getString("id").toString();
                        String seq = dataJSONArrayInspectionItem.getJSONObject(j).getString("seq").toString();
                        String name = dataJSONArrayInspectionItem.getJSONObject(j).getString("name").toString();
                        String item_result = dataJSONArrayInspectionItem.getJSONObject(j).getString("item_result").toString();
                        String item_result_id = dataJSONArrayInspectionItem.getJSONObject(j).getString("item_result_id").toString();

                        inspectionItems = new InspectionItems(inspection_item_id,equipment_id,seq,name,item_result,item_result_id);
                        inspectionItems.save();
                    }

                }

            }


            if(dataJSONArrayProtectionTypeID.length() > 0){ // inspection protection type

                ProtectionTypeID.deleteAll(ProtectionTypeID.class);

                for (int i = 0; i < dataJSONArrayProtectionTypeID.length(); i++) {

                    String label = dataJSONArrayProtectionTypeID.getJSONObject(i).getString("label").toString();
                    String value = dataJSONArrayProtectionTypeID.getJSONObject(i).getString("value").toString();
                    String key = dataJSONArrayProtectionTypeID.getJSONObject(i).getString("key").toString();

                    protectionTypeID = new ProtectionTypeID(label,value,key);
                    protectionTypeID.save();
                }
            }

            if(dataJSONArrayModuleAreaID.length() > 0){ // module_area_id

                AreaClassID.deleteAll(AreaClassID.class);

                for (int i = 0; i < dataJSONArrayModuleAreaID.length(); i++) {

                    String label = dataJSONArrayModuleAreaID.getJSONObject(i).getString("label").toString();
                    String value = dataJSONArrayModuleAreaID.getJSONObject(i).getString("value").toString();
                    String key = dataJSONArrayModuleAreaID.getJSONObject(i).getString("key").toString();

                    moduleAreaID = new ModuleAreaID(label,value,key);
                    moduleAreaID.save();
                }
            }


            if(dataJSONArrayAreaClassID.length() > 0){ // area_class_id

                AreaClassID.deleteAll(AreaClassID.class);

                for (int i = 0; i < dataJSONArrayAreaClassID.length(); i++) {

                    String label = dataJSONArrayAreaClassID.getJSONObject(i).getString("label").toString();
                    String value = dataJSONArrayAreaClassID.getJSONObject(i).getString("value").toString();
                    String key = dataJSONArrayAreaClassID.getJSONObject(i).getString("key").toString();

                    areaClassID = new AreaClassID(label,value,key);
                    areaClassID.save();
                }
            }

            if(dataJSONAreaTempClassID.length() > 0){ // area_temp_class_id

                AreaTempClassID.deleteAll(AreaTempClassID.class);

                for (int i = 0; i < dataJSONAreaTempClassID.length(); i++) {

                    String row_id = String.valueOf(i+1);
                    String label = dataJSONAreaTempClassID.getJSONObject(i).getString("label").toString();
                    String value = dataJSONAreaTempClassID.getJSONObject(i).getString("value").toString();
                    String key = dataJSONAreaTempClassID.getJSONObject(i).getString("key").toString();

                    areaTempClassID = new AreaTempClassID(row_id,label,value,key);
                    areaTempClassID.save();
                }
            }


            if(dataJSONAreaGasGroupID.length() > 0){ // area_gas_group_id

                AreaGasGroupID.deleteAll(AreaGasGroupID.class);

                for (int i = 0; i < dataJSONAreaGasGroupID.length(); i++) {

                    String label = dataJSONAreaGasGroupID.getJSONObject(i).getString("label").toString();
                    String value = dataJSONAreaGasGroupID.getJSONObject(i).getString("value").toString();
                    String key = dataJSONAreaGasGroupID.getJSONObject(i).getString("key").toString();

                    areaGasGroupID = new AreaGasGroupID(label,value,key);
                    areaGasGroupID.save();
                }
            }


            if(dataJSONEquipmentIpRating1ID.length() > 0){ // equipment_ip_rating1_id

                EquipmentIpRating1ID.deleteAll(EquipmentIpRating1ID.class);

                for (int i = 0; i < dataJSONEquipmentIpRating1ID.length(); i++) {

                    String label = dataJSONEquipmentIpRating1ID.getJSONObject(i).getString("label").toString();
                    String value = dataJSONEquipmentIpRating1ID.getJSONObject(i).getString("value").toString();
                    String key = dataJSONEquipmentIpRating1ID.getJSONObject(i).getString("key").toString();

                    equipmentIpRating1ID = new EquipmentIpRating1ID(label,value,key);
                    equipmentIpRating1ID.save();
                }
            }


            if(dataJSONEquipmentIpRating2ID.length() > 0){ // equipment_ip_rating2_id

                EquipmentIpRating2ID.deleteAll(EquipmentIpRating2ID.class);

                for (int i = 0; i < dataJSONEquipmentIpRating2ID.length(); i++) {

                    String label = dataJSONEquipmentIpRating2ID.getJSONObject(i).getString("label").toString();
                    String value = dataJSONEquipmentIpRating2ID.getJSONObject(i).getString("value").toString();
                    String key = dataJSONEquipmentIpRating2ID.getJSONObject(i).getString("key").toString();

                    equipmentIpRating2ID = new EquipmentIpRating2ID(label,value,key);
                    equipmentIpRating2ID.save();
                }
            }


            if(dataJSONEquipmentZoneID.length() > 0){ // equipment_zone_id

                EquipmentZoneID.deleteAll(EquipmentZoneID.class);

                for (int i = 0; i < dataJSONEquipmentZoneID.length(); i++) {

                    String label = dataJSONEquipmentZoneID.getJSONObject(i).getString("label").toString();
                    String value = dataJSONEquipmentZoneID.getJSONObject(i).getString("value").toString();
                    String key = dataJSONEquipmentZoneID.getJSONObject(i).getString("key").toString();

                    equipmentZoneID = new EquipmentZoneID(label,value,key);
                    equipmentZoneID.save();
                }
            }


            if(dataJSONDocTypes.length() > 0){ // doc_types

                DocTypes.deleteAll(DocTypes.class);

                for (int i = 0; i < dataJSONDocTypes.length(); i++) {

                    String label = dataJSONDocTypes.getJSONObject(i).getString("label").toString();
                    String value = dataJSONDocTypes.getJSONObject(i).getString("value").toString();

                    docTypes = new DocTypes(label,value);
                    docTypes.save();
                }
            }


            if(dataJSONComplianceStatus.length() > 0){ // compliance_status

                ComplianceStatus.deleteAll(ComplianceStatus.class);

                for (int i = 0; i < dataJSONComplianceStatus.length(); i++) {

                    String label = dataJSONComplianceStatus.getJSONObject(i).getString("label").toString();
                    String value = dataJSONComplianceStatus.getJSONObject(i).getString("value").toString();

                    complianceStatus = new ComplianceStatus(label,value);
                    complianceStatus.save();
                }
            }


            if(dataJSONInspectionCategories.length() > 0){ // inspection_category

                InspectionCategories.deleteAll(InspectionCategories.class);

                for (int i = 0; i < dataJSONInspectionCategories.length(); i++) {

                    String category_name = dataJSONInspectionCategories.getJSONObject(i).getString("category_name").toString();
                    String id = dataJSONInspectionCategories.getJSONObject(i).getString("id").toString();
                    String code = dataJSONInspectionCategories.getJSONObject(i).getString("code").toString();

                    inspectionCategories = new InspectionCategories(id,code,category_name);
                    inspectionCategories.save();
                }
            }


            if(dataJSONInspectionItemsDetails.length() > 0){ // inspection_item_details

                InspectionItemsDetails.deleteAll(InspectionItemsDetails.class);

                for (int i = 0; i < dataJSONInspectionItemsDetails.length(); i++) {

                    String inspection_category_id = dataJSONInspectionItemsDetails.getJSONObject(i).getString("inspection_category_id").toString();
                    String grade_c = dataJSONInspectionItemsDetails.getJSONObject(i).getString("grade_c").toString();
                    String item_name = dataJSONInspectionItemsDetails.getJSONObject(i).getString("item_name").toString();
                    String seq_no = dataJSONInspectionItemsDetails.getJSONObject(i).getString("seq_no").toString();
                    String grade_v = dataJSONInspectionItemsDetails.getJSONObject(i).getString("grade_v").toString();
                    String grade_d = dataJSONInspectionItemsDetails.getJSONObject(i).getString("grade_d").toString();
                    String id = dataJSONInspectionItemsDetails.getJSONObject(i).getString("id").toString();

                    inspectionItemsDetails = new InspectionItemsDetails(inspection_category_id,grade_c,item_name,seq_no,grade_v,grade_d,id);
                    inspectionItemsDetails.save();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
