package com.scaniahack.profile;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

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

        // Start popup service
        startService(new Intent(Profile.this, ProfilePopUpService.class));
    }


    public void profilesearch(View view) {
        ProfileLog.Print('i', "ProfileSearch", "Button pressed");
        String searchtext = ((EditText) findViewById(R.id.searchText)).getText().toString();
        ProfileSearcher ps = new ProfileSearcher();
        ResJson res = null;
        String result = "Inget hittat....";
        res = ps.jsonsearch(searchtext);
        if (res != null) {
             result = res.getValue("preferred_name") + "\r\n " + res.getValue("profile_job_position")
                    + "\r\n" + res.getDepartment() + "\r\n" + res.getPhone();
        }
        ProfileLog.Print('i', "JsonRes", result);
        ((EditText) findViewById(R.id.searchResult)).setText(result);
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

}
