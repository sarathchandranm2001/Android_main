package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText ed1, ed2, ed3, ed4;
    DBHandler dbHandler;

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

        ed1 = findViewById(R.id.editTextText);
        ed2 = findViewById(R.id.editTextText2);
        ed3 = findViewById(R.id.editTextText3);
        ed4 = findViewById(R.id.editTextText6);
        dbHandler = new DBHandler(MainActivity.this);
    }

    public void addstudent(View view) {
        String student_name = ed1.getText().toString();
        String student_course = ed2.getText().toString();
        int student_sem = Integer.parseInt(ed3.getText().toString());

        dbHandler.addstudent(student_name, student_course, student_sem);
        Toast.makeText(MainActivity.this, "Record Inserted Successfully", Toast.LENGTH_SHORT).show();
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
    }

    public void findstud(View view) {
        String stud_name = ed4.getText().toString();
        Student student = dbHandler.findstud(stud_name);
        if (student != null) {
            ed1.setText(student.getName());
            ed2.setText(student.getCourse());
            ed3.setText(String.valueOf(student.getSemester()));
        } else {
            Toast.makeText(MainActivity.this, "No Records Found", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewAllStudents(View view) {
        List<Student> studentList = dbHandler.getAllStudents();
        ArrayList<String> studentData = new ArrayList<>();

        for (Student student : studentList) {
            String studentInfo = "Name: " + student.getName() + ", Course: " + student.getCourse() + ", Semester: " + student.getSemester();
            studentData.add(studentInfo);
        }

        Intent intent = new Intent(MainActivity.this, DisplayStudentsActivity.class);
        intent.putStringArrayListExtra("studentList", studentData);
        startActivity(intent);
    }
}
