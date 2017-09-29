package com.example.root.alex_count_book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> ArrayList;
    ArrayAdapter<String> ArrayAdapter;
    String Item_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list_item);
        ArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ArrayList);
        listView.setAdapter(ArrayAdapter);
    }

    public void onCreate(View v){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,Input.class);
        startActivityForResult(intent,Constants.Intent_request);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Constants.Intent_request){
            Item_name = data.getStringExtra(Constants.Intent_name);
            ArrayList.add(Item_name);
            ArrayAdapter.notifyDataSetChanged();
        }
    }
}
