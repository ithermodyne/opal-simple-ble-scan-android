package com.teravision.simple.activities.home;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.teravision.simple.R;
import com.teravision.simple.altbeacon.beacon.reference.RangingActivity;

import org.altbeacon.beacon.BeaconManager;

/**
 * Created by Sara Villarreal on 7/19/2016.
 */
public class HomeActivity extends MainActivity{

    protected static final String TAG = "HomeActivity";
    private ImageView mImageBrand;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    private static int txt_count_foreground = 0;
    private static int txt_count_background = 0;
    private static boolean isBackground = Boolean.FALSE;
    public static boolean isForeground = Boolean.FALSE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_details);
        isForeground = Boolean.FALSE;

        Intent i = getIntent();
        isBackground = i.getBooleanExtra("isBackground", Boolean.FALSE);
        if (isBackground) {
            txt_count_background = txt_count_background + 1;
        }
        else {
            isForeground = Boolean.TRUE;
        }

        verifyBluetooth();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android M Permission check
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle("This app needs location access");
                builder.setMessage("Please grant location access so this app can detect beacons in the background.");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                    @TargetApi(23)
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                PERMISSION_REQUEST_COARSE_LOCATION);
                    }

                });
                builder.show();
            }
        }
        mImageBrand = (ImageView) findViewById(R.id.brandDetailImageView);
        mImageBrand.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {


                Intent i = new Intent(getApplicationContext(), RangingActivity.class);

                i.putExtra("txt_count_foreground", txt_count_foreground);
                i.putExtra("txt_count_background", txt_count_background);
                startActivity(i);
            }
        });

    }


    private void verifyBluetooth() {

        try {
            if (!BeaconManager.getInstanceForApplication(this).checkAvailability()) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Bluetooth not enabled");
                builder.setMessage("Please enable bluetooth in settings");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {

                        //System.exit(0);
                    }
                });
                builder.show();
            }
        }
        catch (RuntimeException e) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Bluetooth LE not available");
            builder.setMessage("Sorry, this device does not support Bluetooth LE.");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                    System.exit(0);
                }

            });
            builder.show();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "coarse location permission granted");
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                    builder.setTitle("Functionality limited");
                    builder.setMessage("Since location access has not been granted, this app will not be able to discover beacons when in the background.");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                        @Override
                        public void onDismiss(DialogInterface dialog) {
                        }

                    });
                    builder.show();
                }
                return;
            }
        }
        Intent i = new Intent(getApplicationContext(), RangingActivity.class);
        i.putExtra("txt_count_foreground", txt_count_foreground);
        i.putExtra("txt_count_background", txt_count_background);
        startActivity(i);

    }


    @Override
    public void onResume() {
        super.onResume();
        //((BeaconReferenceApplication) this.getApplicationContext()).setRangingActivity(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        //((BeaconReferenceApplication) this.getApplicationContext()).setMonitoringActivity(null);
    }
}
