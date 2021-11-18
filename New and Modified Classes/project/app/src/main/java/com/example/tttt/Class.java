package com.example.tttt;

import java.util.Date;

public class Class {
    String title, type, description, difficulty, date, time, instructor;
    //Date[] dayOfWeek, time;
    int capacity;

    /*
        I am thinking that instead of making 2 Data[] arrays dayOfWeek and time, we can
        make a dropdown menu in the class enroll interface (once it is created)
     */

    public Class(String title, String type, String description, String difficulty, int capacity, String date, String time, String instructor) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.difficulty = difficulty;
        this.date = date;
        this.time = time;
        this.capacity = capacity;
        this.instructor = instructor;
    }
}
