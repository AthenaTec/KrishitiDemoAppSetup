package com.realappraiser.gharvalue.worker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import com.google.android.gms.location.LocationResult;
import com.realappraiser.gharvalue.MyApplication;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import java.util.List;

public class LocationUpdatesBroadcastReceiver extends BroadcastReceiver {
    public static final String ACTION_PROCESS_UPDATES =
            "com.realappraiser.gharvalue.worker.locationupdatespendingintent.action.PROCESS_UPDATES";
    private static final String TAG = "LUBroadcastReceiver";

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (ACTION_PROCESS_UPDATES.equals(intent.getAction())) {
                LocationResult result = LocationResult.extractResult(intent);
                if (result != null) {
                    List<Location> locations = result.getLocations();
                    SettingsUtils.init(MyApplication.getAppContext());
                    SettingsUtils.Latitudes = result.getLocations().get(0).getLatitude();
                    SettingsUtils.Longitudes = result.getLocations().get(0).getLongitude();
                    Utils.setLocationUpdatesResult(context, locations);
                    Utils.sendNotification(context, Utils.getLocationResultTitle(context, locations));
                    Log.i(TAG, Utils.getLocationUpdatesResult(context));
                }
            }
        }
    }
}
