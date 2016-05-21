package com.scaniahack.profile;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ssstsu on 2016-05-21.
 */
public class ProfileSearcher {
    private String name;
    private String department;
    private String number;

    public ProfileSearcher(){}

    public String search(String search_text) {

        // Get the JSON

        Search  asyncGetResult = new Search();
        String urls = "https://devwcm.scania.com/profile?q=" + search_text.replace(" ", "%20");
        ProfileLog.Print('d', "URL: " + urls, "ALL");
        URL url = null;

        try {
            url = new URL(urls);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ProfileLog.Print('d', "SENT URL:", url.toString());

        try {
            ResJson result = asyncGetResult.execute(url).get();
            return result.getValue("preferred_name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "no!";


    }

    public ResJson jsonsearch(String search_text) {

        // Get the JSON

        Search  asyncGetResult = new Search();
        String urls = "https://devwcm.scania.com/profile?q=" + search_text.replace(" ", "%20");
        ProfileLog.Print('d', "URL: " + urls, "ALL");
        URL url = null;

        try {
            url = new URL(urls);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ProfileLog.Print('d', "SENT URL:", url.toString());

        try {
            ResJson result = asyncGetResult.execute(url).get();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
