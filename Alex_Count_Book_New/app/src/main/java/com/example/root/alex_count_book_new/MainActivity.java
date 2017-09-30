package com.example.root.alex_count_book_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

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
        Counter test6 = new Counter("test6","6","today6");

        ArrayList<Counter> counter_list = new ArrayList<>();
        counter_list.add(test1);
        counter_list.add(test2);
        counter_list.add(test3);
        counter_list.add(test4);
        counter_list.add(test5);
        counter_list.add(test6);

        CounterListAdapter adapter = new CounterListAdapter(this,R.layout.adapter_view,counter_list);
        counter_list_view.setAdapter(adapter);
        Button create_but = (Button)findViewById(R.id.create_button);

        create_but.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Log.d(TAG, "onClick: Clicked G");

                Intent intent = new Intent(MainActivity.this,Edit_Create_Screen.class);
                startActivity(intent);
            }
        });
    }
}
