package com.example.tttt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class EditClassInstructor extends AppCompatActivity {

    Button viewClasses, cancelClasses, editClass;
    ImageButton back;
    DBClass db3;
    Spinner typeDropDown, difficultyDropDown;
    EditText getClassId, getClassTitle, getClassDescription;
    TextInputEditText capacity, startTime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class_instructor);

        //drop down list to select type of class
        typeDropDown = (Spinner) findViewById(R.id.classType_id2);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.classType));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeDropDown.setAdapter(typeAdapter);

        //drop down list for difficulty
        difficultyDropDown = (Spinner) findViewById(R.id.classDifficulty_id2);
        ArrayAdapter<String> difficultyAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.difficultyType));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultyDropDown.setAdapter(difficultyAdapter);

        capacity = findViewById(R.id.insEnterCap);

        viewClasses = findViewById(R.id.instructorView);
        back = findViewById(R.id.backBtn2);
        cancelClasses = findViewById(R.id.cancelClass);
        editClass = findViewById(R.id.confirmChangesInstructor);
        getClassId = findViewById(R.id.insGetClassID);
        getClassTitle = findViewById(R.id.insGetClassTitle);
        getClassDescription = findViewById(R.id.insGetClassDescr);

        db3 = new DBClass(this);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InstructorActivity.class);
                startActivity(intent);
            }
        });

        cancelClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer deletedRows = db3.deleteData(getClassId.getText().toString());

                if (deletedRows > 0) {

                    Toast.makeText(EditClassInstructor.this, "Data Updated.", Toast.LENGTH_LONG).show();

                }   else {

                    Toast.makeText(EditClassInstructor.this,"Data not Updated", Toast.LENGTH_LONG).show();

                }

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

                    buffer.append("ID : " + res.getString(0) + "\n");
                    buffer.append("Title : " + res.getString(1) + "\n");
                    buffer.append("Type : " + res.getString(2) + "\n");
                    buffer.append("Description : " + res.getString(3) + "\n");
                    buffer.append("Difficulty : " + res.getString(4) + "\n");
                    buffer.append("Capacity : " + res.getString(5) + "\n");
                    buffer.append("Date : " + res.getString(6) + "\n");
                    buffer.append("Time : " + res.getString(7) + "\n\n");

                }

                showMessage("Data", buffer.toString());

            }
        });

        editClass.setOnClickListener(new View.OnClickListener() {
            @Override
            //getClassId, getClassTitle
            public void onClick(View view) {
                try{
                    String classID = getClassId.getText().toString();
                    String type = typeDropDown.getSelectedItem().toString();
                    String diff = difficultyDropDown.getSelectedItem().toString();
                    String cap = capacity.getText().toString();
                    String classDescription = getClassDescription.getText().toString();
                    String classTitle = getClassTitle.getText().toString();
                    if (classTitle.isEmpty() && classDescription.isEmpty()){
                        Toast.makeText(EditClassInstructor.this,"Both update fields cannot be empty", Toast.LENGTH_LONG).show();
                    }
                    else if(classID.isEmpty()){
                        Toast.makeText(EditClassInstructor.this,"Must input a class ID to update", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(EditClassInstructor.this,"Exception occurred", Toast.LENGTH_LONG).show();
                }

                Toast.makeText(EditClassInstructor.this,"Updated Class!", Toast.LENGTH_LONG).show();

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