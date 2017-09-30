package com.example.root.alex_count_book_new;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 9/29/17.
 */

class CounterListAdapter extends ArrayAdapter<Counter>{
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
    public View getView(int position,  View convertView,  ViewGroup parent) {
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
