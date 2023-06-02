package com.realappraiser.gharvalue.worker;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.realappraiser.gharvalue.MyApplication;
import com.realappraiser.gharvalue.fragments.PhotoLatLong;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;

public class GeoUpdate extends Service implements LocationListener {
    private static final String TAG = "GeoUpdate";
    public LocationCallback locationCallback;
    private LocationRequest locationRequest;
    private FusedLocationProviderClient mFusedLocationClient;
    private double wayLatitude = 0.0d;
    private double wayLongitude = 0.0d;

    public void initiateLocationUpdate() {
        this.locationCallback = new LocationCallback() {
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    General.customToast("Cannot get your Location!", MyApplication.getAppContext());
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        String sb = "onLocationResult: " +
                                location.getLatitude() +
                                "\n" +
                                location.getLongitude();
                        PhotoLatLong.latsValue = String.valueOf(location.getLatitude());
                        PhotoLatLong.longsValue = String.valueOf(location.getLongitude());
                        Log.e(GeoUpdate.TAG, sb);
                        if (location.getLatitude() > 0.0d) {
                            SettingsUtils.Latitudes = location.getLatitude();
                            SettingsUtils.Longitudes = location.getLongitude();
                        }
                    }
                }
            }
        };
    }

    @SuppressLint({"MissingPermission"})
    public void startLocationUpdate() {
        this.mFusedLocationClient.requestLocationUpdates(this.locationRequest, this.locationCallback, null);
    }

    public void stopLocationUpdate() {
        this.mFusedLocationClient.removeLocationUpdates(this.locationCallback);
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        SettingsUtils.init(MyApplication.getAppContext());
        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(MyApplication.getAppContext());
        this.locationRequest = LocationRequest.create();
        this.locationRequest.setPriority(100);
        this.locationRequest.setInterval(5000);
        this.locationRequest.setFastestInterval(2000);
        initiateLocationUpdate();
        startLocationUpdate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        stopLocationUpdate();
    }

    public void onLocationChanged(Location location) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onProviderDisabled(String provider) {
    }
}
