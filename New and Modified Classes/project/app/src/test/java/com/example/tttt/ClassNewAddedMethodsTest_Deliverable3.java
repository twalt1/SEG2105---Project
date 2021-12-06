package com.example.tttt;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ClassNewAddedMethodsTest_Deliverable3 {

    private String[] id = {"0","1","2"};
    private String[] title = {"classA", "classB","classC"};
    private String[] description = {"Aerobics", "Anaerobic","Stretching"};
    private String[] difficulty = {"Beginner", "Intermediate","Advanced"};
    private String[] date = {"18/11/2021", "19/11/2021","20/11/2021"};
    private String[] time = {"10", "12","14"};
    private Integer[] capacity = {20, 48, 64};
    private String[] instructor = {"instructorA", "instructorB","instructorC"};
    private String[] type = {"Yoga", "Cardio","Weight Lifting"};
    private String[] dayOfWeek = {"Monday", "Tuesday","Wednesday"};
    private String memberlist = "user1@gmail.com,user2@gmail.com,user3@gmail.com";

    public Class setUp() {

        Class c = new Class(id[0], title[0], type[0], description[0], difficulty[0], capacity[0], date[0], time[0], instructor[0], dayOfWeek[0], memberlist);

        return c;

    }

    @Test
    public void TestGetID() {

        Class c = setUp();
        assertEquals("0", c.getID());

        c.setID(id[1]);
        assertEquals("1", c.getID());

        c.setID(id[2]);
        assertEquals("2", c.getID());

    }

    @Test
    public void TestGetMemberList() {

        Class c = setUp();
        List<String> list = new ArrayList<String>();
        list.add("user1@gmail.com");
        list.add("user2@gmail.com");
        list.add("user3@gmail.com");

        assertEquals(list, c.getMemberList());

        c.setMemberList("user1@gmail.com,user2@gmail.com");
        list.remove(2);
        assertEquals(list, c.getMemberList());

        c.setMemberList("user1@gmail.com");
        list.remove(1);
        assertEquals(list, c.getMemberList());


    }

}