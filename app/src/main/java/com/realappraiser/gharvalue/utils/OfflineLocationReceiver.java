package com.realappraiser.gharvalue.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;



import java.util.Objects;

public class OfflineLocationReceiver {
    private static final String TAG = "OfflineLocationReceiver";
    private Context context;
    private OfflineLocationInterface locationInterface;
    private LocationManager locationManager;


    @SuppressLint({"MissingPermission"})
    public OfflineLocationReceiver(Context context2, final OfflineLocationInterface locationInterface2) {
        this.context = context2;
        this.locationInterface = locationInterface2;
        this.locationManager = (LocationManager) context2.getSystemService(Context.LOCATION_SERVICE);
        if (General.GPS_status()) {
            ((LocationManager) Objects.requireNonNull(this.locationManager)).requestLocationUpdates("gps", 2000, 10.0f, new LocationListener() {
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    String msg = "New Latitude: " +
                            latitude +
                            " New Longitude: " +
                            longitude;
                    String sb2 = "onLocationChanged: " +
                            msg;
                    Log.e(OfflineLocationReceiver.TAG, sb2);
                    if (latitude < 0.0d) {
                        locationInterface2.sendLocationUpdate(0.0d, 0.0d);
                        return;
                    }
                    SettingsUtils.Latitudes = latitude;
                    SettingsUtils.Longitudes = longitude;
                    locationInterface2.sendLocationUpdate(latitude, longitude);
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {
                }
            });
            return;
        }
        Log.e(TAG, "OfflineLocationReceiver: Gps Disabled");
        isLocationEnabled(context2);
    }

    public static boolean isLocationEnabled(final Context context2) {
        LocationManager locationManager2 = (LocationManager) context2.getSystemService(Context.LOCATION_SERVICE);
        String str = "gps";
        if (!Objects.requireNonNull(locationManager2).isProviderEnabled(str)) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context2);
            alertDialog.setTitle((CharSequence) "Enable Location");
            alertDialog.setMessage((CharSequence) "Your locations setting is not enabled. Please enabled it in settings menu.");
            alertDialog.setPositiveButton((CharSequence) "Location Settings", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    context2.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
                }
            });
            alertDialog.create().show();
        }
        locationManager2.isProviderEnabled(str);
        return locationManager2.isProviderEnabled(str);
    }
}
