package com.scaniahack.profile;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.LinkedHashMap;

/**
 * Created by psk on 2016-05-20.
 */
public class ResJson extends JSONObject{
    private String hits;
    private String name;
    private JSONObject documents;
    private JSONArray doclists;
    private JSONObject document;
    private String preferred_name;
    private JSONArray documents1;
    private JSONObject profileinfo;





    public String getName() {
        return this.getValue("preferred_name");
    }

    public String getImageUrl() {
        String ImageUrl="";
        String urloriginal = this.getValue("object_image");
        String roturltemp= "https://devwcm.scania.com/profile/images/";
        urloriginal.lastIndexOf("/");
        ImageUrl = roturltemp + urloriginal.substring(urloriginal.lastIndexOf("/")+1);
      //  http://accinline.scania.com/profile/ssspsk/ssspsk.jpg
        // https://devwcm.scania.com/profile/images/ACHXWP.jpg
        return ImageUrl;
    }

    public String getPhone() {
        return this.getValue("profile_telephone");
    }

    public String getDepartment() {
        return this.getValue("object_organisation_acronym");
    }

    public String getRole() {
        return this.getValue("profile_job_position");
    }




    public ResJson(String json) throws JSONException {
        super(json);
        hits = this.get("numberOfHits").toString();
        doclists = this.getJSONObject("components").getJSONArray("doclists");
        documents = doclists.getJSONObject(0);

        documents1 = documents.getJSONArray("documents");
        profileinfo = documents1.getJSONObject(0);

    }

    public String hits(){
        return hits;
    }
    public String preferredName(){
        return preferred_name;
    }

    public String getValue(String name){
        String value = "";
        try {
            value = profileinfo.get(name).toString();
        }
            catch (Exception e){
                e.printStackTrace();
            }
        return value;
    }




}
