package concept.test.app.com.simple;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by Edgard Rendon on 14/11/2016.
 */

public class PermissionActivity extends Activity {

    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    public static final int MY_BLUETOOTH_ENABLE_REQUEST_ID = 2;
    static Boolean permissionEnable;
    static Boolean bluetoothActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissionEnable = Boolean.FALSE;
        bluetoothActive = Boolean.FALSE;
        verifiedPermission();
        verifyBluetooth2();
        if (permissionEnable && bluetoothActive){
            Intent i = new Intent(PermissionActivity.this, MainActivity.class);
            startActivity(i);
        }
        else if (permissionEnable && !bluetoothActive){
            verifyBluetooth2();
        }
    }

    private void verifyBluetooth2(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()){
            Intent i = new Intent(PermissionActivity.this, MainActivity.class);
            startActivity(i);
        }
        else {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, MY_BLUETOOTH_ENABLE_REQUEST_ID);
        }

    }


    private void verifiedPermission (){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android M Permission check
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        PERMISSION_REQUEST_COARSE_LOCATION);
            }
            else {
                permissionEnable = Boolean.TRUE;
            }
        }
    }
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Log.d(TAG, "coarse location permission granted");
                    Intent i = new Intent(PermissionActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Functionality limited");
                    builder.setMessage("Since location access has not been granted, this app will not be able to discover beacons when in the background.");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            finish();
                        }

                    });
                    builder.show();
                }
                return;
            }
            case MY_BLUETOOTH_ENABLE_REQUEST_ID :
                if (requestCode == MY_BLUETOOTH_ENABLE_REQUEST_ID) {
                    if (grantResults[0] == RESULT_OK) {
                        // Request granted - bluetooth is turning on...
                        bluetoothActive = Boolean.TRUE;
                        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                        if (!mBluetoothAdapter.isEnabled()){
                            mBluetoothAdapter.enable();
                        }
                        Intent i = new Intent(PermissionActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                    if (grantResults[0] == RESULT_CANCELED) {
                        // Request denied by user, or an error was encountered while
                        // attempting to enable bluetooth
                        finish();
                    }
                }
        }
    }
}