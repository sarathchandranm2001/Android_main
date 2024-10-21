package com.example.loginapp;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private EditText edit1, edit2, edit3;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize EditText fields and DBHandler


        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edit2 = findViewById(R.id.unam);
        edit3 = findViewById(R.id.pass);
        dbHandler = new DBHandler(Register.this);

    }

    public void submit(View v)
    {
        //retrieve the user input from EditTexts
        String user_uname=edit2.getText().toString();
        String user_pwd=edit3.getText().toString();

        //call the addNewUser of DBHandler class
        dbHandler.insert(user_uname,user_pwd);

        //print a toast
        Toast.makeText(Register.this,"NEW USER ADDED",Toast.LENGTH_LONG).show();

        //clear the EditTexts after user is added
        edit1.setText("");
        edit2.setText("");
        edit3.setText("");
    }
}
