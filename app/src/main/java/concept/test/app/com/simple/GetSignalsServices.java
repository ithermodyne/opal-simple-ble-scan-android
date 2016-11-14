package concept.test.app.com.simple;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.ParcelUuid;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class GetSignalsServices extends IntentService {

    public BluetoothLeScanner mLEScanner;
    private BluetoothAdapter mBluetoothAdapter;
    private List<ScanFilter> filters;

    private ScanCallback mScanCallback;
    private ScanSettings settings;

    private static byte[] lastAdvertisementData;

    public GetSignalsServices(){
        super("GetSignalServices");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initializeBleScannerForSDK21();
        runScanning();
        return Service.START_STICKY;
    }


    /**
     * Initialization of all Android Ble components neccesary to scan a ble device
     * <p>
     * Only for a Android Build Version 21 +|
     */
    @TargetApi(21)
    public void initializeBleScannerForSDK21() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBluetoothAdapter = ((BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter();
            if (mBluetoothAdapter != null) {
                mLEScanner = mBluetoothAdapter.getBluetoothLeScanner();
                settings = new ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_POWER).build();
                filters = new ArrayList<>();
                mScanCallback = new ScanCallback() {
                    @Override
                    public void onScanResult(int callbackType, ScanResult result) {
                        if(result.getScanRecord().getManufacturerSpecificData().get(6666) != null && SingleSignalFragment.txt_single_signal_log != null && AllSignalsFragment.txt_all_signals_log != null) {
                            new ThreadLE(result).run();
                        }
                    }

                    @Override
                    public void onBatchScanResults(List<ScanResult> results) {
                        for(ScanResult scanResult : results) {
                            Log.i("Checking", " onBatchScanResults " + scanResult.describeContents());
                        }
                    }

                    @Override
                    public void onScanFailed(int errorCode) {
                        Log.i("Checking", " onScanFailed " + errorCode);
                    }
                };
            }
        }
    }

    /**
     * perform the best android way to run scan ble devices
     */

    public void runScanning() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
                UUID uuid = UUID.fromString("00000a18-0000-1000-8000-00805f9b34fb");
                ParcelUuid puuid = new ParcelUuid(uuid);
                ScanFilter filter = new ScanFilter.Builder().setServiceUuid(puuid).build();
                filters.add(filter);
                mLEScanner.startScan(filters, settings, mScanCallback);
            }
        }

    }

    public static int count = 0;

    class ThreadLE implements Runnable {

        final ScanResult scanResult;

        public ThreadLE(ScanResult scanResult) {
            this.scanResult = scanResult;
        }

        @Override
        public void run() {
            int sn = 0;
            double capacitor = 0;
            double battery = 0;

            byte[] advertisementData;
            String logDevice;
            String logSingle;

            advertisementData = scanResult.getScanRecord().getManufacturerSpecificData().get(6666);

            if(!Arrays.equals(advertisementData,lastAdvertisementData)){

                count++;
                sn = Utils.byteArray4ToInt(advertisementData, 0);
                capacitor = Utils.hexToVoltaje(advertisementData[4], advertisementData[5]);
                battery = Utils.hexToVoltaje(advertisementData[6], advertisementData[7]);

                SingleSignalFragment.txt_single_signal_log.setText("");
                logDevice = "# "+count+ ". \nDevice = "+scanResult.toString()+"\n";

                logSingle = "Device = "+scanResult.toString()+"\n";

                AllSignalsFragment.txt_all_signals_log.append(logDevice);

                SingleSignalFragment.txt_single_signal_log.append(logSingle);

                logDevice = "------------\nParse = Serial Number:"+sn+" Capacitor: "+capacitor+" Battery: "+battery+"\n\n";

                AllSignalsFragment.txt_all_signals_log.append(logDevice);
                SingleSignalFragment.txt_single_signal_log.append(logDevice);
                AllSignalsFragment.scrollView.fullScroll(View.FOCUS_DOWN);

                lastAdvertisementData = advertisementData;
            }
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}