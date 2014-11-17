package com.example.tzvi.notebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;



public class MyActivity extends Activity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Intent intent = getIntent();
        if(!(intent.getStringExtra(Intent.EXTRA_TEXT) == null)){
        String message = intent.getStringExtra(Intent.EXTRA_TEXT);
        editText = (EditText)findViewById(R.id.editText_input);
        editText.setText(message);}

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }
    @Override
    protected void onPause() {
        Log.v("in onpause","paused");
       super.onPause();
       String note = editText.getText().toString();
        Context context = getApplicationContext();
        Intent intent = new Intent(context,
                SaveDataService.class);
        intent.putExtra("note",note);
        context.startService(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Created by tzvi on 11/12/14.
     */


}
