package com.example.tzvi.notebook;

import android.app.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

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


public class IndexPage extends Activity {

     public ArrayList<String> noteString = new ArrayList<String>();
     public NoteAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_index_page);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        adapter = new NoteAdapter(this, 0);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                String noteText = ((TextView) v.findViewById(R.id.grid_item))
                        .getText().toString();
                Intent intent = new Intent(IndexPage.this, MyActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, noteText);
                startActivity(intent);
            }
        });
        MyAsyncTask aT = new MyAsyncTask(noteString,adapter);
        aT.execute((Void)null);
        findViewById(R.id.addNote).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexPage.this, MyActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, "message");
                startActivity(intent);
            }
        });
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.index_page, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

    }

