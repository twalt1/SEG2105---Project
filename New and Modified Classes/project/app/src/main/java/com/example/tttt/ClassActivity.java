package com.example.tttt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ClassActivity extends AppCompatActivity {

    Button viewClasses;
    ImageButton back;
    DBHelper3 db3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        viewClasses = findViewById(R.id.classView);
        back = findViewById(R.id.backEdit);
        db3 = new DBHelper3(this);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent);
            }
        });

        viewClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = db3.getAllData();
                if (res.getCount() == 0) {

                    showMessage("Error", "Nothing Found");
                    return;

                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {

                    buffer.append("ID :" + res.getString(0) + "\n");
                    buffer.append("Username :" + res.getString(1) + "\n");
                    buffer.append("Password :" + res.getString(2) + "\n");
                    buffer.append("Email :" + res.getString(3) + "\n");
                    buffer.append("Age :" + res.getString(4) + "\n");
                    buffer.append("Phone Number :" + res.getString(5) + "\n\n");

                }

                showMessage("Data", buffer.toString());

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
