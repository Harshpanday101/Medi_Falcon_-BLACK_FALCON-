package com.example.myapplication;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class Signin extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView gotoSignup ;
    EditText signinemail , signinpass ;
    Button signinBtn ;
    LottieAnimationView sinLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signin);

        gotoSignup = findViewById(R.id.createtext);
        signinBtn = findViewById(R.id.SigninBtn);
        signinemail = findViewById(R.id.signinEmail);
        signinpass = findViewById(R.id.SigninPassword);
        mAuth = FirebaseAuth.getInstance();
        sinLoad = findViewById(R.id.sinLoad);


//        MAVLink Java library
//        Holybro Telemetry Radio



        gotoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoSignupPage = new Intent(getApplicationContext(), Signup.class);
                startActivity(gotoSignupPage);
            }
        });

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sinLoad.setVisibility(View.VISIBLE);
                String sinEmail = signinemail.getText().toString();
                String sinpass = signinpass.getText().toString();
                if (sinEmail.isEmpty())
                {
                    Toast.makeText(Signin.this, "Email Empty", Toast.LENGTH_SHORT).show();
                    sinLoad.setVisibility(View.INVISIBLE);
                    return;
                }
                if(sinpass.isEmpty())
                {
                    Toast.makeText(Signin.this, "Password Empty", Toast.LENGTH_SHORT).show();
                    sinLoad.setVisibility(View.INVISIBLE);
                    return ;
                }

                mAuth.signInWithEmailAndPassword (sinEmail, sinpass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete (@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            Intent gotoHome = new Intent(getApplicationContext(),MainActivity.class);
                            Intent gotoHome = new Intent(getApplicationContext(),Splash1.class);
                            startActivity(gotoHome);
                            sinLoad.setVisibility(View.INVISIBLE);
                            finish();
                        } else {
                            Toast.makeText(Signin.this, "Id/Pass mismatch", Toast.LENGTH_SHORT).show();
                            sinLoad.setVisibility(View.INVISIBLE);
                        }
                    }
                    });
            }
        });

    }
}