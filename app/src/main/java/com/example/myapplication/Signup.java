package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {


    EditText signupemail, signuppassword ;
    Button signupbutton ;
    FirebaseAuth mAuth;
    LottieAnimationView supLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        signupemail = findViewById(R.id.signupEmail);
        signuppassword = findViewById(R.id.signupPassword);
        signupbutton = findViewById(R.id.signupBtn);
        supLoad = findViewById(R.id.supLoad);

        mAuth = FirebaseAuth.getInstance();

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supLoad.setVisibility(View.VISIBLE);
                String supEmail = signupemail.getText().toString();
                String suppass = signuppassword.getText().toString();

                if(supEmail.isEmpty())
                {
                    Toast.makeText(Signup.this, "Email Empty", Toast.LENGTH_SHORT).show();
                    supLoad.setVisibility(View.INVISIBLE);
                    return ;
                }
                if(suppass.isEmpty())
                {
                    Toast.makeText(Signup.this, "Password Empty", Toast.LENGTH_SHORT).show();
                    supLoad.setVisibility(View.INVISIBLE);
                    return ;
                }

                mAuth.createUserWithEmailAndPassword(supEmail, suppass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete (@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent gotoSignin = new Intent(getApplicationContext(), Signin.class);
                            startActivity(gotoSignin);
                            supLoad.setVisibility(View.INVISIBLE);
                            finish();
                        } else {
                            Toast.makeText(Signup.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            supLoad.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });

    }
}