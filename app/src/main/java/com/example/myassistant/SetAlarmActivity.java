package com.example.myassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class SetAlarmActivity extends AppCompatActivity {

    Button btn_setTime, btn_setAlarm;
    EditText et_hour, et_minute;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour, currentMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        btn_setAlarm = findViewById(R.id.btn_setAlarm);
        btn_setTime = findViewById(R.id.btn_setTime);
        et_hour = findViewById(R.id.editTextHour);
        et_minute = findViewById(R.id.editTextMinute);

        btn_setTime.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(SetAlarmActivity.this, (timePicker, hourOfDay, minute) -> {

                    et_hour.setText(String.format("%02d", hourOfDay));
                    et_minute.setText(String.format("%02d", minute));

                }, currentHour, currentMinute, false);
            timePickerDialog.show();
            }
        });

        btn_setAlarm.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View v) {
                if(!et_hour.getText().toString().isEmpty() && !et_minute.getText().toString().isEmpty()){ //check that time set is not empty
                    
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(et_hour.getText().toString()));
                    intent.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(et_minute.getText().toString()));
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Set your alarm clock description");
                    if(intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else{
                        Toast.makeText(SetAlarmActivity.this, "Clock app is missing from this device, please check", Toast.LENGTH_SHORT).show(); //check if device had alarm clock before start activity
                    }
                }else {
                    Toast.makeText(SetAlarmActivity.this, "Please pick time", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}