package com.example.seg2105;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.login);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);

            }
        });

        btn2 = findViewById(R.id.register);

        btn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);

            }


        });

    }

}