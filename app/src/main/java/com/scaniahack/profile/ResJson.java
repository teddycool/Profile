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
