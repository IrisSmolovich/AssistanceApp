package com.example.myassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button buttonContacts, buttonSendmail, button_exitapp, buttonMap, buttonAlarm, buttonNewContact, buttonWeb, btn_capture;
    ImageView iv_translate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadLocale();

        button_exitapp = findViewById(R.id.buttonExit);
        buttonContacts = findViewById(R.id.buttonDialContact); // Action Dial
        buttonMap = findViewById(R.id.buttonMap); // Action View
        buttonSendmail = findViewById(R.id.buttonSendMail); // Action Send
        buttonAlarm = findViewById(R.id.buttonSetAlarm); // Action Set Alarm
        buttonNewContact =findViewById(R.id.buttonNewContacts); // Action Insert
        buttonWeb = findViewById(R.id.buttonSearchWeb); // Action Web Search
        btn_capture = findViewById(R.id.buttonCapture); // Action Image Capture

        iv_translate = findViewById(R.id.iv_translation);


        buttonAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SetAlarmActivity.class);
                startActivity(intent);
            }
        });

        buttonSendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SendMailActivity.class);
                startActivity(intent);
            }
        });

        buttonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebSearchActivity.class);
                startActivity(intent);
            }
        });

        buttonContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, DialActivity.class);
               startActivity(intent);
            }
        });


        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchLocationActivity.class);
                startActivity(intent);
            }
        });

        btn_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CameraPictureActivity.class);
                startActivity(intent);
            }
        });

        buttonNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateContactActivity.class);
                startActivity(intent);
            }
        });


        button_exitapp.setOnClickListener(v -> {
             moveTaskToBack(true);
             android.os.Process.killProcess(android.os.Process.myPid());
             System.exit(1);
        });


        iv_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* show popup window (dialog) to display list of languages, the user will pick its preferable one */
                showChangeLangDialog();
            }
        });
    }


    /* display list of all possible languages */
    public void showChangeLangDialog () {
        final String[] listItems = {"English", "עברית"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(getString(R.string.choose_lang));
        builder.setSingleChoiceItems(listItems, -1, (dialog, i) -> {
            if (i == 0) {
                setLocale("en");
                recreate();
            } else if (i == 1) {
                setLocale("iw");
                recreate();
            }
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setLocale (String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        /* safe data to shared preferences so in next use the chosen language will automatically by applied */
        SharedPreferences.Editor editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
        editor.putString("My_Language", lang);
        editor.apply();
    }

    /* load language saved in Shared Preferences */
    public void loadLocale () {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Language", "");
        setLocale(language);
    }
}