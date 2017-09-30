package com.example.root.alex_count_book_new;

import java.util.Date;

/**
 * Created by root on 9/29/17.
 */

public class Counter {

    private String name;
    //private Date date;
    private String value;
    private String date;
    public Counter(String name, String value, String date){
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
