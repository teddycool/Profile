package com.scaniahack.profile;

/**
 * Created by ssstsu on 2016-05-21.
 */
import com.scaniahack.profile.R;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class ProfilePopUpService extends Service {

    private WindowManager windowManager;
    private ImageView chatHead;
    //private PopUpView popUp;
    private TextView textView;

    /**
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        textView = new TextView(this);
        //popUp = new PopUpView(this);
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        //textView.append("tgtest");

        //windowManager.addView(textView, params);


        final TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        PhoneStateListener phoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String number) {
                String currentPhoneState = null;
                switch (state) {
                    case TelephonyManager.CALL_STATE_RINGING:
                        currentPhoneState = "Device is ringing. Call from " + number + ".\n\n";

                        ProfileSearcher ps = new ProfileSearcher();
                        String name = ps.search(number.replaceFirst("^0+(?!$)", ""));

                        Context context = getApplicationContext();
                        CharSequence text = currentPhoneState;
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, name, duration);
                        toast.show();


                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        currentPhoneState = "Device call state is currently Off Hook.\n\n";
                        break;
                    case TelephonyManager.CALL_STATE_IDLE:
                        currentPhoneState = "Device call state is currently Idle.\n\n";
                        break;

                }





            }
        };
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
/*
        try {
            chatHead.setOnTouchListener(new View.OnTouchListener() {
                private WindowManager.LayoutParams paramsF = params;
                private int initialX;
                private int initialY;
                private float initialTouchX;
                private float initialTouchY;

                @Override public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:

                            // Get current time in nano seconds.

                            initialX = paramsF.x;
                            initialY = paramsF.y;
                            initialTouchX = event.getRawX();
                            initialTouchY = event.getRawY();
                            break;
                        case MotionEvent.ACTION_UP:
                            break;
                        case MotionEvent.ACTION_MOVE:
                            paramsF.x = initialX + (int) (event.getRawX() - initialTouchX);
                            paramsF.y = initialY + (int) (event.getRawY() - initialTouchY);
                            windowManager.updateViewLayout(chatHead, paramsF);
                            break;
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
        }

        */

    }

    public void onSearchResult()
    {


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (chatHead != null) windowManager.removeView(chatHead);
    }
}