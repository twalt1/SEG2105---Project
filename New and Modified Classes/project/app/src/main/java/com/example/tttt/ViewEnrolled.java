package com.example.tttt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewEnrolled extends AppCompatActivity {

    DBClass db = new DBClass(this);
    DBUser uDb = new DBUser(this);
    EditText txt;
    Button btn;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_enrolled);

        btn = findViewById(R.id.findMembers);
        txt = findViewById(R.id.classIDtoenter);
        back = findViewById(R.id.viewEnrolledBack);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                SharedPreferences currentUserSession = getApplicationContext().getSharedPreferences("currentUserSession", Context.MODE_PRIVATE);
                String email = currentUserSession.getString("email", "");

                Cursor res = db.getAllData();
                if (res.getCount() == 0) {

                    showMessage("Error", "No classes are scheduled yet");
                    return;
                }
                if (txt.getText().toString() == null){

                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    if (res.getString(8).equals(email)) {
                        showMessage("Members Enrolled", res.getString(10));
                        return;
                    }

                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), InstructorActivity.class);
                startActivity(intent);

            }
        });
    }

    public void showMessage (String title, String Message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
}