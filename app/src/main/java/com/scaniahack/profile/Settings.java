package com.scaniahack.profile;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Created by psk on 2016-05-20.
 */
public class Settings extends PreferenceActivity {

    //Option names and default values...'
    private static final String PROFILE_URL = "URL";
    private static final String PROFILE_URL_DEF = "enter your url";
    private static final String PROFILE_USER = "user";
    private static final String PROFILE_PW ="UserPw";

    public static String getShopperCode(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getString(PROFILE_URL, PROFILE_URL_DEF );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.settingview);
       // addPreferencesFromResource(R.xml.settings);

    }
}
