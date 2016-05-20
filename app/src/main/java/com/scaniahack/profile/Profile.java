package com.scaniahack.profile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import android.util.Base64;

import android.widget.EditText;
public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * Called when the user clicks the Send button
     */
    public void search(View view) {
        // Do something in response to button
        ProfileLog.Print('i', "Search", "Button pressed");
        Search  asyncGetResult = new Search();
        String text = ((EditText) findViewById(R.id.searchText)).getText().toString();
        String urls = "https://devwcm.scania.com/profile?q=" + text.replace(" ", "%20");
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
        asyncGetResult.execute(url);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class GetResultFromServer extends AsyncTask<URL, String, Integer> {
        @Override
        protected Integer doInBackground(URL... params) {

            // Get the JSON
            URL url;
            try {
                url = params[0];
                ProfileLog.Print('d', "USED URL:", url.toString());

                URLConnection connection;
                //connection.
                final String basicAuth = "Basic " + Base64.encodeToString("user:passw".getBytes(), Base64.NO_WRAP);

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
