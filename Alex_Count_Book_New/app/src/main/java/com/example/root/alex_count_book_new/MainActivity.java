package com.example.root.alex_count_book_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static ArrayList<Counter> counter_list;
    public static ArrayList<String> comment_list;
    CounterListAdapter adapter;
    String incoming_name;
    String incoming_value;
    String incoming_date;
    String incoming_comment;
    public  static int size_of_counters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");

        ListView counter_list_view = (ListView)findViewById(R.id.List_view);

        Counter test1 = new Counter("test1","1","today1");
        Counter test2 = new Counter("test2","2","today2");
        Counter test3 = new Counter("test3","3","today3");
        Counter test4 = new Counter("test4","4","today4");
        Counter test5 = new Counter("test5","5","today5");

        counter_list = new ArrayList<>();
        counter_list.add(test1);
        counter_list.add(test2);
        counter_list.add(test3);
        counter_list.add(test4);
        counter_list.add(test5);
        adapter = new CounterListAdapter(this,R.layout.adapter_view,counter_list);
        counter_list_view.setAdapter(adapter);
        size_of_counters = counter_list.size();

        TextView summary_of_counters = (TextView) findViewById(R.id.Number_Counters);
        Log.d(TAG, "onCreate: The number of counters is " + size_of_counters);
        summary_of_counters.setText(""+size_of_counters);

        Button create_but = (Button)findViewById(R.id.create_button);

        create_but.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Log.d(TAG, "onClick: Clicked G");

                Intent intent = new Intent(MainActivity.this,Edit_Create_Screen.class);
                startActivityForResult(intent,Constants.Intent_request);
            }
        });



        counter_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: names " + counter_list.get(i).getName());

                Intent intent = new Intent(MainActivity.this,Edit_Create_Screen.class);
                intent.putExtra("name_of_list",counter_list.get(i).getName());
                intent.putExtra("value_of_list",counter_list.get(i).getValue());
                intent.putExtra("comment_of_list",counter_list.get(i).getComment());
                Log.d(TAG, "onItemClick: the comment is this " + counter_list.get(i).getComment());
                startActivityForResult(intent,Constants.Intent_request);
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode , int resultCode, Intent data){
        if(resultCode == Constants.Intent_request){

            incoming_name = data.getStringExtra("name");
            incoming_value = data.getStringExtra("value");
            incoming_date = data.getStringExtra("date");
            incoming_comment = data.getStringExtra("comment");
            Log.d(TAG, "onActivityResult: the comment is this incoming comment " + incoming_comment);

//            String incoming_value = incoming_intent.getStringExtra("value");
 //           String incoming_date = incoming_intent.getStringExtra("Date");

            Log.d(TAG, "onActivityResult: lol im here here now" + incoming_name);
            Counter test14 = new Counter(incoming_name,incoming_value,incoming_date);
            test14.setComment(incoming_comment);
            counter_list.add(test14);
            size_of_counters = counter_list.size();
            Log.d(TAG, "onActivityResult: The size of the counter is  now this value " + size_of_counters);
            TextView summary_of_counters = (TextView) findViewById(R.id.Number_Counters);
            summary_of_counters.setText(""+size_of_counters);
            adapter.notifyDataSetChanged();
        }
    }

}
