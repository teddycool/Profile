package com.scaniahack.profile;

import android.util.Log;

/**
 * Created by psk on 2016-05-20.
 */
public class ProfileLog {

    public static void Print(char level, java.lang.String tag, java.lang.String message)         {
        if(level=='d'){
            Log.d("PROFILE " + tag,message);
        }
        if(level=='i'){
            Log.i("PROFILE " + tag,message);
        }

    }

}
