package com.example.tttt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class UserSearchActivity extends AppCompatActivity {

    DBClass db;
    TextInputEditText searchByTitle;
    Button searchByT, searchByD;
    ImageButton back;
    String classTitle, classDOW;
    Spinner searchByDOW;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);

        searchByDOW = (Spinner) findViewById(R.id.sbdow);
        ArrayAdapter<String> dayOfWeekAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.dayOfWeek));
        dayOfWeekAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchByDOW.setAdapter(dayOfWeekAdapter);

        searchByTitle = findViewById(R.id.sbn);
        back = findViewById(R.id.backtouser);
        searchByT = findViewById(R.id.search1);
        searchByD = findViewById(R.id.search2);
        db = new DBClass(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(intent);

            }
        });

        searchByD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                classDOW = searchByDOW.getSelectedItem().toString();

                if (classDOW.isEmpty()) {

                    Toast.makeText(UserSearchActivity.this, "Please select the day of week for the class", Toast.LENGTH_SHORT).show();

                }

                Cursor cursor = db.searchByDOW(classDOW);

                StringBuffer buffer = new StringBuffer();

                if ((cursor.getCount() == 0) && !classDOW.isEmpty()) {

                    showMessage("Error", "There is no class.");

                }

                while(cursor.moveToNext()) {

                    buffer.append("ID :" + cursor.getString(0) + "\n");
                    buffer.append("Title :" + cursor.getString(1) + "\n");
                    buffer.append(" Type:" + cursor.getString(2) + "\n");
                    buffer.append("Description :" + cursor.getString(3) + "\n");
                    buffer.append("Difficulty :" + cursor.getString(4) + "\n");
                    buffer.append("Capacity :" + cursor.getString(5) + "\n");
                    buffer.append("Date :" + cursor.getString(6) + "\n");
                    buffer.append("Time :" + cursor.getString(7)+"\n");
                    buffer.append("Instructor Email :" + cursor.getString(8)+"\n");
                    buffer.append("Day of Week :" + cursor.getString(9)+"\n\n");

                }

                showMessage("Classes", buffer.toString());

            }
        });

        searchByT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                classTitle = searchByTitle.getText().toString();

                if (classTitle.isEmpty()) {

                    Toast.makeText(UserSearchActivity.this, "Please select the day of week for the class", Toast.LENGTH_SHORT).show();

                }
                //Toast.makeText(UserSearchActivity.this, classTitle, Toast.LENGTH_SHORT).show();

                Cursor cursor = db.searchByTitle(classTitle);

                StringBuffer buffer = new StringBuffer();

                if ((cursor.getCount() == 0) && !classTitle.isEmpty()) {

                    showMessage("Error", "There is no class.");

                }

                while(cursor.moveToNext()) {

                    buffer.append("ID :" + cursor.getString(0) + "\n");
                    buffer.append("Title :" + cursor.getString(1) + "\n");
                    buffer.append(" Type:" + cursor.getString(2) + "\n");
                    buffer.append("Description :" + cursor.getString(3) + "\n");
                    buffer.append("Difficulty :" + cursor.getString(4) + "\n");
                    buffer.append("Capacity :" + cursor.getString(5) + "\n");
                    buffer.append("Date :" + cursor.getString(6) + "\n");
                    buffer.append("Time :" + cursor.getString(7)+"\n");
                    buffer.append("Instructor Email :" + cursor.getString(8)+"\n");
                    buffer.append("Day of Week :" + cursor.getString(9)+"\n\n");

                }

                showMessage("Classes", buffer.toString());

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
