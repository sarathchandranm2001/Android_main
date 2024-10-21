package com.example.login_register;


import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import the following
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    //declare the edittexts
    private EditText edit1,edit2,edit3;
    //declare an object for DBHandler
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //initialize edittext objects
        edit1=findViewById(R.id.text_name);
        edit2=findViewById(R.id.text_uname);
        edit3=findViewById(R.id.text_pwd);
        //create an object for DBHandler
        dbHandler=new DBHandler(RegisterActivity.this);
    }

    //onclick method for submit button
    public void adduser(View v)
    {
        //retrieve the user input from EditTexts
        String user_name=edit1.getText().toString();
        String user_uname=edit2.getText().toString();
        String user_pwd=edit3.getText().toString();

        //call the addNewUser of DBHandler class
        dbHandler.addNewUser(user_name,user_uname,user_pwd);

        //print a toast
        Toast.makeText(RegisterActivity.this,"NEW USER ADDED",Toast.LENGTH_LONG).show();

        //clear the EditTexts after user is added
        edit1.setText("");
        edit2.setText("");
        edit3.setText("");
    }
    //onclick method for back to home button
    public void backtohome(View v)
    {
        //clicking on BACK TO HOME launches MainActivity
        Intent i = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(i);
    }
}