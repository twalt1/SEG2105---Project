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

public class EnrollClassesMember extends AppCompatActivity {

    Button viewClasses, enroll;
    ImageButton back;
    DBClass db3;
    Spinner typeDropDown, difficultyDropDown, dayOfWeekDropDown;
    EditText getClassId, getClassTitle, getClassDescription, getClassTime;
    TextInputEditText capacity, startTime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_classes_member);

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
        //cancelClasses = findViewById(R.id.cancelClass);
        enroll = findViewById(R.id.apply_id);
        getClassId = findViewById(R.id.class_id);
        getClassTitle = findViewById(R.id.title_id);
        getClassDescription = findViewById(R.id.description_id);
        getClassTime = findViewById(R.id.time_id);

        db3 = new DBClass(this);



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
                    buffer.append("# Members : " + numMembers + "\n");
                    buffer.append("\n");

                }

                showMessage("Data", buffer.toString());

            }
        });



        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            //getClassId, getClassTitle
            public void onClick(View view) {
                try{
                    String classID = getClassId.getText().toString();
                    String type = typeDropDown.getSelectedItem().toString();
                    String diff = difficultyDropDown.getSelectedItem().toString();
                    String cap = capacity.getText().toString();
                    String time = getClassTime.getText().toString();
                    String classDescription = getClassDescription.getText().toString();
                    String classTitle = getClassTitle.getText().toString();
                    String dayOfWeek = dayOfWeekDropDown.getSelectedItem().toString();


                    if (classTitle.isEmpty() && classDescription.isEmpty() && time.isEmpty()){
                        Toast.makeText(EnrollClassesMember.this,"Both title and description fields cannot be empty", Toast.LENGTH_LONG).show();
                    }
                    else if(classID.isEmpty()){
                        Toast.makeText(EnrollClassesMember.this,"Must input a class ID to update", Toast.LENGTH_LONG).show();
                    }
                    else if (!isInteger(cap) && cap.length() != 0){
                        Toast.makeText(EnrollClassesMember.this,"Please enter an integer for 'capacity'", Toast.LENGTH_LONG).show();
                    }
                    //if capacity is an integer but is smaller than 1
                    else if (isInteger(cap) && Integer.parseInt(cap) < 1){
                        Toast.makeText(EnrollClassesMember.this, "Class's capacity must be > 1", Toast.LENGTH_SHORT).show();
                    }
                    else if(!time.isEmpty()&&(Integer.parseInt(time) < 0 || Integer.parseInt(time) > 24)){
                        Toast.makeText(EnrollClassesMember.this, "Invalid Time", Toast.LENGTH_SHORT).show();
                    }

                    SharedPreferences currentUserSession = getApplicationContext().getSharedPreferences("currentUserSession", Context.MODE_PRIVATE);
                    String memberEmail = currentUserSession.getString("email", "");
                    int enrollStatus = db3.updateMemberList(classID, memberEmail);
                    if (enrollStatus == 1){
                        Toast.makeText(EnrollClassesMember.this,"Successfully enrolled!", Toast.LENGTH_LONG).show();
                    }
                    if (enrollStatus == -1){
                        Toast.makeText(EnrollClassesMember.this,"You're already enrolled!", Toast.LENGTH_LONG).show();
                    }
                    /*
                    //if description isn't empty and the class title isn't empty
                    if (!cap.isEmpty() && Integer.parseInt(cap) > 1){
                        db3.updateCapacity(classID, Integer.parseInt(cap));
                    }
                    if (!diff.isEmpty()) {
                        db3.updateDifficulty(classID, diff);
                    }
                    if(!type.isEmpty()) {
                        db3.updateType(classID, type);
                    }
                    if(!dayOfWeek.isEmpty()) {
                        db3.updateDayOfWeek(classID, dayOfWeek);
                    }
                    if(!time.isEmpty() && (Integer.parseInt(time) > 0 && Integer.parseInt(time) < 24)){
                        db3.updateTime(classID, time);
                    }
                    if(!classTitle.isEmpty()){
                        db3.updateName(classID, classTitle);
                    }
                    if(!classDescription.isEmpty()){
                        db3.updateDescription(classID, classDescription);
                    }
                    */




                }
                catch(Exception e){
                    Toast.makeText(EnrollClassesMember.this,"Exception occurred: " + e, Toast.LENGTH_LONG).show();
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