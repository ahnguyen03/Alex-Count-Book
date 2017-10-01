package com.example.root.alex_count_book_new;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by root on 9/29/17.
 */

public class Edit_Create_Screen extends AppCompatActivity {
    private  final String TAG = "Edit_Create_Screen";
    public static int  value = 0;
    public static int old_value;
    public static TextView Incoming_data_name;
    public static TextView Incoming_value_init;
    public static TextView Incoming_value_current;
    public static String incoming_value;
    public static TextView Incoming_comment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_edit_layout);
        Button save_but = (Button)findViewById(R.id.save_but);
        Button inc_but = (Button)findViewById(R.id.Increment_but);
        Button dec_but = (Button)findViewById(R.id.Decrement_but);
        Button reset_but = (Button)findViewById(R.id.reset_but);
        value = 0;

        //This is the data when someone clicks on a list then we grab that data
        Intent incomingIntent = getIntent();
        Bundle extra = incomingIntent.getExtras();
        Log.d(TAG, "onCreate: this is bundle extra" +extra);

        if(extra != null){
            Incoming_data_name = (TextView) findViewById(R.id.NameText);
            Incoming_value_init = (TextView) findViewById(R.id.Initial_value);
            Incoming_value_current = (TextView) findViewById(R.id.Current_value);
            Incoming_comment = (TextView)findViewById(R.id.Comment);
            String incoming_comment = incomingIntent.getStringExtra("comment_of_list");
            String incoming_name = incomingIntent.getStringExtra("name_of_list");

            incoming_value = incomingIntent.getStringExtra("value_of_list");
            Incoming_comment.setText(incoming_comment);
            Incoming_data_name.setText(incoming_name);
            Incoming_value_init.setText(incoming_value);
            Incoming_value_current.setText(incoming_value);
            int current_value = Integer.parseInt(incoming_value);
            value = current_value;

        }


        Date date = new Date();
        Log.d(TAG, "onCreate: The date is now " + date);

        //INCREMENT BUTTON
        inc_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onCreate: the value is now " + value);
                value = value + 1;
                Display(value);
            }
        });

        //DECREMENT BUTTON
        dec_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = value - 1;
                Display(value);
            }
        });

        //RESET BUTTON
        reset_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String is_there_a_init_value = ((TextView)findViewById(R.id.Initial_value)).getText().toString();
                Log.d(TAG, "onClick: the is there a init value ? " + is_there_a_init_value);
            if(is_there_a_init_value.equals("")){
                value = 0;
                Display(value);
            }
            else{

                Incoming_value_init = (TextView) findViewById(R.id.Initial_value);
                Log.d(TAG, "onClick: inside the reset the inital value " + incoming_value);
                value = Integer.parseInt(incoming_value);
                Display(value);
            }
            }

        });



        /*
        save_but.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: I just clicked the save button");
                String Item_name = ((EditText)findViewById(R.id.NameText)).getText().toString();
                Log.d(TAG, "onClick: the item_name is " + Item_name);
                String Item_curr_value = ((TextView)findViewById(R.id.Current_value)).getText().toString();
                Log.d(TAG, "onClick: the item value is " + Item_curr_value);
                Date date_to_send = new Date();
                String String_date = date_to_send.toString();
                Log.d(TAG, "onClick: the string date is now " + String_date);

                if(Item_name.equals("") ) {

                }

                else {
                    Intent intent = new Intent();
                    intent.putExtra("name", Item_name);
                    intent.putExtra("value", Item_curr_value);
                    intent.putExtra("Date", String_date);
                    setResult(Constants.RESULT_OK,intent);
                    //startActivity(intent);
                    finish();
                }
            }
        });
        */
    }

    //SAVE BUTTON
    public void Save_button_clicked(View v){
        String Item_name = ((EditText)findViewById(R.id.NameText)).getText().toString();
        String Item_curr_value = ((TextView)findViewById(R.id.Current_value)).getText().toString();
        String Item_comment = ((EditText)findViewById(R.id.Comment)).getText().toString();
        Date date_to_send = new Date();
        String String_date = date_to_send.toString();

        if(Item_name.equals(""))
        {

        }
        // fix the if Item_curr_value = blank error
        if(Item_curr_value.equals("")){
            Log.d(TAG, "Save_button_clicked: i am in this where curr_value.equals");
            Intent intent = new Intent();
            intent.putExtra("name", Item_name);
            Item_curr_value = "0";
            intent.putExtra("date",String_date);
            intent.putExtra("comment",Item_comment);
            Log.d(TAG, "Save_button_clicked: the name is " + Item_curr_value);
            setResult(Constants.RESULT_OK,intent);
            finish();
        }
        else{
            Intent intent = new Intent();
            intent.putExtra("name", Item_name);
            intent.putExtra("value",Item_curr_value);
            intent.putExtra("date",String_date);
            intent.putExtra("comment",Item_comment);
            Log.d(TAG, "Save_button_clicked: the name is " + Item_curr_value);
            setResult(Constants.RESULT_OK,intent);
            finish();
        }
    }
    public void Display(int num){
        if(num <= 0 ){
            num = 0;
            value = 0;
        }
        TextView displayInteger = (TextView)findViewById(R.id.Current_value);
        displayInteger.setText("" + num);
    }

}
