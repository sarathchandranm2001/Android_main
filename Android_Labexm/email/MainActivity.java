package com.example.email;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

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
    }
    public void sendEmail(View v)
    {
        Log.i("start email:","");
        //array storing all recipients; TO and CC
        String[] TO={};
        String[] CC={};
        //IMPLICIT INTENT FOR SENDING EMAIL
        Intent email=new Intent(Intent.ACTION_SEND);
        email.setData(Uri.parse("mailto:"));
        email.setType("text/plain");
        //email address of recipient
        email.putExtra(Intent.EXTRA_EMAIL,TO);
        //email address of cc recipients
        email.putExtra(Intent.EXTRA_CC,CC);
        //insert subject
        email.putExtra(Intent.EXTRA_SUBJECT,"Your Subject");
        //insert the text message
        email.putExtra(Intent.EXTRA_TEXT,"Email message content is");
        try {
            startActivity(Intent.createChooser(email,"Select an app"));
            finish();
            Log.i("Finished sending email:","");
        }
        catch (android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(MainActivity.this,
                    "there is no email client installed",
                    Toast.LENGTH_SHORT).show();
        }
    }
}