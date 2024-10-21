package com.example.avgandsum;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
//additional imports
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;


public class MainActivity extends AppCompatActivity {
    public EditText e1,e2,e3;
    int a,b,c,sum,avg,maxim;

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
    public void submit(View view){
        e1=findViewById(R.id.editTextText);
        e2=findViewById(R.id.editTextText2);
        e3=findViewById(R.id.editTextText3);
        a=Integer.parseInt(e1.getText().toString());
        b=Integer.parseInt(e2.getText().toString());
        c=Integer.parseInt(e3.getText().toString());
        sum=a+b+c;
        avg=sum/3;
        if (a>b&&a>c){
            maxim=a;
        } else if (b>a&&b>c) {
            maxim=b;

        }
        else {
            maxim=c;
        }

        Intent i=new Intent(MainActivity.this,Disp_Sum_Avg.class);
        i.putExtra("d",sum);
        i.putExtra("f",avg);
        i.putExtra("g",maxim);
        Toast.makeText(this,"Calculating sum",Toast.LENGTH_SHORT).show();
        startActivity(i);

    }
    public void alertbox(View view){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Alert").setMessage("Do you Want to Contine");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

    }

}