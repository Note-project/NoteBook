package com.example.tzvi.notebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tzvi on 10/31/14.
 */

    public class NoteAdapter extends ArrayAdapter {
        private Context mContext;
        private String[] MnoteString;


     public NoteAdapter(Context context, int resource) {
       super(context, resource);
        mContext=context;


     }






        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View gridView;
            String note =(String)getItem(position);
            if (convertView == null) {  // if it's not recycled, initialize some attributes

                gridView = new View(mContext);

                // get layout from mobile.xml
                gridView = inflater.inflate(R.layout.tn_text_veiw, null);

                // set value into textview
                TextView textView = (TextView) gridView
                        .findViewById(R.id.grid_item);
                textView.setText(note);

                } else {
                gridView = (View) convertView;
            }

            return gridView;
        }




        };


