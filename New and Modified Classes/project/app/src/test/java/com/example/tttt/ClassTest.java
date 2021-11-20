package com.example.tttt;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClassTest {

    private String[] title = {"classA", "classB","classC"};
    private String[] description = {"Aerobics", "Anaerobic","Stretching"};
    private String[] difficulty = {"Beginner", "Intermediate","Advanced"};
    private String[] date = {"18/11/2021", "19/11/2021","20/11/2021"};
    private String[] time = {"10", "12","14"};
    private Integer[] capacity = {20, 48, 64};
    private String[] instructor = {"instructorA", "instructorB","instructorC"};
    private String[] type = {"Yoga", "Cardio","Weight Lifting"};
    private String[] dayOfWeek = {"Monday", "Tuesday","Wednesday"};

    public Class setUp() {

        Class c = new Class(title[0], type[0], description[0], difficulty[0], capacity[0], date[0], time[0], instructor[0], dayOfWeek[0]);

        return c;

    }

    @Test
    public void test_setTitle() {

        Class c = setUp();
        assertEquals(c.getTitle(), "classA");

        c.setTitle(title[1]);
        assertEquals(c.getTitle(), "classB");

        c.setTitle(title[2]);
        assertEquals(c.getTitle(), "classC");

        c.setTitle("newClass");
        assertEquals(c.getTitle(), "newClass");

    }

    @Test
    public void test_setDescription() {

        Class c = setUp();
        assertEquals(c.getDescription(), "Aerobics");

        c.setDescription("none");
        assertEquals(c.getDescription(), "none");

        c.setDescription(description[1]);
        assertEquals(c.getDescription(), "Anaerobic");

        c.setDescription(description[2]);
        assertEquals(c.getDescription(), "Stretching");

    }

    @Test
    public void test_setDifficulty() {

        Class c = setUp();
        assertEquals(c.getDifficulty(), "Beginner");

        c.setDifficulty("Hell");
        assertEquals(c.getDifficulty(), "Hell");

        c.setDifficulty(difficulty[1]);
        assertEquals(c.getDifficulty(), "Intermediate");

        c.setDifficulty(difficulty[2]);
        assertEquals(c.getDifficulty(), "Advanced");

    }

    @Test
    public void test_setDate() {

        Class c = setUp();
        assertEquals(c.getDate(), "18/11/2021");

        c.setDate("22/12/2222");
        assertEquals(c.getDate(), "22/12/2222");

        c.setDate(date[1]);
        assertEquals(c.getDate(), "19/11/2021");

        c.setDate(date[2]);
        assertEquals(c.getDate(), "20/11/2021");

    }

    @Test
    public void test_setTime() {

        Class c = setUp();
        assertEquals(c.getTime(), "10");

        c.setTime("24");
        assertEquals(c.getTime(), "24");

        c.setTime(time[1]);
        assertEquals(c.getTime(), "12");

        c.setTime(time[2]);
        assertEquals(c.getTime(), "14");

    }

    @Test
    public void test_setCapacity() {

        Class c = setUp();
        assertEquals(c.getCapacity(), 20);

        c.setCapacity(2048);
        assertEquals(c.getCapacity(), 2048);

        c.setCapacity(capacity[1]);
        assertEquals(c.getCapacity(), 48);

        c.setCapacity(capacity[2]);
        assertEquals(c.getCapacity(), 64);

    }

    @Test
    public void test_setInstructor() {

        Class c = setUp();
        assertEquals(c.getInstructor(), "instructorA");

        c.setInstructor("Ekko");
        assertEquals(c.getInstructor(), "Ekko");

        c.setInstructor(instructor[1]);
        assertEquals(c.getInstructor(), "instructorB");

        c.setInstructor(instructor[2]);
        assertEquals(c.getInstructor(), "instructorC");

    }

    @Test
    public void test_setType() {

        Class c = setUp();
        assertEquals(c.getType(), "Yoga");

        c.setType("Muay Thai");
        assertEquals(c.getType(), "Muay Thai");

        c.setType(type[1]);
        assertEquals(c.getType(), "Cardio");

        c.setType(type[2]);
        assertEquals(c.getType(), "Weight Lifting");

    }

    @Test
    public void test_setDayOfWeek() {

        Class c = setUp();
        assertEquals(c.getDayOfWeek(), "Monday");

        c.setDayOfWeek("Sunday");
        assertEquals(c.getDayOfWeek(), "Sunday");

        c.setDayOfWeek(dayOfWeek[1]);
        assertEquals(c.getDayOfWeek(), "Tuesday");

        c.setDayOfWeek(dayOfWeek[2]);
        assertEquals(c.getDayOfWeek(), "Wednesday");

    }

    @Test
    public void test_checkClass() {

        Class c = setUp();
        Class c2 = new Class(title[2], type[1], description[0], difficulty[1], capacity[2], date[2], time[1], instructor[2], dayOfWeek[1]);
        assertTrue(c.checkClass(c2));

        c2.setType(type[0]);
        assertTrue(c.checkClass(c2));

        c2.setDate(date[0]);
        assertFalse(c.checkClass(c2));

    }

}