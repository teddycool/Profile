package com.scaniahack.profile;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ssstsu on 2016-05-21.
 */
public class ImageDownloader extends AsyncTask<String, String, Drawable> {
    private ImageView img_v = null;

    public void setImgView(ImageView img_view)
    {
        img_v = img_view;
    }

    @Override
    protected Drawable doInBackground(String... params) {
        Drawable d = null;
        URL url = null;

        try {
            url = new URL(params[0]);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            URLConnection connection;
            //connection.
            final String basicAuth = "Basic " + Base64.encodeToString("ScaniaHack:N5rAB3bDwkjqjkJzAgh69v4".getBytes(), Base64.NO_WRAP);

            connection = url.openConnection();
            connection.setRequestProperty ("Authorization", basicAuth);
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            ProfileLog.Print('d', "HTTP ResponseCode: ", String.valueOf(responseCode));
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = httpConnection.getInputStream();
                d = Drawable.createFromStream(in, "src name");

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

        return d;
    }

    protected void onPostExecute(Drawable d) {
        img_v.setImageDrawable(d);
    }
}
