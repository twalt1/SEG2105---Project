package com.example.tttt;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }



    @Test
    public void getStringAfterUnenroll_Test(){

        //remove first member
        String s = "gym1@gmail.com, gym2@gmail.com, gym3@gmail.com";
        assertEquals("gym2@gmail.com, gym3@gmail.com", DBClass.getStringAfterUnenroll(s, "gym1@gmail.com"));
        //remove second member
        s = "gym1@gmail.com, gym2@gmail.com, gym3@gmail.com";
        assertEquals("gym1@gmail.com, gym3@gmail.com", DBClass.getStringAfterUnenroll(s, "gym2@gmail.com"));
        //remove last member
        s = "gym1@gmail.com, gym2@gmail.com, gym3@gmail.com";
        assertEquals("gym1@gmail.com, gym2@gmail.com", DBClass.getStringAfterUnenroll(s, "gym3@gmail.com"));
    }
}