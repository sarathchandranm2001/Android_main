package com.example.jsonapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import the following
import android.widget.TextView;
import org.json.JSONObject;
import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    TextView tv1,tv2;
    //json object
    public static final String JSON_STRING="{\"employee\":{\"name\":\"Sachin\",\"salary\":56000}}";
    //json array
    public static final String JSON_ARRAY= "{ \"Employee\" :[{\"id\":101,\"name\":\"Sonoo Jaiswal\",\"salary\":50000}," +
            "{\"id\":102,\"name\":\"Vimal Jaiswal\",\"salary\":60000}] }";

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
        tv1=findViewById(R.id.tv_1);
        tv2=findViewById(R.id.tv_2);
        try {
            JSONObject emp=(new JSONObject(JSON_STRING)).getJSONObject("employee");
            String emp_name=emp.getString("name");
            int emp_salary=emp.getInt("salary");
            //create a string to display data on the text view
            String str= "Employee Name:"+emp_name+ "\n" + "Employee Salary:" +emp_salary;
            tv1.setText(str);

            //JSON Array Parsing
            String data="";
            // Create the root JSONObject from the JSON string
            JSONObject array_obj=(new JSONObject(JSON_ARRAY));
            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray=array_obj.optJSONArray("Employee");
            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                //retrieve data from each object
                int id=jsonObject.getInt("id");
                String name=jsonObject.getString("name");
                int salary=jsonObject.getInt("salary");
                //adding each object to the display data
                data= data+ " Node:"+i+ " ID:" +id+ " Name:"+name+ " Salary:"+salary+ "\n";
            }
            tv2.setText(data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}