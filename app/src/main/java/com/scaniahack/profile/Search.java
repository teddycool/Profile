package com.scaniahack.profile;

import android.os.AsyncTask;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by psk on 2016-05-21.
 */
public class Search extends AsyncTask<URL, String, Integer>

    {
        @Override
        protected Integer doInBackground(URL... params) {

        // Get the JSON
        URL url;
        try {
            url = params[0];
            ProfileLog.Print('d', "USED URL:", url.toString());

            URLConnection connection;
            //connection.
            final String basicAuth = "Basic " + Base64.encodeToString("us:pw".getBytes(), Base64.NO_WRAP);

            connection = url.openConnection();
            connection.setRequestProperty ("Authorization", basicAuth);
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            ProfileLog.Print('d', "HTTP ResponseCode: ", String.valueOf(responseCode));
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = httpConnection.getInputStream();
                String myString = convertStreamToString(in);
                ProfileLog.Print('i', "Response", myString);
                try {
                    ResJson myjason = new ResJson(myString);

                    ProfileLog.Print('i', "Hits", myjason.hits());
                    ProfileLog.Print('i', "Name", myjason.getValue("preferred_name"));
                    ProfileLog.Print('i', "Department", myjason.getValue("object_organisation_acronym"));
                    ProfileLog.Print('i', "ImageLink", myjason.getValue("object_image"));
                    ProfileLog.Print('i', "Jobposition", myjason.getValue("profile_job_position"));
                    ProfileLog.Print('i', "Phone", myjason.getValue("profile_telephone"));


                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

        return null;
    }

        private String convertStreamToString(InputStream is) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }

    }


