package com.example.tttt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {


    ImageButton backButton;
    Button enrollOrUnenrollButton;
    ImageButton btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome);


        backButton = findViewById(R.id.backbutton_id);
        //viewButton = findViewById(R.id.viewClasses_id);
        enrollOrUnenrollButton = findViewById(R.id.enroll_id);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        /*viewButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ViewClassesMember.class);
                startActivity(intent);

            }

        });*/

        enrollOrUnenrollButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), EnrollOrUnenrollClassesMember.class);

                //Toast.makeText(UserActivity.this, username, Toast.LENGTH_SHORT).show();
                intent.putExtra("username1", username);
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