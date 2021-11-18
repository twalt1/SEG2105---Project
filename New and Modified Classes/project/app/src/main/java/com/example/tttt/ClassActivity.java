package com.example.tttt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ClassActivity extends AppCompatActivity {

    Button viewClasses, deleteClass, editClass;
    ImageButton back;
    DBClass db3;
    EditText getClassId, getClassTitle, getClassDescription;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        viewClasses = findViewById(R.id.classView);
        back = findViewById(R.id.backEdit);
        deleteClass = findViewById(R.id.classDelete);
        editClass = findViewById(R.id.applyChanges);
        getClassId = findViewById(R.id.getClassID);
        getClassTitle = findViewById(R.id.getClassTitle);
        getClassDescription = findViewById(R.id.getClassDescr);

        db3 = new DBClass(this);



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
                    buffer.append("Title :" + res.getString(1) + "\n");
                    buffer.append("Description :" + res.getString(2) + "\n");
                    buffer.append("Difficulty :" + res.getString(3) + "\n");
                    buffer.append("Capacity :" + res.getString(4) + "\n");
                    buffer.append("Date :" + res.getString(5) + "\n");
                    buffer.append("Time :" + res.getString(6) + "\n");

                }

                showMessage("Data", buffer.toString());

            }
        });

        deleteClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer deletedRows = db3.deleteData(getClassId.getText().toString());

                if (deletedRows > 0) {

                    Toast.makeText(ClassActivity.this, "Data Updated.", Toast.LENGTH_LONG).show();

                }   else {

                    Toast.makeText(ClassActivity.this,"Data not Updated", Toast.LENGTH_LONG).show();

                }

            }
        });

        editClass.setOnClickListener(new View.OnClickListener() {
            @Override
            //getClassId, getClassTitle
            public void onClick(View view) {
                try{
                    String classID = getClassId.getText().toString();
                    String classDescription = getClassDescription.getText().toString();
                    String classTitle = getClassTitle.getText().toString();
                    if (classTitle.isEmpty() && classDescription.isEmpty()){
                        Toast.makeText(ClassActivity.this,"Both update fields cannot be empty", Toast.LENGTH_LONG).show();
                    }
                    else if(classID.isEmpty()){
                        Toast.makeText(ClassActivity.this,"Must input a class ID to update", Toast.LENGTH_LONG).show();
                    }
                    else if(!classDescription.isEmpty() && !classTitle.isEmpty()){
                        db3.updateName(classID, classTitle);
                        db3.updateDescription(classID, classDescription);
                    }
                    else if(!classDescription.isEmpty()){
                        db3.updateDescription(classID, classDescription);
                    }
                    else{
                        db3.updateName(classID, classTitle);
                    }
                }
                catch(Exception e){
                    Toast.makeText(ClassActivity.this,"Exception occurred", Toast.LENGTH_LONG).show();
                }

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
