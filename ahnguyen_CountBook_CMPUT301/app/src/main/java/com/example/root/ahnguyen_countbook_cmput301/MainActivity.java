package com.example.root.ahnguyen_countbook_cmput301;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private static final String TAG = "MainActivity";
    public static ArrayList<Counter> counter_list;
    public static ArrayList<String> comment_list;
    CounterListAdapter adapter;
    String incoming_name;
    String incoming_value;
    String incoming_date;
    String incoming_comment;
    int incoming_position;
    public static int size_of_counters;
    public ListView counter_list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");

        counter_list_view = (ListView) findViewById(R.id.List_view);


        counter_list = new ArrayList<>();

        adapter = new CounterListAdapter(this, R.layout.adapter_view, counter_list);
        counter_list_view.setAdapter(adapter);
        size_of_counters = counter_list.size();

        TextView summary_of_counters = (TextView) findViewById(R.id.Number_Counters);
        Log.d(TAG, "onCreate: The number of counters is " + size_of_counters);
        summary_of_counters.setText("" + size_of_counters);

        Button create_but = (Button) findViewById(R.id.create_button);
        create_but.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked G");
                Intent intent = new Intent(MainActivity.this, Edit_Create_Screen.class);
                intent.putExtra("Creating", Constants.Creation);

                Log.d(TAG, "onClick: LOL this is after the intent !!@~!@ ");
                startActivityForResult(intent, Constants.Intent_request);
            }
        });




        //This is when someone clicks on an item then it brings us to the EDIT/CREATE
        counter_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: names " + counter_list.get(i).getName());

                Intent intent = new Intent(MainActivity.this, Edit_Create_Screen.class);
                intent.putExtra("name_of_list", counter_list.get(i).getName());
                intent.putExtra("value_of_list", counter_list.get(i).getValue());
                intent.putExtra("comment_of_list", counter_list.get(i).getComment());
                intent.putExtra("date_of_list", counter_list.get(i).getDate());
                intent.putExtra("position_of_list", i);
                Log.d(TAG, "onItemClick: the postioin of the item is " + i);
                Log.d(TAG, "onItemClick: the date of the counter_list i clsssicked is " + counter_list.get(i).getDate());
                //intent.putExtra("date_of_list",counter_list.get(i).getDate());
                Log.d(TAG, "onItemClick: the comment is this " + counter_list.get(i).getComment());

                startActivityForResult(intent, Constants.Intent_request);
            }
        });

    }

    //THIS IS WHEN SOMEONE PASSES DATA FROM THE SECOND ACTIVITY
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Constants.Intent_request) {
            incoming_name = data.getStringExtra("name");
            incoming_value = data.getStringExtra("value");
            incoming_date = data.getStringExtra("date");
            incoming_comment = data.getStringExtra("comment");
            incoming_position = data.getIntExtra("position", 0);
            int was_i_creating = data.getIntExtra("creating", 0);
            int was_i_deleting = data.getIntExtra("deleting",0);
            Log.d(TAG, "onActivityResult: was i deleting ?" + was_i_deleting);

            if (was_i_creating == 0) {
                counter_list.get(incoming_position).setName(incoming_name);
                counter_list.get(incoming_position).setValue(incoming_value);
                counter_list.get(incoming_position).setDate(incoming_date);
                counter_list.get(incoming_position).setComment(incoming_comment);
                adapter.notifyDataSetChanged();
            }
            if(was_i_deleting == 1){
                Log.d(TAG, "onActivityResult: the incoming position is " + incoming_position );
                counter_list.remove(incoming_position);
                size_of_counters = counter_list.size() ;
                Log.d(TAG, "onActivityResult: the size of the counter_list is " + size_of_counters);
                TextView summary_of_counters = (TextView) findViewById(R.id.Number_Counters);
                summary_of_counters.setText("" + size_of_counters);
                adapter.notifyDataSetChanged();
            }

            else {
                if (was_i_creating == 1) {
                    Counter new_counter = new Counter(incoming_name, incoming_value, incoming_date);
                    new_counter.setComment(incoming_comment);
                    counter_list.add(new_counter);
                    size_of_counters = counter_list.size();
                    TextView summary_of_counters = (TextView) findViewById(R.id.Number_Counters);
                    summary_of_counters.setText("" + size_of_counters);
                    adapter.notifyDataSetChanged();

                }

            }

            //saveInFile();
        }
    }
    /*
    @Override
    public void onStart(){
        Log.d(TAG, "onStart: lsol im in here ");
        super.onStart();
        loadFromFile();
        counter_list_view = (ListView) findViewById(R.id.List_view);

        adapter = new CounterListAdapter(this, R.layout.adapter_view, counter_list);

        counter_list_view.setAdapter(adapter);

    }
    */

  /*  private void loadFromFile() {
        //ArrayList<String> tweets = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Counter>>(){}.getType();
            counter_list = gson.fromJson(in,listType);



        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            counter_list = new ArrayList<>();

            // e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
            //e.printStackTrace();
        }

    }


    private void saveInFile() {
        try {
            Log.d(TAG, "saveInFile: im savinging everything in this file");
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(counter_list, writer);
            writer.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
            //e.printStackTrace();
        } catch (IOException e) {

            // TODO Auto-generated catch block
            throw new RuntimeException();
            //e.printStackTrace();
        }
    }
    */
}
