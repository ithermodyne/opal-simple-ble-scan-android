package com.teravision.simple.activities.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.teravision.simple.R;
import com.teravision.simple.util.BluetoothScanUtil;
import com.teravision.simple.util.GPSTracker;

/**
 * Created by Sara Villarreal on 8/3/2016.
 */
public class MainActivity2 extends AppCompatActivity {
    private BluetoothScanUtil mBluetoothScanUtil;
    public static TextView txt_info_foreground, txt_info_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txt_info_foreground = (TextView) findViewById(R.id.txt_info_foreground);
        txt_info_background = (TextView) findViewById(R.id.txt_info_background);
        scanBLE();
    }

    private void scanBLE() {
        double latitude = 0;
        double longitude = 0;
        GPSTracker gps = new GPSTracker(this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            // \n is for new line
            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

        mBluetoothScanUtil = new BluetoothScanUtil(this,latitude,longitude);
        mBluetoothScanUtil.startScan();
    }
}
