package com.example.crud;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayStudentsActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_students);

        listView = findViewById(R.id.student_list_view);
        ArrayList<String> studentData = getIntent().getStringArrayListExtra("studentList");
        if (studentData != null && !studentData.isEmpty()) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentData);
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(DisplayStudentsActivity.this,"No Records Found",Toast.LENGTH_SHORT).show();
        }
    }
}
