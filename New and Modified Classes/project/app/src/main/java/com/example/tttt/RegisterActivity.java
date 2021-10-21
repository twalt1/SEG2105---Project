package com.example.tttt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    DBHelper1 db1;
    DBHelper2 db2;
    TextInputEditText username, password, email, age, phonenumber;
    Button registerButton, loginButton;
    ImageButton backButton;
    CheckBox checkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //gym member
        db1 = new DBHelper1(this);
        //instructor
        db2 = new DBHelper2(this);
        username = findViewById(R.id.enterUsername_r);
        password = findViewById(R.id.enterPassword_r);
        email = findViewById(R.id.enterEmail_r);
        age = findViewById(R.id.enterAge_r);
        phonenumber = findViewById(R.id.enterPhoneNum_r);
        registerButton = findViewById(R.id.register_r);
        loginButton = findViewById(R.id.login_r);
        backButton = findViewById(R.id.back_button_r);
        checkButton = findViewById(R.id.check);

        //AddData();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    AddData();
                } catch (Exception e){
                    Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }

                //Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                //startActivity(intent);

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                //startActivity(intent);

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);

            }
        });

    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        }   catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public void AddData(){
        String usernameInput = username.getText().toString();
        String passwordInput = password.getText().toString();
        String emailInput = email.getText().toString();
        String ageInput = age.getText().toString();
        String phoneInput = phonenumber.getText().toString();
        boolean checkInput = checkButton.isChecked();

        boolean allValidInput = false;

        //INPUT VALIDATION
        //if empty string
        if (usernameInput.isEmpty() || passwordInput.isEmpty() || emailInput.isEmpty() || ageInput.isEmpty() || phoneInput.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Error: please fill out all fields", Toast.LENGTH_SHORT).show();
        }
        // if age is not an integer
        else if (!isInteger(ageInput)){
            Toast.makeText(RegisterActivity.this, "Error: age has to be an integer", Toast.LENGTH_SHORT).show();

        }
        else {
            allValidInput = true;
        }

        //we will print out message to make sure we got the right input
        String enteredPrint = "username = " + usernameInput;
        enteredPrint += "\npassword = " + passwordInput;
        enteredPrint += "\nemail = " + emailInput;
        enteredPrint += "\nage = " + ageInput;
        enteredPrint += "\nphone = " + phoneInput;
        enteredPrint += "\ncheckbox = " + checkInput;
        enteredPrint += "\nallValidInput = " + allValidInput;
        Toast.makeText(RegisterActivity.this, enteredPrint, Toast.LENGTH_SHORT).show();


        //INSERT USER INTO DATABASE
        //if instructor checked
        if (checkInput){
            //create an instructor
            Instructor newInstructor = new Instructor(usernameInput, passwordInput, emailInput, ageInput, phoneInput);

            //if user already existed
            if (db2.checkusername(newInstructor.getUserName())){
                Toast.makeText(RegisterActivity.this, "Error: instructor already exists in database!", Toast.LENGTH_SHORT).show();
            }
            //if user does not exist, then add into database
            else {

            }

        } else {
            //if instructor not checked
            //create a gym member
            GymMember newGymMember = new GymMember(usernameInput, passwordInput, emailInput, ageInput, phoneInput);

            //if user already existed
            if (db1.checkusername(newGymMember.getUserName())){
                Toast.makeText(RegisterActivity.this, "Error: gym member already exists in database!", Toast.LENGTH_SHORT).show();
            }
            //if user does not exist, then add into database
            else {

            }

        }







    }
    /*
    public void AddData() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String phoneNum = phonenumber.getText().toString();
                String a = age.getText().toString();
                String em = email.getText().toString();
                Integer a2 = 0;
                Boolean checked = false;

                if (check.isChecked()) {

                    checked = true;

                }   else {

                    checked = false;

                }

                if (!isInteger(a)) {

                    Toast.makeText(RegisterActivity.this, "Please enter correct number.", Toast.LENGTH_SHORT).show();

                }   else {

                    a2 = Integer.parseInt(a);

                }

                if (!checked) {

                    if (user.equals("") || pass.equals("") || phoneNum.equals("") || a.equals("") || em.equals("")) {

                        Toast.makeText(RegisterActivity.this, "Please enter all the fields.", Toast.LENGTH_SHORT).show();

                    } else {

                        Boolean checkuser = db1.checkusername(user);

                        if (!checkuser) {

                            Boolean insert = db1.insertData(user, pass, a, em, phoneNum);

                            if (insert) {

                                Toast.makeText(RegisterActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                               // startActivity(intent);

                            } else {

                                Toast.makeText(RegisterActivity.this, "Registered failed.", Toast.LENGTH_SHORT).show();

                            }

                        } else {

                            Toast.makeText(RegisterActivity.this, "User already exists! Please login!", Toast.LENGTH_SHORT).show();

                        }

                    }

                }   else {

                    if (user.equals("") || pass.equals("") || phoneNum.equals("") || a.equals("") || em.equals("")) {

                        Toast.makeText(RegisterActivity.this, "Please enter all the fields.", Toast.LENGTH_SHORT).show();

                    } else {

                        Boolean checkuser = db2.checkusername(user);

                        if (!checkuser) {

                            Boolean insert = db2.insertData(user, pass, a, em, phoneNum);

                            if (insert) {

                                Toast.makeText(RegisterActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                //startActivity(intent);

                            } else {

                                Toast.makeText(RegisterActivity.this, "Registered failed.", Toast.LENGTH_SHORT).show();

                            }

                        } else {

                            Toast.makeText(RegisterActivity.this, "User already exists! Please login!", Toast.LENGTH_SHORT).show();

                        }

                    }

                }

            }
        });


    }
    */
}