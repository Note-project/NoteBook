package com.example.tzvi.notebook;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by tzvi on 11/13/14.
 */
public class SaveDataService extends IntentService {
   String note;


    public SaveDataService(){
        super("SaveDataService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        note = intent.getStringExtra("note");
        Log.v("begin", "good");
        String urlParameters = "";
        String result = "";
        URL url = null;
        String line = "";
        HttpURLConnection connection = null;
        try {
            url = new URL("http://162.243.45.239/OneNote/recieveNotes.php");
            urlParameters = "email=example2@yahoo.com&note=" + note;
            Log.v("url", url.toString());

            connection = (HttpURLConnection) url.openConnection();
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            connection.setUseCaches(false);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            connection.connect();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null)
                result += line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("rawresult", result);



        }
    }




