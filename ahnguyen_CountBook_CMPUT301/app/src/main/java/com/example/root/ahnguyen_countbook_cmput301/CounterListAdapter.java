package com.example.root.ahnguyen_countbook_cmput301;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by root on 10/1/17.
 */

/*
 Ahnguyen 1480849
 CMPUT 301
 This CounterListAdapter was made by following a tutorial on how to create custom layouts for our listview
 the author of this and the link is below
 Mitch Tabian
 https://www.youtube.com/watch?v=E6vE8fqQPTE&t=281s

 by following this tutorial we are able to create a custom listview where we're now able to display
 three textviews on our list instead of just one textview we now have three of them which is more
 convient to show the date/value/name
  */

class CounterListAdapter extends ArrayAdapter<Counter> {
    private static final String TAG = "CounterListAdapter";

    private Context mcContext;
    int mResource;



    public CounterListAdapter(Context context,int resource, ArrayList<Counter> objects) {
        super(context, resource, objects);
        mcContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem(position).getName();
        String date = getItem(position).getDate();
        String value = getItem(position).getValue();
        Counter counter = new Counter(name,value,date);

        LayoutInflater inflater = LayoutInflater.from(mcContext);
        convertView = inflater.inflate(mResource,parent,false);
        //return super.getView(position, convertView, parent);

        TextView textName = (TextView) convertView.findViewById(R.id.name);
        TextView textValue = (TextView) convertView.findViewById(R.id.Value);
        TextView textDate = (TextView) convertView.findViewById(R.id.Date);

        textName.setText(name);
        textValue.setText(value);
        textDate.setText(date);

        return convertView;
    }
}