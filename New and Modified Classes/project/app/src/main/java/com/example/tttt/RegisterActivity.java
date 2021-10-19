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
    Button btn, btn1;
    ImageButton btn2;
    CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db1 = new DBHelper1(this);
        db2 = new DBHelper2(this);
        username = findViewById(R.id.enterUsername_r);
        password = findViewById(R.id.enterPassword_r);
        email = findViewById(R.id.enterEmail_r);
        age = findViewById(R.id.enterAge_r);
        phonenumber = findViewById(R.id.enterPhoneNum_r);
        btn = findViewById(R.id.register_r);
        btn1 = findViewById(R.id.login_r);
        btn2 = findViewById(R.id.back_button_r);
        check = findViewById(R.id.check);

        AddData();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

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
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

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
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

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
}