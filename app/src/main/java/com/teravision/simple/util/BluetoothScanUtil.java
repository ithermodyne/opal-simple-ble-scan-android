package com.teravision.simple.util;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.teravision.simple.R;
import com.teravision.simple.activities.BluetoothScan;
import com.teravision.simple.activities.home.MainActivity;
import com.teravision.simple.adapters.LeDeviceListAdapter;
import com.teravision.simple.bean.APIResponse;
import com.teravision.simple.bean.UserActivity;
import com.teravision.simple.model.BluetoothLeDeviceStore;
import com.teravision.simple.urls.UrlBle;
import com.teravision.simple.util.parser.DiscovererBeaconUUID;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.UUID;

import uk.co.alt236.bluetoothlelib.device.BluetoothLeDevice;
import uk.co.alt236.easycursor.objectcursor.EasyObjectCursor;

/**
 * Created by Javier Diaz on 28/07/2016.
 */
public class BluetoothScanUtil {

    private BluetoothUtils mBluetoothUtils;
    private BluetoothLeScanner mScanner;
    private LeDeviceListAdapter mLeDeviceListAdapter;
    private BluetoothLeDeviceStore mDeviceStore;
    private Activity mContext;
    public EasyObjectCursor<BluetoothLeDevice>  cursorBleData;
    private Toast mToast;
    public byte[] advertisementData;
    public static UUID uuid = null;
    private double latitud,longitud;
    private static String manufacter;
    private static final String TAG = "BluetoothScanUtil";
    int count_foreground = 0;
    int count_background = 0;
    public BluetoothScanUtil(Activity mContext, double latitud, double longitud) {
        this.mContext = mContext;
        mDeviceStore = new BluetoothLeDeviceStore();
        mBluetoothUtils = new BluetoothUtils(mContext);
        mScanner = new BluetoothLeScanner(mLeScanCallback, mBluetoothUtils);
        this.latitud = latitud;
        this.longitud = longitud;
        manufacter = "takeit";
    }

    static int count = 0;



    public final BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {

            final BluetoothLeDevice deviceLe = new BluetoothLeDevice(device, rssi, scanRecord, System.currentTimeMillis());
            mDeviceStore.addDevice(deviceLe);
            String uid = "";
            if (uuid != null) {
                uid = uuid.toString();
            }
            final String finalUid = uid;

            mContext.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int sn = 0;
                    double capacitor = 0;
                    double battery = 0;
                    String logDevice = "";
                    String logParse = "";

                    for(BluetoothLeDevice item : mDeviceStore.getDeviceList()){
                        uuid = DiscovererBeaconUUID.parseAdvertisementPacketToGetUUID(mDeviceStore.getDeviceList().get(0).getScanRecord());
                        advertisementData = deviceLe.getScanRecord();

                        if (advertisementData != null && advertisementData[2] == 0x0a && advertisementData[3] == 0x1a) {

                            sn = Utils.byteArray4ToInt(advertisementData, 4);
                            if(Utils.isAdvertisementAvailableToSend(Integer.valueOf(sn),advertisementData)){
                                capacitor = Utils.hexToVoltaje(advertisementData[8],advertisementData[9]);

                                battery = Utils.hexToVoltaje(advertisementData[10],advertisementData[11]);

                                MainActivity.setlogs(finalUid.toString(), sn, capacitor, battery);

                                UserActivity userActivity = new UserActivity();
                                userActivity.setSerialNumber(String.valueOf(sn));
                                userActivity.setCreatedTimeUser(String.valueOf(System.currentTimeMillis()));
                                userActivity.setvCapacitor(capacitor);
                                userActivity.setvBattery(battery);
                                userActivity.setLattitude(latitud);
                                userActivity.setLongitude(longitud);
                                userActivity.setPhoneMake(Build.MANUFACTURER);
                                userActivity.setPhoneModel(Build.MODEL);
                                userActivity.setOsMake("ANDROID");
                                userActivity.setOsVersion(Build.VERSION.RELEASE);

                                count++;
                                logDevice = "# "+count+". [Device] / "+item.toString()+"\n";


                                BluetoothScan.txt_log.append(logDevice);
                                logParse = "# "+count+". [Parse] / Serial Number:"+sn+" Capacitor: "+capacitor+" Battery: "+battery+"\n\n";
                                BluetoothScan.txt_log.append(logParse);
                                BluetoothScan.scrollView.fullScroll(View.FOCUS_DOWN);

                                Log.d("BluetoothScan",logDevice);
                                Log.d("BluetoothScan",logParse);


                                try
                                {
                                    File path_file = Environment.getExternalStorageDirectory();
                                    Writer writer = new BufferedWriter(new FileWriter(path_file.getAbsolutePath()+"/ble_scan.txt", true));

                                    writer.append(logDevice);
                                    writer.append(logParse);
                                    writer.close();
                                }
                                catch (Exception ex)
                                {
                                    Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
                                }


                            }
                        }
                    }
                }
            });

        }

        private String capitalize(String str) {
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            char[] arr = str.toCharArray();
            boolean capitalizeNext = true;

//        String phrase = "";
            StringBuilder phrase = new StringBuilder();
            for (char c : arr) {
                if (capitalizeNext && Character.isLetter(c)) {
//                phrase += Character.toUpperCase(c);
                    phrase.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                    continue;
                } else if (Character.isWhitespace(c)) {
                    capitalizeNext = true;
                }
//            phrase += c;
                phrase.append(c);
            }

            return phrase.toString();
        }

        /**
         *
         */
        private void savedDataBle(final String jsonObject) {
            Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Type responseType = new TypeToken<APIResponse<UserActivity>>() {
                    }.getType();

                }
            };
            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(mContext, "NOT SAVE IN DATA BASE", Toast.LENGTH_LONG).show();
                }
            };
            UrlBle.saveDataBle(mContext, jsonObject, responseListener, errorListener);
        }





    };

    public void startScan() {
        final boolean mIsBluetoothOn = mBluetoothUtils.isBluetoothOn();
        final boolean mIsBluetoothLePresent = mBluetoothUtils.isBluetoothLeSupported();
        mDeviceStore.clear();


        //mLeDeviceListAdapter = new LeDeviceListAdapter(this, mDeviceStore.getDeviceCursor());

        cursorBleData = mDeviceStore.getDeviceCursor();
        mBluetoothUtils.askUserToEnableBluetoothIfNeeded();
        if (mIsBluetoothOn && mIsBluetoothLePresent) {
            mScanner.scanLeDevice(-1, true);
            //  invalidateOptionsMenu();
        }


    }



    public void displayToast(Context caller, String toastMsg) {

        try {// try-catch to avoid stupid app crashes
            LayoutInflater inflater = LayoutInflater.from(caller);

            View mainLayout = inflater.inflate(R.layout.toast_layout, null);
            View rootLayout = mainLayout.findViewById(R.id.toast_layout_root);

            ImageView image = (ImageView) mainLayout.findViewById(R.id.image);
            image.setImageResource(R.drawable.icon_user);
            TextView text = (TextView) mainLayout.findViewById(R.id.text);
            text.setText(toastMsg);

            Toast toast = new Toast(caller);
            //toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);

            toast.setView(rootLayout);
            toast.show();
        } catch (Exception ex) {// to avoid stupid app crashes
            Log.w(TAG, ex.toString());
        }
    }
}
