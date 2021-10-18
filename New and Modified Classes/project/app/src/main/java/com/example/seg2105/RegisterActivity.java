package com.example.seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class RegisterActivity extends MainActivity {

    ImageButton btn1;
    Button btn2, btn3;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn1 = findViewById(R.id.back_button_r);
        btn2 = findViewById(R.id.login_r);
        btn3 = findViewById(R.id.register_r);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);

            }
        });

    }

}
