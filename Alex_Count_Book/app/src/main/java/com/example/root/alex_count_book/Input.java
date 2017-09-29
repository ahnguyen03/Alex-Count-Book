package com.example.root.alex_count_book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Input extends AppCompatActivity {
    int value = 0;

    //String test = ((EditText)findViewById(R.id.counter_value)).getText().toString();


    /*
    String value_string = ((EditText)findViewById(R.id.counter_value)).getText().toString() ;
    int value = Integer.parseInt(value_string);
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listadddecrement);
    }

    public void SaveButtonClicked(View v){
        String Item_name = ((EditText)findViewById(R.id.item_name)).getText().toString();
        if(Item_name.equals("")){
        }
        else{
            Intent intent = new Intent();
            intent.putExtra(Constants.Intent_name,Item_name);
            setResult(Constants.RESULT_OK,intent);
        }

    }

    public void IncrementButtonClicked(View v){
        value = value + 1;
        Display(value);
    }

    public void DecrementButtonClicked(View v){
        value = value - 1;
        Display(value);
    }
    public void Display(int num){
        if(num <= 0 ){
            num = 0;
            value = 0;
        }
        TextView displayInteger = (TextView) findViewById(R.id.counter_value);
        displayInteger.setText("" + num);
    }
}
