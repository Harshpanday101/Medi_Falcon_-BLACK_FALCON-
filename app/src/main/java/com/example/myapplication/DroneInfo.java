package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DroneInfo extends AppCompatActivity {

    TextView Battery ;
    Button gotohome , dbBtn;
    DatabaseReference fdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_drone_info);
        Battery = findViewById(R.id.batterytxt);
        dbBtn = findViewById(R.id.dbBtn);
        fdb = FirebaseDatabase.getInstance().getReference();

        this.registerReceiver(this.batteryLevelreeciever,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        gotohome  = findViewById(R.id.gotohome);
        gotohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                fdb.child()



//                Toast.makeText(DroneInfo.this, "work in progress", Toast.LENGTH_SHORT).show();
                Intent gotoWip = new Intent(getApplicationContext(), Wip.class);
                startActivity(gotoWip);
            }
        });



    }
    private BroadcastReceiver batteryLevelreeciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level =     intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
            Battery.setText("Battery : "+Integer.toString(level)+"%");
        }
    };
}