package com.example.listsensorsapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import the following
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView sensorListView;
    private SensorManager sensorManager;
    private List<Sensor> deviceSensors;
    private ArrayList<String> sensorNames;

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

        //initialize the ListView from activity_main.xml
        sensorListView=findViewById(R.id.sensor_list);

        //initialize SensorManager object to retrieve Sensor Service from System
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //get the list of all sensors
        deviceSensors=sensorManager.getSensorList(Sensor.TYPE_ALL);

        //initialize ArrayList to hold sensor names
        sensorNames=new ArrayList<>();

        //loop through list of sensors and get their names
        //deviceSensors currently hold list of all sensors
        //for each sensor in deviceSensor, call getName() and add it to ArrayList
        for(Sensor sensor:deviceSensors)
        {
            sensorNames.add(sensor.getName());
        }

        //display count: deviceSensors.size();

        //display the list of sensors to the ListView
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,sensorNames);
        sensorListView.setAdapter(adapter);
    }
}