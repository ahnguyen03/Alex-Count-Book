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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/*
    Ahnguyen 1480849
    CMPUT 301
    Assignment 1
 */


public class MainActivity extends AppCompatActivity {

    //This is the initialization of Variables being used
    private static final String FILENAME = "file.sav";
    private static final String TAG = "MainActivity";
    private static ArrayList<Counter> counter_list;
    private static ArrayList<String> comment_list;
    private CounterListAdapter adapter;
    private String incoming_name;
    private String incoming_value;
    private String incoming_date;
    private String incoming_comment;
    private int incoming_position;
    private static int size_of_counters;
    private ListView counter_list_view;

    //The method of onCreate when starting the app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter_list_view = (ListView) findViewById(R.id.List_view);
        counter_list = new ArrayList<>();
        loadFromFile();
        //Right here is using a custom Array Adapter for my listview where it will display 3 Textviews
        adapter = new CounterListAdapter(this, R.layout.adapter_view, counter_list);
        counter_list_view.setAdapter(adapter);
        size_of_counters = counter_list.size();
        TextView summary_of_counters = (TextView) findViewById(R.id.Number_Counters);
        summary_of_counters.setText("" + size_of_counters);

        /*
         Underneath here is the button method where we set a onClickListener to see when
         the button is pressed and once its pressed we use a intent to move from the first
         activity to the second one, for this we're using startActivityForResults where we are
         looking for results of the activity
         */

        Button create_but = (Button) findViewById(R.id.create_button);
        create_but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Edit_Create_Screen.class);
                intent.putExtra("Creating", Constants.Creation);
                startActivityForResult(intent, Constants.Intent_request);
            }
        });




        /*
        This is when someone clicks a item thats on the list in which we use Intent to push
        that data of the item onto the second Activity.Again we are using AcvitivityForResults to
        see if there was any thing that's changed in which we can later use that to update our list
         */
        counter_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Edit_Create_Screen.class);
                intent.putExtra("name_of_list", counter_list.get(i).getName());
                intent.putExtra("value_of_list", counter_list.get(i).getValue());
                intent.putExtra("comment_of_list", counter_list.get(i).getComment());
                intent.putExtra("date_of_list", counter_list.get(i).getDate());
                intent.putExtra("position_of_list", i);
                startActivityForResult(intent, Constants.Intent_request);
            }
        });

    }

    //THIS IS WHEN SOMEONE PASSES DATA FROM THE SECOND ACTIVITY
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //This is just grabbing the data from the second activity
        if (resultCode == Constants.Intent_request) {
            incoming_name = data.getStringExtra("name");
            incoming_value = data.getStringExtra("value");
            incoming_date = data.getStringExtra("date");
            incoming_comment = data.getStringExtra("comment");
            incoming_position = data.getIntExtra("position", 0);
            int was_i_creating = data.getIntExtra("creating", 0);
            int was_i_deleting = data.getIntExtra("deleting",0);
            //Now using a constant we see if we were planning on creating a new counter if not then just
            //update the current counter i clicked one
            if (was_i_creating == 0) {
                counter_list.get(incoming_position).setName(incoming_name);
                counter_list.get(incoming_position).setValue(incoming_value);
                counter_list.get(incoming_position).setDate(incoming_date);
                counter_list.get(incoming_position).setComment(incoming_comment);
                adapter.notifyDataSetChanged();
            }
            //Now if we clicked on deleting in our second activity then we remove it from our
            //list which then we update the summary and the list
            if(was_i_deleting == 1){
                counter_list.remove(incoming_position);
                size_of_counters = counter_list.size() ;
                TextView summary_of_counters = (TextView) findViewById(R.id.Number_Counters);
                summary_of_counters.setText("" + size_of_counters);
                adapter.notifyDataSetChanged();
            }

            else {
                //This here is when we are creating a new counter then we add it onto the list
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
        }
        saveInFile();

    }

    //Following the code provided from Tweet i implemeneted the method of save/load
    private void loadFromFile() {
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

}
