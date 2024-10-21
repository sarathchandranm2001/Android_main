package com.example.avgandsum;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//additional imports
import android.view.View;
import android.widget.TextView;
public class Disp_Sum_Avg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_disp_sum_avg);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bundle e=getIntent().getExtras();
        if(e!=null){
            TextView t1=findViewById(R.id.textView);
            TextView t2=findViewById(R.id.textView2);
            TextView t3=findViewById(R.id.textView3);
            int d=e.getInt("d");
            int f=e.getInt("f");
            int g=e.getInt("g");
            t1.setText("Sum is "+d);
            t2.setText("average is"+f);
            t3.setText("Max is :"+g);
        }
    }
}