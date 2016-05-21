package com.scaniahack.profile;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

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
        URL url = null;
        try {
            final String encodedURL = URLEncoder.encode(urls, "UTF-8");
            url = new URL(encodedURL);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
         catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
         ProfileLog.Print('d', "URL ENC:", url.toString());

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
        URL url = null;
        try {
            final String encodedURL = URLEncoder.encode(urls, "UTF-8");
            url = new URL(encodedURL);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ProfileLog.Print('d', "URL ENC:", url.toString());


        try {
            ResJson result = asyncGetResult.execute(url).get();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
