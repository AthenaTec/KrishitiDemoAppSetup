package com.realappraiser.gharvalue.worker;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.realappraiser.gharvalue.MyApplication;
import com.realappraiser.gharvalue.utils.SettingsUtils;

public class OreoLocation {
    private static final String TAG = "OreoLocation";
    private Context context;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;

    public OreoLocation(Context context) {
        this.context = context;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    public void startLocationUpdate() {
        createOreoLocationRequest();
        new Handler().postDelayed(() -> {
            try {
                Log.e(TAG, "Starting location updates");
                Utils.setRequestingLocationUpdates(MyApplication.getAppContext(), true);
               mFusedLocationClient.requestLocationUpdates(mLocationRequest, getPendingIntent());
            } catch (SecurityException e) {
                Utils.setRequestingLocationUpdates(MyApplication.getAppContext(), false);
                e.printStackTrace();
            }
        }, 5000);
    }


    private void createOreoLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(SettingsUtils.UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(30000);
        mLocationRequest.setPriority(100);
        mLocationRequest.setMaxWaitTime(300000);
    }

    public void stopOreoLocationUpdates() {
        Log.i(TAG, "Removing location updates");
        Utils.setRequestingLocationUpdates(context, false);
        mFusedLocationClient.removeLocationUpdates(getPendingIntent());
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(context, LocationUpdatesBroadcastReceiver.class);
        intent.setAction(LocationUpdatesBroadcastReceiver.ACTION_PROCESS_UPDATES);
        return PendingIntent.getBroadcast(context, 0, intent,  PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
