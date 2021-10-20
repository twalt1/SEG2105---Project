package com.example.tttt;

import java.util.Date;

public class Class {
    String title, description, difficulty, date, time;
    //Date[] dayOfWeek, time;
    int capacity;

    /*
        I am thinking that instead of making 2 Data[] arrays dayOfWeek and time, we can
        make a dropdown menu in the class enroll interface (once it is created)
     */

    public Class(String title, String description, String difficulty, int capacity, String date, String time) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.date = date;
        this.time = time;
        this.capacity = capacity;
    }
}
