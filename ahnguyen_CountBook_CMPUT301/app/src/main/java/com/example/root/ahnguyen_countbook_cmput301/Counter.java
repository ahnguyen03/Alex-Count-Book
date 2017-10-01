package com.example.root.ahnguyen_countbook_cmput301;

/**
 * Created by root on 10/1/17.
 */

public class Counter {
    private String name;
    //private Date date;
    private String value;
    private String date;
    private String comment;

    public Counter(String name, String value, String date){
        this.name = name;
        this.value = value;
        this.date = date;
        this.comment = comment;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
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