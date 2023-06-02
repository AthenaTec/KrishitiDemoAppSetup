package com.realappraiser.gharvalue;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.util.DisplayMetrics;

import androidx.multidex.MultiDex;

import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.realappraiser.gharvalue.utils.ConnectionReceiver;

/**
 * Created by kaptas on 15/12/17.
 */

@SuppressWarnings("ALL")
public class MyApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    public static final String TAG = MyApplication.class.getSimpleName();
    @SuppressLint("StaticFieldLeak")
    public static MyApplication mInstance;


    public void onCreate() {
        if (false && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
        }
        super.onCreate();
       /*Fabric.with(this, new Crashlytics());*/
        MultiDex.install(this);
//        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(false);

        FirebaseApp.initializeApp(getApplicationContext());
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);

        context = this;
        mInstance = this;
    }
    public static MyApplication getApplication() {
        return (MyApplication) context;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    // Method to return DisplayMetrics
    public DisplayMetrics getDisplayMatrix() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return dm;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }


    /*******
     * Network Listener for Entire application - now other details only called
     * ********/
    public void setConnectionListener(ConnectionReceiver.ConnectionReceiverListener listener) {
        ConnectionReceiver.connectionReceiverListener = listener;
    }
}
