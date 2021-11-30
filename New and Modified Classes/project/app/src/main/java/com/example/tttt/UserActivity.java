package com.example.tttt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {


    ImageButton backButton;
    Button viewButton, enrollButton, unenrollButton;
    ImageButton btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome);


        backButton = findViewById(R.id.backbutton_id);
        viewButton = findViewById(R.id.viewClasses_id);
        enrollButton = findViewById(R.id.enroll_id);
        unenrollButton = findViewById((R.id.unenroll_id));

        viewButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ViewClassesMember.class);
                startActivity(intent);

            }

        });

        enrollButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), EnrollClassesMember.class);
                startActivity(intent);

            }

        });

        unenrollButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), UnenrollClassesMember.class);
                startActivity(intent);

            }

        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

    }


}