package com.forbitbd.batteryindicator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ProgressBar progressBar;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.battery_level);
        progressBar = findViewById(R.id.progressbar);

        batteryLevel();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void batteryLevel() {
       broadcastReceiver = new BroadcastReceiver() {
           @Override
           public void onReceive(Context context, Intent intent) {
               int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
               textView.setText("Battery Level Remaining:" +Integer.toString(level)+ "%");
               progressBar.setProgress(level);
           }
       };
    }
}