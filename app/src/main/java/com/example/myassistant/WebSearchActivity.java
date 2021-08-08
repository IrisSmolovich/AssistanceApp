package com.example.myassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WebSearchActivity extends AppCompatActivity {

    Button btn_search;
    EditText et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_search);

        btn_search = findViewById(R.id.btn_searchWeb);
        et_search = findViewById(R.id.editTextSearch);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchWords = et_search.getText().toString().trim();
                if(!searchWords.equals("")){
                    searchWeb(searchWords);
                }
            }
        });
    }

    // Search using default search application
    private void searchWeb(String words){
        try {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, words);
            startActivity(intent);
        }catch (ActivityNotFoundException e) {
            e.printStackTrace();
            searchWebCompat(words);
        }

    }

    // if no default application is found, search through browser
    private void searchWebCompat(String words){
        try {
            Uri uri = Uri.parse("http://www.google.com/#q=" + words);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

    }
}