package com.example.myassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchLocationActivity extends AppCompatActivity {

    EditText etSource, etDestination;
    Button btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);

        etSource = findViewById(R.id.editSourceLocation);
        etDestination = findViewById(R.id.editDestLocation);
        btn_search = findViewById(R.id.buttonSearch);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sSourse = etSource.getText().toString().trim();
                String sDest = etDestination.getText().toString().trim();

                if(sSourse.equals("") && sDest.equals("")){
                    Toast.makeText(getApplicationContext(), "Please fill both text fields", Toast.LENGTH_SHORT).show();
                } else {
                    SearchMap(sSourse, sDest);
                }
            }
        });

    }

    private void SearchMap(String sSource, String sDest) {
        Uri uri = Uri.parse("https://www.google.co.il/maps/dir/" + sSource + "/" + sDest);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}