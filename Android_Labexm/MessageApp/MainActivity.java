package com.example.messageapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.EditText;
import android.content.pm.PackageManager;
import android.Manifest;
import android.widget.Toast;
import android.telephony.SmsManager;

public class MainActivity extends AppCompatActivity {

    EditText phonenumber,msg;
    private static final int SMS_PERMISSION_CODE=1;

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
        msg=findViewById(R.id.editTextText);
    }
    public void send(View v)
    {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)!=
                PackageManager.PERMISSION_GRANTED)
        {
            //request the permission
            ActivityCompat.requestPermissions(this,new String[]
                            {Manifest.permission.SEND_SMS},
                    SMS_PERMISSION_CODE);
        }
        else
        {
            sendSms();
        }
    }
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions,
                grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                sendSms();
            } else {
                Toast.makeText(MainActivity.this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
        private void sendSms()
        {
            String number=phonenumber.getText().toString();
            String message=msg.getText().toString();

            //Sent an sms
            SmsManager sms= SmsManager.getDefault();
            sms.sendTextMessage(number,null,message,null,null);
            Toast.makeText(MainActivity.this,"SMS SENT",Toast.LENGTH_SHORT).show();
        }
}