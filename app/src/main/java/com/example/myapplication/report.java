package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class report extends AppCompatActivity {



    Button send ;
    EditText recieverEmail , subject , msgContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_report);
        msgContent = findViewById(R.id.msg_content);
        recieverEmail = findViewById(R.id.recieverEmail);
        subject = findViewById(R.id.subject);

        send = findViewById(R.id.sendbtn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(subject.getText().toString().isEmpty() || msgContent.getText().toString().isEmpty() || recieverEmail.getText().toString().isEmpty())
                    Toast.makeText(report.this, "Field's empty", Toast.LENGTH_SHORT).show();
                else
                    sendEmail(subject.getText().toString(),msgContent.getText().toString(),recieverEmail.getText().toString());
            }
        });


    }
    public void sendEmail (String subject, String content, String to_email){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to_email});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setType("message/rfc822");
        startActivity (Intent.createChooser (intent, "Choose email client:"));
    }
}