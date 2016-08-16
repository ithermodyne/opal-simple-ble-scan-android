package com.teravision.simple.activities;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.teravision.simple.R;
import com.teravision.simple.activities.home.MainActivity;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Javier Diaz on 01/08/2016.
 */
public class OpalApp extends MultiDexApplication implements Application.ActivityLifecycleCallbacks{

    /**
     * indicador descriptivo de la clase
     */
    private static final String TAG = OpalApp.class.getName();

    /**
     * indicador de actividad de la app
     */
    private static final AtomicBoolean isForeGround = new AtomicBoolean(false);
    /*
    App context
     */
    private static Context context;
    static NotificationManager manager;
    static Notification myNotication;


    @Override
    public void onCreate() {
        OpalApp.context = getApplicationContext();
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
        /**
         * FIXME JAVIER DIAZ
         * startService(new Intent(OpalApp.context, GetSignalsServices.class));
         */

    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.i(TAG, "Aplicacion Activa");
        OpalApp.setIsForeGround(Boolean.TRUE);


    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.i(TAG,"Aplicacion Inactiva");
        OpalApp.setIsForeGround(Boolean.FALSE);

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    /**
     * True si la app esta activa, False si no
     * @return
     */
    public static boolean isForeGround() {
        return isForeGround.get();
    }

    /**
     * Carga el indicador de app en reposo o app en activo
     * @param isForeGround
     */
    public static void setIsForeGround(boolean isForeGround) {
        OpalApp.isForeGround.set(isForeGround);
    }

    public static Context getAppContext() {
        return OpalApp.context;
    }


    public static  void sendPushTest(){
        Intent intent = new Intent(OpalApp.getAppContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(OpalApp.getAppContext(), 1, intent, 0);

        Notification.Builder builder = new Notification.Builder(OpalApp.getAppContext());

        builder.setAutoCancel(true);
        builder.setTicker("OPAL subtext promotion description");
        builder.setContentTitle("OPAL Notification");
        builder.setContentText("You have a new OPAL message");
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setOngoing(true);
        builder.setSubText("This is subtext...");   //API level 16
        builder.setNumber(100);
        builder.build();

        myNotication = builder.getNotification();
        manager.notify(11, myNotication);
    }


}
