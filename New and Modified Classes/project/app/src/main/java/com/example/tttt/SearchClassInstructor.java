package com.example.tttt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class SearchClassInstructor extends AppCompatActivity {
    DBInstructor db;
    String txt;
    TextInputEditText text;
    Button btn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_class_search);
        db = new DBInstructor(this);
        text = findViewById(R.id.txtInstructor);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt = text.getText().toString();

            }
        });
    }
}
