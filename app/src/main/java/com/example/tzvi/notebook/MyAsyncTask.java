package com.example.tzvi.notebook;

import android.net.Uri;
import android.os.AsyncTask;
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
 * Created by tzvi on 11/12/14.
 */

    public  class MyAsyncTask extends AsyncTask<Void,Void,Boolean> {
        NoteAdapter adapter=null;
        ArrayList<String> noteString;
        String note;

        public MyAsyncTask(ArrayList<String> noteString,NoteAdapter adapter){
            this.noteString = noteString;
            this.adapter = adapter;
        }
        public MyAsyncTask(String note){
            this.note = note;
            Log.v("note",note);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Log.v("begin", "good");
            String urlParameters="";
            String result = "";
            URL url = null;
            String line = "";
            HttpURLConnection connection= null;
            try {
                if (adapter!=null){
                    url = new URL("http://162.243.45.239/OneNote/retrievNotes.php");
                    urlParameters ="email=example2@yahoo.com";
                    Log.v("url",url.toString());
                }
                if(adapter==null) {
                    url = new URL("http://162.243.45.239/OneNote/recieveNotes.php");
                    urlParameters = "email=example2@yahoo.com&note="+note;
                    Log.v("url",url.toString());
                }
                connection = (HttpURLConnection) url.openConnection();
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setInstanceFollowRedirects(false);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("charset", "utf-8");
                connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
                connection.setUseCaches (false);
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
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
            Log.v("rawresult",result);
            if(adapter!=null){
            try {
                JSONArray values = new JSONArray(result);
                JSONObject mJsonObject;
                for (int i = 0; i < values.length(); i++) {
                    mJsonObject = values.getJSONObject(i);
                    String note = mJsonObject.getString("note");
                    noteString.add(note);
                    Log.v("note:",note);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }}


            return true;
        }
        @Override
        protected void onPostExecute(final Boolean success) {

            if (success) {
                adapter.addAll(noteString);
            }}

    }

