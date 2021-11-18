package com.example.tttt;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class CreateClassInstructor extends MainActivity implements DatePickerDialog.OnDateSetListener{
    DBClass db3;
    TextInputEditText title, description, capacity, startTime;
    Spinner typeDropDown, difficultyDropDown;
    Button btn, btn1;
    ImageButton btn2;
    static String date;
//Note, might need to add an additional column to include the instructor name, or all tag
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class_instructor);

        db3 = new DBClass(this);
        title = findViewById(R.id.insEnterTitleInstructor);

        //drop down list to select type of class
        typeDropDown = (Spinner) findViewById(R.id.classType_id);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.classType));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeDropDown.setAdapter(typeAdapter);

        //drop down list for difficulty
        difficultyDropDown = (Spinner) findViewById(R.id.classDifficulty_id);
        ArrayAdapter<String> difficultyAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.difficultyType));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultyDropDown.setAdapter(difficultyAdapter);

        description = findViewById(R.id.enterDesc);
        capacity = findViewById(R.id.insEnterCap);
        startTime = findViewById(R.id.insEnterTime);
        btn = findViewById(R.id.chooseDate);
        btn1 = findViewById(R.id.confirmChangesBtn);
        btn2 = findViewById(R.id.backbutton3);

        AddData();

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), InstructorActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showPickerDialog();
            }
            private void showPickerDialog(){
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        CreateClassInstructor.this,
                        CreateClassInstructor.this,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

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

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        date = "day/month/year: " + dayOfMonth + "/" + month + 1 + "/" + year;
    }


    public void AddData() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titl = title.getText().toString();
                String type = typeDropDown.getSelectedItem().toString();
                String desc = description.getText().toString();
                String diff = difficultyDropDown.getSelectedItem().toString();
                String cap = capacity.getText().toString();
                String time = startTime.getText().toString();
                Integer c2 = 0;

                //we will print out message to make sure we got the right input
                String enteredPrint = "You entered: ";
                enteredPrint += "\ntitle = " + titl;
                enteredPrint += "\ntype = " + type;
                enteredPrint += "\ndescription = " + desc;
                enteredPrint += "\ndifficulty = " + diff;
                enteredPrint += "\ncapacity = " + cap;
                enteredPrint += "\ntime = " + time;
                Toast.makeText(getApplicationContext(), enteredPrint, Toast.LENGTH_SHORT).show();


                if (!isInteger(cap)) {

                    Toast.makeText(CreateClassInstructor.this, "Please enter correct number.", Toast.LENGTH_SHORT).show();

                }   else {

                    c2 = Integer.parseInt(cap);

                }

                if (titl.equals("") || desc.equals("") || diff.equals("") || cap.equals("") || time.equals("") || date.equals("")) {

                    Toast.makeText(CreateClassInstructor.this, "Please enter all the fields.", Toast.LENGTH_SHORT).show();

                } else {

                    Boolean insert = db3.insertData(titl, type, desc, diff, c2, date, time, "instructor");

                    if (insert) {

                        Toast.makeText(CreateClassInstructor.this, "Class created!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), InstructorActivity.class);
                        startActivity(intent);

                    } else {

                        Toast.makeText(CreateClassInstructor.this, "Class not created.", Toast.LENGTH_SHORT).show();

                    }


                }

            }
        });
    }


}
