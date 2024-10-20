package com.example.shakeapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//Import the following
import androidx.constraintlayout.widget.ConstraintLayout;
import android.widget.TextView;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {
    private ShakeDetector shakeDetector;
    static TextView tv;
    static ConstraintLayout layout;

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
        tv=findViewById(R.id.text);
        layout=findViewById(R.id.main);
        shakeDetector = new ShakeDetector(this);
    }
    protected void onResume() {
        super.onResume();
        shakeDetector.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shakeDetector.stop();
    }

    public static void onShake() {
        // Handle shake event, e.g., show a Toast message
        tv.setText("Shake detected");
        tv.setBackgroundColor(Color.GREEN);
        layout.setBackgroundColor(Color.RED);
    }
}