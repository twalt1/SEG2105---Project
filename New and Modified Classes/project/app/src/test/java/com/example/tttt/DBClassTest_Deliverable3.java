package com.example.tttt;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

public class DBClassTest_Deliverable3 {

    @Test
    public void TestGetStringAfterUnenroll(){

        //remove first member
        String s = "gym1@gmail.com, gym2@gmail.com, gym3@gmail.com";
        assertEquals("gym2@gmail.com, gym3@gmail.com", DBClass.getStringAfterUnenroll(s, "gym1@gmail.com"));
        //remove second member
        s = "gym1@gmail.com, gym2@gmail.com, gym3@gmail.com";
        assertEquals("gym1@gmail.com, gym3@gmail.com", DBClass.getStringAfterUnenroll(s, "gym2@gmail.com"));
        //remove last member
        s = "gym1@gmail.com, gym2@gmail.com, gym3@gmail.com";
        assertEquals("gym1@gmail.com, gym2@gmail.com", DBClass.getStringAfterUnenroll(s, "gym3@gmail.com"));

        s = "gym1@gmail.com, gym2@gmail.com, gym3@gmail.com, gym4@gmail.com, gym5@gmail.com";
        String newS = DBClass.getStringAfterUnenroll(s,"gym1@gmail.com");
        newS = DBClass.getStringAfterUnenroll(newS, "gym3@gmail.com");
        assertEquals("gym2@gmail.com, gym4@gmail.com, gym5@gmail.com", newS);

        s = "gym1@gmail.com, gym2@gmail.com, gym3@gmail.com, gym4@gmail.com, gym5@gmail.com";
        newS = DBClass.getStringAfterUnenroll(s,"gym1@gmail.com");
        newS = DBClass.getStringAfterUnenroll(newS, "gym3@gmail.com");
        newS = DBClass.getStringAfterUnenroll(newS, "gym5@gmail.com");
        assertEquals("gym2@gmail.com, gym4@gmail.com", newS);

    }

    @Test
    public void TestStringToList() {

        String s1 = "gym1@gmail.com";
        String s2 = "gym2@gmail.com";
        String s3 = "gym3@gmail.com";
        String toTest1 = "gym1@gmail.com,gym2@gmail.com,gym3@gmail.com";
        ArrayList<String> res1 = DBClass.stringToList(toTest1);
        ArrayList<String> eql1 = new ArrayList<String>();

        eql1.add(s1);
        eql1.add(s2);
        eql1.add(s3);

        assertEquals(eql1,res1);

        String toTest2 = "gym1@gmail.com,gym3@gmail.com";
        ArrayList<String> res2 = DBClass.stringToList(toTest2);
        ArrayList<String> eql2 = new ArrayList<String>();

        eql2.add(s1);
        eql2.add(s3);

        assertEquals(eql2, res2);

        String toTest3 = "gym1@gmail.com";
        ArrayList<String> res3 = DBClass.stringToList(toTest3);
        ArrayList<String> eql3 = new ArrayList<String>();

        eql3.add(s1);

        assertEquals(eql3, res3);

    }

    @Test
    public void removeFromList() {

        String s1 = "gym1@gmail.com";
        String s2 = "gym2@gmail.com";
        String s3 = "gym3@gmail.com";
        String toTest = "gym1@gmail.com,gym2@gmail.com,gym3@gmail.com";
        ArrayList<String> res1 = DBClass.stringToList(toTest);
        ArrayList<String> eql1 = new ArrayList<String>();

        eql1.add(s1);
        eql1.add(s2);

        assertEquals(eql1, DBClass.removeFromList(res1,"gym3@gmail.com"));

        ArrayList<String> res2 = DBClass.stringToList(toTest);
        ArrayList<String> eql2 = new ArrayList<String>();

        eql2.add(s1);
        eql2.add(s3);

        assertEquals(eql2, DBClass.removeFromList(res2,"gym2@gmail.com"));

        ArrayList<String> res3 = DBClass.stringToList(toTest);
        ArrayList<String> eql3 = new ArrayList<String>();

        eql3.add(s2);
        eql3.add(s3);

        assertEquals(eql3, DBClass.removeFromList(res3,"gym1@gmail.com"));

    }
}