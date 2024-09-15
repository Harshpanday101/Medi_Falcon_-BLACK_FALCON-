package com.example.myapplication;

import android.animation.Animator;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.opengl. GLSurfaceView;
import android.opengl. GLSurfaceView.Renderer;
import android.opengl. GLU;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax. microedition.khronos.egl.EGLConfig;
import javax. microedition.khronos.opengles. GL10;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar ;
    DrawerLayout drawerLayout ;
    NavigationView navigationView ;
    ImageView info, share, errors;
    LottieAnimationView tickanim;

    TextView tv1key, tv1data, tv2key, tv2data, tv3key, tv3data, tv4key, tv4data;
    TextView tv5key, tv5data, tv6key, tv6data, tv7key, tv7data, tv8key, tv8data;
    TextView tv9key, tv9data;

    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tickanim = findViewById(R.id.tickanim);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        // Initialize the TextView references for key and data
        tv1key = findViewById(R.id.tv1key);
        tv1data = findViewById(R.id.tv1data);
        tv2key = findViewById(R.id.tv2key);
        tv2data = findViewById(R.id.tv2data);
        tv3key = findViewById(R.id.tv3key);
        tv3data = findViewById(R.id.tv3data);
        tv4key = findViewById(R.id.tv4key);
        tv4data = findViewById(R.id.tv4data);
        tv5key = findViewById(R.id.tv5key);
        tv5data = findViewById(R.id.tv5data);
        tv6key = findViewById(R.id.tv6key);
        tv6data = findViewById(R.id.tv6data);
        tv7key = findViewById(R.id.tv7key);
        tv7data = findViewById(R.id.tv7data);
        tv8key = findViewById(R.id.tv8key);
        tv8data = findViewById(R.id.tv8data);
        tv9key = findViewById(R.id.tv9key);
        tv9data = findViewById(R.id.tv9data);

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigration_open,R.string.navigration_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        info = findViewById(R.id.info);
        share = findViewById(R.id.share);
        errors = findViewById(R.id.errors);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoInfo = new Intent(getApplicationContext(),DroneInfo.class);
                startActivity(gotoInfo);
            }
        });
        errors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoreport = new Intent(getApplicationContext(), report.class);
                startActivity(gotoreport);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dir = getCurrentTime() ;
                databaseReference.child(dir).child(tv1key.getText().toString()).setValue(tv1data.getText().toString());
                databaseReference.child(dir).child(tv2key.getText().toString()).setValue(tv2data.getText().toString());
                databaseReference.child(dir).child(tv3key.getText().toString()).setValue(tv3data.getText().toString());
                databaseReference.child(dir).child(tv4key.getText().toString()).setValue(tv4data.getText().toString());
                databaseReference.child(dir).child(tv5key.getText().toString()).setValue(tv5data.getText().toString());
                databaseReference.child(dir).child(tv6key.getText().toString()).setValue(tv6data.getText().toString());
                databaseReference.child(dir).child(tv7key.getText().toString()).setValue(tv7data.getText().toString());
                databaseReference.child(dir).child(tv8key.getText().toString()).setValue(tv8data.getText().toString());
                databaseReference.child(dir).child(tv9key.getText().toString()).setValue(tv9data.getText().toString());

                tickanim.setVisibility(View.VISIBLE);
                tickanim.playAnimation();


            }
        });

        // Add a listener to detect when the animation ends
        tickanim.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                tickanim.setVisibility(View.VISIBLE);
                // Optional: Do something when the animation starts
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // Set visibility to invisible when the animation ends
                tickanim.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // Optional: Handle animation cancel
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // Optional: Handle animation repeat
            }
        });


    }
    public static String getCurrentTime() {
        // Define the date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        // Get current date and time
        Date now = new Date();
        // Return the formatted time
        return sdf.format(now);
    }




}