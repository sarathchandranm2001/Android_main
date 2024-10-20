package com.example.callapp;

import android.Manifest;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import the following
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
import android.Manifest;
import android.content.pm.PackageManager;//permission granting/denying


public class MainActivity extends AppCompatActivity {

    EditText phonenumber;
    //uniquely identify your request
    private static final int REQUEST_CALL_PHONE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        phonenumber=findViewById(R.id.editTextPhone);
    }

    public void call(View v)
    {
        //click on button "call
        //ask for runtime permission from the user to access caller
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)!=
                PackageManager.PERMISSION_GRANTED)
        {
            //request the permission
            ActivityCompat.requestPermissions(this,new String[]
                            {Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PHONE);
        }
        else {
            //permission granted already
            makePhoneCall();
        }
    }
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults)
    {
        //click on allow on the alert box
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode==REQUEST_CALL_PHONE)
        {
            if(grantResults.length >0 && grantResults[0]==
            PackageManager.PERMISSION_GRANTED)
            {
                //granted
                makePhoneCall();
            }
            else
            {
                Toast.makeText(MainActivity.this,"Permission denied",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void makePhoneCall()
    {
        String number=phonenumber.getText().toString();
        Intent call=new Intent(Intent.ACTION_CALL);
        //Intent call=new Intent(Intent.ACTION_DIAL);
        call.setData(Uri.parse("tel:"+number));
        startActivity(call);
    }
}