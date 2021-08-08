package com.example.myassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateContactActivity extends AppCompatActivity {

    Button btn_createContact;
    EditText et_name, et_email, et_number, et_company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        btn_createContact = findViewById(R.id.btn_createContact);
        et_name = findViewById(R.id.editTextName);
        et_email = findViewById(R.id.editTextEmail);
        et_number = findViewById(R.id.editTextPhoneNum);
        et_company = findViewById(R.id.editTextCompany);

        btn_createContact.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View v) {
                if(!et_name.getText().toString().isEmpty() && !et_email.getText().toString().isEmpty() && !et_number.getText().toString().isEmpty() && !et_company.getText().toString().isEmpty()){

                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, et_name.getText().toString());
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, et_number.getText().toString());
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, et_email.getText().toString());
                    intent.putExtra(ContactsContract.Intents.Insert.COMPANY, et_company.getText().toString());

                    if(intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    }else{
                        Toast.makeText(CreateContactActivity.this, "Please make sure you have an app supports this action", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(CreateContactActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}