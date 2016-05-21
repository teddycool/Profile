package com.scaniahack.profile;

/**
 * Created by psk on 2016-05-21.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class BootUpReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        /***** For start Service  ****/
        Intent myIntent = new Intent(context, ProfilePopUpService.class);
        context.startService(myIntent);
    }

}