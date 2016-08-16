package com.teravision.simple.activities.home;


import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.teravision.simple.R;
import com.teravision.simple.appfragments.HomeFragment;
import com.teravision.simple.appfragments.NotificationFragment;
import com.teravision.simple.appfragments.ScanBarcodeFragment;

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    private static TextView txtlog;
    public static  int i =0;
    private ImageView imageViewActionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtlog = (TextView) findViewById(R.id.txt_log_ble);


        // Set up your ActionBar
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_actionbar);
        imageViewActionBar = (ImageView) findViewById(R.id.opal_logo_action_bar);
        //imageViewActionBar.setVisibility(View.INVISIBLE);

        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this,getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("ScanBarcode").setIndicator("",getResources().getDrawable(R.drawable.tab_selector_scan)),
                ScanBarcodeFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Home").setIndicator("",getResources().getDrawable(R.drawable.tab_selector_home)),
                HomeFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Notification").setIndicator("",getResources().getDrawable(R.drawable.tab_selector_notification)),
                NotificationFragment.class, null);
        mTabHost.setCurrentTab(1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        String[] objects = getResources().getStringArray(R.array.mydrawables);
//
//
//        getMenuInflater().inflate(R.menu.dropdown_main, menu);
//        MenuItem item = menu.findItem(R.id.spinner);
//        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
//        spinner.setBackgroundResource(R.drawable.ic_home);
//        spinner.setVisibility(View.GONE);
//        spinner.setAdapter(new SpinnerActionBarAdapter(this,R.layout.row_spinner,android.R.id.text1,objects)); // set the adapter to provide layout of rows and content
        //spinner.setOnItemSelectedListener(onItemSelectedListener); // set the listener, to perform actions based on item selection
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    public static void setlogs(String log, int sn, Double capacitor, Double battery){
        if(log != null && txtlog != null){

                txtlog.append(++i+"-  Reading BLE / serial: "+sn+", UUID: "+log+", Capacitor: "+capacitor+", Battery: "+battery+ "\n");
        }
    }


}
