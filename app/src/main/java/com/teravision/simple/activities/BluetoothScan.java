package com.teravision.simple.activities;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;
import android.widget.TextView;

import com.teravision.simple.R;
import com.teravision.simple.util.BluetoothScanUtil;
import com.teravision.simple.util.GPSTracker;

import java.io.File;

public class BluetoothScan extends AppCompatActivity {

    private BluetoothScanUtil mBluetoothScanUtil;

    public static TextView txt_log;
    public static ScrollView scrollView;

    public static File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_bluetooth_scan);

        txt_log = (TextView) findViewById(R.id.logApplication);
        scrollView = (ScrollView) findViewById(R.id.scrollView);


        File path_file = Environment.getExternalStorageDirectory();

        file = new File(path_file.getAbsolutePath(), "ble_scan.txt");

        scanBLE();
    }

    private void scanBLE() {
        double latitude = 0;
        double longitude = 0;
        GPSTracker gps = new GPSTracker(this);

        // check if GPS enabled
        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            // \n is for new line
            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

        mBluetoothScanUtil = new BluetoothScanUtil(this, latitude, longitude);
        mBluetoothScanUtil.startScan();
    }
}
