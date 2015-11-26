
package com.example.batterystatsdemo;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class BatteryStatsActivity extends ActionBarActivity {

    private TextView mCharging;
    private TextView mDisCharging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_stats_demo);
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(receiver, filter);
        mCharging = (TextView) findViewById(R.id.one);
        mDisCharging = (TextView) findViewById(R.id.two);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int plugType = intent.getIntExtra("plugged", -1);
            int status = intent.getIntExtra("status", 0);
            int level = intent.getIntExtra("level", -1);
            Log.i("sunkun", "plugType:" + plugType + "status:" + status + "level" + level);
            BatteryStatsDemo.getInstance().setBatteryState(status, plugType, level,
                    getApplicationContext());
            Log.i("sunkun", "OnBattery--   "
                    + BatteryStatsDemo.getInstance().computeBatteryTimeRemaining()+"\n");
            mDisCharging.setText("OnBattery:   "
                    + BatteryStatsDemo.getInstance().computeBatteryTimeRemaining());
            Log.i("sunkun", "OnCharging---    "
                    + BatteryStatsDemo.getInstance().computeChargeTimeRemaining()+"\n\n");
            mCharging.setText("OnCharging:   "
                    + BatteryStatsDemo.getInstance().computeChargeTimeRemaining());
        }
    };
}
