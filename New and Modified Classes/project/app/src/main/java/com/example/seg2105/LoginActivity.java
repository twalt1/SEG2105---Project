package com.example.seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class LoginActivity extends MainActivity{

    //back button
    ImageButton btn1;
    //button 2 is the create account button
    //button 3 is the login button
    Button btn2, btn3;
    TextInputEditText input1, input2;

    DBHelper database = new DBHelper(LoginActivity.this);

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn1 = findViewById(R.id.back_button_l);
        btn3 = findViewById(R.id.login_l);
        btn2 = findViewById(R.id.register_l);
        input1 = findViewById(R.id.enterUsername_l);
        input2 = findViewById(R.id.enterPassword_l);

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

                Intent i=new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);

            }

        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                database = new DBHelper(LoginActivity.this);

                try{
                    String enteredUsername = input1.getText().toString();
                    String enteredPassword = input2.getText().toString();
                    Boolean status = database.checkusernamepassword(enteredUsername, enteredPassword );
                    if (!status){
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        //start new welcome page

                    }

                }
                catch(Exception e){
                    Toast.makeText(LoginActivity.this, "ERROR: " + e.toString(), Toast.LENGTH_SHORT).show();
                }



            }

        });

    }

}
