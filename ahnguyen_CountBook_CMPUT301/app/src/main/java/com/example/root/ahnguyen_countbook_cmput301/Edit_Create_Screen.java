package com.example.root.ahnguyen_countbook_cmput301;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 10/1/17.
 */

//Creating all the variables being used in this class
public class Edit_Create_Screen extends AppCompatActivity {
    private  final String TAG = "Edit_Create_Screen";
    public static int  value = 0;
    public static int old_value;
    public static int position;
    public static TextView Incoming_data_name;
    public static TextView Incoming_value_init;
    public static TextView Incoming_value_current;
    public static String incoming_value;
    public static TextView Incoming_comment;
    public static String incoming_date;
    public static int Am_i_creating;
    public SimpleDateFormat format;

    //When going to the second activity we  initialize everything
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = 0;
        setContentView(R.layout.create_edit_layout);
        format = new SimpleDateFormat("yyyy-MM-dd");
        Button save_but = (Button)findViewById(R.id.save_but);
        Button inc_but = (Button)findViewById(R.id.Increment_but);
        Button dec_but = (Button)findViewById(R.id.Decrement_but);
        Button reset_but = (Button)findViewById(R.id.reset_but);
        value = 0;

        //This is the data when someone clicks on a list then we grab that data
        Intent incomingIntent = getIntent();
        Am_i_creating = incomingIntent.getIntExtra("Creating",0);

        //We check if there was any data incoming
        Bundle extra = incomingIntent.getExtras();
        if(extra != null ){
            //If we are not creating a new counter then we just grab the data
            if(Am_i_creating == 0) {
                Incoming_data_name = (TextView) findViewById(R.id.NameText);
                Incoming_value_init = (TextView) findViewById(R.id.Initial_value);
                Incoming_value_current = (TextView) findViewById(R.id.Current_value);
                Incoming_comment = (TextView) findViewById(R.id.Comment);
                String incoming_comment = incomingIntent.getStringExtra("comment_of_list");
                String incoming_name = incomingIntent.getStringExtra("name_of_list");
                position = incomingIntent.getIntExtra("position_of_list", -1);
                incoming_date = incomingIntent.getStringExtra("date_of_list");
                incoming_value = incomingIntent.getStringExtra("value_of_list");
                Incoming_comment.setText(incoming_comment);
                Incoming_data_name.setText(incoming_name);
                Incoming_value_init.setText(incoming_value);
                Incoming_value_current.setText(incoming_value);
                int current_value = Integer.parseInt(incoming_value);
                old_value = Integer.parseInt(incoming_value);
                value = current_value;
            }
        }

        Date date = new Date();

        //INCREMENT BUTTON
        inc_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        /*
        if our inital value equals "" then we reset it to original 0
        however if there was every a incoming_value_initaly then we
        can then set our value to that old one when we click reset
         */
        reset_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String is_there_a_init_value = ((TextView)findViewById(R.id.Initial_value)).getText().toString();
                if(is_there_a_init_value.equals("")){
                    value = 0;
                    Display(value);
                }
                else{

                    Incoming_value_init = (TextView) findViewById(R.id.Initial_value);
                    value = Integer.parseInt(incoming_value);
                    Display(value);
                }
            }

        });

    }

    /*
    Basically the Delete button here is just checking if the item name is blanked and then
    we also check if we are creating a new counter or not.
    thus after we create a new intent in which we pass a constant value to let the first activity know
    that we are deleting and the position from the list we are deleting from
     */
    public void Delete_button_clicked(View v){
        String Item_name = ((EditText)findViewById(R.id.NameText)).getText().toString();
        if(Item_name.equals(""))
        {

        }
        if(Am_i_creating == 1){

        }
        else {
            Intent intent = new Intent();
            intent.putExtra("deleting", Constants.Deletion);
            intent.putExtra("position", position);
            setResult(Constants.RESULT_OK, intent);
            finish();
        }

    }

    //SAVE BUTTON
    /*
    Right in the begining we just grab every value from the text view and the current date
    then we check if the name is blank if it is blank then we cant save. however if not then we can
    see if our old value and current value was ever changed if not then dont change the date.
    thus afterwards we just use intent.putExtra() to transfer data over from the second activity to
    the first one.
     */
    public void Save_button_clicked(View v){
        String Item_name = ((EditText)findViewById(R.id.NameText)).getText().toString();
        String Item_curr_value = ((TextView)findViewById(R.id.Current_value)).getText().toString();
        String Item_comment = ((EditText)findViewById(R.id.Comment)).getText().toString();
        Date date_to_send = new Date();
        String String_date = date_to_send.toString();
        String_date = format.format(Date.parse(String_date));

        if(Item_name.equals(""))
        {

        }
        else{
            Intent intent = new Intent();
            int Check_if_old_is_to_curr = Integer.parseInt(Item_curr_value);
            if(position != 0 ) {
                if (Am_i_creating == 0) {
                    if (Check_if_old_is_to_curr == old_value) {
                        intent.putExtra("name", Item_name);
                        intent.putExtra("value", Item_curr_value);
                        intent.putExtra("comment", Item_comment);
                        intent.putExtra("position", position);
                        intent.putExtra("date", incoming_date);
                        setResult(Constants.RESULT_OK, intent);
                        finish();
                    } else {
                        intent.putExtra("name", Item_name);
                        intent.putExtra("value", Item_curr_value);
                        intent.putExtra("date", String_date);
                        intent.putExtra("comment", Item_comment);
                        intent.putExtra("position", position);
                        setResult(Constants.RESULT_OK, intent);
                        finish();
                    }
                }
            }
            else{
                intent.putExtra("name", Item_name);
                intent.putExtra("value", Item_curr_value);
                intent.putExtra("date", String_date);
                intent.putExtra("comment", Item_comment);
                intent.putExtra("creating",Am_i_creating);
                setResult(Constants.RESULT_OK, intent);
                finish();

            }
        }
    }

    //Displays
    /*
    Simplay Display function to show the results in our textview and to have a a condition where
    if we go less then 0 then we just set our value to 0 instead of negative.
     */
    public void Display(int num){
        if(num <= 0 ){
            num = 0;
            value = 0;
        }
        TextView displayInteger = (TextView)findViewById(R.id.Current_value);
        displayInteger.setText("" + num);
    }

}