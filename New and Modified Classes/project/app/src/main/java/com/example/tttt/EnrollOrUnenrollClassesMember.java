package com.example.tttt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnrollOrUnenrollClassesMember extends AppCompatActivity {

    Button viewClasses, enroll, unenroll, viewEnrolled;
    ImageButton back;
    DBClass db3;
    DBUser db2;
    Spinner typeDropDown, difficultyDropDown, dayOfWeekDropDown;
    EditText getClassId, getClassTitle, getClassDescription, getClassTime;
    TextInputEditText capacity, startTime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_or_unenroll_classes_member);

        //drop down list to select type of class
        typeDropDown = (Spinner) findViewById(R.id.type_id);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.classType));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeDropDown.setAdapter(typeAdapter);

        //drop down list for difficulty
        difficultyDropDown = (Spinner) findViewById(R.id.difficulty_id);
        ArrayAdapter<String> difficultyAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.difficultyType));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultyDropDown.setAdapter(difficultyAdapter);

        //drop down list for dayofweek
        dayOfWeekDropDown = (Spinner) findViewById(R.id.dow_id);
        ArrayAdapter<String> dayOfWeekAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.dayOfWeek));
        dayOfWeekAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayOfWeekDropDown.setAdapter(dayOfWeekAdapter);

        capacity = findViewById(R.id.cap_id);
        viewClasses = findViewById(R.id.view_id);
        back = findViewById(R.id.back_id);
        unenroll = findViewById(R.id.unenroll_id);
        enroll = findViewById(R.id.apply_id);
        getClassId = findViewById(R.id.class_id);
        getClassTitle = findViewById(R.id.title_id);
        getClassDescription = findViewById(R.id.description_id);
        getClassTime = findViewById(R.id.time_id);
        viewEnrolled = findViewById(R.id.ViewEnrolled);
        db2 = new DBUser(this);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username1");
        String email = db2.getUserEmail(username);
        //Toast.makeText(EnrollOrUnenrollClassesMember.this, email, Toast.LENGTH_SHORT).show();

        db3 = new DBClass(this);

        viewEnrolled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Class> classes = db3.getEnrolled(email);

                StringBuffer buffer = new StringBuffer();

                for(int i = 0; i < classes.size(); i++) {

                    Class aClass = classes.get(i);

                    buffer.append("ID : " + aClass.getID() + "\n");
                    buffer.append("Title : " + aClass.getTitle() + "\n");
                    buffer.append("Type : " + aClass.getType() + "\n");
                    buffer.append("Description : " + aClass.getDescription() + "\n");
                    buffer.append("Difficulty : " + aClass.getDifficulty() + "\n");
                    buffer.append("Capacity : " + aClass.getCapacity() + "\n");
                    buffer.append("Date : " + aClass.getDate() + "\n");
                    buffer.append("Time : " + aClass.getTime() + "\n");
                    buffer.append("Instructor : " + aClass.getInstructor() + "\n");
                    buffer.append("Day of week : " + aClass.getDayOfWeek() + "\n");
                    buffer.append("\n");

                }

                showMessage("Data", buffer.toString());

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(intent);
            }
        });

        viewClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = db3.getAllData();
                if (res.getCount() == 0) {

                    showMessage("Error", "Nothing Found");
                    return;

                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {

                    buffer.append("ID : " + res.getString(0) + "\n");
                    buffer.append("Title : " + res.getString(1) + "\n");
                    buffer.append("Type : " + res.getString(2) + "\n");
                    buffer.append("Description : " + res.getString(3) + "\n");
                    buffer.append("Difficulty : " + res.getString(4) + "\n");
                    buffer.append("Capacity : " + res.getString(5) + "\n");
                    buffer.append("Date : " + res.getString(6) + "\n");
                    buffer.append("Time : " + res.getString(7) + "\n");
                    buffer.append("Instructor : " + res.getString(8) + "\n");
                    buffer.append("Day of week : " + res.getString(9) + "\n");

                    String stringOfMembers = res.getString(10);
                    String[] arrayOfMembers = stringOfMembers.split(",");
                    List<String> listMembers = new ArrayList<String>();
                    listMembers = Arrays.asList(arrayOfMembers);
                    int numMembers = -1;
                    if (listMembers.toString().equals("[]")){
                        numMembers = 0;
                    } else {
                        numMembers = listMembers.size();
                    }
                    buffer.append("# Members : " + listMembers.toString() + "\n");
                    buffer.append("\n");

                }

                showMessage("Data", buffer.toString());

            }
        });



        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            //getClassId, getClassTitle
            public void onClick(View view) {
                try {
                    String classID = getClassId.getText().toString();
                    String type = typeDropDown.getSelectedItem().toString();
                    String diff = difficultyDropDown.getSelectedItem().toString();
                    String cap = capacity.getText().toString();
                    String time = getClassTime.getText().toString();
                    String classDescription = getClassDescription.getText().toString();
                    String classTitle = getClassTitle.getText().toString();
                    String dayOfWeek = dayOfWeekDropDown.getSelectedItem().toString();
                    Boolean checkConflict;
                    Class aClass = null;

                    if (!classID.isEmpty()) {
                        try {

                            Integer x = Integer.parseInt(classID);

                        } catch (NumberFormatException e) {

                            Toast.makeText(EnrollOrUnenrollClassesMember.this, "The class ID must be a number", Toast.LENGTH_SHORT).show();

                        }
                    }

                    if (!classID.isEmpty()) {

                        aClass = db3.findClass(Integer.parseInt(classID));

                    } else if (!classTitle.isEmpty()) {

                        aClass = db3.findClass(classTitle);

                    } else {

                        Toast.makeText(EnrollOrUnenrollClassesMember.this, "Please at least enter the title or the id of the class", Toast.LENGTH_SHORT).show();

                    }

                    checkConflict = db3.checkConflict(email, aClass.getDate());

                    //if (checkConflict) {

                        if (classID.isEmpty()) {

                            if (classTitle.isEmpty()) {
                                Toast.makeText(EnrollOrUnenrollClassesMember.this, "Please at least enter the title or the id of the class", Toast.LENGTH_LONG).show();
                            }


                                SharedPreferences currentUserSession = getApplicationContext().getSharedPreferences("currentUserSession", Context.MODE_PRIVATE);
                                String memberEmail = currentUserSession.getString("email", "");
                                int enrollStatus = db3.enrollMemberList(classID, memberEmail, checkConflict);
                                if (enrollStatus == 1) {
                                    Toast.makeText(EnrollOrUnenrollClassesMember.this, "Successfully enrolled!", Toast.LENGTH_LONG).show();
                                }
                                else if (enrollStatus == -1) {
                                    Toast.makeText(EnrollOrUnenrollClassesMember.this, "You're already enrolled!", Toast.LENGTH_LONG).show();
                                }
                                else if (enrollStatus == -2) {
                                    Toast.makeText(EnrollOrUnenrollClassesMember.this, "Class already at full capacity", Toast.LENGTH_LONG).show();
                                }
                                else if (enrollStatus == 0) {

                                    Toast.makeText(EnrollOrUnenrollClassesMember.this, "There is conflict with another class you have enrolled", Toast.LENGTH_SHORT).show();

                                }


                        } else {

                            SharedPreferences currentUserSession = getApplicationContext().getSharedPreferences("currentUserSession", Context.MODE_PRIVATE);
                            String memberEmail = currentUserSession.getString("email", "");
                            int enrollStatus = db3.enrollMemberList(classID, memberEmail, checkConflict);
                            if (enrollStatus == -1) {
                                Toast.makeText(EnrollOrUnenrollClassesMember.this, "You're already enrolled!", Toast.LENGTH_LONG).show();
                            }
                            else if (enrollStatus == -2) {
                                Toast.makeText(EnrollOrUnenrollClassesMember.this, "Class already at full capacity", Toast.LENGTH_LONG).show();
                            }
                            else if (enrollStatus == 0) {

                                Toast.makeText(EnrollOrUnenrollClassesMember.this, "There is conflict with another class you have enrolled", Toast.LENGTH_SHORT).show();

                            }
                            else if (enrollStatus == 1) {
                                Toast.makeText(EnrollOrUnenrollClassesMember.this, "Successfully enrolled!", Toast.LENGTH_LONG).show();
                            }

                        }

                    //}   else {

                    //    Toast.makeText(EnrollOrUnenrollClassesMember.this, "There is a conflict with another class you have enrolled", Toast.LENGTH_SHORT).show();

                    //}
                }
                catch(Exception e){
                    Toast.makeText(EnrollOrUnenrollClassesMember.this,"Exception occurred: " + e, Toast.LENGTH_LONG).show();
                }
            }
        });

        unenroll.setOnClickListener(new View.OnClickListener() {
            @Override
            //getClassId, getClassTitle
            public void onClick(View view) {
                try {
                    String classID = getClassId.getText().toString();
                    String type = typeDropDown.getSelectedItem().toString();
                    String diff = difficultyDropDown.getSelectedItem().toString();
                    String cap = capacity.getText().toString();
                    String time = getClassTime.getText().toString();
                    String classDescription = getClassDescription.getText().toString();
                    String classTitle = getClassTitle.getText().toString();
                    String dayOfWeek = dayOfWeekDropDown.getSelectedItem().toString();

                    if (classID.isEmpty()) {

                        if (classTitle.isEmpty() && classDescription.isEmpty() && time.isEmpty()) {
                            Toast.makeText(EnrollOrUnenrollClassesMember.this, "Both title and description fields cannot be empty", Toast.LENGTH_LONG).show();
                            return;
                        } else if (classID.isEmpty()) {
                            Toast.makeText(EnrollOrUnenrollClassesMember.this, "Must input a class ID to update", Toast.LENGTH_LONG).show();
                            return;
                        } else if (!isInteger(cap) && cap.length() != 0) {
                            Toast.makeText(EnrollOrUnenrollClassesMember.this, "Please enter an integer for 'capacity'", Toast.LENGTH_LONG).show();
                        }
                        //if capacity is an integer but is smaller than 1
                        else if (isInteger(cap) && Integer.parseInt(cap) < 1) {
                            Toast.makeText(EnrollOrUnenrollClassesMember.this, "Class's capacity must be > 1", Toast.LENGTH_SHORT).show();
                        } else if (!time.isEmpty() && (Integer.parseInt(time) < 0 || Integer.parseInt(time) > 24)) {
                            Toast.makeText(EnrollOrUnenrollClassesMember.this, "Invalid Time", Toast.LENGTH_SHORT).show();
                        }

                        SharedPreferences currentUserSession = getApplicationContext().getSharedPreferences("currentUserSession", Context.MODE_PRIVATE);
                        String memberEmail = currentUserSession.getString("email", "");
                        int enrollStatus = db3.unenrollMemberList(classID, memberEmail);
                        if (enrollStatus == 1) {
                            Toast.makeText(EnrollOrUnenrollClassesMember.this, "Successfully unenrolled!", Toast.LENGTH_LONG).show();
                        }
                        if (enrollStatus == 0) {
                            Toast.makeText(EnrollOrUnenrollClassesMember.this, "Error: Cannot unenroll!", Toast.LENGTH_LONG).show();
                        }
                        if (enrollStatus == -1) {
                            Toast.makeText(EnrollOrUnenrollClassesMember.this, "You're not in this class to begin with!", Toast.LENGTH_LONG).show();
                        }

                    }   else {

                        SharedPreferences currentUserSession = getApplicationContext().getSharedPreferences("currentUserSession", Context.MODE_PRIVATE);
                        String memberEmail = currentUserSession.getString("email", "");
                        int enrollStatus = db3.unenrollMemberList(classID, memberEmail);
                        if (enrollStatus == 1) {
                            Toast.makeText(EnrollOrUnenrollClassesMember.this, "Successfully unenrolled!", Toast.LENGTH_LONG).show();
                        }
                        if (enrollStatus == 0) {
                            Toast.makeText(EnrollOrUnenrollClassesMember.this, "Error: Cannot unenroll!", Toast.LENGTH_LONG).show();
                        }
                        if (enrollStatus == -1) {
                            Toast.makeText(EnrollOrUnenrollClassesMember.this, "You're not in this class to begin with!", Toast.LENGTH_LONG).show();
                        }

                    }
                }
                catch(Exception e){
                    Toast.makeText(EnrollOrUnenrollClassesMember.this,"Exception occurred: " + e, Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void showMessage (String title, String Message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        }   catch (NumberFormatException e) {
            return false;
        }   catch (NullPointerException e) {
            return false;
        }
        return true;
    }

}