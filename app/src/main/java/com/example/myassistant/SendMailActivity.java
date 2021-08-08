package com.example.myassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SendMailActivity extends AppCompatActivity {

    Button btn_send;
    EditText et_to, et_subject, et_message;
//    TextView tv_to, tv_subject, tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);

        btn_send = findViewById(R.id.buttonSendMail);
        et_to = findViewById(R.id.editTextTo);
        et_message = findViewById(R.id.editTextMessage);
        et_subject = findViewById(R.id.editTextSubject);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }

    public void sendMail(){
        String recipientsList = et_to.getText().toString();
        String[] recipients = recipientsList.split(",");

        String subject = et_subject.getText().toString();
        String message = et_message.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email sending option"));

    }
}