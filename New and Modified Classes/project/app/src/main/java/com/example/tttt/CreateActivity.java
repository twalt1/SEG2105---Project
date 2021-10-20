package com.example.tttt;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class CreateActivity extends MainActivity implements DatePickerDialog.OnDateSetListener{
    DBHelper3 db3;
    TextInputEditText title, description, difficulty, capacity;
    Button btn, btn1, btn2;
    ImageButton btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        db3 = new DBHelper3(this);
        title = findViewById(R.id.enterTitle);
        description = findViewById(R.id.enterDesc);
        difficulty = findViewById(R.id.enterDiff);
        capacity = findViewById(R.id.enterCap);
        btn = findViewById(R.id.dateDialog);
        btn1 = findViewById(R.id.timeDialog);
        btn2 = findViewById(R.id.enterbtn);
        btn3 = findViewById(R.id.backbutton);

        AddData();

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

    public void AddData() {
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titl = title.getText().toString();
                String desc = description.getText().toString();
                String diff = difficulty.getText().toString();
                String cap = capacity.getText().toString();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        String date = "day/month/year: " + dayOfMonth + "/" + month + "/" + year;
    }
}
