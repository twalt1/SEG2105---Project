package com.example.tttt;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class CreateActivity extends MainActivity implements DatePickerDialog.OnDateSetListener{
    DBAdmin db3;
    TextInputEditText title, description, difficulty, capacity, startTime;
    Button btn, btn1;
    ImageButton btn2;
    static String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        db3 = new DBAdmin(this);
        title = findViewById(R.id.enterTitle);
        description = findViewById(R.id.enterDesc);
        difficulty = findViewById(R.id.enterDiff);
        capacity = findViewById(R.id.enterCap);
        startTime = findViewById(R.id.enterTime);
        btn = findViewById(R.id.dateDialog);
        btn1 = findViewById(R.id.enterbtn);
        btn2 = findViewById(R.id.backbutton);

        AddData();

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
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
                        CreateActivity.this,
                        CreateActivity.this,
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
                String desc = description.getText().toString();
                String diff = difficulty.getText().toString();
                String cap = capacity.getText().toString();
                String time = startTime.getText().toString();
                Integer c2 = 0;


                if (!isInteger(cap)) {

                    Toast.makeText(CreateActivity.this, "Please enter correct number.", Toast.LENGTH_SHORT).show();

                }   else {

                    c2 = Integer.parseInt(cap);

                }

                if (titl.equals("") || desc.equals("") || diff.equals("") || cap.equals("") || time.equals("") || date.equals("")) {

                    Toast.makeText(CreateActivity.this, "Please enter all the fields.", Toast.LENGTH_SHORT).show();

                } else {

                    Boolean insert = db3.insertData(titl, desc, diff, c2, date, time);

                    if (insert) {

                        Toast.makeText(CreateActivity.this, "Class created!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);

                    } else {

                        Toast.makeText(CreateActivity.this, "Class not created.", Toast.LENGTH_SHORT).show();

                    }


                }

            }
        });
    }


}
