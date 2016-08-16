package com.teravision.simple.altbeacon.beacon.reference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.widget.EditText;
import android.widget.TextView;

import com.teravision.simple.R;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;


public class RangingActivity extends Activity implements BeaconConsumer {
    protected static final String TAG = "RangingActivity";
    private BeaconManager beaconManager = BeaconManager.getInstanceForApplication(this);
    private TextView mTxtCountForeground, mTxtCountBackground;
    private String txt_count_foreground, txt_count_background;
    private int txt_count_foreground2=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranging);
        mTxtCountForeground = (TextView) findViewById(R.id.txt_count_foreground);
        mTxtCountBackground = (TextView) findViewById(R.id.txt_count_background);
        Intent i = getIntent();
        txt_count_foreground = String.valueOf(i.getIntExtra("txt_count_foreground", 0));
        txt_count_background = String.valueOf(i.getIntExtra("txt_count_background", 0));

        beaconManager.bind(this);
    }

    @Override 
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override 
    protected void onPause() {
        super.onPause();
        if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(true);
    }

    @Override 
    protected void onResume() {
        super.onResume();
        mTxtCountForeground.setText("FALSE");
        if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(false);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier() {
           @Override
           public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
              if (beacons.size() > 0) {

                 EditText editText = (EditText)RangingActivity.this.findViewById(R.id.rangingText);
                 Beacon firstBeacon = beacons.iterator().next();
                 logToDisplay("The beacon information " + firstBeacon.toString() + " is about " + firstBeacon.getDistance() + " meters away.");
              }
           }

        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {   }
    }

    public void logToDisplay(final String line) {

        runOnUiThread(new Runnable() {
            public void run() {
                EditText editText = (EditText) RangingActivity.this.findViewById(R.id.rangingText);
                editText.append(line + "\n");
                mTxtCountForeground.setText("TRUE");
            }
        });
    }

}
