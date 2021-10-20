package com.example.tttt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    TextView display;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        display = findViewById(R.id.welcome);

        Intent intent = getIntent();

        String got[] = intent.getStringExtra("name").split(" ");

        display.setText("Welcome\n" + got[0] + "!\n\n You are logged in as\n\n" +got[1]);

    }

}
