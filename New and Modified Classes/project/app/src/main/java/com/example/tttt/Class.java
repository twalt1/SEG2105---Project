package com.example.tttt;

import java.util.Date;

public class Class {
    String name, description, type;
    Date[] dayOfWeek, time;
    int capacity;

    /*
        I am thinking that instead of making 2 Data[] arrays dayOfWeek and time, we can
        make a dropdown menu in the class enroll interface (once it is created)
     */

    public Class(String name, String description, String type, int capacity, Date[] dayOfWeek,
                 Date[] time) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.dayOfWeek = dayOfWeek.clone();
        this.time = time.clone();
        this.capacity = capacity;
    }
}
